package com.github.sejoslaw.unimod.block;

import com.github.sejoslaw.unimod.tileentity.TileEntityTransferNode;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

/**
 * Transfer Node Block.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class BlockTransferNode extends BlockWithEntity {
	protected static final VoxelShape AABB = Block.createCubeShape(6.0D, 6.0D, 6.0D, 10.0D, 10.0D, 10.0D);

	public BlockTransferNode(Settings settings) {
		super(settings);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos,
			VerticalEntityPosition verticalEntityPosition) {
		return AABB;
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.SOLID;
	}

	public BlockEntity createBlockEntity(BlockView view) {
		return new TileEntityTransferNode();
	}
}
