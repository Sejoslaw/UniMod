package com.github.sejoslaw.unimod.api.enums;

import net.minecraft.util.StringRepresentable;

/**
 * Transfer Modes. Implements StringRepresentable to allow it to be converted
 * into Minecraft Property.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public enum EnumTransferMode implements StringRepresentable {
	DEFAULT("default", 0), TRANSFER("transfer", 1), INPUT("input", 2), OUTPUT("output", 3);

	private final String modeName;
	private final int id;

	EnumTransferMode(String modeName, int id) {
		this.modeName = modeName;
		this.id = id;
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

	public int getId() {
		return this.id;
	}

	public String asString() {
		return this.modeName;
	}
}
