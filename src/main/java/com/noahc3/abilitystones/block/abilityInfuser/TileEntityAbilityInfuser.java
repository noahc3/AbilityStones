package com.noahc3.abilitystones.block.abilityInfuser;

import com.noahc3.abilitystones.recipe.InfuserCraftingManager;
import jline.internal.Nullable;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TileEntityAbilityInfuser extends TileEntity implements ITickable {


    // define inventory + # of slots
    public ItemStackHandler inventory = new ItemStackHandler(4);

    @Override
    @MethodsReturnNonnullByDefault
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
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }


    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }


    @Override
    public void update() {

        ItemStack slot0 = inventory.getStackInSlot(0);
        ItemStack slot1 = inventory.getStackInSlot(1);
        ItemStack slot2 = inventory.getStackInSlot(2);

        ItemStack result = InfuserCraftingManager.getResult(0, slot0, slot1, slot2);

        inventory.setStackInSlot(3, result);

    }

}
