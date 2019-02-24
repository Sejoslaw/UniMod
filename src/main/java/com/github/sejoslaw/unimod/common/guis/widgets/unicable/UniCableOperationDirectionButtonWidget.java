package com.github.sejoslaw.unimod.common.guis.widgets.unicable;

import com.github.sejoslaw.unimod.common.enums.EnumOperationDirection;
import com.github.sejoslaw.unimod.common.guis.UniCableGui;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableOperationDirectionButtonWidget extends AbstractUniCableButtonWidget {
	private EnumOperationDirection operationDirection;

	public UniCableOperationDirectionButtonWidget(int id, int posX, int posY, int width, int height, UniCableGui gui,
			EnumOperationDirection operationDirection) {
		super(id, posX, posY, width, height, gui, operationDirection.getText());
		this.operationDirection = operationDirection;
	}

	public void onClick() {
		this.gui.getContainer().stage++;
		this.gui.getContainer().operationDirection = this.operationDirection;

		this.gui.redraw();
	}
}
