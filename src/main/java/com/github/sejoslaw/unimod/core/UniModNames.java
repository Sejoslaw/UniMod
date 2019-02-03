package com.github.sejoslaw.unimod.core;

import net.minecraft.util.Identifier;

/**
 * Holds different names for objects used in the mod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModNames {
	public static final String UNI_CABLE_NAME = "unicable";
	public static final Identifier UNI_CABLE_ID = new Identifier(UniModCore.MOD_ID, UNI_CABLE_NAME);

	public static final String UNI_WRENCH_NAME = "uniwrench";
	public static final Identifier UNI_WRENCH_ID = new Identifier(UniModCore.MOD_ID, UNI_WRENCH_NAME);

	public static final String ITEM_GROUP_NAME = "unimod_group";
	public static final Identifier ITEM_GROUP_ID = new Identifier(UniModCore.MOD_ID, ITEM_GROUP_NAME);

	private UniModNames() {
	}
}
