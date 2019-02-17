package com.github.sejoslaw.unimod.common.modloaders;

import static com.github.sejoslaw.unimod.api.modules.unicable.IUniCableTogglableModule.MODULE_GROUP_FLUIDS_KEY;
import static com.github.sejoslaw.unimod.api.modules.unicable.IUniCableTogglableModule.MODULE_GROUP_REDSTONE_KEY;

import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.common.UniModBlocks;
import com.github.sejoslaw.unimod.common.UniModItems;
import com.github.sejoslaw.unimod.common.UniModTileEntities;
import com.github.sejoslaw.unimod.common.modules.unicable.fluid.FluidConnectionModule;
import com.github.sejoslaw.unimod.common.modules.unicable.fluid.FluidInputModule;
import com.github.sejoslaw.unimod.common.modules.unicable.fluid.FluidStorageModule;
import com.github.sejoslaw.unimod.common.modules.unicable.general.CableConnectionModule;
import com.github.sejoslaw.unimod.common.modules.unicable.general.ModuleGroupsInformationModule;
import com.github.sejoslaw.unimod.common.modules.unicable.redstone.RedstoneSignalTransportModule;
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
		// UniCable general modules
		ModuleRegistry.addUniCableModule(new CableConnectionModule());
		ModuleRegistry.addUniCableModule(new ModuleGroupsInformationModule());

		// Redstone-related modules
		ModuleRegistry.addUniCableTogglableModule(MODULE_GROUP_REDSTONE_KEY, new RedstoneSignalTransportModule());

		// Fluid-related modules
		ModuleRegistry.addUniCableTogglableModule(MODULE_GROUP_FLUIDS_KEY, new FluidStorageModule());
		ModuleRegistry.addUniCableTogglableModule(MODULE_GROUP_FLUIDS_KEY, new FluidConnectionModule());
		ModuleRegistry.addUniCableTogglableModule(MODULE_GROUP_FLUIDS_KEY, new FluidInputModule());

		// Item-related modules
		// ModuleRegistry.addUniCableModule(new ItemTransportModule());
	}

	private static void registerBlock(Identifier id, Block block) {
		Registry.BLOCK.register(id, block);

		Settings itemSettings = new Item.Settings().stackSize(64).itemGroup(UniModItemGroup.ITEM_GROUP);
		Registry.ITEM.register(id, new BlockItem(block, itemSettings));
	}
}
