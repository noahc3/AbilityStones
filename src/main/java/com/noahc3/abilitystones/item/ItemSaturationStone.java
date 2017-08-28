package com.noahc3.abilitystones.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemSaturationStone extends ItemAbilityStoneBase {

    public ItemSaturationStone(String name, PotionEffect effect) {
        super(name, effect);
    }

    @Override
    public void doEffect(ItemStack stack, Entity entity) {

        EntityPlayer player = (EntityPlayer)entity;
        int foodLevelIn = player.getFoodStats().getFoodLevel() + 1;

        long checkValue = player.world.getTotalWorldTime() % 60;

        if (checkValue == 1){

            if(player.getFoodStats().getFoodLevel() < 20){
                player.getFoodStats().setFoodLevel(foodLevelIn);
                player.getFoodStats().setFoodSaturationLevel(15);
            }
        }
    }

}
