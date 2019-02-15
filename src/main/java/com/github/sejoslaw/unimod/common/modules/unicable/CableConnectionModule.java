package com.github.sejoslaw.unimod.common.modules.unicable;

import java.util.ArrayList;
import java.util.Collection;

import com.github.sejoslaw.unimod.api.modules.IUniCableModule;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.common.UniModProperties;
import com.github.sejoslaw.unimod.common.utils.UniCableUtils;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * Module which is responsible for connecting multiple UniCables.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class CableConnectionModule implements IUniCableModule {
	public void onBlockPlaced(World world, BlockPos pos, BlockState state) {
		IUniCable cable = UniCableUtils.getCable(world, pos);

		if (cable == null) {
			return;
		}

		for (Direction direction : Direction.values()) {
			IUniCable neighbour = UniCableUtils.getCable(world, pos.offset(direction));

			if (neighbour == null) {
				continue;
			}

			UniModProperties.setDirectionState(cable, direction, true);
			UniModProperties.setDirectionState(neighbour, direction.getOpposite(), true);
		}
	}

	public boolean canConnect(IUniCable cable, Direction direction) {
		return cable.getCableSide(direction).isConnected();
	}

	public Collection<String> getMessages(IUniCable cable) {
		boolean hasAny = false;
		String message = "Connected Cables: [ ";

		for (Direction direction : Direction.values()) {
			if (this.canConnect(cable, direction)) {
				message += direction.getName() + " ";
				hasAny = true;
			}
		}

		message += "]";

		Collection<String> messages = new ArrayList<>();

		if (hasAny) {
			messages.add(message);
		}

		return messages;
	}
}
