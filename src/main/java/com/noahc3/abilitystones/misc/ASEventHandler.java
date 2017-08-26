package com.noahc3.abilitystones.misc;

import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.ArrayList;
import java.util.UUID;

public class ASEventHandler {

    @SubscribeEvent
    public void PlayerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {

        EntityPlayer player = event.player;
        InventoryPlayer inv = event.player.inventory;
        ArrayList<ItemStack> items = new ArrayList<>();

        for(int i=0; i < inv.getSizeInventory(); i++) {
            if (!(inv.getStackInSlot(i) == AbilityStones.blankItemStack)) {
                if(ModItems.isStackAbilityStone(inv.getStackInSlot(i))){
                    if (inv.getStackInSlot(i).getTagCompound().getInteger("Enabled") == 1) {
                        disableRegularStone(inv.getStackInSlot(i));
                    }
                } else if (inv.getStackInSlot(i).getItem() == ModItems.advancedAbilityStone) {
                    disableAdvancedStone(player, inv.getStackInSlot(i));
                }
            }
        }

    }

    private void disableRegularStone(ItemStack stack) {
        stack.clearCustomName();
        stack.getTagCompound().setInteger("Enabled", 0);
        stack.getTagCompound().setInteger("Cooldown", 20);
        stack.getTagCompound().setInteger("Remtime", stack.getTagCompound().getInteger("Deltime") - ((int) System.currentTimeMillis() / 1000));
    }

    private void disableAdvancedStone(EntityPlayer playerIn, ItemStack stack) {

        stack.clearCustomName();
        stack.getTagCompound().setInteger("Enabled", 0);
        stack.getTagCompound().setInteger("Cooldown", 20);
        stack.getTagCompound().setInteger("Remtime", stack.getTagCompound().getInteger("Deltime") - ((int) System.currentTimeMillis() / 1000));
        cleanupEffects(stack, playerIn);

        return;
    }

    private void cleanupEffects(ItemStack stack, Entity entity) {

        EntityPlayer player = (EntityPlayer)entity;

        if((stack.getTagCompound().getString("eff1").equals("Flight"))) {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }
        if((stack.getTagCompound().getString("eff2").equals("Flight"))) {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }
        if((stack.getTagCompound().getString("eff3").equals("Flight"))) {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }
        if((stack.getTagCompound().getString("eff4").equals("Flight"))) {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }

        if((stack.getTagCompound().getString("eff1").equals("Weapon Cooldown Reduction"))) {
            player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier(UUID.fromString(stack.getTagCompound().getString("UUID")));
        }
        if((stack.getTagCompound().getString("eff2").equals("Weapon Cooldown Reduction"))) {
            player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier(UUID.fromString(stack.getTagCompound().getString("UUID")));
        }
        if((stack.getTagCompound().getString("eff3").equals("Weapon Cooldown Reduction"))) {
            player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier(UUID.fromString(stack.getTagCompound().getString("UUID")));
        }
        if((stack.getTagCompound().getString("eff4").equals("Weapon Cooldown Reduction"))) {
            player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier(UUID.fromString(stack.getTagCompound().getString("UUID")));
        }

        stack.getTagCompound().setBoolean("RequiresCleanup", false);

    }

}
