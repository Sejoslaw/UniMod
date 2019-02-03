package com.github.sejoslaw.unimod.common;

import net.minecraft.state.property.BooleanProperty;

/**
 * Holds properties used in UniMod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModProperties {
	public static final BooleanProperty IS_CONNECTED_TOP = BooleanProperty.create("ctop");
	public static final BooleanProperty IS_CONNECTED_BOTTOM = BooleanProperty.create("cbottom");
	public static final BooleanProperty IS_CONNECTED_NORTH = BooleanProperty.create("cnorth");
	public static final BooleanProperty IS_CONNECTED_SOUTH = BooleanProperty.create("csouth");
	public static final BooleanProperty IS_CONNECTED_EAST = BooleanProperty.create("ceast");
	public static final BooleanProperty IS_CONNECTED_WEST = BooleanProperty.create("cwest");

	private UniModProperties() {
	}
}
