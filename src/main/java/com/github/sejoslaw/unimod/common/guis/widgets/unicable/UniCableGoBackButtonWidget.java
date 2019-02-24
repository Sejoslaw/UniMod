package com.github.sejoslaw.unimod.common.guis.widgets.unicable;

import com.github.sejoslaw.unimod.api.modules.unicable.UniCableCoreModuleNames;
import com.github.sejoslaw.unimod.common.containers.UniCableContainer;
import com.github.sejoslaw.unimod.common.guis.UniCableGui;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableGoBackButtonWidget extends AbstractUniCableButtonWidget {
	public UniCableGoBackButtonWidget(int id, int posX, int posY, int width, int height, UniCableGui gui) {
		super(id, posX, posY, width, height, gui, "Back");
	}

	public void onClick() {
		UniCableContainer container = this.getGui().getContainer();

		switch (container.stage) {
		case 2:
			container.side = null;
		case 3:
			container.moduleGroupName = UniCableCoreModuleNames.MODULE_GROUP_CORE_KEY;
		case 4:
			container.operationDirection = null;
		}

		container.stage--;

		this.gui.redraw();
	}
}
