package com.github.sejoslaw.unimod.common.modules.unicable;

import java.util.ArrayList;
import java.util.Collection;

import com.github.sejoslaw.unimod.api.modules.IUniCableModule;
import com.github.sejoslaw.unimod.api.tileentities.IUniCable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class ConnectionModule implements IUniCableModule {
	public boolean canConnect(IUniCable cable, Direction direction) {
		World world = cable.getWorld();
		BlockPos pos = cable.getPos().offset(direction);

		if (world.getBlockEntity(pos) instanceof IUniCable) {
			return true;
		}

		return false;
	}

	public Collection<String> getMessages(IUniCable cable) {
		boolean hasAny = false;
		String message = "UniCable: [ ";

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
