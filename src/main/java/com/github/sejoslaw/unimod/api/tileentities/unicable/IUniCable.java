package com.github.sejoslaw.unimod.api.tileentities.unicable;

import java.util.Collection;
import java.util.Map;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * UniCable is a modular version of transportation system. It can transport
 * anything what is availabe by the modules provided into it.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public interface IUniCable {
	/**
	 * @return Returns the World on which the UniCable currently is.
	 */
	World getWorld();

	/**
	 * @return Returns the position of the UniCable.
	 */
	BlockPos getPos();

	/**
	 * @return Returns the current state of the UniCable.
	 */
	BlockState getBlockState();

	/**
	 * Updates the current state of the UniCable.
	 */
	void setBlockState(BlockState state);

	/**
	 * @return Returns all the data about current UniCable.
	 */
	Map<String, Object> getData();

	/**
	 * @return Returns all messages which should be added into Player's chat.
	 */
	Collection<String> getMessages();

	/**
	 * @return Returns the Redstone Power of the current cable.
	 */
	int getWeakRedstonePower();

	/**
	 * @return Returns the UniCable data for the specified side.
	 */
	IUniCableSide getCableSide(Direction side);
}
