package com.github.sejoslaw.unimod.common.guis.widgets.unicable;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModuleGroup;
import com.github.sejoslaw.unimod.api.registries.UniCableModuleRegistry;
import com.github.sejoslaw.unimod.common.containers.UniCableContainer;
import com.github.sejoslaw.unimod.common.guis.UniCableGui;

import net.minecraft.client.gui.widget.ButtonWidget;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableSwitchModuleGroupButtonWidget extends AbstractUniCableButtonWidget {
	private int position = 0;
	private int delta;

	public UniCableSwitchModuleGroupButtonWidget(int id, int posX, int posY, int width, int height, UniCableGui gui,
			String text, int delta) {
		super(id, posX, posY, width, height, gui, text);
		this.delta = delta;
	}

	public void onClick() {
		UniCableContainer container = this.getGui().getContainer();

		this.position += this.delta;

		if (this.position >= UniCableModuleRegistry.UNI_CABLE_MODULES.size()) {
			this.position = 0;
		}

		if (this.position < 0) {
			this.position = UniCableModuleRegistry.UNI_CABLE_MODULES.size() - 1;
		}

		// Set new text for the button.
		ButtonWidget moduleGroupNameButton = this.gui.getButtons().get(2);
		String text = ((IUniCableModuleGroup) UniCableModuleRegistry.UNI_CABLE_MODULES.toArray()[this.position])
				.getGroupName();
		moduleGroupNameButton.method_2060(text);
		container.moduleGroupName = text;
	}
}
