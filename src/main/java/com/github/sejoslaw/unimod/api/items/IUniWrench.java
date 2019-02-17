package com.github.sejoslaw.unimod.api.items;

import net.minecraft.item.ItemStack;

/**
 * Indicates that an item can be used as a wrench.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public interface IUniWrench {
	/**
	 * @return Returns the name (key) of the currently selected module group. If
	 *         this returns empty string ("") it means that the current UniWrench
	 *         should work on all module groups for current UniCable side.
	 */
	String getModuleGroup(ItemStack stack);
}
