package com.noahc3.abilitystones.guisupport.slot;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class BasicSlot extends SlotItemHandler {

    private TileEntity tileEntity;

    public BasicSlot(IItemHandler inventory, int index, int xPosition, int yPosition, TileEntity tileEntity) {
        super(inventory, index, xPosition, yPosition);
        this.tileEntity = tileEntity;
    }

    @Override
    public void onSlotChanged() {
        tileEntity.markDirty();
    }

    @Override
    public boolean isItemValid( @Nonnull final ItemStack par1ItemStack ) {
        return true;
    }
}
