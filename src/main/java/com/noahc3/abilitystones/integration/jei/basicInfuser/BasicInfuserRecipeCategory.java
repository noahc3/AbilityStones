package com.noahc3.abilitystones.integration.jei.basicInfuser;

import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.block.ModBlocks;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class BasicInfuserRecipeCategory extends BlankRecipeCategory<BasicInfuserRecipeWrapper>{


    public static final int width = 134;
    public static final int height = 69;

    private final IDrawable background;
    private final String localizedName;

    private BasicInfuserRecipeWrapper currentRecipe;


    public BasicInfuserRecipeCategory(IGuiHelper guiHelper) {

        ResourceLocation location = new ResourceLocation(AbilityStones.modID, "textures/gui/ability_infuser.png");
        background = guiHelper.createDrawable(location, 22, 27, width, height);
        localizedName = I18n.format(ModBlocks.abilityInfuser.getUnlocalizedName() + ".name");

    }

    @Override
    public String getUid() {
        return "abilitystones.basicinfusing";
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, BasicInfuserRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        //input slots
        guiItemStacks.init(0, true, 0, 0);
        guiItemStacks.init(1, true, 58, 0);
        guiItemStacks.init(2, true, 116, 0);
        //output slot
        guiItemStacks.init(3, false, 58, 51);

        List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);

        for (int index = 0; index < inputs.size(); index++) {
            List<ItemStack> input = inputs.get(index);
            if (input != null) {
                guiItemStacks.set(index, input);
            }
        }
        List<ItemStack> outputs = ingredients.getOutputs(ItemStack.class);
        guiItemStacks.set(3, outputs);
        currentRecipe = recipeWrapper;
    }

}
