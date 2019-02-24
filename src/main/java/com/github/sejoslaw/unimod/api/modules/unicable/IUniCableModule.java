package com.github.sejoslaw.unimod.api.modules.unicable;

import java.util.Collection;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Describes a module which can be used as an addon to extend the functionality
 * of a UniCable.
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
	default Collection<String> getMessages(IUniCableSide cableSide, ItemStack stack) {
		return null;
	}

	/**
	 * Inputs X on specified side.
	 */
	default void input(IUniCableSide cableSide) {
	}

	/**
	 * Outputs X on specified side.
	 */
	default void output(IUniCableSide cableSide) {
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
	default int getWeakRedstonePower(IUniCableSide cableSide) {
		return 0;
	}

	/**
	 * Executes after the UniCable has been placed on World.
	 */
	default void onBlockPlaced(IUniCable cable, World world, BlockPos pos, BlockState state) {
	}
}
