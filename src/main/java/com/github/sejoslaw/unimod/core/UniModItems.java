package com.github.sejoslaw.unimod.core;

import com.github.sejoslaw.unimod.items.ItemUniwrench;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;

/**
 * Holds information about all of the items in the mod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModItems {
	public static final Item UNIWRENCH;

	private UniModItems() {
	}

	static {
		Settings uniwrenchSettings = new Item.Settings().stackSize(1);
		UNIWRENCH = new ItemUniwrench(uniwrenchSettings);
	}
}
