package com.github.sejoslaw.unimod.common.tileentities.unicable;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.UniModProperties;
import com.github.sejoslaw.unimod.common.utils.UniCableUtils;

import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniCableSide implements IUniCableSide {
	private final IUniCable cable;
	private final Direction direction;

	public UniCableSide(IUniCable cable, Direction direction) {
		this.cable = cable;
		this.direction = direction;
	}

	public IUniCable getCable() {
		return this.cable;
	}

	public Direction getSide() {
		return this.direction;
	}

	public void toggleNextMode() {
		boolean cableStateValue = UniModProperties.isConnected(this.cable, this.direction);
		cableStateValue = !cableStateValue;

		UniModProperties.setDirectionState(this.cable, this.direction, cableStateValue);
	}

	public boolean isConnected() {
		return UniModProperties.isConnected(this.cable, this.direction);
	}

	public static IUniCableSide getOpposite(IUniCableSide cableSide) {
		IUniCable cable = cableSide.getCable();
		Direction direction = cableSide.getSide();
		IUniCable neighbourCable = UniCableUtils.getCable(cable.getWorld(), cable.getPos().offset(direction));

		if (neighbourCable == null) {
			return null;
		}

		return neighbourCable.getCableSide(direction.getOpposite());
	}
}
