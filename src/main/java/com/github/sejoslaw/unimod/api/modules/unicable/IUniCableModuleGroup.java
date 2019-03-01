package com.github.sejoslaw.unimod.api.modules.unicable;

import java.util.Set;

/**
 * Contains informations about modules which are related to the same area of
 * work. For instance: Fluids, Items, Redstone, etc.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public interface IUniCableModuleGroup {
	/**
	 * @return Returns the name of the current group of modules.
	 */
	String getGroupName();

	/**
	 * @return Returns all the modules of this group.
	 */
	Set<IUniCableModule> getModules();
}
