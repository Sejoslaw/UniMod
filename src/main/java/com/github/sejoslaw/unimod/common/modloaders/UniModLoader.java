package com.github.sejoslaw.unimod.common.modloaders;

import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.common.UniModBlocks;
import com.github.sejoslaw.unimod.common.UniModItems;
import com.github.sejoslaw.unimod.common.UniModTileEntities;
import com.github.sejoslaw.unimod.common.modules.unicable.ConnectionModule;
import com.github.sejoslaw.unimod.common.modules.unicable.RedstoneSignalTransportModule;
import com.github.sejoslaw.unimod.common.modules.unicable.TransferModeModule;
import com.github.sejoslaw.unimod.core.UniModItemGroup;
import com.github.sejoslaw.unimod.core.UniModNames;

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
		initUniCableModules();
	}

	private static void initItems() {
		Registry.ITEM.register(UniModNames.UNI_WRENCH_ID, UniModItems.UNI_WRENCH);
	}

	private static void initBlocks() {
		registerBlock(UniModNames.UNI_CABLE_ID, UniModBlocks.UNI_CABLE);
	}

	private static void initTileEntities() {
		Registry.register(Registry.BLOCK_ENTITY, UniModNames.UNI_CABLE_ID, UniModTileEntities.UNI_CABLE);
	}

	private static void initUniCableModules() {
		ModuleRegistry.addUniCableModule(new TransferModeModule());
		ModuleRegistry.addUniCableModule(new ConnectionModule());
		// ModuleRegistry.addUniCableModule(new ItemTransportModule());
		// ModuleRegistry.addUniCableModule(new FluidTransportModule());
		ModuleRegistry.addUniCableModule(new RedstoneSignalTransportModule());
	}

	private static void registerBlock(Identifier id, Block block) {
		Registry.BLOCK.register(id, block);

		Settings itemSettings = new Item.Settings().stackSize(64).itemGroup(UniModItemGroup.ITEM_GROUP);
		Registry.ITEM.register(id, new BlockItem(block, itemSettings));
	}
}
