package com.github.sejoslaw.unimod.common.enums;

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
		case DEFAULT:
			return TRANSFER;
		case TRANSFER:
			return INPUT;
		case INPUT:
			return OUTPUT;
		case OUTPUT:
			return DEFAULT;
		default:
			return this;
		}
	}

	public String asString() {
		return this.modeName;
	}
}
