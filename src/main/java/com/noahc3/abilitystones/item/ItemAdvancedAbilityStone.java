package com.noahc3.abilitystones.item;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.noahc3.abilitystones.AbilityStones;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.noahc3.abilitystones.misc.ItemModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ItemAdvancedAbilityStone extends Item {

    protected String name;
    private ItemStack thisStack;
    private EntityPlayer thisPlayer;

    public ItemAdvancedAbilityStone(String name) {
        this.name = name;
        this.maxStackSize = 1;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(AbilityStones.creativeTab);
    }

    public void registerItemModel() {
        AbilityStones.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack stack = playerIn.getHeldItem(hand);

        if(stack.getTagCompound().getInteger("Enabled") == 1 && stack.getTagCompound().getInteger("Cooldown") == 0)
        {
            stack.clearCustomName();
            stack.getTagCompound().setInteger("Enabled", 0);
            stack.getTagCompound().setInteger("Cooldown", 20);
            stack.getTagCompound().setInteger("Remtime", stack.getTagCompound().getInteger("Deltime") - ((int) System.currentTimeMillis() / 1000));
            cleanupEffects(stack, playerIn);

            return new ActionResult(EnumActionResult.SUCCESS, stack);
        }
        else if(stack.getTagCompound().getInteger("Enabled") == 0 && stack.getTagCompound().getInteger("Cooldown") == 0)
        {
            if(!(stack.getTagCompound().getString("eff1").equals(""))) {
                stack.setStackDisplayName(ChatFormatting.AQUA + getItemStackDisplayName(stack));
                stack.getTagCompound().setInteger("Enabled", 1);
                stack.getTagCompound().setInteger("Cooldown", 20);
                stack.getTagCompound().setInteger("Entime", (int) System.currentTimeMillis() / 1000);
                stack.getTagCompound().setInteger("Deltime", ((int) System.currentTimeMillis() / 1000) + stack.getTagCompound().getInteger("Remtime"));
                //debugState(null, stack);

                if((stack.getTagCompound().getString("eff1").equals("Weapon Cooldown Reduction"))) {
                    weaponCooldownEffect(stack, playerIn);
                }
                if((stack.getTagCompound().getString("eff2").equals("Weapon Cooldown Reduction"))) {
                    weaponCooldownEffect(stack, playerIn);
                }
                if((stack.getTagCompound().getString("eff3").equals("Weapon Cooldown Reduction"))) {
                    weaponCooldownEffect(stack, playerIn);
                }
                if((stack.getTagCompound().getString("eff4").equals("Weapon Cooldown Reduction"))) {
                    weaponCooldownEffect(stack, playerIn);
                }

                return new ActionResult(EnumActionResult.SUCCESS, stack);
            } else {return new ActionResult(EnumActionResult.FAIL, stack);}

        }

        return new ActionResult(EnumActionResult.FAIL, stack);

    }

    public void fakeOnRightClick(ItemStack stack, EntityPlayer playerIn) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List tooltip, ITooltipFlag flagIn) {

        if(!(stack.getTagCompound() == null)) {
            if(stack.getTagCompound().getInteger("Enabled") == 0) {
                tooltip.add("Minutes Remaining: " + (int) Math.ceil((((double) stack.getTagCompound().getInteger("Remtime") / 60))));
            } else if (stack.getTagCompound().getInteger("Enabled") == 1) {
                tooltip.add("Minutes Remaining: " + (int) Math.ceil((double) (stack.getTagCompound().getInteger("Deltime") - ((int) System.currentTimeMillis() / 1000)) / 60));
            }

            if(!(stack.getTagCompound().getString("eff1").equals(""))) {
                tooltip.add("");
                tooltip.add("Effects:")      ;
            } else {
                tooltip.add("");
                tooltip.add("No Effects");
            }


            if(!(stack.getTagCompound().getString("eff1").equals(""))) {
                tooltip.add(stack.getTagCompound().getString("eff1"));
            }
            if(!(stack.getTagCompound().getString("eff2").equals(""))) {
                tooltip.add(stack.getTagCompound().getString("eff2"));
            }
            if(!(stack.getTagCompound().getString("eff3").equals(""))) {
                tooltip.add(stack.getTagCompound().getString("eff3"));
            }
            if(!(stack.getTagCompound().getString("eff4").equals(""))) {
                tooltip.add(stack.getTagCompound().getString("eff4"));
            }

        }
    }

    private void saturationEffect(ItemStack stack, Entity entity) {

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

    private void flightEffect(ItemStack stack, Entity entity) {

        EntityPlayer player = (EntityPlayer)entity;
        player.capabilities.allowFlying = true;
        stack.getTagCompound().setBoolean("RequiresCleanup", true);

    }

    private void itemRepairEffect(ItemStack stack, Entity entity) {
        EntityPlayer player = (EntityPlayer) entity;
        ArrayList<ItemStack> repairableItems = new ArrayList<>();
        ArrayList<Integer> repairableItemsSlots = new ArrayList<>();

        for(int i=0; i<player.inventory.getSizeInventory(); i++) {
            if(!(player.inventory.getStackInSlot(i).isEmpty())) {
                if(player.inventory.getStackInSlot(i).isItemStackDamageable()) {
                    if(!(player.inventory.getStackInSlot(i).getMaxDamage() == player.inventory.getStackInSlot(i).getItemDamage())) {
                        repairableItems.add(player.inventory.getStackInSlot(i));
                        repairableItemsSlots.add(i);
                    }
                }
            }
        }

        Random rand = new Random();

        int num1 = rand.nextInt(repairableItems.size());

        repairableItems.get(num1).setItemDamage(repairableItems.get(num1).getItemDamage() - 1);

    }

    private void weaponCooldownEffect(ItemStack stack, Entity entity) {

        EntityPlayer player = (EntityPlayer) entity;

        IAttributeInstance cooldownAttribute = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);

        AttributeModifier cooldownModifier = new AttributeModifier(UUID.fromString(stack.getTagCompound().getString("UUID")),"Cooldown Modifier", 2, 1);

        cooldownAttribute.applyModifier(cooldownModifier);

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

    private void doEffectByID(String id, Entity entity, ItemStack stack) {

        switch (id) {
            case "Swiftness": ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 2)); break;
            case "Strength": ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 2)); break;
            case "Night Vision": ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 20, 2)); break;
            case "Regeneration": ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60, 2)); break;
            case "Leaping": ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 20, 2)); break;
            case "Fire Resistance": ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 20, 2)); break;
            case "Invisibility": ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 20, 2)); break;
            case "Water Breathing": ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 20, 2)); break;
            case "Resistance": ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 2)); break;
            case "Saturation": saturationEffect(stack, entity); break;
            case "Flight": flightEffect(stack, entity); break;
            case "Item Repair": itemRepairEffect(stack, entity); break;
            case "Weapon Cooldown Reduction": break;
            default: break;

        }

    }

    private void doEffect(ItemStack stack, Entity entity) {

        if(!(stack.getTagCompound().getString("eff1").equals(""))) {
            doEffectByID(stack.getTagCompound().getString("eff1"), entity, stack);
        }
        if(!(stack.getTagCompound().getString("eff2").equals(""))) {
            doEffectByID(stack.getTagCompound().getString("eff2"), entity, stack);
        }
        if(!(stack.getTagCompound().getString("eff3").equals(""))) {
            doEffectByID(stack.getTagCompound().getString("eff3"), entity, stack);
        }
        if(!(stack.getTagCompound().getString("eff4").equals(""))) {
            doEffectByID(stack.getTagCompound().getString("eff4"), entity, stack);
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

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int metadata, boolean bool) {

        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setInteger("Enabled", 0);
            stack.getTagCompound().setString("UUID", UUID.randomUUID().toString());
            stack.getTagCompound().setInteger("Cooldown", 0);
            stack.getTagCompound().setInteger("Entime", (int) System.currentTimeMillis() / 1000);
            stack.getTagCompound().setInteger("Remtime", AbilityStones.defaultTimeInSeconds);
            stack.getTagCompound().setInteger("Deltime", ((int) System.currentTimeMillis() / 1000) + stack.getTagCompound().getInteger("Remtime"));
            stack.getTagCompound().setString("eff1", "");
            stack.getTagCompound().setString("eff2", "");
            stack.getTagCompound().setString("eff3", "");
            stack.getTagCompound().setString("eff4", "");
            stack.getTagCompound().setBoolean("RequiresCleanup", false);
            stack.getTagCompound().setInteger("TagsSet", 1);
        }

        if (!(stack.getTagCompound() == null))
        {
            EntityPlayer player = (EntityPlayer)entity;
            if(((int) System.currentTimeMillis() / 1000) >= stack.getTagCompound().getInteger("Deltime") && stack.getTagCompound().getInteger("TagsSet") == 1 && stack.getTagCompound().getInteger("Enabled") == 1){

                cleanupEffects(stack, entity);
                player.inventory.deleteStack(stack);

            } else if(stack.getTagCompound().getInteger("Remtime") == 0) {

                cleanupEffects(stack, entity);
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

        this.thisStack = stack;
        this.thisPlayer = (EntityPlayer) entity;

        //debugTimer(null, stack);
    }

}
