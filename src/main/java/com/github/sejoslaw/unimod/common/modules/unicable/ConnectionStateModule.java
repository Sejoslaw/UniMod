package com.github.sejoslaw.unimod.common.modules.unicable;

import java.util.ArrayList;
import java.util.Collection;

import com.github.sejoslaw.unimod.api.modules.IUniCableModule;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.UniModProperties;
import com.github.sejoslaw.unimod.common.tileentities.unicable.TileEntityUniCable;
import com.github.sejoslaw.unimod.common.tileentities.unicable.UniCableSide;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

/**
 * Module which is responsible for getting informations about connection state
 * for each side of the UniCable.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class ConnectionStateModule implements IUniCableModule {
	public void initialize(IUniCable cable) {
		for (Direction side : Direction.values()) {
			String key = TileEntityUniCable.getDirectionKey(side);
			IUniCableSide cableSide = new UniCableSide(cable, side);
			cable.getData().put(key, cableSide);
		}
	}

	public Collection<String> getMessages(IUniCable cable) {
		Collection<String> messages = new ArrayList<>();

		messages.add("Connection Side Data (Is Connected): ");
		for (Direction side : Direction.values()) {
			String sideName = side.getName().toUpperCase();
			boolean connected = UniModProperties.isConnected(cable, side);

			messages.add(" - " + sideName + ": " + connected);
		}

		return messages;
	}

	public void readFromNBT(IUniCable cable, CompoundTag tag) {
		for (Direction side : Direction.values()) {
			String key = TileEntityUniCable.getDirectionKey(side);
			boolean isConnected = tag.getBoolean(key);
			UniModProperties.setDirectionState(cable, side, isConnected);
		}
	}

	public void writeToNBT(IUniCable cable, CompoundTag tag) {
		for (Direction side : Direction.values()) {
			String key = TileEntityUniCable.getDirectionKey(side);
			boolean isCableConnected = UniModProperties.isConnected(cable, side);
			tag.putBoolean(key, isCableConnected);
		}
	}
}
