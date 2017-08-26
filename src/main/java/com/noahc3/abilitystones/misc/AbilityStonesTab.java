package com.noahc3.abilitystones.misc;

import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.block.ModBlocks;
import com.noahc3.abilitystones.item.ModItems;
import com.noahc3.abilitystones.recipe.InfuserCraftingManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.List;

public class AbilityStonesTab extends CreativeTabs {

    public AbilityStonesTab() {
        super(AbilityStones.modID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.regenerationStone);
    }

    @Override
    public boolean hasSearchBar() {
        return false;
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> list) {

        list.add(new ItemStack(ModItems.magicalDust));
        list.add(new ItemStack(ModItems.mundaneStone));
        list.add(new ItemStack(Item.getItemFromBlock(ModBlocks.abilityInfuser)));
        list.add(new ItemStack(Item.getItemFromBlock(ModBlocks.advancedAbilityInfuser)));
        list.add(new ItemStack(Item.getItemFromBlock(ModBlocks.oreMagicalDust)));

        list.add(InfuserCraftingManager.setStoneNBT(new ItemStack(ModItems.fireResistanceStone)));
        list.add(InfuserCraftingManager.setStoneNBT(new ItemStack(ModItems.invisibilityStone)));
        list.add(InfuserCraftingManager.setStoneNBT(new ItemStack(ModItems.leapingStone)));
        list.add(InfuserCraftingManager.setStoneNBT(new ItemStack(ModItems.nightVisionStone)));
        list.add(InfuserCraftingManager.setStoneNBT(new ItemStack(ModItems.regenerationStone)));
        list.add(InfuserCraftingManager.setStoneNBT(new ItemStack(ModItems.saturationStone)));
        list.add(InfuserCraftingManager.setStoneNBT(new ItemStack(ModItems.strengthStone)));
        list.add(InfuserCraftingManager.setStoneNBT(new ItemStack(ModItems.swiftnessStone)));
        list.add(InfuserCraftingManager.setStoneNBT(new ItemStack(ModItems.waterBreathingStone)));

        list.add(new ItemStack(ModItems.advancedAbilityStone));




    }

}
