package com.github.sejoslaw.unimod.common.modules.unicable.cable;

import java.util.ArrayList;
import java.util.Collection;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.modules.unicable.UniCableCoreModuleNames;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.modules.unicable.core.UniCableSettingsModule;
import com.github.sejoslaw.unimod.common.tileentities.unicable.TileEntityUniCable;
import com.github.sejoslaw.unimod.common.tileentities.unicable.UniCableSide;
import com.github.sejoslaw.unimod.common.utils.UniCableUtils;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * Module which is responsible for connecting multiple UniCables as well as
 * getting informations about currently connected cable.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class CableConnectionModule implements IUniCableModule {
	public void onBlockPlaced(IUniCable cable, World world, BlockPos pos, BlockState state) {
		for (Direction direction : Direction.values()) {
			IUniCable neighbour = UniCableUtils.getCable(world, pos.offset(direction));

			if (neighbour == null) {
				continue;
			}

			IUniCableSide side = cable.getCableSide(direction);

			UniCableSide.setSide(side, true);
			UniCableSettingsModule.setBoth(side, UniCableCoreModuleNames.MODULE_GROUP_CABLE_KEY, true);
		}
	}

	public void initialize(IUniCable cable) {
		for (Direction side : Direction.values()) {
			String key = TileEntityUniCable.getDirectionKey(side);
			IUniCableSide cableSide = new UniCableSide(cable, side);
			cable.getData().put(key, cableSide);
		}
	}

	public Collection<String> getMessages(IUniCableSide cableSide, ItemStack stack) {
		Collection<String> messages = new ArrayList<>();

		if (UniCableSettingsModule.isConnected(cableSide, UniCableCoreModuleNames.MODULE_GROUP_CABLE_KEY)
				&& UniCableSide.getOpposite(cableSide) != null) {
			messages.add("Found connected cable.");
		}

		return messages;
	}
}
