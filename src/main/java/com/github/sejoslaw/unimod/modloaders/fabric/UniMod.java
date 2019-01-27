package com.github.sejoslaw.unimod.modloaders.fabric;

import com.github.sejoslaw.unimod.core.UniModBlocks;
import com.github.sejoslaw.unimod.core.UniModItemGroup;
import com.github.sejoslaw.unimod.core.UniModItems;
import com.github.sejoslaw.unimod.core.UniModNames;
import com.github.sejoslaw.unimod.modloaders.UniModLoader;

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
		UniModItemGroup.ITEM_GROUP = FabricItemGroupBuilder.create(UniModNames.ITEMGROUP_ID)
				.icon(() -> new ItemStack(UniModItems.UNIWRENCH)).stacksForDisplay((elements) -> {
					// Items
					elements.add(new ItemStack(UniModItems.UNIWRENCH));
					// Blocks
					elements.add(new ItemStack(UniModBlocks.TRANSFER_NODE));
				}).build();
	}
}
