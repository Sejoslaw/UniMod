package com.github.sejoslaw.unimod.common;

import com.github.sejoslaw.unimod.common.blocks.BlockUniCable;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Settings;
import net.minecraft.block.Material;

/**
 * Holds information about all of the blocks in the mod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModBlocks {
	public static final Block UNI_CABLE;

	private UniModBlocks() {
	}

	static {
		Settings transferNodeSettings = Block.Settings.of(Material.ICE).strength(1f, 1f);
		UNI_CABLE = new BlockUniCable(transferNodeSettings);
	}
}
