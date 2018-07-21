package com.noahc3.abilitystones.item;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.misc.ItemModelProvider;
import com.noahc3.abilitystones.recipe.InfuserCraftingManager;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemAbilityStoneBase extends Item {

    protected String name;
    protected PotionEffect effect;


    public ItemAbilityStoneBase(String name, PotionEffect effect) {


        this.name = name;
        this.effect = effect;
        this.maxStackSize = 1;
        setUnlocalizedName(name);
        setRegistryName(name);

    }

    public void registerItemModel() {
        AbilityStones.proxy.registerItemRenderer(this, 0, name);
    }


    /* NBT and Effect stuff */

    @Override
    @MethodsReturnNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack stack = playerIn.getHeldItem(hand);

        if(stack.getTagCompound().getInteger("Enabled") == 1 && stack.getTagCompound().getInteger("Cooldown") == 0)
        {
            stack.clearCustomName();
            stack.getTagCompound().setInteger("Enabled", 0);
            stack.getTagCompound().setInteger("Cooldown", 20);
            stack.getTagCompound().setInteger("Remtime", stack.getTagCompound().getInteger("Deltime") - ((int) System.currentTimeMillis() / 1000));

            return new ActionResult(EnumActionResult.SUCCESS, stack);
        }
        else if(stack.getTagCompound().getInteger("Enabled") == 0 && stack.getTagCompound().getInteger("Cooldown") == 0)
        {
            stack.setStackDisplayName(ChatFormatting.AQUA + getItemStackDisplayName(stack));
            stack.getTagCompound().setInteger("Enabled", 1);
            stack.getTagCompound().setInteger("Cooldown", 20);
            stack.getTagCompound().setInteger("Entime", (int) System.currentTimeMillis() / 1000);
            stack.getTagCompound().setInteger("Deltime", ((int) System.currentTimeMillis() / 1000) + stack.getTagCompound().getInteger("Remtime"));
            //debugState(null, stack);
            return new ActionResult(EnumActionResult.SUCCESS, stack);
        }

        return new ActionResult(EnumActionResult.FAIL, stack);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        if(!(stack.getTagCompound() == null)) {
            if(stack.getTagCompound().getInteger("Enabled") == 0) {
                tooltip.add("Minutes Remaining: " + (int) Math.ceil((((double) stack.getTagCompound().getInteger("Remtime") / 60))));
            } else if (stack.getTagCompound().getInteger("Enabled") == 1) {
                tooltip.add("Minutes Remaining: " + (int) Math.ceil((double) (stack.getTagCompound().getInteger("Deltime") - ((int) System.currentTimeMillis() / 1000)) / 60));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)  {
        if (!(stack.getTagCompound() == null)) {
            if (stack.getTagCompound().getInteger("Enabled") == 1) {
                return true;
            }
            else if (stack.getTagCompound().getInteger("Enabled") == 0) {
                return false;
            }
        }
        return false;
    }


    public void doEffect(ItemStack stack, Entity entity) {

        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(this.effect));

    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int metadata, boolean bool) {

        if (stack.getTagCompound() == null) {
            stack = InfuserCraftingManager.setStoneNBT(stack);
        }

        if (!(stack.getTagCompound() == null))
        {
            EntityPlayer player = (EntityPlayer)entity;
            if(((int) System.currentTimeMillis() / 1000) >= stack.getTagCompound().getInteger("Deltime") && stack.getTagCompound().getInteger("TagsSet") == 1 && stack.getTagCompound().getInteger("Enabled") == 1){
                player.inventory.deleteStack(stack);

            } else if(stack.getTagCompound().getInteger("Remtime") == 0) {
                player.inventory.deleteStack(stack);
            }

            if (stack.getTagCompound().getInteger("Enabled") == 1) {

                doEffect(stack, entity);

            }
            else if (stack.getTagCompound().getInteger("Enabled") == 0) {

            }

            if (stack.getTagCompound().getInteger("Cooldown") > 0) {

                stack.getTagCompound().setInteger("Cooldown", stack.getTagCompound().getInteger("Cooldown") + -1);
            }
        }



        //debugTimer(null, stack);
    }

}
