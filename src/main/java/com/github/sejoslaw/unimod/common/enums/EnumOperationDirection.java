package com.github.sejoslaw.unimod.common.enums;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public enum EnumOperationDirection {
	INPUT("Input"), OUTPUT("Output");

	private String text;

	EnumOperationDirection(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}
