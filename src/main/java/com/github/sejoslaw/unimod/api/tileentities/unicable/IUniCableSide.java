package com.github.sejoslaw.unimod.api.tileentities.unicable;

import net.minecraft.util.math.Direction;

/**
 * Provides data for one of the UniCable sides.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public interface IUniCableSide {
	/**
	 * @return Returns the UniCable of which the current side is.
	 */
	IUniCable getCable();

	/**
	 * @return Returns the current side of the UniCable.
	 */
	Direction getSide();

	/**
	 * Used to toggle next mode of the current cable.
	 */
	void toggleNextMode();

	/**
	 * @return Indicates if the current cable can connect to anything.
	 */
	boolean isConnected();
}
