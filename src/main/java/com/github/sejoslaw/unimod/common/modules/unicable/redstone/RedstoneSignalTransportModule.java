package com.github.sejoslaw.unimod.common.modules.unicable.redstone;

import java.util.ArrayList;
import java.util.Collection;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableTogglableModule;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.common.UniModProperties;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class RedstoneSignalTransportModule implements IUniCableTogglableModule {
	private static final String REDSTONE_POWER_DATA_KEY = "UniMod_RedstonePower";

	public void onBlockPlaced(IUniCable cable, World world, BlockPos pos, BlockState state) {
		for (Direction direction : Direction.values()) {
			if (world.isEmittingRedstonePower(pos.offset(direction), direction.getOpposite())) {
				UniModProperties.setDirectionState(cable, direction, true);
			}
		}
	}

	public void initialize(IUniCable cable) {
		cable.getData().put(REDSTONE_POWER_DATA_KEY, 0);
	}

	public boolean canConnect(IUniCable cable, Direction direction) {
		this.initialize(cable);

		if (!cable.getCableSide(direction).isConnected(IUniCableTogglableModule.MODULE_GROUP_REDSTONE_KEY)) {
			return false;
		}

		return cable.getWorld().isEmittingRedstonePower(cable.getPos().offset(direction), direction.getOpposite());
	}

	public void transmit(IUniCable cable, Direction direction) {
		World world = cable.getWorld();
		BlockPos cablePos = cable.getPos();

		int receivedPower = 0;

		if (cable.getCableSide(direction).isConnected(IUniCableTogglableModule.MODULE_GROUP_REDSTONE_KEY)) {
			receivedPower = world.getEmittedRedstonePower(cablePos.offset(direction), direction.getOpposite());
		}

		cable.getData().put(REDSTONE_POWER_DATA_KEY, receivedPower);

		cable.setBlockState(cable.getBlockState());
	}

	public int getWeakRedstonePower(IUniCable cable, Direction side) {
		if (!cable.getCableSide(side.getOpposite()).isConnected(IUniCableTogglableModule.MODULE_GROUP_REDSTONE_KEY)) {
			return 0;
		}

		if (cable.getData().containsKey(REDSTONE_POWER_DATA_KEY)) {
			return (int) cable.getData().get(REDSTONE_POWER_DATA_KEY);
		}

		return 0;
	}

	public Collection<String> getMessages(IUniCable cable, Direction side, ItemStack stack) {
		Collection<String> messages = new ArrayList<>();

		this.addMessage(cable, messages, this.getRedstonePowerMessage(cable));
		this.addMessage(cable, messages, this.getRedstoneSourcesMessage(cable, side));

		return messages;
	}

	private void addMessage(IUniCable cable, Collection<String> messages, String message) {
		if (message != null) {
			messages.add(message);
		}
	}

	private String getRedstonePowerMessage(IUniCable cable) {
		int power = (int) cable.getData().get(REDSTONE_POWER_DATA_KEY);

		if (power > 0) {
			return "Redstone Power: " + power;
		}

		return null;
	}

	private String getRedstoneSourcesMessage(IUniCable cable, Direction side) {
		return this.canConnect(cable, side) ? "Found Redstone source." : null;
	}
}
