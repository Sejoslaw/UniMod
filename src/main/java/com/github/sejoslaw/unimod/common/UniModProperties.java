package com.github.sejoslaw.unimod.common;

import java.util.HashSet;
import java.util.Set;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.properties.IModularDirectionProperty;
import com.github.sejoslaw.unimod.common.properties.ModularDirectionProperty;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.Direction;

/**
 * Holds properties used in UniMod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModProperties {
	private static final Set<IModularDirectionProperty> IS_CONNECTED_PROPERTIES = new HashSet<>();

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

	public static void addConnectionProperty(IModularDirectionProperty prop) {
		IS_CONNECTED_PROPERTIES.add(prop);
	}

	public static BooleanProperty getConnectionPropertyFromDirection(Direction direction) {
		for (IModularDirectionProperty prop : IS_CONNECTED_PROPERTIES) {
			if (prop.getDirection() == direction) {
				return prop.getProperty();
			}
		}

		return null;
	}

	public static boolean isConnected(IUniCableSide cableSide) {
		BooleanProperty prop = getConnectionPropertyFromDirection(cableSide.getSide());
		BlockState cableState = cableSide.getCable().getBlockState();

		if (cableState == null) {
			return false;
		}

		boolean isSideConnected = cableState.get(prop);
		return isSideConnected;
	}

	public static void setDirectionState(IUniCableSide cableSide, boolean value) {
		if (cableSide == null) {
			return;
		}

		IUniCable cable = cableSide.getCable();
		BooleanProperty prop = getConnectionPropertyFromDirection(cableSide.getSide());
		BlockState state = cable.getBlockState();

		if (state == null) {
			return;
		}

		state = state.with(prop, value);
		cable.setBlockState(state);
	}
}