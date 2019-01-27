package com.github.sejoslaw.unimod.tileentities;

import com.github.sejoslaw.unimod.core.UniModTileEntities;

import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;
import net.minecraft.util.Tickable;

/**
 * Tile Entity for Transfer Node Block.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class TileEntityTransferNode extends LockableContainerBlockEntity implements Tickable {
	public TileEntityTransferNode() {
		super(UniModTileEntities.TRANSFER_NODE);
	}

	public void tick() {
	}

	public int getInvSize() {
		return 0;
	}

	public boolean isInvEmpty() {
		return false;
	}

	public ItemStack getInvStack(int var1) {
		return null;
	}

	public ItemStack takeInvStack(int var1, int var2) {
		return null;
	}

	public ItemStack removeInvStack(int var1) {
		return null;
	}

	public void setInvStack(int var1, ItemStack stack) {
	}

	public boolean canPlayerUseInv(PlayerEntity player) {
		return false;
	}

	public void clearInv() {
	}

	protected TextComponent method_17823() {
		return null;
	}

	protected Container createContainer(int var1, PlayerInventory playerInv) {
		return null;
	}
}
