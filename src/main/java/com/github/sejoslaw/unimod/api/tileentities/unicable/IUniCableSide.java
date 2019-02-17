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
	 * Used to toggle next mode of the current cable. For toggling all the groups
	 * pass empty string ("") as an argument.
	 */
	void toggleNextMode(String moduleGroupName);

	/**
	 * @return Indicates if the current cable can connect to anything. For checking
	 *         all the modules, pass empty string ("") as an argument.
	 */
	boolean isConnected(String moduleGroupName);
}
