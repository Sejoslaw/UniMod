package com.github.sejoslaw.unimod.common.modules.unicable.fluid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public abstract class FluidModuleBase implements IUniCableModule {
	public Collection<String> getMessages(IUniCableSide side, ItemStack stack) {
		Stack<String> messages = new Stack<>();

		this.filterMessages(side, messages);

		if (messages.isEmpty()) {
			return null;
		}

		Collection<String> list = new ArrayList<>();

		do {
			list.add(messages.pop());
		} while (!messages.empty());

		return list;
	}

	protected String getFluidFullName(Fluid fluid) {
		return Registry.FLUID.getId(fluid).toString();
	}

	protected FluidState getFluidState(IUniCableSide side) {
		IUniCable cable = side.getCable();
		return cable.getWorld().getFluidState(cable.getPos().offset(side.getSide()));
	}

	public abstract void filterMessages(IUniCableSide side, Stack<String> messages);
}