package com.github.sejoslaw.unimod.api.registries;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;

/**
 * Registry which is responsible to store all informations about currently
 * registered modules.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class ModuleRegistry {
	/**
	 * Contains information about all of the modules. <br>
	 * Key: Modules-group name (for instance: Fluids, Items, Redstone, etc...), see:
	 * UniCableCoreModuleNames <br>
	 * Value: Collection of modules related to the specified group.
	 */
	public static final Map<String, Set<IUniCableModule>> UNI_CABLE_MODULES = new LinkedHashMap<>();

	private ModuleRegistry() {
	}

	/**
	 * Adds the module to the specified group. For the names of default groups see:
	 * UniCableCoreModuleNames.
	 */
	public static void addUniCableModule(String groupName, IUniCableModule module) {
		if (!UNI_CABLE_MODULES.containsKey(groupName)) {
			UNI_CABLE_MODULES.put(groupName, new HashSet<>());
		}

		UNI_CABLE_MODULES.get(groupName).add(module);
	}

	/**
	 * @return Returns the Id of the specified Module Group. If none found, returns
	 *         -1.
	 */
	public static int getModuleGroupId(String moduleGroupName) {
		Object[] moduleGroupNames = UNI_CABLE_MODULES.keySet().toArray();
		for (int i = 0; i < moduleGroupNames.length; ++i) {
			if (moduleGroupNames[i].equals(moduleGroupName)) {
				return i;
			}
		}
		return -1;
	}
}
