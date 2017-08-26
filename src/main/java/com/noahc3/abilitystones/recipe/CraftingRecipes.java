package com.noahc3.abilitystones.recipe;

import com.noahc3.abilitystones.block.ModBlocks;
import com.noahc3.abilitystones.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingRecipes {

    public static void init() {

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mundaneStone), "CSC", "SMS", "CSC", 'C', Blocks.COBBLESTONE, 'S', Blocks.STONE, 'M', ModItems.magicalDust);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.abilityInfuser), "#}#", "###", "*#*", '#', Blocks.COBBLESTONE, '}', Items.WATER_BUCKET, '*', ModItems.magicalDust);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.advancedAbilityInfuser), "^*^", "#*#", "#-#", '#', Blocks.COBBLESTONE, '-', Item.getItemFromBlock(ModBlocks.abilityInfuser), '*', ModItems.magicalDust, '^', Items.DIAMOND);

    }

}
