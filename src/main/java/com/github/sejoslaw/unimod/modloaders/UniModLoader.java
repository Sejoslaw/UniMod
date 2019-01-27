package com.github.sejoslaw.unimod.modloaders;

import com.github.sejoslaw.unimod.core.UniModBlocks;
import com.github.sejoslaw.unimod.core.UniModItemGroup;
import com.github.sejoslaw.unimod.core.UniModItems;
import com.github.sejoslaw.unimod.core.UniModNames;
import com.github.sejoslaw.unimod.core.UniModTileEntities;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * Core mod class for registering mod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModLoader {
	private UniModLoader() {
	}

	public static void initialize() {
		initItems();
		initBlocks();
		initTileEntities();
	}

	private static void initItems() {
		Registry.ITEM.register(UniModNames.UNIWRENCH_ID, UniModItems.UNIWRENCH);
	}

	private static void initBlocks() {
		registerBlock(UniModNames.TRANSFER_NODE_ID, UniModBlocks.TRANSFER_NODE);
	}

	private static void initTileEntities() {
		Registry.register(Registry.BLOCK_ENTITY, UniModNames.TRANSFER_NODE_ID, UniModTileEntities.TRANSFER_NODE);
	}

	private static void registerBlock(Identifier id, Block block) {
		Registry.BLOCK.register(id, block);

		Settings itemSettings = new Item.Settings().stackSize(64).itemGroup(UniModItemGroup.ITEM_GROUP);
		Registry.ITEM.register(id, new BlockItem(block, itemSettings));
	}
}
