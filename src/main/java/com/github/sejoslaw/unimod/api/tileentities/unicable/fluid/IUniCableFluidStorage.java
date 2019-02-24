package com.github.sejoslaw.unimod.api.tileentities.unicable.fluid;

import java.util.Map;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.Direction;

/**
 * Provides data for one of the UniCable fluid storage.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public interface IUniCableFluidStorage {
	/**
	 * @return Returns the UniCable with which the current storage is connected.
	 */
	IUniCable getCable();

	/**
	 * @return Returns the side of a UniCable on which this storage is positioned.
	 */
	Direction getSide();

	/**
	 * @return Returns the map of stored fluids, where the key is a stored fluid and
	 *         value is a number of buckets currently stored. <br>
	 * 
	 *         E.g. LAVA - 3.0 buckets
	 */
	Map<Fluid, Long> getStoredFluids();

	/**
	 * Adds the number of the specified fluid into the current fluid storage.
	 */
	void addFluid(Fluid fluid, long buckets);
}
