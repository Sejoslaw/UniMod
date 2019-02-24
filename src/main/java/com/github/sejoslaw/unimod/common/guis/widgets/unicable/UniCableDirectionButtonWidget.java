package com.github.sejoslaw.unimod.common.guis.widgets.unicable;

import com.github.sejoslaw.unimod.common.guis.UniCableGui;

import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableDirectionButtonWidget extends AbstractUniCableButtonWidget {
	private Direction side;

	public UniCableDirectionButtonWidget(int id, int posX, int posY, int width, int height, Direction side,
			UniCableGui gui) {
		super(id, posX, posY, width, height, gui, side.getName().toUpperCase());
		this.side = side;
	}

	public void onClick() {
		this.gui.getContainer().stage = 2;
		this.gui.getContainer().side = this.side;

		this.gui.redraw();
	}
}
