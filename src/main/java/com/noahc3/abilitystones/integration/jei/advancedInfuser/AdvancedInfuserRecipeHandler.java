package com.noahc3.abilitystones.integration.jei.advancedInfuser;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import com.noahc3.abilitystones.recipe.AdvRecipe;

import javax.annotation.Nonnull;

public class AdvancedInfuserRecipeHandler implements IRecipeHandler<AdvRecipe>{

    @Nonnull
    @Override
    public Class<AdvRecipe> getRecipeClass() {
        return AdvRecipe.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return "abilitystones.advancedinfusing";
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull AdvRecipe recipe) {
        return "abilitystones.advancedinfusing";
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull AdvRecipe recipe) {
        return new AdvancedInfuserRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull AdvRecipe recipe) {
        return recipe.recipe.size() <=6;
    }

}
