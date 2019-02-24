package com.github.sejoslaw.unimod.common.modules.unicable.fluid;

import java.util.Stack;

import com.github.sejoslaw.unimod.api.modules.unicable.UniCableCoreModuleNames;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.api.tileentities.unicable.fluid.UniCableFluidAPI;
import com.github.sejoslaw.unimod.common.modules.unicable.core.UniCableSettingsModule;
import com.github.sejoslaw.unimod.common.tileentities.unicable.UniCableSide;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * Module which is responsible for inputting fluid into UniCable.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class FluidInputModule extends FluidModuleBase {
	public void onBlockPlaced(IUniCable cable, World world, BlockPos pos, BlockState state) {
		for (Direction direction : Direction.values()) {
			BlockPos fluidPos = pos.offset(direction);
			FluidState fluidState = world.getFluidState(fluidPos);
			Fluid fluid = fluidState.getFluid();

			if (fluid == Fluids.EMPTY || !fluidState.isStill()) {
				continue;
			}

			UniCableSide.setSide(cable.getCableSide(direction), true);
		}
	}

	public void input(IUniCableSide side) {
		IUniCable cable = side.getCable();
		Direction direction = side.getSide();

		if (!cable.getCableSide(direction).isConnected()) {
			return;
		}

		FluidState fluidState = this.getFluidState(side);

		if (!fluidState.isStill()) {
			return;
		}

		Fluid fluid = fluidState.getFluid();
		BlockPos fluidPos = cable.getPos().offset(direction);

		cable.getWorld().setBlockState(fluidPos, Fluids.EMPTY.getDefaultState().getBlockState());
		cable.getWorld().updateNeighbors(fluidPos, fluid.getDefaultState().getBlockState().getBlock());

		UniCableFluidAPI.getFluidStorage(side).addFluid(fluid, 1);
	}

	public void filterMessages(IUniCableSide side, Stack<String> messages) {
		FluidState fluidState = this.getFluidState(side);

		if (!UniCableSettingsModule.canInput(side, UniCableCoreModuleNames.MODULE_GROUP_FLUIDS_KEY)) {
			return;
		}

		messages.push("Found Fluid: " + this.getFluidFullName(fluidState.getFluid()));
	}
}
