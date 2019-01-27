package com.github.sejoslaw.unimod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

/**
 * Item Uniwrench.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class ItemUniwrench extends Item {
	public ItemUniwrench(Settings settings) {
		super(settings);
	}

	/**
	 * Shift-Right-Click => Save position of hit block (container).
	 */
	public ActionResult useOnBlock(ItemUsageContext context) {
		return ActionResult.PASS;
	}
}
