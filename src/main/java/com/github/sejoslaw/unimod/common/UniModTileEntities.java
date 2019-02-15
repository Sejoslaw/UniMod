package com.github.sejoslaw.unimod.common;

import com.github.sejoslaw.unimod.common.tileentities.unicable.TileEntityUniCable;

import net.minecraft.block.entity.BlockEntityType;

/**
 * Holds information about all of the tile entities in the mod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModTileEntities {
	public static final BlockEntityType<TileEntityUniCable> UNI_CABLE = BlockEntityType.Builder
			.create(TileEntityUniCable::new).build(null);

	private UniModTileEntities() {
	}
}
