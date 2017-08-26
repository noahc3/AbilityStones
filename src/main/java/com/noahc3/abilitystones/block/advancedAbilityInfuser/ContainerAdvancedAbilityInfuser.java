package com.noahc3.abilitystones.block.advancedAbilityInfuser;

import com.noahc3.abilitystones.guisupport.slot.BasicSlot;
import com.noahc3.abilitystones.guisupport.slot.OutputSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ContainerAdvancedAbilityInfuser extends Container {

    public ContainerAdvancedAbilityInfuser(InventoryPlayer playerInv, final TileEntityAdvancedAbilityInfuser infuser) {

        IItemHandler inventory = infuser.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        ItemStackHandler inv = infuser.inventory;

        // add slots to gui interface, incl. x/y positions
        addSlotToContainer(new BasicSlot(inventory, 0, 17, 24, infuser));
        addSlotToContainer(new BasicSlot(inventory, 1, 46, 30, infuser));
        addSlotToContainer(new BasicSlot(inventory, 2, 81, 24, infuser));
        addSlotToContainer(new BasicSlot(inventory, 3, 114, 30, infuser));
        addSlotToContainer(new BasicSlot(inventory, 4, 143, 24, infuser));
        addSlotToContainer(new BasicSlot(inventory, 5, 81, 62, infuser));
        addSlotToContainer(new OutputSlot(inventory, 6, 81, 108, infuser, inv));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 132 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 190));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = null;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.length;

            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

}


