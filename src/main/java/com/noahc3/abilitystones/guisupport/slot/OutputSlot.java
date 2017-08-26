package com.noahc3.abilitystones.guisupport.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.block.ModBlocks;
import com.noahc3.abilitystones.recipe.AdvancedInfuserCraftingManager;
import com.noahc3.abilitystones.recipe.InfuserCraftingManager;
import com.noahc3.abilitystones.recipe.ItemGroup;

import java.util.ArrayList;

public class OutputSlot extends SlotItemHandler {

    private TileEntity tileEntity;
    private ItemStackHandler inv;

    public OutputSlot(IItemHandler inventory, int index, int xPosition, int yPosition, TileEntity tileEntity, ItemStackHandler inv) {
        super(inventory, index, xPosition, yPosition);
        this.tileEntity = tileEntity;
        this.inv = inv;
    }

    @Override
    public void onSlotChanged() {
        tileEntity.markDirty();
    }

    @Override
    public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack){

        this.onSlotChanged();

        if(tileEntity.getBlockType() == ModBlocks.abilityInfuser) {

            ItemStack newSlot0 = inv.getStackInSlot(0);
            ItemStack newSlot1 = inv.getStackInSlot(1);
            ItemStack newSlot2 = inv.getStackInSlot(2);

            int slot0Cost = InfuserCraftingManager.getItemCost(stack, 0);
            int slot1Cost = InfuserCraftingManager.getItemCost(stack, 1);
            int slot2Cost = InfuserCraftingManager.getItemCost(stack, 2);

            if(newSlot0.stackSize <= slot0Cost) {
                newSlot0 = AbilityStones.blankItemStack;
            } else {
                newSlot0.stackSize = newSlot0.stackSize - slot0Cost;
            }
            if(newSlot1.stackSize <= slot1Cost) {
                newSlot1 = AbilityStones.blankItemStack;
            } else {
                newSlot1.stackSize = newSlot1.stackSize - slot1Cost;
            }

            if(newSlot2.stackSize <= slot2Cost) {
                newSlot2 = AbilityStones.blankItemStack;
            } else {
                newSlot2.stackSize = newSlot2.stackSize - slot2Cost;
            }

            inv.setStackInSlot(0, newSlot0);
            inv.setStackInSlot(1, newSlot1);
            inv.setStackInSlot(2, newSlot2);


        }

        if(tileEntity.getBlockType() == ModBlocks.advancedAbilityInfuser || tileEntity.getBlockType() == ModBlocks.advancedAbilityInfuserTop) {

            ItemStack newSlot0 = inv.getStackInSlot(0);
            ItemStack newSlot1 = inv.getStackInSlot(1);
            ItemStack newSlot2 = inv.getStackInSlot(2);
            ItemStack newSlot3 = inv.getStackInSlot(3);
            ItemStack newSlot4 = inv.getStackInSlot(4);
            ItemStack newSlot5 = inv.getStackInSlot(5);
            ItemStack newSlot6 = stack;

            ArrayList<String> effects = new ArrayList<>();
            ArrayList<ItemGroup> itemsUsed = new ArrayList<>();

            int dustCost = 0;

            for(int i=0; i < 4; i++) {

                if(i==0){if(!(newSlot6.getTagCompound().getString("eff1").isEmpty())){effects.add(newSlot6.getTagCompound().getString("eff1"));}}
                if(i==1){if(!(newSlot6.getTagCompound().getString("eff2").isEmpty())){effects.add(newSlot6.getTagCompound().getString("eff2"));}}
                if(i==2){if(!(newSlot6.getTagCompound().getString("eff3").isEmpty())){effects.add(newSlot6.getTagCompound().getString("eff3"));}}
                if(i==3){if(!(newSlot6.getTagCompound().getString("eff4").isEmpty())){effects.add(newSlot6.getTagCompound().getString("eff4"));}}

            }

            for(int i=0; i < effects.size(); i++) {

                for(int n=0; n < AdvancedInfuserCraftingManager.recipes.size(); n++) {

                    if(AdvancedInfuserCraftingManager.recipes.get(n).output == effects.get(i)) {

                        for(int m=0; m < AdvancedInfuserCraftingManager.recipes.get(n).recipe.size(); m++) {

                            itemsUsed.add(AdvancedInfuserCraftingManager.recipes.get(n).recipe.get(m));

                        }

                        dustCost = dustCost + AdvancedInfuserCraftingManager.recipes.get(n).dustCost;

                    }

                }

            }

            dustCost = AdvancedInfuserCraftingManager.curveDustCost(effects, dustCost);

            if(newSlot2.stackSize == dustCost) {
                newSlot2 = AbilityStones.blankItemStack;
            } else {
                newSlot2.stackSize = newSlot2.stackSize - dustCost;
            }

            if(newSlot5.stackSize == 1) {
                newSlot5 = AbilityStones.blankItemStack;
            } else {
                newSlot5.stackSize--;
            }

            for(int i=0; i < itemsUsed.size(); i++) {

                for(int n=0; n < 4; n++) {

                    if(n==0) {
                        if(!(newSlot0 == AbilityStones.blankItemStack)) {
                            if (itemsUsed.get(i).item.getItem() == newSlot0.getItem()) {
                                if (itemsUsed.get(i).item.hasTagCompound() && newSlot0.hasTagCompound()) {
                                    if (itemsUsed.get(i).item.getTagCompound().equals(newSlot0.getTagCompound())) {
                                        if (itemsUsed.get(i).count == newSlot0.stackSize) {
                                            newSlot0 = AbilityStones.blankItemStack;
                                        } else {
                                            newSlot0.stackSize = newSlot0.stackSize - itemsUsed.get(i).count;
                                        }
                                    }
                                } else if (!(itemsUsed.get(i).item.hasTagCompound() && newSlot0.hasTagCompound())){
                                    if (itemsUsed.get(i).count == newSlot0.stackSize) {
                                        newSlot0 = AbilityStones.blankItemStack;
                                    } else {
                                        newSlot0.stackSize = newSlot0.stackSize - itemsUsed.get(i).count;
                                    }
                                }
                            }
                        }
                    }
                    if(n==1) {
                        if(!(newSlot1 == AbilityStones.blankItemStack)) {
                            if (itemsUsed.get(i).item.getItem().equals(newSlot1.getItem())) {
                                if (itemsUsed.get(i).item.hasTagCompound() && newSlot1.hasTagCompound()) {
                                    if (itemsUsed.get(i).item.getTagCompound().equals(newSlot1.getTagCompound())) {
                                        if (itemsUsed.get(i).count == newSlot1.stackSize) {
                                            newSlot1 = AbilityStones.blankItemStack;
                                        } else {
                                            newSlot1.stackSize = newSlot1.stackSize - itemsUsed.get(i).count;
                                        }
                                    }
                                } else if (!(itemsUsed.get(i).item.hasTagCompound() && newSlot1.hasTagCompound())){
                                    if (itemsUsed.get(i).count == newSlot1.stackSize) {
                                        newSlot1 = AbilityStones.blankItemStack;
                                    } else {
                                        newSlot1.stackSize = newSlot1.stackSize - itemsUsed.get(i).count;
                                    }
                                }
                            }
                        }
                    }
                    if(n==2) {
                        if(!(newSlot3 == AbilityStones.blankItemStack)) {
                            if (itemsUsed.get(i).item.getItem().equals(newSlot3.getItem())) {
                                if (itemsUsed.get(i).item.hasTagCompound() && newSlot3.hasTagCompound()) {
                                    if (itemsUsed.get(i).item.getTagCompound().equals(newSlot3.getTagCompound())) {
                                        if (itemsUsed.get(i).count == newSlot3.stackSize) {
                                            newSlot3 = AbilityStones.blankItemStack;
                                        } else {
                                            newSlot3.stackSize = newSlot3.stackSize - itemsUsed.get(i).count;
                                        }
                                    }
                                } else if (!(itemsUsed.get(i).item.hasTagCompound() && newSlot3.hasTagCompound())){
                                    if (itemsUsed.get(i).count == newSlot3.stackSize) {
                                        newSlot3 = AbilityStones.blankItemStack;
                                    } else {
                                        newSlot3.stackSize = newSlot3.stackSize - itemsUsed.get(i).count;
                                    }
                                }
                            }
                        }
                    }
                    if(n==3) {
                        if(!(newSlot4 == AbilityStones.blankItemStack)) {
                            if (itemsUsed.get(i).item.getItem().equals(newSlot4.getItem())) {
                                if (itemsUsed.get(i).item.hasTagCompound() && newSlot4.hasTagCompound()) {
                                    if (itemsUsed.get(i).item.getTagCompound().equals(newSlot4.getTagCompound())) {
                                        if (itemsUsed.get(i).count == newSlot4.stackSize) {
                                            newSlot4 = AbilityStones.blankItemStack;
                                        } else {
                                            newSlot4.stackSize = newSlot4.stackSize - itemsUsed.get(i).count;
                                        }
                                    }
                                } else if (!(itemsUsed.get(i).item.hasTagCompound() && newSlot4.hasTagCompound())){
                                    if (itemsUsed.get(i).count == newSlot4.stackSize) {
                                        newSlot4 = AbilityStones.blankItemStack;
                                    } else {
                                        newSlot4.stackSize = newSlot4.stackSize - itemsUsed.get(i).count;
                                    }
                                }
                            }
                        }
                    }
                }

            }





            inv.setStackInSlot(0, newSlot0);
            inv.setStackInSlot(1, newSlot1);
            inv.setStackInSlot(2, newSlot2);
            inv.setStackInSlot(3, newSlot3);
            inv.setStackInSlot(4, newSlot4);
            inv.setStackInSlot(5, newSlot5);


        }
    }

    @Override
    public boolean isItemValid( final ItemStack par1ItemStack ) {

        return false;
    }

    @Override
    public int getSlotStackLimit() {

        return 1;
    }

}
