package com.github.sejoslaw.unimod.common.guis.widgets.unicable;

import com.github.sejoslaw.unimod.common.guis.UniCableGui;

import net.minecraft.client.gui.widget.ButtonWidget;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public abstract class AbstractUniCableButtonWidget extends ButtonWidget {
	protected UniCableGui gui;

	public AbstractUniCableButtonWidget(int id, int posX, int posY, int width, int height, UniCableGui gui,
			String text) {
		super(id, posX, posY, width, height, text);
		this.gui = gui;
	}

	public UniCableGui getGui() {
		return this.gui;
	}

	public void onPressed(double posX, double posY) {
		super.onPressed(posX, posY);
		this.onClick();
	}

	public String getText() {
		return this.method_18329();
	}

	public abstract void onClick();
}
