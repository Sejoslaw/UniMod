package com.github.sejoslaw.unimod.common.modules.unicable.fluid;

import java.util.Collection;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;

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
public class FluidInputModule extends FluidConnectionModule {
	public void onBlockPlaced(IUniCable cable, World world, BlockPos pos, BlockState state) {
	}

	public Collection<String> getMessages(IUniCable cable) {
		return null;
	}

	public void transmit(IUniCable cable, Direction direction) {
		if (!cable.getCableSide(direction).isConnected()) {
			return;
		}

		FluidState fluidState = this.getFluidState(cable, direction);

		if (!fluidState.isStill()) {
			return;
		}

		Fluid fluid = fluidState.getFluid();
		BlockPos fluidPos = cable.getPos().offset(direction);

		cable.getWorld().setBlockState(fluidPos, Fluids.EMPTY.getDefaultState().getBlockState());
		cable.getWorld().updateNeighbors(fluidPos, fluid.getDefaultState().getBlockState().getBlock());

		cable.getFluidStorage(direction).addFluid(fluid, 1);
	}
}
