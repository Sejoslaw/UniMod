package com.github.sejoslaw.unimod.common.properties;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class ModularDirectionProperty implements IModularDirectionProperty {
	private final BooleanProperty property;
	private final Direction direction;

	public ModularDirectionProperty(BooleanProperty property, Direction direction) {
		this.property = property;
		this.direction = direction;
	}

	public BooleanProperty getProperty() {
		return this.property;
	}

	public Direction getDirection() {
		return this.direction;
	}
}