package com.noahc3.abilitystones.block.advancedAbilityInfuser;


import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.block.ModBlocks;
import com.noahc3.abilitystones.item.ModItems;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiAdvancedAbilityInfuser extends GuiContainer {

    private InventoryPlayer playerInv;
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(AbilityStones.modID, "textures/gui/advanced_ability_infuser.png");
    private TileEntityAdvancedAbilityInfuser te;
    private int colour;
    private int dustInSlot;
    public int dustCost = 0;

    public GuiAdvancedAbilityInfuser(Container container, InventoryPlayer playerInv, TileEntityAdvancedAbilityInfuser te) {
        super(container);
        this.playerInv = playerInv;
        this.xSize = 176;
        this.ySize = 214;
        this.te = te;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    public boolean areItemSlotsEmpty(TileEntityAdvancedAbilityInfuser te) {

        if (te.inventory.getStackInSlot(0).isEmpty() && te.inventory.getStackInSlot(1).isEmpty() && te.inventory.getStackInSlot(3).isEmpty() && te.inventory.getStackInSlot(4).isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1,1,1,1);
        mc.getTextureManager().bindTexture(BG_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format(ModBlocks.advancedAbilityInfuser.getUnlocalizedName() + ".name");
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);

        if (areItemSlotsEmpty(this.te)) {
            dustCost = 0;
        } else {
            dustCost = te.getDustCost();
        }


        String dustCostString = "Dust Cost: " + dustCost;

        if (te.inventory.getStackInSlot(2).isEmpty()) {
            dustInSlot = 0;
        } else {
            if (te.inventory.getStackInSlot(2).getItem() == ModItems.magicalDust) {
                dustInSlot = te.inventory.getStackInSlot(2).getCount();
            } else {
                dustInSlot = 0;
            }
        }
        if (dustCost == 0) {
            colour = 0x404040;
        } else if (dustInSlot < dustCost) {
            colour = 0xb22525;
        } else {
            colour = 0x067c00;
        }

        fontRenderer.drawString(dustCostString, 7, 118, colour);


    }

}
