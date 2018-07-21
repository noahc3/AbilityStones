package com.noahc3.abilitystones.guisupport.slot;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SingleSlot extends SlotItemHandler {

    private TileEntity tileEntity;

    public SingleSlot(IItemHandler inventory, int index, int xPosition, int yPosition, TileEntity tileEntity) {
        super(inventory, index, xPosition, yPosition);
        this.tileEntity = tileEntity;
    }

    @Override
    public void onSlotChanged() {
        tileEntity.markDirty();
    }

    @Override
    public boolean isItemValid( @Nonnull final ItemStack stack ) {
        if (stack.getCount() == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

}
