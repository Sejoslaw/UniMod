package com.github.sejoslaw.unimod.common.modloaders;

import static com.github.sejoslaw.unimod.api.modules.unicable.UniCableCoreModuleNames.MODULE_GROUP_CORE_KEY;
import static com.github.sejoslaw.unimod.api.modules.unicable.UniCableCoreModuleNames.MODULE_GROUP_FLUIDS_KEY;
import static com.github.sejoslaw.unimod.api.modules.unicable.UniCableCoreModuleNames.MODULE_GROUP_REDSTONE_KEY;

import java.util.Arrays;
import java.util.HashSet;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.modules.unicable.UniCableModuleGroup;
import com.github.sejoslaw.unimod.api.registries.UniCableModuleRegistry;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.common.UniModBlocks;
import com.github.sejoslaw.unimod.common.UniModItems;
import com.github.sejoslaw.unimod.common.UniModTileEntities;
import com.github.sejoslaw.unimod.common.modules.unicable.core.CableConnectionModule;
import com.github.sejoslaw.unimod.common.modules.unicable.core.UniCableSettingsModule;
import com.github.sejoslaw.unimod.common.modules.unicable.fluid.FluidInputModule;
import com.github.sejoslaw.unimod.common.modules.unicable.fluid.FluidStorageModule;
import com.github.sejoslaw.unimod.common.modules.unicable.redstone.RedstoneSignalTransportModule;
import com.github.sejoslaw.unimod.core.UniModItemGroup;
import com.github.sejoslaw.unimod.core.UniModNames;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
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
public abstract class UniModLoader {
	public static UniModLoader INSTANCE;

	public void initialize() {
		initItems();
		initBlocks();
		initTileEntities();
		initUniCableModules();
	}

	public abstract void openContainer(PlayerEntity player, IUniCable cable);

	private void initItems() {
		Registry.ITEM.register(UniModNames.UNI_WRENCH_ID, UniModItems.UNI_WRENCH);
	}

	private void initBlocks() {
		this.registerBlock(UniModNames.UNI_CABLE_ID, UniModBlocks.UNI_CABLE);
	}

	private void initTileEntities() {
		Registry.register(Registry.BLOCK_ENTITY, UniModNames.UNI_CABLE_ID, UniModTileEntities.UNI_CABLE);
	}

	private void initUniCableModules() {
		this.addUniCableModules(MODULE_GROUP_CORE_KEY, new CableConnectionModule(), new UniCableSettingsModule());
		this.addUniCableModules(MODULE_GROUP_REDSTONE_KEY, new RedstoneSignalTransportModule());
		this.addUniCableModules(MODULE_GROUP_FLUIDS_KEY, new FluidStorageModule(), new FluidInputModule());
	}

	private void registerBlock(Identifier id, Block block) {
		Registry.BLOCK.register(id, block);

		Settings itemSettings = new Item.Settings().stackSize(64).itemGroup(UniModItemGroup.ITEM_GROUP);
		Registry.ITEM.register(id, new BlockItem(block, itemSettings));
	}

	private void addUniCableModules(String groupName, IUniCableModule... module) {
		UniCableModuleRegistry.UNI_CABLE_MODULES
				.add(new UniCableModuleGroup(groupName, new HashSet<IUniCableModule>(Arrays.asList(module))));
	}
}
