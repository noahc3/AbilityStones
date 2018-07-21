package com.noahc3.abilitystones.item;

import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.noahc3.abilitystones.misc.ItemModelProvider;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class ModItems {

    public static ItemBase mundaneStone = new ItemBase("mundane_stone");
    public static ItemMagicalDust magicalDust = new ItemMagicalDust();

    public static ItemAbilityStoneBase swiftnessStone = new ItemAbilityStoneBase("swiftness_stone", new PotionEffect(MobEffects.SPEED, 20, 2));
    public static ItemAbilityStoneBase fireResistanceStone = new ItemAbilityStoneBase("fire_resistance_stone", new PotionEffect(MobEffects.FIRE_RESISTANCE, 20, 2));
    public static ItemAbilityStoneBase leapingStone = new ItemAbilityStoneBase("leaping_stone", new PotionEffect(MobEffects.JUMP_BOOST, 20, 2));
    public static ItemAbilityStoneBase nightVisionStone = new ItemAbilityStoneBase("night_vision_stone", new PotionEffect(MobEffects.NIGHT_VISION, 20, 2));
    public static ItemAbilityStoneBase regenerationStone = new ItemAbilityStoneBase("regeneration_stone", new PotionEffect(MobEffects.REGENERATION, 60, 2));
    public static ItemAbilityStoneBase strengthStone = new ItemAbilityStoneBase("strength_stone", new PotionEffect(MobEffects.STRENGTH, 20, 2));
    public static ItemAbilityStoneBase invisibilityStone = new ItemAbilityStoneBase("invisibility_stone", new PotionEffect(MobEffects.INVISIBILITY, 20, 2));
    public static ItemAbilityStoneBase waterBreathingStone = new ItemAbilityStoneBase("water_breathing_stone", new PotionEffect(MobEffects.WATER_BREATHING, 20, 2));
    public static ItemSaturationStone saturationStone = new ItemSaturationStone("saturation_stone", null);
    public static ItemAdvancedAbilityStone advancedAbilityStone = new ItemAdvancedAbilityStone("advanced_ability_stone");

    public static void register(IForgeRegistry<Item> registry) {

        registry.registerAll(
                mundaneStone,
                magicalDust,
                swiftnessStone,
                fireResistanceStone,
                leapingStone,
                nightVisionStone,
                regenerationStone,
                strengthStone,
                invisibilityStone,
                waterBreathingStone,
                saturationStone,
                advancedAbilityStone
        );

    }

    public static void registerModels() {

        mundaneStone.registerItemModel();
        magicalDust.registerItemModel();
        swiftnessStone.registerItemModel();
        fireResistanceStone.registerItemModel();
        leapingStone.registerItemModel();
        nightVisionStone.registerItemModel();
        regenerationStone.registerItemModel();
        strengthStone.registerItemModel();
        invisibilityStone.registerItemModel();
        waterBreathingStone.registerItemModel();
        saturationStone.registerItemModel();
        advancedAbilityStone.registerItemModel();

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

        return validStones.contains(stack.getItem());
    }

}
