package com.github.sejoslaw.unimod.common;

import com.github.sejoslaw.unimod.core.UniModCore;

import net.minecraft.text.StringTextComponent;
import net.minecraft.text.Style;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.text.TextFormatter;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class UniModLogger {
	private static final String MOD_PREFIX = "[" + UniModCore.NAME + "]";

	private UniModLogger() {
	}

	public static TextComponent info(String message) {
		return formatMessage("[INFO] ", message, new Style().setColor(TextFormat.GREEN));
	}

	private static TextComponent formatMessage(String messagePrefix, String message, Style prefixStyle) {
		TextComponent messageTypePrefixComp = getTextTypePrefix(messagePrefix, prefixStyle);
		message = messageTypePrefixComp.getFormattedText() + message;
		TextComponent messageComp = new StringTextComponent(message);
		return messageComp;
	}

	private static TextComponent getTextTypePrefix(String message, Style style) {
		TextComponent modPrefixComp = getModNamePrefix();
		TextComponent messageTypePrefixComp = styleText(message, style);
		return new StringTextComponent(modPrefixComp.getFormattedText() + messageTypePrefixComp.getFormattedText());
	}

	private static TextComponent getModNamePrefix() {
		return styleText(MOD_PREFIX + " ", new Style().setColor(TextFormat.GOLD));
	}

	private static TextComponent styleText(String message, Style style) {
		TextComponent prefixComp = new StringTextComponent(message);
		return TextFormatter.addStyle(prefixComp, style);
	}
}
