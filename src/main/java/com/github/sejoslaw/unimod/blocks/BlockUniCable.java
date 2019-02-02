package com.github.sejoslaw.unimod.blocks;

import com.github.sejoslaw.unimod.core.UniModProperties;
import com.github.sejoslaw.unimod.enums.EnumTransferMode;
import com.github.sejoslaw.unimod.tileentities.TileEntityUniCable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateFactory;
import net.minecraft.util.BlockHitResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

/**
 * Transfer Node Block.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class BlockUniCable extends BlockWithEntity {
	protected static final VoxelShape AABB = Block.createCubeShape(6.0D, 6.0D, 6.0D, 10.0D, 10.0D, 10.0D);

	public BlockUniCable(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateFactory.getDefaultState().with(UniModProperties.MODE, EnumTransferMode.DEFAULT));
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
	 * Remove connections from connected nodes.
	 */
	public void onBroken(IWorld world, BlockPos pos, BlockState state) {
	}

	/**
	 * Right-Click => Change TransferNode mode.
	 */
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hitResult) {
		return true;
	}

	/**
	 * Add Mode property.
	 */
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.with(UniModProperties.MODE);
	}
}
