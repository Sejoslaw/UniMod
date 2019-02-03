package com.github.sejoslaw.unimod.common.properties;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public interface IModularDirectionProperty {
	/**
	 * @return Model connection property.
	 */
	BooleanProperty getProperty();

	/**
	 * @return Corresponding direction.
	 */
	Direction getDirection();
}