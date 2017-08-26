package com.noahc3.abilitystones.integration.jei.basicInfuser;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import com.noahc3.abilitystones.recipe.InfuserCraftingManager;
import com.noahc3.abilitystones.recipe.Recipe;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class BasicInfuserRecipeWrapper extends BlankRecipeWrapper{

    private final List<ItemStack> input;
    private final ItemStack output;

    public BasicInfuserRecipeWrapper(Recipe recipe) {

        ItemStack o = recipe.output.item;
        List<ItemStack> ing = new ArrayList<>();

        o.stackSize = recipe.output.count;

        o = InfuserCraftingManager.setStoneNBT(o);

        this.output = o;

        for (int i=0; i < recipe.recipe.size(); i++) {
            ItemStack in = recipe.recipe.get(i).item;
            in.stackSize = recipe.recipe.get(i).count;

            ing.add(in);
        }

        this.input = ing;

    }

    /*public List<List<ItemStack>> convertRecipesToInputList() {

        ArrayList<Recipe> recipes = InfuserCraftingManager.recipes;
        List<List<ItemStack>> out = new ArrayList<>();

        for (int i=0; i < recipes.size(); i++) {
            List<ItemStack> gen = new ArrayList<ItemStack>() {};

            for (int n=0; n < recipes.get(i).recipe.size(); n++) {
                ItemStack st = recipes.get(i).recipe.get(n).item;
                st.stackSize = recipes.get(i).recipe.get(n).count;

                gen.add(st);
            }

            out.add(gen);
        }

        return out;

    }

    public List<ItemStack> convertRecipesToOutputList() {

        ArrayList<Recipe> recipes = InfuserCraftingManager.recipes;
        List<ItemStack> out = new ArrayList<>();

        for (int i=0; i < recipes.size(); i++) {

            ItemStack st = recipes.get(i).output.item;

            st.stackSize = recipes.get(i).output.count;

            out.add(st);
        }

        return out;

    }*/


    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {

        ingredients.setInputs(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);

    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

    }


}
