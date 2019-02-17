package com.github.sejoslaw.unimod.common.modules.unicable.fluid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableTogglableModule;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public abstract class FluidModuleBase implements IUniCableTogglableModule {
	public Collection<String> getMessages(IUniCable cable, Direction side, ItemStack stack) {
		Stack<String> messages = new Stack<>();

		this.filterMessages(cable, side, messages);

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

	protected FluidState getFluidState(IUniCable cable, Direction side) {
		return cable.getWorld().getFluidState(cable.getPos().offset(side));
	}

	public abstract void filterMessages(IUniCable cable, Direction side, Stack<String> messages);
}