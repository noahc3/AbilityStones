package com.noahc3.abilitystones.integration.jei.advancedInfuser;

import com.noahc3.abilitystones.item.ModItems;
import com.noahc3.abilitystones.recipe.AdvRecipe;
import com.noahc3.abilitystones.recipe.AdvancedInfuserCraftingManager;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AdvancedInfuserRecipeWrapper extends BlankRecipeWrapper{

    private final List<ItemStack> input;
    private final ItemStack output;

    public AdvancedInfuserRecipeWrapper(AdvRecipe recipe) {

        ArrayList<String> oal = new ArrayList<>();
        oal.add(recipe.output);

        this.output = AdvancedInfuserCraftingManager.setStoneNBT(oal, true);

        ArrayList<ItemStack> ial = new ArrayList<>();
        ItemStack st;

        for(int i=0; i < 6; i++) {ial.add(null);}

        for (int i=0; i < recipe.recipe.size(); i++) {

            switch (i) {
                case 0: st = recipe.recipe.get(0).item; st.stackSize = recipe.recipe.get(0).count; ial.set(0, st); break;
                case 1: st = recipe.recipe.get(1).item; st.stackSize = recipe.recipe.get(1).count; ial.set(1, st); break;
                case 2: st = recipe.recipe.get(2).item; st.stackSize = recipe.recipe.get(2).count; ial.set(3, st); break;
                case 3: st = recipe.recipe.get(3).item; st.stackSize = recipe.recipe.get(3).count; ial.set(4, st); break;
                default: break;
            }
        }

        ItemStack dust = new ItemStack(ModItems.magicalDust);
        dust.stackSize = recipe.dustCost;
        ItemStack stone = new ItemStack(ModItems.mundaneStone);

        ial.set(2, dust);
        ial.set(5, stone);

        this.input = ial;

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
