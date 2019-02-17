package com.github.sejoslaw.unimod.common.modules.unicable.fluid;

import java.util.Stack;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.common.UniModProperties;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * Module which is responsible for connecting with fluids.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class FluidConnectionModule extends FluidModuleBase {
	public void onBlockPlaced(IUniCable cable, World world, BlockPos pos, BlockState state) {
		for (Direction direction : Direction.values()) {
			BlockPos fluidPos = pos.offset(direction);
			FluidState fluidState = world.getFluidState(fluidPos);
			Fluid fluid = fluidState.getFluid();

			if (fluid == Fluids.EMPTY || !fluidState.isStill()) {
				continue;
			}

			UniModProperties.setDirectionState(cable, direction, true);
		}
	}

	public boolean canConnect(IUniCable cable, Direction direction) {
		FluidState fluidState = this.getFluidState(cable, direction);
		return fluidState.isStill() && fluidState.getFluid() != Fluids.EMPTY;
	}

	public void filterMessages(IUniCable cable, Direction side, Stack<String> messages) {
		FluidState fluidState = this.getFluidState(cable, side);

		if (!this.canConnect(cable, side)) {
			return;
		}

		messages.push(" - " + side.getName().toUpperCase() + ": " + this.getFluidFullName(fluidState.getFluid()));
	}

	protected String getMessagesTopic() {
		return "Found Fluids:";
	}
}
