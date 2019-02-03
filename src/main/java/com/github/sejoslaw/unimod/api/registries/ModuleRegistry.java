package com.github.sejoslaw.unimod.api.registries;

import java.util.HashSet;
import java.util.Set;

import com.github.sejoslaw.unimod.api.modules.IUniCableModule;

/**
 * Registry which is responsible to store all informations about currently
 * registered modules.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class ModuleRegistry {
	/**
	 * Contains information about all currently registered UniCable modules.
	 */
	public static final Set<IUniCableModule> UNI_CABLE_MODULES = new HashSet<>();

	private ModuleRegistry() {
	}

	/**
	 * @return Adds new UniCable module to set.
	 */
	public static boolean addUniCableModule(IUniCableModule module) {
		return UNI_CABLE_MODULES.add(module);
	}
}
