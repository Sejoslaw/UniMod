package com.github.sejoslaw.unimod.common.tileentities.unicable;

import java.util.HashMap;
import java.util.Map;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableFluidStorage;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableFluidStorage implements IUniCableFluidStorage {
	private final IUniCable cable;
	private final Direction side;
	private final Map<Fluid, Long> fluids;

	public UniCableFluidStorage(IUniCable cable, Direction side) {
		this.cable = cable;
		this.side = side;
		this.fluids = new HashMap<>();
	}

	public IUniCable getCable() {
		return this.cable;
	}

	public Direction getSide() {
		return this.side;
	}

	public Map<Fluid, Long> getStoredFluids() {
		return fluids;
	}

	public void addFluid(Fluid fluid, long buckets) {
		if (this.fluids.containsKey(fluid)) {
			buckets += this.fluids.get(fluid);
		}

		this.fluids.put(fluid, buckets);
	}
}
