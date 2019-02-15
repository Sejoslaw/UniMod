package com.github.sejoslaw.unimod.common.tileentities.unicable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.sejoslaw.unimod.api.modules.IUniCableModule;
import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.UniModTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class TileEntityUniCable extends BlockEntity implements Tickable, IUniCable {
	public static final String UNI_CABLE_SIDE_KEY = "UniMod_UniCableSide_IsConnected_";

	/**
	 * Data which passed to modules.
	 */
	private final Map<String, Object> data = new HashMap<>();

	public TileEntityUniCable() {
		super(UniModTileEntities.UNI_CABLE);

		ModuleRegistry.UNI_CABLE_MODULES.forEach(module -> module.initialize(this));
	}

	public void tick() {
		// Which modules were connected on which direction.
		Map<Direction, Set<IUniCableModule>> connections = new HashMap<>();

		// Get all connected directions
		for (IUniCableModule module : ModuleRegistry.UNI_CABLE_MODULES) {
			for (Direction direction : Direction.values()) {
				if (module.canConnect(this, direction)) {
					if (!connections.containsKey(direction)) {
						connections.put(direction, new HashSet<>());
					}

					connections.get(direction).add(module);
				}
			}
		}

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

	public BlockState getBlockState() {
		return this.world != null ? this.world.getBlockState(this.pos) : this.getCachedState();
	}

	public void setBlockState(BlockState state) {
		this.world.setBlockState(this.pos, state);
		this.world.updateNeighbors(this.pos, state.getBlock());
	}

	public Collection<String> getMessages() {
		List<String> messages = new ArrayList<>();
		ModuleRegistry.UNI_CABLE_MODULES.forEach(module -> messages.addAll(module.getMessages(this)));
		return messages;
	}

	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);

		for (IUniCableModule module : ModuleRegistry.UNI_CABLE_MODULES) {
			module.readFromNBT(this, tag);
		}
	}

	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);

		for (IUniCableModule module : ModuleRegistry.UNI_CABLE_MODULES) {
			module.writeToNBT(this, tag);
		}

		return tag;
	}

	public int getWeakRedstonePower() {
		int power = 0;

		for (IUniCableModule module : ModuleRegistry.UNI_CABLE_MODULES) {
			int moduleRedstonePower = module.getWeakRedstonePower(this);
			power = Math.max(power, moduleRedstonePower);
		}

		return power;
	}

	public IUniCableSide getCableSide(Direction side) {
		String key = UNI_CABLE_SIDE_KEY + side.getId();
		return this.getData().containsKey(key) ? (IUniCableSide) this.getData().get(key) : null;
	}

	public static String getDirectionKey(Direction side) {
		return UNI_CABLE_SIDE_KEY + side.getId();
	}
}
