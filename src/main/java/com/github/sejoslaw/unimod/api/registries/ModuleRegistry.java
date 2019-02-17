package com.github.sejoslaw.unimod.api.registries;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableTogglableModule;

/**
 * Registry which is responsible to store all informations about currently
 * registered modules.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class ModuleRegistry {
	/**
	 * Contains information about togglable modules. <br>
	 * Key: Modules-group (for instance: Fluids, Items, Redstone, etc...) <br>
	 * Value: Collection of modules which should be not checked when the specified
	 * group is toggled off.
	 */
	public static final Map<String, Set<IUniCableModule>> UNI_CABLE_MODULES = new LinkedHashMap<>();

	private ModuleRegistry() {
	}

	/**
	 * Adds standard UniCable module which will always be working for the single
	 * UniCable.
	 */
	public static void addUniCableModule(IUniCableModule module) {
		if (!UNI_CABLE_MODULES.containsKey(IUniCableTogglableModule.MODULE_GROUP_CORE_KEY)) {
			UNI_CABLE_MODULES.put(IUniCableTogglableModule.MODULE_GROUP_CORE_KEY, new HashSet<>());
		}

		UNI_CABLE_MODULES.get(IUniCableTogglableModule.MODULE_GROUP_CORE_KEY).add(module);
	}

	/**
	 * Adds the togglable module which will be added to the selected group.
	 */
	public static void addUniCableTogglableModule(String groupName, IUniCableTogglableModule module) {
		if (!UNI_CABLE_MODULES.containsKey(groupName)) {
			UNI_CABLE_MODULES.put(groupName, new HashSet<>());
		}

		UNI_CABLE_MODULES.get(groupName).add(module);
	}
}
