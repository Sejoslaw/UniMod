package com.github.sejoslaw.unimod.modloader.fabric;

import com.github.sejoslaw.unimod.core.UniModBlocks;
import com.github.sejoslaw.unimod.core.UniModNames;
import com.github.sejoslaw.unimod.core.UniModTileEntities;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * Core mod class for Fabric Mod Loader.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniMod implements ModInitializer {
	public void onInitialize() {
		initBlocks();
		initTileEntities();
	}

	private void initBlocks() {
		registerBlock(UniModNames.TRANSFER_NODE_ID, UniModBlocks.TRANSFER_NODE);
	}

	private void initTileEntities() {
		Registry.register(Registry.BLOCK_ENTITY, UniModNames.TRANSFER_NODE_ID, UniModTileEntities.TRANSFER_NODE);
	}

	private void registerBlock(Identifier id, Block block) {
		Registry.BLOCK.register(id, block);

		Settings itemSettings = new Item.Settings().stackSize(64).itemGroup(ItemGroup.BUILDING_BLOCKS);
		Registry.ITEM.register(id, new BlockItem(block, itemSettings));
	}
}
