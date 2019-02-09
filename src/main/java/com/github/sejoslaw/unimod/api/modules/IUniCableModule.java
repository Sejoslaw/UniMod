package com.github.sejoslaw.unimod.api.modules;

import java.util.Collection;

import com.github.sejoslaw.unimod.api.tileentities.IUniCable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

/**
 * Describes a module which can be used as an addon to extend the functionality
 * of a UniCable. <br>
 * <br>
 * 
 * By default UniCable is build of these modules: <br>
 * - <b style="color:darkviolet">TransferModeModule</b><br>
 * - <b style="color:lightgreen">ConnectionModule</b><br>
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
	default void initialize(IUniCable cable) {
	}

	/**
	 * @return Returns all messages which should be added into Player's chat.
	 */
	default Collection<String> getMessages(IUniCable cable) {
		return null;
	}

	/**
	 * @return Returns true if the given cable connect to a block on a given
	 *         direction; otherwise false.
	 */
	default boolean canConnect(IUniCable cable, Direction direction) {
		return false;
	}

	/**
	 * Transmits X into specified direction.
	 */
	default void transmit(IUniCable cable, Direction direction) {
	}

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

	/**
	 * @return Returns the Redstone Power that the module can create.
	 */
	default int getWeakRedstonePower(IUniCable cable) {
		return 0;
	}
}
