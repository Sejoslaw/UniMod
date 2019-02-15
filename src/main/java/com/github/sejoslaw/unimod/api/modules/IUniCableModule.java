package com.github.sejoslaw.unimod.api.modules;

import java.util.Collection;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.StateFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * Describes a module which can be used as an addon to extend the functionality
 * of a UniCable. <br>
 * <br>
 * 
 * By default UniCable is build of these modules: <br>
 * - <b style="color:yellow">ConnectionStateModule</b><br>
 * - <b style="color:lightgreen">CableConnectionModule</b><br>
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
	 * Transmits X into / from specified direction.
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
	default int getWeakRedstonePower(IUniCable cable, Direction side) {
		return 0;
	}

	/**
	 * Appends additional properties into UniCable block builder.
	 */
	default void appendCableProperties(StateFactory.Builder<Block, BlockState> builder) {
	}

	/**
	 * @return Returns the state with default properties.
	 */
	default BlockState setDefaultProperties(BlockState state) {
		return state;
	}

	/**
	 * Executes after the UniCable has been placed on World.
	 */
	default void onBlockPlaced(IUniCable cable, World world, BlockPos pos, BlockState state) {
	}
}
