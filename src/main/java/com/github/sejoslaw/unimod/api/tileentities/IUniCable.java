package com.github.sejoslaw.unimod.api.tileentities;

import java.util.Collection;
import java.util.Map;

import com.github.sejoslaw.unimod.api.enums.EnumTransferMode;

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

	/**
	 * @return Data which is stored by the current UniCable, that can be retrieved
	 *         by the unique key.
	 */
	Map<String, Object> getData();

	/**
	 * @return Returns all messages which should be added into Player's chat.
	 */
	Collection<String> getMessages();

	/**
	 * @return Returns the current mode of the UniCable.
	 */
	EnumTransferMode getCurrentMode();

	/**
	 * Sets new mode for this UniCable.
	 */
	void setCurrentMode(EnumTransferMode mode);

	/**
	 * Used to toggle next mode of the current cable.
	 */
	void toggleNextMode();
}
