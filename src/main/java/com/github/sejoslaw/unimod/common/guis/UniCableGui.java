package com.github.sejoslaw.unimod.common.guis;

import java.util.List;

import com.github.sejoslaw.unimod.api.modules.unicable.UniCableCoreModuleNames;
import com.github.sejoslaw.unimod.common.containers.UniCableContainer;
import com.github.sejoslaw.unimod.common.enums.EnumOperationDirection;
import com.github.sejoslaw.unimod.common.guis.widgets.unicable.DirectionButtonWidget;
import com.github.sejoslaw.unimod.common.guis.widgets.unicable.EnableModuleGroupWidgetButton;
import com.github.sejoslaw.unimod.common.guis.widgets.unicable.GoBackButtonWidget;
import com.github.sejoslaw.unimod.common.guis.widgets.unicable.OpenModuleGroupSettingsButtonWidget;
import com.github.sejoslaw.unimod.common.guis.widgets.unicable.OperationDirectionButtonWidget;
import com.github.sejoslaw.unimod.common.guis.widgets.unicable.SwitchModuleGroupButtonWidget;
import com.github.sejoslaw.unimod.common.texts.ChangeableStringTextComponent;
import com.github.sejoslaw.unimod.core.UniModNames;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.ContainerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.util.math.Direction;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class UniCableGui extends ContainerScreen<UniCableContainer> {
	private UniCableGuiConfig config = new UniCableGuiConfig(this);

	public UniCableGui(UniCableContainer container) {
		super(container, container.getPlayer().inventory, new ChangeableStringTextComponent(container.titleTextPrefix));
		this.containerHeight = 210;
	}

	public void draw(int posX, int posY, float f) {
		super.draw(posX, posY, f);
		this.drawMousoverTooltip(posX, posY);
	}

	public void redraw() {
		this.buttons.clear();
		this.listeners.clear();

		this.onInitialized();
	}

	public ChangeableStringTextComponent getPath() {
		return (ChangeableStringTextComponent) this.name;
	}

	public ItemRenderer getItemRenderer() {
		return this.itemRenderer;
	}

	public int getContainerWidth() {
		return this.containerWidth;
	}

	public int getContainerHeight() {
		return this.containerHeight;
	}

	public List<ButtonWidget> getButtons() {
		return this.buttons;
	}

	public void buildTitle() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.container.stage + ". ");
		builder.append(this.container.titleTextPrefix);

		if (this.container.side != null) {
			builder.append(this.container.side.getName().toUpperCase() + " / ");
		}

		if (this.container.stage > 2) {
			builder.append(this.container.moduleGroupName + " / ");
		}

		if (this.container.operationDirection != null) {
			builder.append(this.container.operationDirection.getText());
		}

		this.getPath().setText(builder.toString());
	}

	protected void onInitialized() {
		this.buildTitle();

		super.onInitialized();

		switch (this.container.stage) {
		case 1:
			this.initializeStage1();
			break;
		case 2:
			this.initializeStage2();
			break;
		case 3:
			this.initializeStage3();
			break;
		case 4:
			this.initializeStage4();
			break;
		}
	}

	protected void drawForeground(int posX, int posY) {
		this.fontRenderer.draw(this.name.getFormattedText(), 5.0F, 6.0F, 4210752);
	}

	protected void drawBackground(float var1, int var2, int var3) {
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.client.getTextureManager().bindTexture(UniModNames.UNI_CABLE_CONTAINER_ID);
		int marginLeft = this.left;
		int marginTop = (this.height - this.containerHeight) / 2;
		this.drawTexturedRect(marginLeft, marginTop, 0, 0, this.containerWidth, this.containerHeight);
	}

	private void initializeStage1() {
		this.config.reload(this);

		this.addButton(
				new DirectionButtonWidget(0, this.config.left + 10, this.config.top + 20, 40, 20, Direction.UP, this));
		this.addButton(new DirectionButtonWidget(1, this.config.left + this.config.centerContainerX - 20,
				this.config.top + 20, 40, 20, Direction.NORTH, this));
		this.addButton(new DirectionButtonWidget(2, this.config.left + this.config.centerContainerX - 20,
				this.config.down - this.config.centerContainerY - 5, 40, 20, Direction.SOUTH, this));
		this.addButton(new DirectionButtonWidget(3, this.config.right - 10, this.config.top + 20 + 40, 40, 20,
				Direction.EAST, this));
		this.addButton(new DirectionButtonWidget(4, this.config.left + 10,
				this.config.top + this.config.centerContainerY - 45, 40, 20, Direction.WEST, this));
		this.addButton(new DirectionButtonWidget(5, this.config.right - 10, this.config.down - 110, 40, 20,
				Direction.DOWN, this));
	}

	private void initializeStage2() {
		this.config.reload(this);

		this.addButton(this.getGoBackButtonWidget());
		this.addButton(new SwitchModuleGroupButtonWidget(1, this.config.left + this.config.centerContainerX - 20,
				this.config.top + 20, 40, 20, this, "/\\", -1));
		this.addButton(new OpenModuleGroupSettingsButtonWidget(2, this.config.left + this.config.centerContainerX - 40,
				this.config.top + this.config.centerContainerY - 45, 80, 20, this,
				UniCableCoreModuleNames.MODULE_GROUP_CABLE_KEY));
		this.addButton(new SwitchModuleGroupButtonWidget(3, this.config.left + this.config.centerContainerX - 20,
				this.config.down - this.config.centerContainerY - 5, 40, 20, this, "\\/", 1));
	}

	private void initializeStage3() {
		this.config.reload(this);

		this.addButton(this.getGoBackButtonWidget());
		this.addButton(new OperationDirectionButtonWidget(1, this.config.left + this.config.centerContainerX - 20,
				this.config.top + 40, 40, 20, this, EnumOperationDirection.INPUT));
		this.addButton(new OperationDirectionButtonWidget(2, this.config.left + this.config.centerContainerX - 20,
				this.config.down - this.config.centerContainerY - 20, 40, 20, this, EnumOperationDirection.OUTPUT));
		this.addButton(this.getEnableModuleGroupWidgetButton());
	}

	private void initializeStage4() {
		this.config.reload(this);

		this.addButton(this.getGoBackButtonWidget());
		this.addButton(this.getEnableModuleGroupWidgetButton());
	}

	private ButtonWidget getGoBackButtonWidget() {
		return new GoBackButtonWidget(0, this.config.left + 10, this.config.top + 20, 40, 20, this);
	}

	private ButtonWidget getEnableModuleGroupWidgetButton() {
		return new EnableModuleGroupWidgetButton(3, this.config.right - 10, this.config.top + 20, 45, 20, this,
				this.container.getModuleGroupEnabled());
	}
}
