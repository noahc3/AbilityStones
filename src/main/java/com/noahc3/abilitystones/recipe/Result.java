package com.noahc3.abilitystones.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class Result {

    public ItemStack stack;
    public ArrayList<ItemGroup> itemsUsed = new ArrayList<>();
    public int dustCost;

    public Result(ItemStack stack, ArrayList<ItemGroup> itemsUsed, int dustCost) {

        this.stack = stack;
        this.dustCost = dustCost;
        this.itemsUsed = itemsUsed;

    }

}
