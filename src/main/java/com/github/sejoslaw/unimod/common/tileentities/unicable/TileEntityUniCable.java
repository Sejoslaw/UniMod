package com.github.sejoslaw.unimod.common.tileentities.unicable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.github.sejoslaw.unimod.api.items.IUniWrench;
import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableFluidStorage;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.UniModTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class TileEntityUniCable extends BlockEntity implements Tickable, IUniCable {
	public static final String UNI_CABLE_SIDE_KEY = "UniMod_UniCableSide_IsConnected_";
	public static final String UNI_CABLE_FLUID_STORAGE_KEY = "UniMod_FluidStorage_";

	/**
	 * Data which passed to modules.
	 */
	private final Map<String, Object> data = new HashMap<>();

	public TileEntityUniCable() {
		super(UniModTileEntities.UNI_CABLE);

		for (Map.Entry<String, Set<IUniCableModule>> entry : ModuleRegistry.UNI_CABLE_MODULES.entrySet()) {
			for (IUniCableModule module : entry.getValue()) {
				module.initialize(this);
			}
		}
	}

	public void tick() {
		// Which modules were connected on which direction.
		Map<Direction, Set<IUniCableModule>> connections = new HashMap<>();

		// Get all connected directions
		for (Map.Entry<String, Set<IUniCableModule>> entry : ModuleRegistry.UNI_CABLE_MODULES.entrySet()) {
			for (IUniCableModule module : entry.getValue()) {
				for (Direction direction : Direction.values()) {
					if (module.canConnect(this, direction)) {
						if (!connections.containsKey(direction)) {
							connections.put(direction, new HashSet<>());
						}

						connections.get(direction).add(module);
					}
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
		return this.world.getBlockState(this.pos);
	}

	public void setBlockState(BlockState state) {
		this.world.setBlockState(this.pos, state);
		this.world.updateNeighbors(this.pos, state.getBlock());
	}

	public Collection<String> getMessages(Direction side, ItemStack stack) {
		Collection<String> messages = new ArrayList<>();

		String currentGroup = "";

		if (stack.getItem() instanceof IUniWrench) {
			currentGroup = ((IUniWrench) stack.getItem()).getModuleGroup(stack);
		}

		// Process for all module groups.
		if (currentGroup.equals("")) {
			for (Map.Entry<String, Set<IUniCableModule>> entry : ModuleRegistry.UNI_CABLE_MODULES.entrySet()) {
				this.filterMessages(side, stack, messages, entry.getValue());
			}
		} else {
			Set<IUniCableModule> modules = ModuleRegistry.UNI_CABLE_MODULES.get(currentGroup);
			this.filterMessages(side, stack, messages, modules);
		}

		return messages;
	}

	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);

		for (Map.Entry<String, Set<IUniCableModule>> entry : ModuleRegistry.UNI_CABLE_MODULES.entrySet()) {
			for (IUniCableModule module : entry.getValue()) {
				module.readFromNBT(this, tag);
			}
		}
	}

	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);

		for (Map.Entry<String, Set<IUniCableModule>> entry : ModuleRegistry.UNI_CABLE_MODULES.entrySet()) {
			for (IUniCableModule module : entry.getValue()) {
				module.writeToNBT(this, tag);
			}
		}

		return tag;
	}

	public int getWeakRedstonePower(Direction side) {
		if (this.world == null) {
			return 0;
		}

		int power = 0;

		for (Map.Entry<String, Set<IUniCableModule>> entry : ModuleRegistry.UNI_CABLE_MODULES.entrySet()) {
			for (IUniCableModule module : entry.getValue()) {
				int moduleRedstonePower = module.getWeakRedstonePower(this, side);
				power = Math.max(power, moduleRedstonePower);
			}
		}

		return power;
	}

	public IUniCableSide getCableSide(Direction side) {
		return (IUniCableSide) getFromKey(getDirectionKey(side));
	}

	public IUniCableFluidStorage getFluidStorage(Direction side) {
		return (IUniCableFluidStorage) getFromKey(getFluidStorageKey(side));
	}

	private Object getFromKey(String key) {
		return this.getData().containsKey(key) ? this.getData().get(key) : null;
	}

	private void filterMessages(Direction side, ItemStack stack, Collection<String> messages,
			Set<IUniCableModule> modules) {
		for (IUniCableModule module : modules) {
			Collection<String> moduleMessages = module.getMessages(this, side, stack);

			if (moduleMessages != null && !moduleMessages.isEmpty()) {
				messages.addAll(moduleMessages);
			}
		}
	}

	public static String getDirectionKey(Direction side) {
		return UNI_CABLE_SIDE_KEY + side.getId();
	}

	public static String getFluidStorageKey(Direction side) {
		return UNI_CABLE_FLUID_STORAGE_KEY + side.getId();
	}
}
