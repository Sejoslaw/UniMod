package com.github.sejoslaw.unimod.common.texts;

import net.minecraft.text.AbstractTextComponent;
import net.minecraft.text.TextComponent;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class ChangeableStringTextComponent extends AbstractTextComponent {
	private String text;

	public ChangeableStringTextComponent(String text) {
		this.text = text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public TextComponent copyShallow() {
		return new ChangeableStringTextComponent(this.text);
	}
}
