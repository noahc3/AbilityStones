package com.noahc3.abilitystones.integration.jei.advancedInfuser;

import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.block.ModBlocks;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class AdvancedInfuserRecipeCategory extends BlankRecipeCategory<AdvancedInfuserRecipeWrapper>{


    public static final int width = 144;
    public static final int height = 102;

    private final IDrawable background;
    private final String localizedName;

    private AdvancedInfuserRecipeWrapper currentRecipe;


    public AdvancedInfuserRecipeCategory(IGuiHelper guiHelper) {

        ResourceLocation location = new ResourceLocation(AbilityStones.modID, "textures/gui/advanced_ability_infuser.png");
        background = guiHelper.createDrawable(location, 16, 23, width, height);
        localizedName = I18n.format(ModBlocks.advancedAbilityInfuser.getUnlocalizedName() + ".name");

    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getUid() {
        return "abilitystones.advancedinfusing";
    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getTitle() {
        return localizedName;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public IDrawable getBackground() {
        return background;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void setRecipe(IRecipeLayout recipeLayout, AdvancedInfuserRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        //input slots
        guiItemStacks.init(0, true, 0, 0);
        guiItemStacks.init(1, true, 29, 6);
        guiItemStacks.init(2, true, 64, 0);
        guiItemStacks.init(3, true, 97, 6);
        guiItemStacks.init(4, true, 126, 0);
        guiItemStacks.init(5, true, 64, 38);
        //output slot
        guiItemStacks.init(6, false, 64, 84);

        List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);

        for (int index = 0; index < inputs.size(); index++) {
            List<ItemStack> input = inputs.get(index);
            if (input != null) {
                guiItemStacks.set(index, input);
            }
        }
        List<ItemStack> outputs = ingredients.getOutputs(ItemStack.class).get(0);
        guiItemStacks.set(6, outputs);
        currentRecipe = recipeWrapper;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getModName() {
        return "Ability Stones";
    }

}
