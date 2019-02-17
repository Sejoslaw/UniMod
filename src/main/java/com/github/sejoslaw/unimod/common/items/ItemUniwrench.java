package com.github.sejoslaw.unimod.common.items;

import com.github.sejoslaw.unimod.api.items.IUniWrench;
import com.github.sejoslaw.unimod.api.registries.ModuleRegistry;
import com.github.sejoslaw.unimod.common.UniModLogger;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.Style;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class ItemUniwrench extends Item implements IUniWrench {
	public static final String CURRENT_MODULE_GROUP_NAME_KEY = "UniMod_CurrentModuleGroupName";

	public ItemUniwrench(Settings settings) {
		super(settings);
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);
		this.setNextModuleGroupName(world, stack);
		player.addChatMessage(stack.getDisplayName(), true);
		return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, stack);
	}

	public TextComponent getTranslatedNameTrimmed(ItemStack stack) {
		TextComponent currentName = super.getTranslatedNameTrimmed(stack);
		String itemName = currentName.getFormattedText();
		String moduleGroupName = this.getModuleGroup(stack);

		if (!moduleGroupName.equals("")) {
			moduleGroupName = " [" + moduleGroupName + "]";
			Style modelGroupStyle = new Style().setColor(TextFormat.GOLD);
			itemName += UniModLogger.styleText(moduleGroupName, modelGroupStyle).getFormattedText();
		}

		return new StringTextComponent(itemName);
	}

	public String getModuleGroup(ItemStack stack) {
		CompoundTag tag = stack.getTag();

		if (tag == null || !tag.containsKey(CURRENT_MODULE_GROUP_NAME_KEY)) {
			return "";
		}

		int currentGroupId = tag.getInt(CURRENT_MODULE_GROUP_NAME_KEY);

		if (currentGroupId == ModuleRegistry.UNI_CABLE_MODULES.size()) {
			return "";
		}

		return ModuleRegistry.UNI_CABLE_MODULES.keySet().toArray()[currentGroupId].toString();
	}

	private void setNextModuleGroupName(World world, ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		int currentGroupId = Integer.MAX_VALUE / 2; // Some random high number

		if (tag.containsKey(CURRENT_MODULE_GROUP_NAME_KEY)) {
			currentGroupId = tag.getInt(CURRENT_MODULE_GROUP_NAME_KEY);
		}

		currentGroupId++;

		if (currentGroupId > ModuleRegistry.UNI_CABLE_MODULES.size()) {
			currentGroupId = 1; // Skip "Core" modules
		}

		tag.putInt(CURRENT_MODULE_GROUP_NAME_KEY, currentGroupId);
	}
}