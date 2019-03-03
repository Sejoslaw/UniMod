package com.github.sejoslaw.unimod.common.guis.widgets.unicable;

import com.github.sejoslaw.unimod.api.tileentities.unicable.IUniCable;
import com.github.sejoslaw.unimod.common.guis.UniCableGui;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class DirectionButtonWidget extends AbstractUniCableButtonWidget {
	private Direction side;

	public DirectionButtonWidget(int id, int posX, int posY, int width, int height, Direction side,
			UniCableGui gui) {
		super(id, posX, posY, width, height, gui, side.getName().toUpperCase());
		this.side = side;
	}

	public void onClick() {
		this.gui.getContainer().stage = 2;
		this.gui.getContainer().side = this.side;

		this.gui.redraw();
	}

	public void draw(int mousePosX, int mousePosY, float delta) {
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		minecraftClient.getTextureManager().bindTexture(WIDGET_TEX);

		GlStateManager.color4f(1.0F, 1.0F, 1.0F, this.field_17766);
		int textureId = this.getTextureId(this.isHovered());

		GlStateManager.enableBlend();
		GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

		this.drawTexturedRect(this.x, this.y, 0, 46 + textureId * 20, this.width / 2, this.height);
		this.drawTexturedRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + textureId * 20,
				this.width / 2, this.height);
		this.drawBackground(minecraftClient, mousePosX, mousePosY);

		int textColor = 14737632;

		if (this.isHovered()) {
			IUniCable cable = this.gui.getContainer().getCable();
			World world = cable.getWorld();
			BlockPos neighbourPos = cable.getPos().offset(this.side);
			Block block = world.getBlockState(neighbourPos).getBlock();

			if (block != Blocks.AIR) {
				GuiLighting.enableForItems();
				this.gui.getItemRenderer().renderGuiItem(new ItemStack(block), (this.x + this.width / 4) + 2,
						this.y + 2);
				return;
			} else {
				textColor = 16777120;
			}
		}

		this.drawStringCentered(minecraftClient.fontRenderer, side.getName().toUpperCase(), this.x + this.width / 2,
				this.y + (this.height - 8) / 2, textColor | MathHelper.ceil(this.field_17766 * 255.0F) << 24);
	}
}
