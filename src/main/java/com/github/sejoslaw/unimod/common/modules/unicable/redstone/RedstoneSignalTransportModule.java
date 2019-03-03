package com.github.sejoslaw.unimod.common.modules.unicable.redstone;

import java.util.ArrayList;
import java.util.Collection;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.api.tileentities.unicable.redstone.UniCableRedstoneAPI;
import com.github.sejoslaw.unimod.common.tileentities.unicable.UniCableSide;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class RedstoneSignalTransportModule implements IUniCableModule {
	public void onBlockPlaced(IUniCable cable, World world, BlockPos pos, BlockState state) {
		for (Direction direction : Direction.values()) {
			if (world.isEmittingRedstonePower(pos.offset(direction), direction.getOpposite())) {
				UniCableSide.setSide(cable.getCableSide(direction), true);
			}
		}
	}

	public void initialize(IUniCable cable) {
		UniCableRedstoneAPI.setWeakRedstonePower(cable, 0);
	}

	public void input(IUniCableSide cableSide) {
		IUniCable cable = cableSide.getCable();
		Direction direction = cableSide.getSide();
		World world = cable.getWorld();
		BlockPos cablePos = cable.getPos();

		int receivedPower = 0;

		if (cable.getCableSide(direction).isConnected()) {
			receivedPower = world.getEmittedRedstonePower(cablePos.offset(direction), direction.getOpposite());
		}

		UniCableRedstoneAPI.setWeakRedstonePower(cable, receivedPower);
	}

	public int getWeakRedstonePower(IUniCableSide cableSide) {
		IUniCable cable = cableSide.getCable();
		Direction side = cableSide.getSide();

		if (!cable.getCableSide(side.getOpposite()).isConnected()) {
			return 0;
		}

		return UniCableRedstoneAPI.getWeakRedstonePower(cable);
	}

	public Collection<String> getMessages(IUniCableSide cableSide, ItemStack stack) {
		Collection<String> messages = new ArrayList<>();

		int power = UniCableRedstoneAPI.getWeakRedstonePower(cableSide.getCable());

		if (power > 0) {
			messages.add("Redstone Power: " + power);
		}

		return messages;
	}
}
