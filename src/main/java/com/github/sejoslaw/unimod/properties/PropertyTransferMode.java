package com.github.sejoslaw.unimod.properties;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.github.sejoslaw.unimod.enums.EnumTransferMode;
import com.google.common.collect.Lists;

import net.minecraft.state.property.EnumProperty;

/**
 * Transfer Mode Property.
 * 
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class PropertyTransferMode extends EnumProperty<EnumTransferMode> {
	protected PropertyTransferMode(String name, Collection<EnumTransferMode> values) {
		super(name, EnumTransferMode.class, values);
	}

	public static PropertyTransferMode create(String name, Predicate<EnumTransferMode> predicate) {
		return create(name, (Collection<EnumTransferMode>) Arrays.stream(EnumTransferMode.values()).filter(predicate)
				.collect(Collectors.toList()));
	}

	public static PropertyTransferMode create(String name, EnumTransferMode... values) {
		return create(name, (Collection<EnumTransferMode>) Lists.newArrayList(values));
	}

	public static PropertyTransferMode create(String name, Collection<EnumTransferMode> values) {
		return new PropertyTransferMode(name, values);
	}
}
