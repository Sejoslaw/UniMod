package com.github.sejoslaw.unimod.common.blocks;

import java.util.Collection;

import com.github.sejoslaw.unimod.api.items.IUniWrench;
import com.github.sejoslaw.unimod.api.tileentities.IUniCable;
import com.github.sejoslaw.unimod.common.UniModLogger;
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
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class BlockUniCable extends BlockWithEntity {
	public BlockUniCable(Settings settings) {
		super(settings);
		this.setDefaultState();
	}

	public void setDefaultState() {
		BlockState defaultState = this.stateFactory.getDefaultState();

		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_TOP.getProperty(), false);
		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_BOTTOM.getProperty(), false);
		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_NORTH.getProperty(), false);
		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_SOUTH.getProperty(), false);
		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_EAST.getProperty(), false);
		defaultState = defaultState.with(UniModProperties.IS_CONNECTED_WEST.getProperty(), false);

		this.setDefaultState(defaultState);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos,
			VerticalEntityPosition verticalEntityPosition) {
		// TODO: Update based on current model and connected sites.
		return Block.createCuboidShape(4.5D, 4.5D, 4.5D, 11.5D, 11.5D, 11.5D);
	}

	public BlockEntity createBlockEntity(BlockView view) {
		return new TileEntityUniCable();
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	/**
	 * How to use UniCable: <br>
	 * - Right-Click with UniWrench to write in the chat current UniCable mode. <br>
	 * - Shift-Right-Click with UniWrench to toggle different mode.
	 */
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hitResult) {
		if (!(player.getMainHandStack().getItem() instanceof IUniWrench)) {
			return false;
		}

		BlockEntity tileEntity = world.getBlockEntity(pos);
		if (!(tileEntity instanceof IUniCable)) {
			return false;
		}

		IUniCable cable = ((IUniCable) tileEntity);

		boolean sneaking = player.isSneaking();
		System.out.println(sneaking);

		if (player.isSneaking()) {
			cable.toggleNextMode();
			return true;
		} else {
			if (!player.world.isClient) {
				return false;
			}

			Collection<String> messages = cable.getMessages();

			if (messages != null) {
				player.addChatMessage(UniModLogger.info("UniCable Details:"), false);
				messages.forEach(message -> player.addChatMessage(UniModLogger.info(message), false));
			}

			return true;
		}
	}

	/**
	 * Add properties.
	 */
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);

		builder.with(UniModProperties.IS_CONNECTED_TOP.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_BOTTOM.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_NORTH.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_SOUTH.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_EAST.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_WEST.getProperty());
	}
}
