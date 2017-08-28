package com.noahc3.abilitystones.recipe;

import java.util.ArrayList;

public class AdvRecipe {

    public String output;
    public int dustCost;
    public ArrayList<ItemGroup> recipe = new ArrayList();

    public AdvRecipe(String output, int dustCost, ItemGroup ... itemGroups) {

        this.output = output;
        this.dustCost = dustCost;

        for(int i=0; i < itemGroups.length; i++) {
            this.recipe.add(itemGroups[i]);
        }

    }

}
