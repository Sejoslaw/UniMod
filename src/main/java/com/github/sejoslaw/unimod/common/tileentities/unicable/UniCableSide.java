package com.github.sejoslaw.unimod.common.tileentities.unicable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableTogglableModule;
import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
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
	private final Map<String, Boolean> moduleGroups;

	public UniCableSide(IUniCable cable, Direction direction) {
		this.cable = cable;
		this.direction = direction;
		this.moduleGroups = new LinkedHashMap<>();
	}

	public IUniCable getCable() {
		return this.cable;
	}

	public Direction getSide() {
		return this.direction;
	}

	public void toggleNextMode(String moduleGroupName) {
		boolean cableStateValue = UniModProperties.isConnected(this.cable, this.direction);

		this.moduleGroups.clear();

		// Toggle all modules
		if (moduleGroupName.equals("")) {
			cableStateValue = !cableStateValue;
		} else {
			// TODO: Add toggling single module.
		}

		UniModProperties.setDirectionState(this.cable, this.direction, cableStateValue);
	}

	public boolean isConnected(String moduleGroupName) {
		// Check all the possible module groups.
		if (moduleGroupName.equals("")) {
			if (!this.moduleGroups.isEmpty()) {
				for (Map.Entry<String, Boolean> entry : this.moduleGroups.entrySet()) {
					if (entry.getValue()) {
						return true;
					}
				}
			}

			for (Map.Entry<String, Set<IUniCableModule>> moduleGroups : ModuleRegistry.UNI_CABLE_MODULES.entrySet()) {
				if (!this.moduleGroups.containsKey(moduleGroups.getKey())) {
					for (IUniCableModule module : moduleGroups.getValue()) {
						if (module.canConnect(this.cable, this.direction)) {
							this.moduleGroups.put(moduleGroups.getKey(), true);
							return true;
						}
					}
				}
			}
		}

		// Check for single module group.
		if (!this.moduleGroups.containsKey(moduleGroupName)) {
			this.moduleGroups.put(moduleGroupName, UniModProperties.isConnected(this.cable, this.direction));
		}

		return this.moduleGroups.containsKey(moduleGroupName) ? this.moduleGroups.get(moduleGroupName) : false;
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
