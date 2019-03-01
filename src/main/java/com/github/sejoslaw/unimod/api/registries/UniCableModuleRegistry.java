package com.github.sejoslaw.unimod.api.registries;

import java.util.HashSet;
import java.util.Set;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModuleGroup;

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
}
