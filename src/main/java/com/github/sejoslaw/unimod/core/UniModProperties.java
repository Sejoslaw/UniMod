package com.github.sejoslaw.unimod.core;

import com.github.sejoslaw.unimod.enums.EnumTransferMode;
import com.github.sejoslaw.unimod.properties.PropertyTransferMode;

/**
 * Holds properties used in UniMod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModProperties {
	public static PropertyTransferMode MODE = PropertyTransferMode.create("mode", EnumTransferMode.values());

	private UniModProperties() {
	}
}
