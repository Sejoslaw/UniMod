package com.github.sejoslaw.unimod.common.modules.unicable;

import java.util.ArrayList;
import java.util.Collection;

import com.github.sejoslaw.unimod.api.modules.IUniCableModule;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class RedstoneSignalTransportModule implements IUniCableModule {
	private static final String REDSTONE_POWER_DATA_KEY = "UniMod_RedstonePower";

	public void initialize(IUniCable cable) {
		cable.getData().put(REDSTONE_POWER_DATA_KEY, 0);
	}

	public boolean canConnect(IUniCable cable, Direction direction) {
		if (!cable.getCableSide(direction).isConnected()) {
			cable.getData().put(REDSTONE_POWER_DATA_KEY, 0);
			return false;
		}

		World world = cable.getWorld();
		BlockPos cablePos = cable.getPos();
		BlockPos sourcePos = cablePos.offset(direction);

		if (world.getBlockState(sourcePos).emitsRedstonePower()) {
			return true;
		}

		BlockEntity sourceEntity = world.getBlockEntity(sourcePos);

		if (sourceEntity instanceof IUniCable) {
			return ((IUniCable) sourceEntity).getData().containsKey(REDSTONE_POWER_DATA_KEY);
		}

		return false;
	}

	public void transmit(IUniCable cable, Direction direction) {
		World world = cable.getWorld();
		BlockPos cablePos = cable.getPos();

		int receivedPower = world.getReceivedRedstonePower(cablePos);

		cable.getData().put(REDSTONE_POWER_DATA_KEY, receivedPower);

		BlockState cableState = world.getBlockState(cablePos);
		world.updateNeighbors(cablePos, cableState.getBlock());
	}

	public int getWeakRedstonePower(IUniCable cable) {
		// In case if someone removes the key from data map.
		if (cable.getData().containsKey(REDSTONE_POWER_DATA_KEY)) {
			return (int) cable.getData().get(REDSTONE_POWER_DATA_KEY);
		}

		return 0;
	}

	public Collection<String> getMessages(IUniCable cable) {
		Collection<String> messages = new ArrayList<>();

		this.addMessage(cable, messages, this.getRedstonePowerMessage(cable));
		this.addMessage(cable, messages, this.getRedstoneSourcesMessage(cable));

		return messages;
	}

	private void addMessage(IUniCable cable, Collection<String> messages, String message) {
		if (message != null) {
			messages.add(message);
		}
	}

	private String getRedstonePowerMessage(IUniCable cable) {
		int power = this.getWeakRedstonePower(cable);

		if (power > 0) {
			return "Redstone Power: " + power;
		}

		return null;
	}

	private String getRedstoneSourcesMessage(IUniCable cable) {
		boolean hasAny = false;
		String message = "Redstone Sources: [ ";

		for (Direction direction : Direction.values()) {
			if (this.canConnect(cable, direction)) {
				message += direction.getName() + " ";
				hasAny = true;
			}
		}

		message += "]";

		if (hasAny) {
			return message;
		}

		return null;
	}
}
