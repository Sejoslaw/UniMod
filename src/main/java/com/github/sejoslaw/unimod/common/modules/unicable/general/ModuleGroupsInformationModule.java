package com.github.sejoslaw.unimod.common.modules.unicable.general;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableTogglableModule;
import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class ModuleGroupsInformationModule implements IUniCableModule {
	public Collection<String> getMessages(IUniCable cable, Direction side, ItemStack stack) {
		IUniCableSide cableSide = cable.getCableSide(side);

		Collection<String> messages = new ArrayList<>();

		messages.add("Available Modules: ");

		for (Map.Entry<String, Set<IUniCableModule>> moduleGroup : ModuleRegistry.UNI_CABLE_MODULES.entrySet()) {
			String moduleGroupName = moduleGroup.getKey();

			if (!moduleGroupName.equals(IUniCableTogglableModule.MODULE_GROUP_CORE_KEY)) {
				boolean isConnected = cableSide.isConnected(moduleGroupName);
				messages.add(" - " + moduleGroupName + " Group: " + String.valueOf(isConnected));
			}
		}

		return messages;
	}
}
