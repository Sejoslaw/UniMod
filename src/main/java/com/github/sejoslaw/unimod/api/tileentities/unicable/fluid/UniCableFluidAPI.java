package com.github.sejoslaw.unimod.api.tileentities.unicable.fluid;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;

import net.minecraft.util.math.Direction;

/**
 * UniCable Fluid API </br>
 * Contains general functions which were made for developers to help them use
 * Fluids with UniCable. </br>
 * By default UniMod contains modules which will automatically create and add
 * FluidStorages.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniCableFluidAPI {
	/**
	 * Key prefix used for retrieving UniCable FluidStorage.
	 */
	public static final String UNI_CABLE_FLUID_STORAGE_KEY = "UniMod_FluidStorage_";

	private UniCableFluidAPI() {
	}

	/**
	 * @return Returns the FluidStorage corresponded to the given side.
	 */
	public static IUniCableFluidStorage getFluidStorage(IUniCableSide side) {
		IUniCable cable = side.getCable();
		String key = getFluidStorageKey(side.getSide());

		if (cable.getData().containsKey(key)) {
			return (IUniCableFluidStorage) cable.getData().get(key);
		}

		return null;
	}

	/**
	 * @return Returns key used to get FluidStorage from UniCable.
	 */
	public static String getFluidStorageKey(Direction side) {
		return UNI_CABLE_FLUID_STORAGE_KEY + side.getId();
	}
}
