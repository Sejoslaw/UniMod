package com.github.sejoslaw.unimod.common.guis.widgets.unicable;

import java.util.Set;

import com.github.sejoslaw.unimod.api.modules.unicable.IUniCableModuleGroup;
import com.github.sejoslaw.unimod.api.registries.UniCableModuleRegistry;
import com.github.sejoslaw.unimod.common.containers.UniCableContainer;
import com.github.sejoslaw.unimod.common.guis.UniCableGui;

import net.minecraft.client.gui.widget.ButtonWidget;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class SwitchModuleGroupButtonWidget extends AbstractUniCableButtonWidget {
	private int position = 1;
	private int delta;

	public SwitchModuleGroupButtonWidget(int id, int posX, int posY, int width, int height, UniCableGui gui,
			String text, int delta) {
		super(id, posX, posY, width, height, gui, text);
		this.delta = delta;
	}

	public void onClick() {
		UniCableContainer container = this.getGui().getContainer();

		Set<IUniCableModuleGroup> moduleGroups = UniCableModuleRegistry.getModuleGroups();

		this.position += this.delta;

		if (this.position >= moduleGroups.size()) {
			this.position = 0;
		} else if (this.position < 0) {
			this.position = moduleGroups.size() - 1;
		}

		// Set new text for the button.
		ButtonWidget moduleGroupNameButton = this.gui.getButtons().get(2);
		String text = ((IUniCableModuleGroup) moduleGroups.toArray()[this.position]).getGroupName();
		moduleGroupNameButton.method_2060(text);
		container.moduleGroupName = text;
	}
}
