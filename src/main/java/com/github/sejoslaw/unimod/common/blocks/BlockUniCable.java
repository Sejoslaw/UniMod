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
import net.minecraft.item.ItemStack;
import net.minecraft.item.block.AirBlockItem;
import net.minecraft.state.StateFactory;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class BlockUniCable extends BlockWithEntity {
	public BlockUniCable(Settings settings) {
		super(settings);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos,
			VerticalEntityPosition verticalEntityPosition) {
		// TODO: Update based on current model and connected sites. Add shapes for
		// connection ends.
		return Block.createCuboidShape(4.5D, 4.5D, 4.5D, 11.5D, 11.5D, 11.5D);
	}

	public BlockEntity createBlockEntity(BlockView view) {
		return new TileEntityUniCable();
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	// TODO: Fix after Minecraft can process Shift-Clicking.
	/**
	 * Everything with IUniWrench: - Right-Click -> show info - Shift-Right-Click ->
	 * toggle mode
	 */
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hitResult) {
		BlockEntity tileEntity = world.getBlockEntity(pos);

		if (!(tileEntity instanceof IUniCable)) {
			return false;
		}

		IUniCable cable = ((IUniCable) tileEntity);

		ItemStack mainHandStack = player.getMainHandStack();

		if (mainHandStack.getItem() instanceof AirBlockItem) {
			if (!player.world.isClient) {
				return false;
			}

			Collection<String> messages = cable.getMessages();

			if (messages != null) {
				player.addChatMessage(UniModLogger.info("UniCable Details:"), false);
				messages.forEach(message -> player.addChatMessage(UniModLogger.info(message), false));
			}

			return true;
		} else if (mainHandStack.getItem() instanceof IUniWrench) {
			cable.toggleNextMode();
			return true;
		}

		return false;
	}

	public boolean emitsRedstonePower(BlockState state) {
		return true;
	}

	public int getWeakRedstonePower(BlockState state, BlockView view, BlockPos pos, Direction direction) {
		BlockEntity cable = view.getBlockEntity(pos);

		if (cable instanceof IUniCable) {
			return ((IUniCable) cable).getWeakRedstonePower();
		}

		return 0;
	}

	/**
	 * Add properties.
	 */
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		builder.with(UniModProperties.IS_CONNECTED_TOP.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_BOTTOM.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_NORTH.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_SOUTH.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_EAST.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_WEST.getProperty());
	}
}
