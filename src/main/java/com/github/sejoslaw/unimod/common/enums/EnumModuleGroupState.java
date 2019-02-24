package com.github.sejoslaw.unimod.common.enums;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public enum EnumModuleGroupState {
	ENABLED("Enabled", true), DISABLED("Disabled", false);

	private String stateName;
	private boolean state;

	EnumModuleGroupState(String stateName, boolean state) {
		this.stateName = stateName;
		this.state = state;
	}

	public String getStateName() {
		return this.stateName;
	}

	public boolean getState() {
		return this.state;
	}
}
