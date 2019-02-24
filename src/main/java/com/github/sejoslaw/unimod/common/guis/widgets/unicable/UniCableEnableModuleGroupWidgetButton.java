package com.github.sejoslaw.unimod.common.guis.widgets.unicable;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCableSide;
import com.github.sejoslaw.unimod.common.enums.EnumOperationDirection;
import com.github.sejoslaw.unimod.common.guis.UniCableGui;
import com.github.sejoslaw.unimod.common.modules.unicable.core.UniCableSettingsModule;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableEnableModuleGroupWidgetButton extends AbstractUniCableButtonWidget {
	public UniCableEnableModuleGroupWidgetButton(int id, int posX, int posY, int width, int height, UniCableGui gui,
			String text) {
		super(id, posX, posY, width, height, gui, text);
	}

	public void onClick() {
		boolean enabled = false;
		boolean isInput = false;
		boolean isOutput = false;

		IUniCable cable = this.gui.getContainer().getCable();
		IUniCableSide side = cable.getCableSide(this.gui.getContainer().side);
		String moduleGroupName = this.gui.getContainer().moduleGroupName;

		if (this.gui.getContainer().operationDirection == null) {
			enabled = !UniCableSettingsModule.isConnected(side, moduleGroupName);
			isInput = true;
			isOutput = true;
		} else if (this.gui.getContainer().operationDirection == EnumOperationDirection.INPUT) {
			enabled = !UniCableSettingsModule.canInput(side, moduleGroupName);
			isInput = true;
		} else if (this.gui.getContainer().operationDirection == EnumOperationDirection.OUTPUT) {
			enabled = !UniCableSettingsModule.canOutput(side, moduleGroupName);
			isOutput = true;
		}

		if (isInput) {
			UniCableSettingsModule.setInput(side, moduleGroupName, enabled);
		}

		if (isOutput) {
			UniCableSettingsModule.setOutput(side, moduleGroupName, enabled);
		}

		side.updateConnections();

		this.gui.redraw();
	}
}
