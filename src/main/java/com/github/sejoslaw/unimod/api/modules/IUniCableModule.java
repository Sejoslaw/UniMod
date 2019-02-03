package com.github.sejoslaw.unimod.api.modules;

import java.util.Collection;

import com.github.sejoslaw.unimod.api.tileentities.IUniCable;

import net.minecraft.nbt.CompoundTag;

/**
 * Describes a module which can be used as an addon to extend the functionality
 * of a UniCable. <br>
 * <br>
 * 
 * By default UniCable is build of 4 modules: <br>
 * - <b style="color:lightgreen">UniCableConnectionModule</b><br>
 * - <b style="color:green">ItemTransportModule</b><br>
 * - <b style="color:#4286f4">FluidTransportModule</b><br>
 * - <b style="color:red">RedstoneSignalTransportModule</b><br>
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public interface IUniCableModule {
	/**
	 * Initializes given UniCable with required data.
	 */
	void initialize(IUniCable cable);

	/**
	 * @return Returns all messages which should be added into Player's chat.
	 */
	Collection<String> getMessages(IUniCable cable);

	/**
	 * Reads data from given NBT tag.
	 */
	default void readFromNBT(IUniCable cable, CompoundTag tag) {
	}

	/**
	 * Writes data into given NBT tag.
	 */
	default void writeToNBT(IUniCable cable, CompoundTag tag) {
	}
}
