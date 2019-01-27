package com.github.sejoslaw.unimod.core;

import net.minecraft.util.Identifier;

/**
 * Holds different names for objects used in the mod.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModNames {
	public static final String TRANSFER_NODE_NAME = "transfer_node";
	public static final Identifier TRANSFER_NODE_ID = new Identifier(UniModCore.MOD_ID, TRANSFER_NODE_NAME);

	public static final String UNIWRENCH_NAME = "uniwrench";
	public static final Identifier UNIWRENCH_ID = new Identifier(UniModCore.MOD_ID, UNIWRENCH_NAME);

	public static final String ITEMGROUP_NAME = "unimod_group";
	public static final Identifier ITEMGROUP_ID = new Identifier(UniModCore.MOD_ID, ITEMGROUP_NAME);

	private UniModNames() {
	}
}
