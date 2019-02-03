package com.github.sejoslaw.unimod.common;

import com.github.sejoslaw.unimod.common.items.ItemUniwrench;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;

/**
 * Holds information about all of the items in the mod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModItems {
	public static final Item UNI_WRENCH;

	private UniModItems() {
	}

	static {
		Settings uniwrenchSettings = new Item.Settings().stackSize(1);
		UNI_WRENCH = new ItemUniwrench(uniwrenchSettings);
	}
}
