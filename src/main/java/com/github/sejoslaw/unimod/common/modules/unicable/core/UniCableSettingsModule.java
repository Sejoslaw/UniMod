package com.github.sejoslaw.unimod.common.modules.unicable.core;

import java.util.function.Consumer;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModule;
import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModuleGroup;
import com.github.sejoslaw.unimod.api.registries.UniCableModuleRegistry;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.enums.EnumOperationDirection;
import com.github.sejoslaw.unimod.common.tileentities.unicable.UniCableSide;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

/**
 * Module which is responsible for reading / writing settings set for specified
 * UniCable.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableSettingsModule implements IUniCableModule {
	private static final String SETTINGS_KEY = "UniMod_UniCable_Settings_";

	public void initialize(IUniCable cable) {
		processEachSettings(key -> {
			if (!cable.getData().containsKey(key)) {
				cable.getData().put(key, false);
			}
		});
	}

	public void readFromNBT(IUniCable cable, CompoundTag tag) {
		processEachSettings(key -> {
			if (tag.containsKey(key)) {
				boolean value = tag.getBoolean(key);
				cable.getData().put(key, value);
			}
		});
	}

	public void writeToNBT(IUniCable cable, CompoundTag tag) {
		processEachSettings(key -> {
			if (cable.getData().containsKey(key)) {
				boolean value = (boolean) cable.getData().get(key);
				tag.putBoolean(key, value);
			}
		});
	}

	public static boolean isConnected(IUniCableSide side, String moduleGroupName) {
		return canInput(side, moduleGroupName) || canOutput(side, moduleGroupName);
	}

	public static boolean canInput(IUniCableSide side, String moduleGroupName) {
		return canOperate(side, moduleGroupName, EnumOperationDirection.INPUT);
	}

	public static void setInput(IUniCableSide side, String moduleGroupName, boolean value) {
		setOperation(side, moduleGroupName, EnumOperationDirection.INPUT, value);
		setOperation(UniCableSide.getOpposite(side), moduleGroupName, EnumOperationDirection.INPUT, value);
	}

	public static boolean canOutput(IUniCableSide side, String moduleGroupName) {
		return canOperate(side, moduleGroupName, EnumOperationDirection.OUTPUT);
	}

	public static void setOutput(IUniCableSide side, String moduleGroupName, boolean value) {
		setOperation(side, moduleGroupName, EnumOperationDirection.OUTPUT, value);
	}

	public static void setBoth(IUniCableSide side, String moduleGroupName, boolean value) {
		setInput(side, moduleGroupName, value);
		setOutput(side, moduleGroupName, value);
	}

	private static boolean canOperate(IUniCableSide side, String moduleGroupName,
			EnumOperationDirection operationDirection) {
		String key = buildKeyEnabled(side.getSide(), moduleGroupName, operationDirection);

		if (side.getCable().getData().containsKey(key)) {
			return (boolean) side.getCable().getData().get(key);
		}

		return false;
	}

	private static void setOperation(IUniCableSide side, String moduleGroupName,
			EnumOperationDirection operationDirection, boolean value) {
		String key = buildKeyEnabled(side.getSide(), moduleGroupName, operationDirection);
		side.getCable().getData().put(key, value);
	}

	private static String buildKeyEnabled(Direction side, String moduleGroupName,
			EnumOperationDirection operationDirection) {
		return SETTINGS_KEY + side.getName() + "_" + moduleGroupName + "_" + operationDirection.getText() + "_Enabled";
	}

	private static void processEachSettings(Consumer<String> consumer) {
		for (Direction side : Direction.values()) {
			for (IUniCableModuleGroup moduleGroup : UniCableModuleRegistry.getModuleGroups()) {
				for (EnumOperationDirection operationDirection : EnumOperationDirection.values()) {
					String key = buildKeyEnabled(side, moduleGroup.getGroupName(), operationDirection);
					consumer.accept(key);
				}
			}
		}
	}
}
