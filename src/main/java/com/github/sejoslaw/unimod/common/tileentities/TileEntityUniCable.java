package com.github.sejoslaw.unimod.common.tileentities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.sejoslaw.unimod.api.enums.EnumTransferMode;
import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.api.tileentities.IUniCable;
import com.github.sejoslaw.unimod.common.UniModTileEntities;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

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
}
