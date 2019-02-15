package com.github.sejoslaw.unimod.common.utils;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniCableUtils {
	private UniCableUtils() {
	}

	public static IUniCable getCable(IWorld world, BlockPos pos) {
		return getCable(world.getBlockEntity(pos));
	}

	public static IUniCable getCable(BlockView view, BlockPos pos) {
		return getCable(view.getBlockEntity(pos));
	}

	public static IUniCable getCable(BlockEntity tileEntity) {
		if (!(tileEntity instanceof IUniCable)) {
			return null;
		}

		IUniCable cable = ((IUniCable) tileEntity);
		return cable;
	}
}
