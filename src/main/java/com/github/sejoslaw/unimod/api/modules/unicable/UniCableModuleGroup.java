package com.github.sejoslaw.unimod.api.modules.unicable;

import java.util.Set;

/**
 * Default implementation of IUniCableModuleGroup.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 * 
 * @see IUniCableModuleGroup
 */
public class UniCableModuleGroup implements IUniCableModuleGroup {
	private String groupName;
	private Set<IUniCableModule> modules;

	public UniCableModuleGroup(String groupName, Set<IUniCableModule> modules) {
		this.groupName = groupName;
		this.modules = modules;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public Set<IUniCableModule> getModules() {
		return this.modules;
	}
}
