package com.github.sejoslaw.unimod.common.tileentities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.sejoslaw.unimod.api.enums.EnumTransferMode;
import com.github.sejoslaw.unimod.api.modules.IUniCableModule;
import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.api.tileentities.IUniCable;
import com.github.sejoslaw.unimod.common.UniModProperties;
import com.github.sejoslaw.unimod.common.UniModTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class TileEntityUniCable extends BlockEntity implements Tickable, IUniCable {
	/**
	 * Data which passed to modules.
	 */
	private final Map<String, Object> data = new HashMap<>();
	private EnumTransferMode mode;

	public TileEntityUniCable() {
		super(UniModTileEntities.UNI_CABLE);
		ModuleRegistry.UNI_CABLE_MODULES.forEach(module -> module.initialize(this));
	}

	public void tick() {
		// Which modules were connected on which direction.
		Map<Direction, Set<IUniCableModule>> connections = new HashMap<>();

		// Get all connected directions
		ModuleRegistry.UNI_CABLE_MODULES.forEach(module -> {
			for (Direction direction : Direction.values()) {
				if (module.canConnect(this, direction)) {
					if (!connections.containsKey(direction)) {
						connections.put(direction, new HashSet<>());
					}

					connections.get(direction).add(module);
				}
			}
		});

		// Update block rendering for all connected directions.
		this.updateRendering(connections);

		// Execute modules logic
		connections.forEach((direction, moduleSet) -> {
			moduleSet.forEach(module -> {
				module.transmit(this, direction);
			});
		});
	}

	public Map<String, Object> getData() {
		return this.data;
	}

	public EnumTransferMode getCurrentMode() {
		return this.mode;
	}

	public Collection<String> getMessages() {
		List<String> messages = new ArrayList<>();
		ModuleRegistry.UNI_CABLE_MODULES.forEach(module -> messages.addAll(module.getMessages(this)));
		return messages;
	}

	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		ModuleRegistry.UNI_CABLE_MODULES.forEach(module -> module.readFromNBT(this, tag));
	}

	public CompoundTag toTag(CompoundTag tag) {
		CompoundTag newTag = super.toTag(tag);
		ModuleRegistry.UNI_CABLE_MODULES.forEach(module -> module.writeToNBT(this, newTag));
		return newTag;
	}

	public void toggleNextMode() {
		mode = mode.toggleTransfer();
	}

	/**
	 * Updates rendering of the current block as well as of neighbours.
	 */
	private void updateRendering(Map<Direction, Set<IUniCableModule>> connections) {
		BlockPos currentPos = this.getPos();
		BlockState currentState = this.world.getBlockState(currentPos);

		// Clear all connections
		currentState = this.updateConnections(currentState, Direction.values(), false);

		// Update new connections
		currentState = this.updateConnections(currentState, connections.keySet().toArray(new Direction[0]), true);

		this.world.setBlockState(currentPos, currentState);
	}

	private BlockState updateConnections(BlockState state, Direction[] directions, boolean value) {
		for (Direction direction : directions) {
			BooleanProperty prop = UniModProperties.getConnectionPropertyFromDirection(direction);
			state = state.with(prop, value);
		}
		return state;
	}
}
