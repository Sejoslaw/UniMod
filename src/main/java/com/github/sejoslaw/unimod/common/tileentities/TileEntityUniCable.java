package com.github.sejoslaw.unimod.common.tileentities;

import com.github.sejoslaw.unimod.api.tileentities.IUniCable;
import com.github.sejoslaw.unimod.common.UniModTileEntities;
import com.github.sejoslaw.unimod.common.enums.EnumTransferMode;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class TileEntityUniCable extends BlockEntity implements Tickable, IUniCable {
	private EnumTransferMode mode;

	public TileEntityUniCable() {
		super(UniModTileEntities.UNI_CABLE);
	}

	public void tick() {
	}
}
