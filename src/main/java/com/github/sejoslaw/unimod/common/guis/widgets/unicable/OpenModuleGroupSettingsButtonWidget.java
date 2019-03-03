package com.github.sejoslaw.unimod.common.guis.widgets.unicable;

import com.github.sejoslaw.unimod.common.guis.UniCableGui;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class OpenModuleGroupSettingsButtonWidget extends AbstractUniCableButtonWidget {
	public OpenModuleGroupSettingsButtonWidget(int id, int posX, int posY, int width, int height, UniCableGui gui,
			String text) {
		super(id, posX, posY, width, height, gui, text);
	}

	public void onClick() {
		this.gui.getContainer().moduleGroupName = this.getText();
		this.gui.getContainer().stage++;
		this.gui.redraw();
	}
}
