package com.github.sejoslaw.unimod.common.blocks;

import com.github.sejoslaw.unimod.common.UniModProperties;
import com.github.sejoslaw.unimod.common.tileentities.TileEntityUniCable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateFactory;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class BlockUniCable extends BlockWithEntity {
	protected static final VoxelShape AABB = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public BlockUniCable(Settings settings) {
		super(settings);
		this.setDefaultState();
	}

	public void setDefaultState() {
		BlockState defaultState = this.stateFactory.getDefaultState();

		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_TOP, false);
		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_BOTTOM, false);
		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_NORTH, false);
		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_SOUTH, false);
		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_EAST, false);
		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_WEST, false);

		this.setDefaultState(defaultState);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos,
			VerticalEntityPosition verticalEntityPosition) {
		return AABB;
	}

	public BlockEntity createBlockEntity(BlockView view) {
		return new TileEntityUniCable();
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	/**
	 * Update adjacent blocks.
	 */
	public void onBroken(IWorld world, BlockPos pos, BlockState state) {
	}

	/**
	 * Right-Click + UniWrench => Write to chat current unicable mode.
	 * Shift-Right-Click + UniWrench => Toggle mode if possible (Output - Pull /
	 * Input - Insert / Transport).
	 */
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hitResult) {
		return true;
	}

	/**
	 * Add properties.
	 */
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);

		builder.with(UniModProperties.IS_CONNECTED_TOP);
		builder.with(UniModProperties.IS_CONNECTED_BOTTOM);
		builder.with(UniModProperties.IS_CONNECTED_NORTH);
		builder.with(UniModProperties.IS_CONNECTED_SOUTH);
		builder.with(UniModProperties.IS_CONNECTED_EAST);
		builder.with(UniModProperties.IS_CONNECTED_WEST);
	}
}
