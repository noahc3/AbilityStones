package com.noahc3.abilitystones.block;

import com.noahc3.abilitystones.block.abilityInfuser.TileEntityAbilityInfuser;
import com.noahc3.abilitystones.block.advancedAbilityInfuser.TileEntityAdvancedAbilityInfuser;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.noahc3.abilitystones.block.abilityInfuser.BlockAbilityInfuser;
import com.noahc3.abilitystones.block.advancedAbilityInfuser.BlockAdvancedAbilityInfuser;
import com.noahc3.abilitystones.block.advancedAbilityInfuser.BlockAdvancedAbilityInfuserTop;
import com.noahc3.abilitystones.misc.ItemModelProvider;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static BlockMagicalOre oreMagicalDust = new BlockMagicalOre("magical_dust_ore");
    //public static BlockCropMagicalDust cropMagicalDust = new BlockCropMagicalDust();
    public static BlockAbilityInfuser abilityInfuser = new BlockAbilityInfuser("ability_infuser");
    public static BlockAdvancedAbilityInfuser advancedAbilityInfuser = new BlockAdvancedAbilityInfuser("advanced_ability_infuser");
    public static BlockAdvancedAbilityInfuserTop advancedAbilityInfuserTop = new BlockAdvancedAbilityInfuserTop("advanced_ability_infuser_top");

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                oreMagicalDust,
                //cropMagicalDust,
                abilityInfuser,
                advancedAbilityInfuser,
                advancedAbilityInfuserTop
        );

        registerTE();
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                oreMagicalDust.createItemBlock().setRegistryName(oreMagicalDust.getRegistryName()),
                //cropMagicalDust.createItemBlock().setRegistryName(cropMagicalDust.getRegistryName()),
                abilityInfuser.createItemBlock().setRegistryName(abilityInfuser.getRegistryName()),
                advancedAbilityInfuser.createItemBlock().setRegistryName(advancedAbilityInfuser.getRegistryName()),
                advancedAbilityInfuserTop.createItemBlock().setRegistryName(advancedAbilityInfuserTop.getRegistryName())
        );
    }

    public static void registerItemModels() {
        oreMagicalDust.registerItemModel(Item.getItemFromBlock(oreMagicalDust));
        //cropMagicalDust.registerItemModel(Item.getItemFromBlock(cropMagicalDust));
        abilityInfuser.registerItemModel(Item.getItemFromBlock(abilityInfuser));
        advancedAbilityInfuser.registerItemModel(Item.getItemFromBlock(advancedAbilityInfuser));
        advancedAbilityInfuserTop.registerItemModel(Item.getItemFromBlock(advancedAbilityInfuserTop));
    }

    private static void registerTE() {
        GameRegistry.registerTileEntity(TileEntityAbilityInfuser.class, "abilstones:ability_infuser");
        GameRegistry.registerTileEntity(TileEntityAdvancedAbilityInfuser.class, "abilstones:advanced_ability_infuser");
        //GameRegistry.registerTileEntity(TileEntityAdvancedAbilityInfuser.class, "abilstones:advanced_ability_infuser_top");
    }

}
