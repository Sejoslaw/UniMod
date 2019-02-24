package com.github.sejoslaw.unimod.common.modloaders.fabric;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.common.UniModBlocks;
import com.github.sejoslaw.unimod.common.UniModItems;
import com.github.sejoslaw.unimod.common.containers.UniCableContainer;
import com.github.sejoslaw.unimod.common.guis.UniCableGui;
import com.github.sejoslaw.unimod.common.modloaders.UniModLoader;
import com.github.sejoslaw.unimod.core.UniModItemGroup;
import com.github.sejoslaw.unimod.core.UniModNames;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Core mod class for Fabric Mod Loader.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniMod extends UniModLoader implements ModInitializer, ClientModInitializer {
	public void onInitialize() {
		UniModLoader.INSTANCE = this;
		this.initialize();

		this.createItemGroup();
		this.registerContainers();
	}

	public void onInitializeClient() {
		this.registerGuis();
	}

	public void openContainer(PlayerEntity player, IUniCable cable) {
		if (!(player instanceof ServerPlayerEntity)) {
			return;
		}

		ContainerProviderRegistry.INSTANCE.openContainer(UniModNames.UNI_CABLE_CONTAINER_ID, player,
				buf -> buf.writeBlockPos(cable.getPos()));
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

	private void registerContainers() {
		ContainerProviderRegistry.INSTANCE.registerFactory(UniModNames.UNI_CABLE_CONTAINER_ID,
				(syncId, identifier, player, buf) -> {
					BlockPos pos = buf.readBlockPos();
					return new UniCableContainer(syncId, pos, player);
				});
	}

	private void registerGuis() {
		ScreenProviderRegistry.INSTANCE.registerFactory(UniModNames.UNI_CABLE_CONTAINER_ID, UniCableGui::new);
	}
}
