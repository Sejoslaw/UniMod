package com.github.sejoslaw.unimod.common.guis;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableGuiConfig {
	private UniCableGui gui;

	public int centerX;
	public int centerY;
	public int centerContainerX;
	public int centerContainerY;
	public int left;
	public int top;
	public int right;
	public int down;

	public UniCableGuiConfig(UniCableGui gui) {
		this.reload(gui);
	}

	public void reload(UniCableGui gui) {
		this.gui = gui;

		this.centerX = this.gui.width / 2;
		this.centerY = this.gui.height / 2;
		this.centerContainerX = this.gui.getContainerWidth() / 2;
		this.centerContainerY = this.gui.getContainerHeight() / 2;
		this.left = centerX - centerContainerX;
		this.top = centerY - centerContainerY;
		this.right = centerX + centerContainerX - 40;
		this.down = centerY + centerContainerY;
	}
}
