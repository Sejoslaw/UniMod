package com.github.sejoslaw.unimod.enums;

import net.minecraft.util.StringRepresentable;

/**
 * Transfer Modes.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public enum EnumTransferMode implements StringRepresentable {
	DEFAULT("default"), TRANSFER("transfer"), INPUT("input"), OUTPUT("output");

	private final String modeName;

	EnumTransferMode(String modeName) {
		this.modeName = modeName;
	}

	public EnumTransferMode toggleTransfer() {
		switch (this) {
		case INPUT:
			return OUTPUT;
		case OUTPUT:
			return INPUT;
		default:
			return this;
		}
	}

	public String asString() {
		return this.modeName;
	}
}
