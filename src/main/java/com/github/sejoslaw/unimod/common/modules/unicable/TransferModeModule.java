package com.github.sejoslaw.unimod.common.modules.unicable;

import java.util.ArrayList;
import java.util.Collection;

import com.github.sejoslaw.unimod.api.enums.EnumTransferMode;
import com.github.sejoslaw.unimod.api.modules.IUniCableModule;
import com.github.sejoslaw.unimod.api.tileentities.IUniCable;
import com.github.sejoslaw.unimod.common.tileentities.TileEntityUniCable;

import net.minecraft.nbt.CompoundTag;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class TransferModeModule implements IUniCableModule {
	public void initialize(IUniCable cable) {
		cable.setCurrentMode(EnumTransferMode.DISCONNECTED);
	}

	public Collection<String> getMessages(IUniCable cable) {
		Collection<String> messages = new ArrayList<>();
		String message = "Transfer Mode: [ " + cable.getCurrentMode().asString() + " ]";
		messages.add(message);
		return messages;
	}

	public void readFromNBT(IUniCable cable, CompoundTag tag) {
		int id = tag.getInt(TileEntityUniCable.TRANSFER_MODE_KEY);
		EnumTransferMode mode = EnumTransferMode.getById(id);
		cable.setCurrentMode(mode);
	}

	public void writeToNBT(IUniCable cable, CompoundTag tag) {
		int modeId = cable.getCurrentMode().getId();
		tag.putInt(TileEntityUniCable.TRANSFER_MODE_KEY, modeId);
	}
}
