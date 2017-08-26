package com.noahc3.abilitystones.recipe;


import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import com.noahc3.abilitystones.block.advancedAbilityInfuser.TileEntityAdvancedAbilityInfuser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class AdvancedInfuserCraftingManager {

    public static ArrayList<AdvRecipe> recipes = new ArrayList<>();
    private static int defaultDustCost = 7;

    private static ItemStack generatePotionWithEffect(String effect) {
        ItemStack potion = new ItemStack(Items.POTIONITEM);

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("Potion", effect);

        potion.setTagCompound(nbt);

        return potion;
    }

    public static int curveDustCost(ArrayList<String> idsToApply, int dustCost) {
        if (idsToApply.size() == 0) {
            return 0;
        }

        ArrayList<String> realIdsToApply = new ArrayList<>();

        for(int i=0; i < idsToApply.size(); i++) {
            if (!(idsToApply.get(i).equals("Time Amplification"))) {
                realIdsToApply.add(idsToApply.get(i));
            }
        }

        for(int i=0; i < realIdsToApply.size(); i++) {
            dustCost = dustCost - (2*i);
        }

        return dustCost;

    }

    public static String getAbilitiesAsString(ItemStack stone) {

        String abilities = "Abilities: ";
        int num = 0;

        if (stone.hasTagCompound()) {
            if (!(stone.getTagCompound().getString("eff1").equals(""))) {
                num++;
                abilities = abilities + stone.getTagCompound().getString("eff1") + ", ";

            }
            if (!(stone.getTagCompound().getString("eff2").equals(""))) {
                num++;
                abilities = abilities + stone.getTagCompound().getString("eff2") + ", ";

            }
            if (!(stone.getTagCompound().getString("eff3").equals(""))) {
                num++;
                abilities = abilities + stone.getTagCompound().getString("eff3") + ", ";

            }
            if (!(stone.getTagCompound().getString("eff1").equals(""))) {
                num++;
                abilities = abilities + stone.getTagCompound().getString("eff4") + ", ";

            }

            if (!(abilities.equals(""))) {
                abilities = abilities.substring(0, abilities.length() - 2);
            }
        }

        if (num == 0) {
            abilities = "No Abilities";
        }

        return abilities;
    }

    public static void generateRecipeList() {

        // All regular ADV stone ability recipes

        recipes.add(new AdvRecipe(
                "Swiftness",
                defaultDustCost,
                new ItemGroup(generatePotionWithEffect("minecraft:strong_swiftness"), 1)
        ));
        recipes.add(new AdvRecipe(
                "Strength",
                defaultDustCost,
                new ItemGroup(generatePotionWithEffect("minecraft:strong_strength"), 1)
        ));
        recipes.add(new AdvRecipe(
                "Night Vision",
                defaultDustCost,
                new ItemGroup(generatePotionWithEffect("minecraft:long_night_vision"), 1)
        ));
        recipes.add(new AdvRecipe(
                "Regeneration",
                defaultDustCost,
                new ItemGroup(generatePotionWithEffect("minecraft:strong_regeneration"), 1)
        ));
        recipes.add(new AdvRecipe(
                "Leaping",
                defaultDustCost,
                new ItemGroup(generatePotionWithEffect("minecraft:strong_leaping"), 1)
        ));
        recipes.add(new AdvRecipe(
                "Fire Resistance",
                defaultDustCost,
                new ItemGroup(generatePotionWithEffect("minecraft:long_fire_resistance"), 1)
        ));
        recipes.add(new AdvRecipe(
                "Invisibility",
                defaultDustCost,
                new ItemGroup(generatePotionWithEffect("minecraft:long_invisibility"), 1)
        ));
        recipes.add(new AdvRecipe(
                "Water Breathing",
                defaultDustCost,
                new ItemGroup(generatePotionWithEffect("minecraft:long_water_breathing"), 1)
        ));
        recipes.add(new AdvRecipe(
                "Saturation",
                defaultDustCost,
                new ItemGroup(new ItemStack(Items.GOLDEN_APPLE), 1)
        ));
        recipes.add(new AdvRecipe(
                "Resistance",
                defaultDustCost,
                new ItemGroup(new ItemStack(Blocks.IRON_BLOCK), 1)
        ));
        recipes.add(new AdvRecipe(
                "Flight",
                20,
                new ItemGroup(new ItemStack(Items.FEATHER), 8),
                new ItemGroup(new ItemStack(Blocks.DIAMOND_BLOCK), 1),
                new ItemGroup(new ItemStack(Blocks.GOLD_BLOCK), 2)
        ));
        recipes.add(new AdvRecipe(
                "Item Repair",
                20,
                new ItemGroup(new ItemStack(Items.DIAMOND), 1),
                new ItemGroup(new ItemStack(Items.GOLD_INGOT), 1),
                new ItemGroup(new ItemStack(Blocks.ANVIL), 1)
        ));
        recipes.add(new AdvRecipe(
                "Weapon Cooldown Reduction",
                20,
                new ItemGroup(generatePotionWithEffect("minecraft:swiftness"), 1),
                new ItemGroup(new ItemStack(Items.ARMOR_STAND), 1),
                new ItemGroup(new ItemStack(Items.GUNPOWDER), 3)
        ));

        // All ADV stone modifier recipes

        recipes.add(new AdvRecipe(
                "Time Amplification",
                2,
                new ItemGroup(new ItemStack(Items.CLOCK), 1)
        ));

    }

    private static boolean doItemStackContentsMatch(int type, ArrayList<ItemStack> list1, ArrayList<ItemStack> list2) {


        ArrayList<Item> list1Items = new ArrayList<>();
        ArrayList<Item> list2Items = new ArrayList<>();
        ArrayList<NBTTagCompound> list1NBT = new ArrayList<>();
        ArrayList<NBTTagCompound> list2NBT = new ArrayList<>();

        for(int i=0; i < list1.size(); i++) {

            if(!(list1.get(i) == AbilityStones.blankItemStack)) {
                list1Items.add(list1.get(i).getItem());
            }


        }

        for(int i=0; i < list2.size(); i++) {

            list2Items.add(list2.get(i).getItem());

        }

        for(int i=0; i < list1.size(); i++) {

            if(!(list1.get(i) == AbilityStones.blankItemStack)) {
                list1NBT.add(list1.get(i).getTagCompound());
            }

        }

        for(int i=0; i < list2.size(); i++) {

            list2NBT.add(list2.get(i).getTagCompound());

        }

        if(!list1Items.containsAll(list2Items)) {

            return false;

        }

        if(!list1NBT.containsAll(list2NBT)) {

            return false;

        }

        return true;
    }

    public static Result getOutputFromInputs(int type, int dustInput, TileEntityAdvancedAbilityInfuser te, ItemStack ... inputs) {


        int dustCost = 0;
        ArrayList<ItemGroup> itemsUsed = new ArrayList<>();
        ArrayList<ItemStack> listOfStacksInput = new ArrayList<>();
        ArrayList<Item> dupeListItems = new ArrayList<>();
        ArrayList<NBTTagCompound> dupeListNBT = new ArrayList<>();
        ArrayList<String> idsToApply = new ArrayList<>();

        for (int i=0; i < inputs.length; i++) {

            if(!(inputs[i] == AbilityStones.blankItemStack)) {
                listOfStacksInput.add(inputs[i]);
            }
            listOfStacksInput.removeAll(Collections.singleton(null));
        }

        for (int i=0; i < listOfStacksInput.size(); i++) {

            if (dupeListItems.contains(listOfStacksInput.get(i).getItem())) {
                if (dupeListNBT.contains(listOfStacksInput.get(i).getTagCompound())) {
                    if (ItemStack.areItemsEqual(listOfStacksInput.get(i), new ItemStack(Items.CLOCK))){

                    } else {
                        return new Result(AbilityStones.blankItemStack, null, 0);
                    }
                }
            }
            if (ItemStack.areItemsEqual(listOfStacksInput.get(i), new ItemStack(Items.CLOCK))){

            } else {
                dupeListItems.add(listOfStacksInput.get(i).getItem());
                dupeListNBT.add(listOfStacksInput.get(i).getTagCompound());
            }
        }


        for (int i=0; i < recipes.size(); i++) {

            int countMatches = 0;

            ArrayList<ItemStack> listOfRecipeStacks = new ArrayList<>();
            for(int n=0; n < recipes.get(i).recipe.size(); n++) {
                listOfRecipeStacks.add(recipes.get(i).recipe.get(n).item);
            }

            if(doItemStackContentsMatch(type, listOfStacksInput, listOfRecipeStacks)) {

                for(int n=0; n < recipes.get(i).recipe.size(); n++) {

                    for(int m=0; m < listOfStacksInput.size(); m++) {
                        if (recipes.get(i).recipe.get(n).item.getItem() == listOfStacksInput.get(m).getItem()) {
                            if (recipes.get(i).recipe.get(n).count <= listOfStacksInput.get(m).stackSize) {
                                if (recipes.get(i).recipe.get(n).item.hasTagCompound() && listOfStacksInput.get(m).hasTagCompound()) {
                                    if (recipes.get(i).recipe.get(n).item.getTagCompound().equals(listOfStacksInput.get(m).getTagCompound())) {
                                        itemsUsed.add(recipes.get(i).recipe.get(n));
                                        countMatches++;
                                    }
                                } else if (!(recipes.get(i).recipe.get(n).item.hasTagCompound() && listOfRecipeStacks.get(m).hasTagCompound())) {
                                    itemsUsed.add(recipes.get(i).recipe.get(n));
                                    countMatches++;
                                }
                            }
                        }

                    }

                }

                if (countMatches == recipes.get(i).recipe.size()) {
                    idsToApply.add(recipes.get(i).output);
                    dustCost = dustCost + recipes.get(i).dustCost;
                } else if (countMatches > recipes.get(i).recipe.size()) {
                    if (recipes.get(i).output == "Time Amplification") {
                        for (int n=0; n < countMatches; n++) {
                            idsToApply.add(recipes.get(i).output);
                            dustCost = dustCost + recipes.get(i).dustCost;
                        }
                    }
                }
            }

        }

        dustCost = curveDustCost(idsToApply, dustCost);

        if(idsToApply.isEmpty()) {
            return new Result(AbilityStones.blankItemStack, null, 0);
        } else if(dustInput < dustCost) {
            return new Result(AbilityStones.blankItemStack, null, dustCost);
        } else {
            if(!(ItemStack.areItemsEqual(new ItemStack(ModItems.magicalDust), te.inventory.getStackInSlot(2)))) {
                return new Result(AbilityStones.blankItemStack, null, dustCost);
            } else {
                return new Result(setStoneNBT(idsToApply, false), itemsUsed, dustCost);
            }

        }
    }

    public static ItemStack setStoneNBT(ArrayList idsToApply, boolean jei) {

        ItemStack stack = new ItemStack(ModItems.advancedAbilityStone);

        boolean hasRealIds = false;

        int timeOccurrences = Collections.frequency(idsToApply, "Time Amplification");

        for(int i=0; i < idsToApply.size(); i++) {
            if (idsToApply.get(i) == "Time Amplification") {

            } else {
                hasRealIds = true;
            }
        }

        if(!hasRealIds) {
            if (!jei) {
                return AbilityStones.blankItemStack;
            }
        }

        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setInteger("Enabled", 0);
        stack.getTagCompound().setString("UUID", UUID.randomUUID().toString());
        stack.getTagCompound().setInteger("Cooldown", 0);
        stack.getTagCompound().setInteger("Entime", (int) (System.currentTimeMillis() / 1000));
        stack.getTagCompound().setInteger("Remtime", AbilityStones.defaultTimeInSeconds + (AbilityStones.amplificationTimeInSeconds * timeOccurrences));
        stack.getTagCompound().setInteger("Deltime", ((int) System.currentTimeMillis() / 1000) + stack.getTagCompound().getInteger("Remtime"));
        stack.getTagCompound().setBoolean("RequiresCleanup", false);
        stack.getTagCompound().setInteger("TagsSet", 1);
        stack.getTagCompound().setDouble("OriginalAttackSpeed", 4);



        for (int i=0; i < idsToApply.size(); i++) {

            if(i==0) {stack.getTagCompound().setString("eff1", idsToApply.get(0).toString());}
            if(i==1) {stack.getTagCompound().setString("eff2", idsToApply.get(1).toString());}
            if(i==2) {stack.getTagCompound().setString("eff3", idsToApply.get(2).toString());}
            if(i==3) {stack.getTagCompound().setString("eff4", idsToApply.get(3).toString());}

        }

        return stack;
    }


    //called by the TE, given x inputs and the machine type, return the resulting item
    public static Result getResult(int type, int dustInput, TileEntityAdvancedAbilityInfuser te, ItemStack ... inputs){

        return getOutputFromInputs(type, dustInput, te, inputs);

    }

}