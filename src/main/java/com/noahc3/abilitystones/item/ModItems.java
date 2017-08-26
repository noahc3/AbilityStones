package com.noahc3.abilitystones.item;

import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.noahc3.abilitystones.misc.ItemModelProvider;

import java.util.ArrayList;

public class ModItems {

    public static ItemBase mundaneStone;
    public static ItemMagicalDust magicalDust;

    public static ItemAbilityStoneBase swiftnessStone;
    public static ItemAbilityStoneBase fireResistanceStone;
    public static ItemAbilityStoneBase leapingStone;
    public static ItemAbilityStoneBase nightVisionStone;
    public static ItemAbilityStoneBase regenerationStone;
    public static ItemAbilityStoneBase strengthStone;
    public static ItemAbilityStoneBase invisibilityStone;
    public static ItemAbilityStoneBase waterBreathingStone;
    public static ItemSaturationStone saturationStone;
    public static ItemAdvancedAbilityStone advancedAbilityStone;

    public static void init() {
        mundaneStone = register(new ItemBase("mundane_stone"));
        magicalDust = register(new ItemMagicalDust());

        swiftnessStone = register(new ItemAbilityStoneBase("swiftness_stone", new PotionEffect(MobEffects.SPEED, 20, 2)));
        fireResistanceStone = register(new ItemAbilityStoneBase("fire_resistance_stone", new PotionEffect(MobEffects.FIRE_RESISTANCE, 20, 2)));
        leapingStone = register(new ItemAbilityStoneBase("leaping_stone", new PotionEffect(MobEffects.JUMP_BOOST, 20, 2)));
        nightVisionStone = register(new ItemAbilityStoneBase("night_vision_stone", new PotionEffect(MobEffects.NIGHT_VISION, 20, 2)));
        regenerationStone = register(new ItemAbilityStoneBase("regeneration_stone", new PotionEffect(MobEffects.REGENERATION, 60, 2)));
        strengthStone = register(new ItemAbilityStoneBase("strength_stone", new PotionEffect(MobEffects.STRENGTH, 20, 2)));
        invisibilityStone = register(new ItemAbilityStoneBase("invisibility_stone", new PotionEffect(MobEffects.INVISIBILITY, 20, 2)));
        waterBreathingStone = register(new ItemAbilityStoneBase("water_breathing_stone", new PotionEffect(MobEffects.WATER_BREATHING, 20, 2)));
        saturationStone = register(new ItemSaturationStone("saturation_stone", null));
        advancedAbilityStone = register(new ItemAdvancedAbilityStone("advanced_ability_stone"));

    }

    private static <T extends Item> T register(T item) {
        GameRegistry.register(item);

        if (item instanceof ItemModelProvider) {
            ((ItemModelProvider)item).registerItemModel(item);
        }

        return item;
    }

    public static boolean isStackAbilityStone(ItemStack stack) {
        ArrayList<Item> validStones = new ArrayList<>();

        validStones.add(ModItems.fireResistanceStone);
        validStones.add(ModItems.invisibilityStone);
        validStones.add(ModItems.leapingStone);
        validStones.add(ModItems.nightVisionStone);
        validStones.add(ModItems.regenerationStone);
        validStones.add(ModItems.saturationStone);
        validStones.add(ModItems.strengthStone);
        validStones.add(ModItems.swiftnessStone);
        validStones.add(ModItems.waterBreathingStone);

        if(validStones.contains(stack.getItem())) {
            return true;
        }
        return false;
    }

}
