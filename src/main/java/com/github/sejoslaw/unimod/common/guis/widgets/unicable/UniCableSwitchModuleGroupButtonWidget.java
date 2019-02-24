package com.github.sejoslaw.unimod.common.guis.widgets.unicable;

import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.common.containers.UniCableContainer;
import com.github.sejoslaw.unimod.common.guis.UniCableGui;

import net.minecraft.client.gui.widget.ButtonWidget;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableSwitchModuleGroupButtonWidget extends AbstractUniCableButtonWidget {
	private int delta;

	public UniCableSwitchModuleGroupButtonWidget(int id, int posX, int posY, int width, int height, UniCableGui gui,
			String text, int delta) {
		super(id, posX, posY, width, height, gui, text);
		this.delta = delta;
	}

	public void onClick() {
		UniCableContainer container = this.getGui().getContainer();

		int moduleGroupId = ModuleRegistry.getModuleGroupId(container.moduleGroupName);
		moduleGroupId += this.delta;

		if (moduleGroupId >= ModuleRegistry.UNI_CABLE_MODULES.size()) {
			moduleGroupId = 0;
		}

		if (moduleGroupId < 0) {
			moduleGroupId = ModuleRegistry.UNI_CABLE_MODULES.size() - 1;
		}

		// Set new text for the button.
		ButtonWidget moduleGroupNameButton = this.gui.getButtons().get(2);
		String text = ModuleRegistry.UNI_CABLE_MODULES.keySet().toArray()[moduleGroupId].toString();
		moduleGroupNameButton.method_2060(text);
		container.moduleGroupName = text;
	}
}
