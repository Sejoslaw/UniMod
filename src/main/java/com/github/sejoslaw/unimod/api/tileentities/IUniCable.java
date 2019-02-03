package com.github.sejoslaw.unimod.api.tileentities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * UniCable is a modular version of transportation system. It can transport
 * anything what is availabe by the modules provided into it.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public interface IUniCable {
	/**
	 * @return World on which the UniCable currently is.
	 */
	World getWorld();

	/**
	 * @return Position of the UniCable.
	 */
	BlockPos getPos();
}
