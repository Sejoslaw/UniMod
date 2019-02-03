package com.github.sejoslaw.unimod.common;

import com.github.sejoslaw.unimod.common.properties.IModularDirectionProperty;
import com.github.sejoslaw.unimod.common.properties.ModularDirectionProperty;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.Direction;

/**
 * Holds properties used in UniMod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModProperties {
	public static final IModularDirectionProperty IS_CONNECTED_TOP = new ModularDirectionProperty(
			BooleanProperty.create("ctop"), Direction.UP);
	public static final IModularDirectionProperty IS_CONNECTED_BOTTOM = new ModularDirectionProperty(
			BooleanProperty.create("cbottom"), Direction.DOWN);
	public static final IModularDirectionProperty IS_CONNECTED_NORTH = new ModularDirectionProperty(
			BooleanProperty.create("cnorth"), Direction.NORTH);
	public static final IModularDirectionProperty IS_CONNECTED_SOUTH = new ModularDirectionProperty(
			BooleanProperty.create("csouth"), Direction.SOUTH);
	public static final IModularDirectionProperty IS_CONNECTED_EAST = new ModularDirectionProperty(
			BooleanProperty.create("ceast"), Direction.EAST);
	public static final IModularDirectionProperty IS_CONNECTED_WEST = new ModularDirectionProperty(
			BooleanProperty.create("cwest"), Direction.WEST);

	private UniModProperties() {
	}
}