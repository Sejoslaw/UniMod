package com.github.sejoslaw.unimod.common.tileentities.unicable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.UniModTileEntities;
import com.github.sejoslaw.unimod.common.modules.unicable.core.UniCableSettingsModule;

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
	public static final String UNI_CABLE_SIDE_KEY = "UniMod_UniCableSide_Side_";

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
		Map<Direction, Set<IUniCableModule>> inputters = new HashMap<>();
		Map<Direction, Set<IUniCableModule>> outputters = new HashMap<>();

		// Get all connected directions
		for (Map.Entry<String, Set<IUniCableModule>> entry : ModuleRegistry.UNI_CABLE_MODULES.entrySet()) {
			for (IUniCableModule module : entry.getValue()) {
				String moduleGroupName = entry.getKey();

				for (Direction direction : Direction.values()) {
					IUniCableSide cableSide = this.getCableSide(direction);

					// Check input
					if (UniCableSettingsModule.canInput(cableSide, moduleGroupName)) {
						if (!inputters.containsKey(direction)) {
							inputters.put(direction, new HashSet<>());
						}
						inputters.get(direction).add(module);
					}

					// Check output
					if (UniCableSettingsModule.canOutput(cableSide, moduleGroupName)) {
						if (!outputters.containsKey(direction)) {
							outputters.put(direction, new HashSet<>());
						}
						outputters.get(direction).add(module);
					}
				}
			}
		}

		// Execute modules logic
		inputters.forEach((direction, moduleSet) -> {
			moduleSet.forEach(module -> {
				module.input(this.getCableSide(direction));
			});
		});

		outputters.forEach((direction, moduleSet) -> {
			moduleSet.forEach(module -> {
				module.output(this.getCableSide(direction));
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

		for (Map.Entry<String, Set<IUniCableModule>> entry : ModuleRegistry.UNI_CABLE_MODULES.entrySet()) {
			for (IUniCableModule module : entry.getValue()) {
				Collection<String> moduleMessages = module.getMessages(this.getCableSide(side), stack);

				if (moduleMessages != null && !moduleMessages.isEmpty()) {
					messages.addAll(moduleMessages);
				}
			}
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
				int moduleRedstonePower = module.getWeakRedstonePower(this.getCableSide(side));
				power = Math.max(power, moduleRedstonePower);
			}
		}

		return power;
	}

	public IUniCableSide getCableSide(Direction side) {
		return (IUniCableSide) getFromKey(getDirectionKey(side));
	}

	private Object getFromKey(String key) {
		return this.getData().containsKey(key) ? this.getData().get(key) : null;
	}

	public static String getDirectionKey(Direction side) {
		return UNI_CABLE_SIDE_KEY + side.getId();
	}
}
