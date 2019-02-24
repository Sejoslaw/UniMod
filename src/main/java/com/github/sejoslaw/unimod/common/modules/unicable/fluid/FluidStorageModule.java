package com.github.sejoslaw.unimod.common.modules.unicable.fluid;

import java.util.Map;
import java.util.Stack;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.api.tileentities.unicable.fluid.IUniCableFluidStorage;
import com.github.sejoslaw.unimod.api.tileentities.unicable.fluid.UniCableFluidAPI;
import com.github.sejoslaw.unimod.common.tileentities.unicable.UniCableFluidStorage;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.Direction;

/**
 * Module which is responsible for creating UniCableFluidStorages for each of
 * the side.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class FluidStorageModule extends FluidModuleBase {
	public void initialize(IUniCable cable) {
		for (Direction side : Direction.values()) {
			String key = UniCableFluidAPI.getFluidStorageKey(side);
			IUniCableFluidStorage fluidStorage = new UniCableFluidStorage(cable, side);
			cable.getData().put(key, fluidStorage);
		}
	}

	public void filterMessages(IUniCableSide side, Stack<String> messages) {
		IUniCableFluidStorage fluidStorage = UniCableFluidAPI.getFluidStorage(side);

		if (fluidStorage.getStoredFluids().isEmpty()) {
			return;
		}

		for (Map.Entry<Fluid, Long> fluidEntry : fluidStorage.getStoredFluids().entrySet()) {
			String fluidName = this.getFluidFullName(fluidEntry.getKey());
			messages.push(" -- " + fluidName + " = " + fluidEntry.getValue());
		}

		messages.push("Stored Fluids: (MAX: 9 Quintillion Buckets Each)"); // Long.MAX_VALUE
	}
}
