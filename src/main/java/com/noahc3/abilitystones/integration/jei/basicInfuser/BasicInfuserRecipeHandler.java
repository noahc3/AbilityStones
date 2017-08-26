package com.noahc3.abilitystones.integration.jei.basicInfuser;

import com.noahc3.abilitystones.recipe.Recipe;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

public class BasicInfuserRecipeHandler implements IRecipeHandler<Recipe>{

    @Nonnull
    @Override
    public Class<Recipe> getRecipeClass() {
        return Recipe.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return "abilitystones.basicinfusing";
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull Recipe recipe) {
        return "abilitystones.basicinfusing";
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull Recipe recipe) {
        return new BasicInfuserRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull Recipe recipe) {
        return recipe.recipe.size() <=3;
    }

}
