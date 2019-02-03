package com.github.sejoslaw.unimod.common.modloaders.fabric;

import com.github.sejoslaw.unimod.common.UniModBlocks;
import com.github.sejoslaw.unimod.common.UniModItems;
import com.github.sejoslaw.unimod.common.modloaders.UniModLoader;
import com.github.sejoslaw.unimod.core.UniModItemGroup;
import com.github.sejoslaw.unimod.core.UniModNames;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;

/**
 * Core mod class for Fabric Mod Loader.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniMod implements ModInitializer {
	public void onInitialize() {
		UniModLoader.initialize();
		this.createItemGroup();
	}

	private void createItemGroup() {
		UniModItemGroup.ITEM_GROUP = FabricItemGroupBuilder.create(UniModNames.ITEM_GROUP_ID)
				.icon(() -> new ItemStack(UniModItems.UNI_WRENCH)).stacksForDisplay((elements) -> {
					// Items
					elements.add(new ItemStack(UniModItems.UNI_WRENCH));
					// Blocks
					elements.add(new ItemStack(UniModBlocks.UNI_CABLE));
				}).build();
	}
}
