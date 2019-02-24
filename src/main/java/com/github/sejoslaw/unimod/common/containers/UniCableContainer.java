package com.github.sejoslaw.unimod.common.containers;

import com.github.sejoslaw.unimod.api.items.IUniWrench;
import com.github.sejoslaw.unimod.api.modules.unicable.UniCableCoreModuleNames;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.enums.EnumModuleGroupState;
import com.github.sejoslaw.unimod.common.enums.EnumOperationDirection;
import com.github.sejoslaw.unimod.common.modules.unicable.core.UniCableSettingsModule;
import com.github.sejoslaw.unimod.common.utils.UniCableUtils;

import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableContainer extends Container {
	private IUniCable cable;
	private PlayerEntity player;

	public final String titleTextPrefix = "/ ";

	/**
	 * Configuration stage on which Player currently is. </br>
	 * Stage 1: Choose side / direction.</br>
	 * Stage 2: Choose module group by name.</br>
	 * Stage 3: Choose Input or Output.</br>
	 * Stage 4: Configure specific side.</br>
	 */
	public int stage = 1;
	/**
	 * Side which is configured at stage 1.
	 */
	public Direction side = null;
	/**
	 * Id of the selected module group at stage 2;
	 */
	public String moduleGroupName = UniCableCoreModuleNames.MODULE_GROUP_CORE_KEY;
	/**
	 * Direciton which is currently being set at stage 3.
	 */
	public EnumOperationDirection operationDirection = null;

	public UniCableContainer(int syncId, BlockPos pos, PlayerEntity player) {
		super(null, syncId);
		this.cable = UniCableUtils.getCable(player.world, pos);
		this.player = player;

		this.addPlayerSlots();
	}

	public IUniCable getCable() {
		return this.cable;
	}

	public PlayerEntity getPlayer() {
		return this.player;
	}

	public boolean canUse(PlayerEntity player) {
		return player.getMainHandStack().getItem() instanceof IUniWrench
				|| player.getOffHandStack().getItem() instanceof IUniWrench;
	}

	public String getModuleGroupEnabled() {
		IUniCableSide side = this.cable.getCableSide(this.side);

		if (this.operationDirection == null && UniCableSettingsModule.isConnected(side, this.moduleGroupName)) {
			return EnumModuleGroupState.ENABLED.getStateName();
		} else if (this.operationDirection == EnumOperationDirection.INPUT
				&& UniCableSettingsModule.canInput(side, this.moduleGroupName)) {
			return EnumModuleGroupState.ENABLED.getStateName();
		} else if (this.operationDirection == EnumOperationDirection.OUTPUT
				&& UniCableSettingsModule.canOutput(side, this.moduleGroupName)) {
			return EnumModuleGroupState.ENABLED.getStateName();
		}

		return EnumModuleGroupState.DISABLED.getStateName();
	}

	private void addPlayerSlots() {
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlot(new Slot(this.player.inventory, x + y * 9 + 9, 8 + x * 18, 128 + y * 18));
			}
		}

		for (int x = 0; x < 9; ++x) {
			this.addSlot(new Slot(this.player.inventory, x, 8 + x * 18, 186));
		}
	}
}
