package com.noahc3.abilitystones.recipe;

import java.util.ArrayList;

public class Recipe {
    public ArrayList<ItemGroup> recipe = new ArrayList<>();
    public ItemGroup output;

    public Recipe(ItemGroup output, ItemGroup ... itemGroups) {

        this.output = output;

        for(int i=0; i < itemGroups.length; i++) {
            this.recipe.add(itemGroups[i]);
        }

    }

}