package com.github.sejoslaw.unimod.common.blocks;

import java.util.Collection;

import com.github.sejoslaw.unimod.api.items.IUniWrench;
import com.github.sejoslaw.unimod.api.modules.IUniCableModule;
import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.UniModLogger;
import com.github.sejoslaw.unimod.common.UniModProperties;
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
import net.minecraft.item.block.AirBlockItem;
import net.minecraft.state.StateFactory;
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

	// TODO: Fix after Minecraft can process Shift-Clicking.
	/**
	 * Everything with IUniWrench: - Right-Click -> show info - Shift-Right-Click ->
	 * toggle mode
	 */
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hitResult) {
		IUniCable cable = UniCableUtils.getCable(world, pos);

		if (cable == null) {
			return false;
		}

		ItemStack mainHandStack = player.getMainHandStack();

		if (mainHandStack.getItem() instanceof AirBlockItem) {
			if (!player.world.isClient) {
				return false;
			}

			this.printMessages(cable, player);
			return true;
		} else if (mainHandStack.getItem() instanceof IUniWrench) {
			this.toggleCable(cable, world, hitResult.getSide());
			return true;
		}

		return false;
	}

	public void onBroken(IWorld world, BlockPos pos, BlockState state) {
		for (Direction direction : Direction.values()) {
			BlockPos neighbourPos = pos.offset(direction);
			direction = direction.getOpposite();

			IUniCable cable = UniCableUtils.getCable(world, neighbourPos);

			if (cable == null) {
				continue;
			}

			UniModProperties.setDirectionState(cable, direction, false);
		}
	}

	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		for (IUniCableModule module : ModuleRegistry.UNI_CABLE_MODULES) {
			module.onBlockPlaced(world, pos, state);
		}
	}

	public boolean emitsRedstonePower(BlockState state) {
		return true;
	}

	public int getWeakRedstonePower(BlockState state, BlockView view, BlockPos pos, Direction direction) {
		IUniCable cable = UniCableUtils.getCable(view, pos);

		if (cable != null) {
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

		ModuleRegistry.UNI_CABLE_MODULES.forEach(module -> module.appendCableProperties(builder));
	}

	private void initializeDefaultState() {
		BlockState state = this.getDefaultState();

		state = state.with(UniModProperties.IS_CONNECTED_BOTTOM.getProperty(), false);
		state = state.with(UniModProperties.IS_CONNECTED_EAST.getProperty(), false);
		state = state.with(UniModProperties.IS_CONNECTED_NORTH.getProperty(), false);
		state = state.with(UniModProperties.IS_CONNECTED_SOUTH.getProperty(), false);
		state = state.with(UniModProperties.IS_CONNECTED_TOP.getProperty(), false);
		state = state.with(UniModProperties.IS_CONNECTED_WEST.getProperty(), false);

		for (IUniCableModule module : ModuleRegistry.UNI_CABLE_MODULES) {
			state = module.setDefaultProperties(state);
		}

		this.setDefaultState(state);
	}

	private void printMessages(IUniCable cable, PlayerEntity player) {
		Collection<String> messages = cable.getMessages();

		if (messages != null) {
			player.addChatMessage(UniModLogger.info("UniCable Details:"), false);
			messages.forEach(message -> player.addChatMessage(UniModLogger.info(message), false));
		}
	}

	private void toggleCable(IUniCable cable, World world, Direction side) {
		IUniCableSide cableSide = cable.getCableSide(side);
		cableSide.toggleNextMode();

		IUniCable neighbour = UniCableUtils.getCable(world, cable.getPos().offset(side));

		if (neighbour == null) {
			return;
		}

		UniModProperties.setDirectionState(neighbour, side.getOpposite(), cableSide.isConnected());
	}
}
