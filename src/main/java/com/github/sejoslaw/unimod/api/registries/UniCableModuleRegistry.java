package com.github.sejoslaw.unimod.api.registries;

import java.util.HashSet;
import java.util.Set;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModuleGroup;
import com.github.sejoslaw.unimod.api.modules.unicable.UniCableCoreModuleNames;

/**
 * Registry which is responsible to store all informations about currently
 * registered modules for UniCable.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniCableModuleRegistry {
	/**
	 * Contains information about all of the module groups.
	 */
	public static final Set<IUniCableModuleGroup> UNI_CABLE_MODULES = new HashSet<>();

	private UniCableModuleRegistry() {
	}

	/**
	 * This is the method which should be called to get all the appropriate module
	 * groups.
	 * 
	 * @return Returns all the module groups except for the "Core" module group.
	 */
	public static Set<IUniCableModuleGroup> getModuleGroups() {
		Set<IUniCableModuleGroup> moduleGroups = new HashSet<>(UNI_CABLE_MODULES);
		moduleGroups.removeIf(
				moduleGroup -> moduleGroup.getGroupName().equals(UniCableCoreModuleNames.MODULE_GROUP_CORE_KEY));
		return moduleGroups;
	}
}
