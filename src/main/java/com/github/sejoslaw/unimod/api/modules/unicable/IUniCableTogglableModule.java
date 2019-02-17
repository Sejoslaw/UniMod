package com.github.sejoslaw.unimod.api.modules.unicable;

/**
 * Togglable modules came with the concept of split-responsibility for each side of the UniCable. <br>
 * UniCable can be connected or disconnected on each side but it can albo be toggled on each modules-group for the side. <br>
 * Modules-group are the groups for multiple modules like: Fluids, Items, Redstone, etc... <br>
 * Modules-group can be toggled separately on each side of the UniCable. <br>
 *  <br>
 * For instance imagine that the UniCable can connect to a block to receive redstone signal but not the items / fluids. <br>
 * In that can UniCable side for Item-group and Fluid-group modules must be toggled to be disconnected but Redstone-group to be connected. <br>
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public interface IUniCableTogglableModule extends IUniCableModule {
	public static final String MODULE_GROUP_CORE_KEY = "Core";
	public static final String MODULE_GROUP_REDSTONE_KEY = "Redstone";
	public static final String MODULE_GROUP_ITEMS_KEY = "Items";
	public static final String MODULE_GROUP_FLUIDS_KEY = "Fluids";
}
