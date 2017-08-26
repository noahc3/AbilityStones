package com.noahc3.abilitystones.block.advancedAbilityInfuser;

import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.item.ModItems;
import com.noahc3.abilitystones.recipe.AdvancedInfuserCraftingManager;
import jline.internal.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import com.noahc3.abilitystones.recipe.ItemGroup;
import com.noahc3.abilitystones.recipe.Result;

import java.util.ArrayList;

public class TileEntityAdvancedAbilityInfuser extends TileEntity implements ITickable {

    private int dustCost = 0;
    public ArrayList<ItemGroup> currentItemsUsed = new ArrayList<>();

    // define inventory + # of slots
    public ItemStackHandler inventory = new ItemStackHandler(7);

    public int getDustCost() {
        return dustCost;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }
    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }

    public ArrayList<ItemGroup> getCurrentItemsUsed() {
        return currentItemsUsed;
    }

    @Override
    public void update() {

        ItemStack slot0 = inventory.getStackInSlot(0);
        ItemStack slot1 = inventory.getStackInSlot(1);
        ItemStack slot2 = inventory.getStackInSlot(2);
        ItemStack slot3 = inventory.getStackInSlot(3);
        ItemStack slot4 = inventory.getStackInSlot(4);
        ItemStack slot5 = inventory.getStackInSlot(5);
        ItemStack slot6 = inventory.getStackInSlot(6);

        ItemStack result = ItemStack.EMPTY;

        if(ItemStack.areItemsEqual(new ItemStack(ModItems.mundaneStone), slot5)) {

            if(!(slot2 == null)) {

                Result returned = AdvancedInfuserCraftingManager.getResult(1, slot2.getCount(), this, slot0, slot1, slot3, slot4);

                result = returned.stack;
                currentItemsUsed = returned.itemsUsed;
                dustCost = returned.dustCost;

            }

        }

        inventory.setStackInSlot(6, result);



    }
}
