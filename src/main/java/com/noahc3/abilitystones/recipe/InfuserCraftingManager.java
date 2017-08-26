package com.noahc3.abilitystones.recipe;


import com.noahc3.abilitystones.AbilityStones;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import com.noahc3.abilitystones.item.ModItems;


import java.util.*;

public class InfuserCraftingManager {

    public static ArrayList<Recipe> recipes = new ArrayList<>();
    protected static int dustCost = 6;

    private static ItemStack generatePotionWithEffect(String effect) {
        ItemStack potion = new ItemStack(Items.POTIONITEM);

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("Potion", effect);

        potion.setTagCompound(nbt);

        return potion;
    }

    public static void generateRecipeList() {

        recipes.add(new Recipe(
                new ItemGroup(new ItemStack(ModItems.saturationStone), 1),
                new ItemGroup(new ItemStack(Items.GOLDEN_APPLE), 1),
                new ItemGroup(new ItemStack(ModItems.mundaneStone), 1),
                new ItemGroup(new ItemStack(ModItems.magicalDust), dustCost)
        ));

        recipes.add(new Recipe(
                new ItemGroup(new ItemStack(ModItems.nightVisionStone), 1),
                new ItemGroup(generatePotionWithEffect("minecraft:long_night_vision"), 1),
                new ItemGroup(new ItemStack(ModItems.mundaneStone), 1),
                new ItemGroup(new ItemStack(ModItems.magicalDust), dustCost)
        ));

        recipes.add(new Recipe(
                new ItemGroup(new ItemStack(ModItems.leapingStone), 1),
                new ItemGroup(generatePotionWithEffect("minecraft:strong_leaping"), 1),
                new ItemGroup(new ItemStack(ModItems.mundaneStone), 1),
                new ItemGroup(new ItemStack(ModItems.magicalDust), dustCost)
        ));

        recipes.add(new Recipe(
                new ItemGroup(new ItemStack(ModItems.fireResistanceStone), 1),
                new ItemGroup(generatePotionWithEffect("minecraft:long_fire_resistance"), 1),
                new ItemGroup(new ItemStack(ModItems.mundaneStone), 1),
                new ItemGroup(new ItemStack(ModItems.magicalDust), dustCost)
        ));

        recipes.add(new Recipe(
                new ItemGroup(new ItemStack(ModItems.swiftnessStone), 1),
                new ItemGroup(generatePotionWithEffect("minecraft:strong_swiftness"), 1),
                new ItemGroup(new ItemStack(ModItems.mundaneStone), 1),
                new ItemGroup(new ItemStack(ModItems.magicalDust), dustCost)
        ));

        recipes.add(new Recipe(
                new ItemGroup(new ItemStack(ModItems.regenerationStone), 1),
                new ItemGroup(generatePotionWithEffect("minecraft:strong_regeneration"), 1),
                new ItemGroup(new ItemStack(ModItems.mundaneStone), 1),
                new ItemGroup(new ItemStack(ModItems.magicalDust), dustCost)
        ));

        recipes.add(new Recipe(
                new ItemGroup(new ItemStack(ModItems.strengthStone), 1),
                new ItemGroup(generatePotionWithEffect("minecraft:strong_strength"), 1),
                new ItemGroup(new ItemStack(ModItems.mundaneStone), 1),
                new ItemGroup(new ItemStack(ModItems.magicalDust), dustCost)
        ));

        recipes.add(new Recipe(
                new ItemGroup(new ItemStack(ModItems.invisibilityStone), 1),
                new ItemGroup(generatePotionWithEffect("minecraft:long_invisibility"), 1),
                new ItemGroup(new ItemStack(ModItems.mundaneStone), 1),
                new ItemGroup(new ItemStack(ModItems.magicalDust), dustCost)
        ));
        recipes.add(new Recipe(
                new ItemGroup(new ItemStack(ModItems.waterBreathingStone), 1),
                new ItemGroup(generatePotionWithEffect("minecraft:long_water_breathing"), 1),
                new ItemGroup(new ItemStack(ModItems.mundaneStone), 1),
                new ItemGroup(new ItemStack(ModItems.magicalDust), dustCost)
        ));

    }

    private static boolean areItemStackListsEqual(int type, ArrayList<ItemStack> list1, ArrayList<ItemStack> list2) {

        if(type == 0) {

            for(int i=0; i < list1.size(); i++) {
                if(!ItemStack.areItemsEqual(list1.get(i), list2.get(i))) {
                    return false;
                }
                if(!ItemStack.areItemStackTagsEqual(list1.get(i), list2.get(i))){
                    return false;
                }
            }

            return true;

        }


        return false;
    }

    public static ItemStack getOutputFromInputs(int type, ItemStack ... inputs) {

        ArrayList<ItemStack> listOfStacksInput = new ArrayList<>();

        for (int i=0; i < inputs.length; i++) {
            listOfStacksInput.add(inputs[i]);
        }

        for (int i=0; i < recipes.size(); i++) {
            ArrayList<ItemStack> listOfRecipeStacks = new ArrayList<>();
            for(int n=0; n < recipes.get(i).recipe.size(); n++) {
                listOfRecipeStacks.add(recipes.get(i).recipe.get(n).item);
            }

            if(areItemStackListsEqual(type, listOfStacksInput, listOfRecipeStacks)) {

                for(int n=0; n < recipes.get(i).recipe.size(); n++) {

                    if(recipes.get(i).recipe.get(n).count <= inputs[n].getCount()){

                    } else {
                        return ItemStack.EMPTY;
                    }
                }

                ItemStack output = recipes.get(i).output.item;

                output.setCount(recipes.get(i).output.count);

                return setStoneNBT(output);

            }

        }

        return ItemStack.EMPTY;
    }

    public static int getItemCost(ItemStack output, int indexInput) {

        for(int i=0; i<recipes.size();i++) {

            if (ItemStack.areItemsEqual(recipes.get(i).output.item, output)) {

                return recipes.get(i).recipe.get(indexInput).count;

            }

        }

        return 0;

    }

    public static ItemStack setStoneNBT(ItemStack stack) {

        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setString("UUID", UUID.randomUUID().toString());
        stack.getTagCompound().setInteger("Enabled", 0);
        stack.getTagCompound().setInteger("Cooldown", 0);
        stack.getTagCompound().setInteger("Entime", (int) System.currentTimeMillis() / 1000);
        stack.getTagCompound().setInteger("Remtime", AbilityStones.defaultTimeInSeconds);
        stack.getTagCompound().setInteger("Deltime", ((int) System.currentTimeMillis() / 1000) + stack.getTagCompound().getInteger("Remtime"));
        stack.getTagCompound().setInteger("TagsSet", 1);

        return stack;
    }


    //called by the TE, given x inputs and the machine type, return the resulting item
    public static ItemStack getResult(int type, ItemStack ... inputs){

        return getOutputFromInputs(type, inputs);

    }

}