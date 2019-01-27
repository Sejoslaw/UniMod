package com.github.sejoslaw.unimod.core;

import com.github.sejoslaw.unimod.blocks.BlockTransferNode;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Settings;
import net.minecraft.block.Material;

/**
 * Holds information about all of the blocks in the mod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModBlocks {
	public static final Block TRANSFER_NODE;

	private UniModBlocks() {
	}

	static {
		Settings transferNodeSettings = Block.Settings.of(Material.ICE).strength(1f, 1f);
		TRANSFER_NODE = new BlockTransferNode(transferNodeSettings);
	}
}
