package com.noahc3.abilitystones.integration.jei;

import com.noahc3.abilitystones.block.ModBlocks;
import com.noahc3.abilitystones.integration.jei.advancedInfuser.AdvancedInfuserRecipeCategory;
import com.noahc3.abilitystones.integration.jei.advancedInfuser.AdvancedInfuserRecipeHandler;
import com.noahc3.abilitystones.integration.jei.basicInfuser.BasicInfuserRecipeCategory;
import com.noahc3.abilitystones.integration.jei.basicInfuser.BasicInfuserRecipeHandler;
import com.noahc3.abilitystones.recipe.AdvancedInfuserCraftingManager;
import com.noahc3.abilitystones.recipe.InfuserCraftingManager;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@mezz.jei.api.JEIPlugin
public class JEIPlugin extends BlankModPlugin{

    @Override
    public void registerItemSubtypes(@Nonnull ISubtypeRegistry subtypeRegistry) {
        //subtypeRegistry.registerSubtypeInterpreter(ModItems.advancedAbilityStone, AdvancedInfuserCraftingManager::getAbilitiesAsString);

        //subtypeRegistry.useNbtForSubtypes(ModItems.fireResistanceStone);
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {

        IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        registry.addRecipeCategories(
                new BasicInfuserRecipeCategory(jeiHelpers.getGuiHelper()),
                new AdvancedInfuserRecipeCategory(jeiHelpers.getGuiHelper())
        );

        registry.addRecipeHandlers(
                new BasicInfuserRecipeHandler(),
                new AdvancedInfuserRecipeHandler()
        );

        registry.addRecipes(InfuserCraftingManager.recipes);
        registry.addRecipes(AdvancedInfuserCraftingManager.recipes);

        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.abilityInfuser), "abilitystones.basicinfusing");
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.advancedAbilityInfuser), "abilitystones.advancedinfusing");

        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();

        blacklist.addIngredientToBlacklist(new ItemStack(Item.getItemFromBlock(ModBlocks.advancedAbilityInfuserTop)));

    }

}
