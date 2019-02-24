package com.github.sejoslaw.unimod.api.tileentities.unicable.redstone;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;

/**
 * UniCable Redstone API </br>
 * Contains general functions which were made for developers to help them use
 * Redstone with UniCable. </br>
 * By default UniMod contains modules which will automatically handle
 * interaction with Redstone.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniCableRedstoneAPI {
	/**
	 * Key prefix used for retrieving Redstone Power inside UniCable.
	 */
	public static final String REDSTONE_POWER_DATA_KEY = "UniMod_RedstonePower";

	private UniCableRedstoneAPI() {
	}

	/**
	 * Simple wrapper for setting Redstone Power in the given UniCable.
	 */
	public static void setWeakRedstonePower(IUniCable cable, int power) {
		cable.getData().put(REDSTONE_POWER_DATA_KEY, power);
	}

	/**
	 * @return Returns Redstone Power level inside of the given UniCable.
	 */
	public static int getWeakRedstonePower(IUniCable cable) {
		if (cable.getData().containsKey(REDSTONE_POWER_DATA_KEY)) {
			return (int) cable.getData().get(REDSTONE_POWER_DATA_KEY);
		}

		return 0;
	}
}
