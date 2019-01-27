package com.github.sejoslaw.unimod.core;

import com.github.sejoslaw.unimod.tileentity.TileEntityTransferNode;

import net.minecraft.block.entity.BlockEntityType;

/**
 * Holds information about all of the tile entities in the mod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModTileEntities {
	public static BlockEntityType<TileEntityTransferNode> TRANSFER_NODE = BlockEntityType.Builder
			.create(TileEntityTransferNode::new).build(null);

	private UniModTileEntities() {
	}
}
