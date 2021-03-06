package com.github.sejoslaw.unimod.common.blocks;

import java.util.Collection;

import com.github.sejoslaw.unimod.api.items.IUniWrench;
import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModuleGroup;
import com.github.sejoslaw.unimod.api.registries.UniCableModuleRegistry;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.common.UniModLogger;
import com.github.sejoslaw.unimod.common.UniModProperties;
import com.github.sejoslaw.unimod.common.modloaders.UniModLoader;
import com.github.sejoslaw.unimod.common.tileentities.unicable.TileEntityUniCable;
import com.github.sejoslaw.unimod.common.utils.UniCableUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.text.Style;
import net.minecraft.text.TextFormat;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class BlockUniCable extends BlockWithEntity {
	public BlockUniCable(Settings settings) {
		super(settings);

		this.initializeDefaultState();
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos,
			VerticalEntityPosition verticalEntityPosition) {
		return Block.createCuboidShape(4.5D, 4.5D, 4.5D, 11.5D, 11.5D, 11.5D);
	}

	public BlockEntity createBlockEntity(BlockView view) {
		return new TileEntityUniCable();
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hitResult) {
		IUniCable cable = UniCableUtils.getCable(world, pos);

		if (cable == null) {
			return false;
		}

		Direction side = hitResult.getSide();
		ItemStack mainHandStack = player.getMainHandStack();

		if (mainHandStack.isEmpty()) {
			if (!player.world.isClient) {
				return false;
			}

			this.printMessages(cable, player, side, mainHandStack);
			return true;
		} else if (mainHandStack.getItem() instanceof IUniWrench) {
			UniModLoader.INSTANCE.openContainer(player, cable);
			return true;
		}

		return false;
	}

	public void onBroken(IWorld world, BlockPos pos, BlockState state) {
		for (Direction direction : Direction.values()) {
			IUniCable neighbourCable = UniCableUtils.getCable(world, pos.offset(direction));

			if (neighbourCable == null) {
				continue;
			}

			UniModProperties.setDirectionState(neighbourCable.getCableSide(direction.getOpposite()), false);
		}
	}

	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		IUniCable cable = UniCableUtils.getCable(world, pos);

		if (cable == null) {
			return;
		}

		for (IUniCableModuleGroup moduleGroup : UniCableModuleRegistry.UNI_CABLE_MODULES) {
			for (IUniCableModule module : moduleGroup.getModules()) {
				module.onBlockPlaced(cable, world, pos, state);
			}
		}
	}

	public boolean emitsRedstonePower(BlockState state) {
		return true;
	}

	public int getWeakRedstonePower(BlockState state, BlockView view, BlockPos pos, Direction direction) {
		IUniCable cable = UniCableUtils.getCable(view, pos);

		if (cable != null) {
			return ((IUniCable) cable).getWeakRedstonePower(direction);
		}

		return 0;
	}

	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		builder.with(UniModProperties.IS_CONNECTED_TOP.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_BOTTOM.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_NORTH.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_SOUTH.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_EAST.getProperty());
		builder.with(UniModProperties.IS_CONNECTED_WEST.getProperty());
	}

	private void initializeDefaultState() {
		BlockState state = this.getDefaultState();

		state = state.with(UniModProperties.IS_CONNECTED_BOTTOM.getProperty(), false);
		state = state.with(UniModProperties.IS_CONNECTED_EAST.getProperty(), false);
		state = state.with(UniModProperties.IS_CONNECTED_NORTH.getProperty(), false);
		state = state.with(UniModProperties.IS_CONNECTED_SOUTH.getProperty(), false);
		state = state.with(UniModProperties.IS_CONNECTED_TOP.getProperty(), false);
		state = state.with(UniModProperties.IS_CONNECTED_WEST.getProperty(), false);

		this.setDefaultState(state);
	}

	private void printMessages(IUniCable cable, PlayerEntity player, Direction side, ItemStack stack) {
		Collection<String> messages = cable.getMessages(side, stack);

		if (messages == null || messages.size() <= 0) {
			return;
		}

		String messagesHeader = "---=== UniCable Details (Side: " + side.getName().toUpperCase() + ") ===---";
		Style messageHeaderStyle = new Style().setColor(TextFormat.AQUA);
		messagesHeader = UniModLogger.styleText(messagesHeader, messageHeaderStyle).getFormattedText();

		player.addChatMessage(UniModLogger.info(messagesHeader), false);

		messages.forEach(message -> player.addChatMessage(UniModLogger.info(message), false));
	}
}
