package com.noahc3.abilitystones.block;

import com.noahc3.abilitystones.block.abilityInfuser.TileEntityAbilityInfuser;
import com.noahc3.abilitystones.block.advancedAbilityInfuser.TileEntityAdvancedAbilityInfuser;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.noahc3.abilitystones.block.abilityInfuser.BlockAbilityInfuser;
import com.noahc3.abilitystones.block.advancedAbilityInfuser.BlockAdvancedAbilityInfuser;
import com.noahc3.abilitystones.block.advancedAbilityInfuser.BlockAdvancedAbilityInfuserTop;
import com.noahc3.abilitystones.misc.ItemModelProvider;

public class ModBlocks {

    public static BlockMagicalOre oreMagicalDust;
    //public static BlockCropMagicalDust cropMagicalDust;
    public static BlockAbilityInfuser abilityInfuser;
    public static BlockAdvancedAbilityInfuser advancedAbilityInfuser;
    public static BlockAdvancedAbilityInfuserTop advancedAbilityInfuserTop;

    public static void init() {
        oreMagicalDust = register(new BlockMagicalOre("magical_dust_ore"));
        //cropMagicalDust = register(new BlockCropMagicalDust(), null);
        abilityInfuser = register(new BlockAbilityInfuser());
        advancedAbilityInfuser = register(new BlockAdvancedAbilityInfuser("advanced_ability_infuser"));
        advancedAbilityInfuserTop = register(new BlockAdvancedAbilityInfuserTop());

        registerTE();
    }

    private static <T extends Block> T register(T block, ItemBlock itemBlock) {

        GameRegistry.register(block);

        if (itemBlock != null) {
            GameRegistry.register(itemBlock);
        }
        if (block instanceof ItemModelProvider) {
            ((ItemModelProvider)block).registerItemModel(itemBlock);
        }

        System.out.println(block.getRegistryName().toString());

//        if (block instanceof BlockTileEntity) {
//
//        }

        return block;
    }

    private static void registerTE() {
        GameRegistry.registerTileEntity(TileEntityAbilityInfuser.class, "abilstones:ability_infuser");
        GameRegistry.registerTileEntity(TileEntityAdvancedAbilityInfuser.class, "abilstones:advanced_ability_infuser");
        //GameRegistry.registerTileEntity(TileEntityAdvancedAbilityInfuser.class, "abilstones:advanced_ability_infuser_top");
    }

    private static <T extends Block> T register(T block) {

        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        itemBlock.setUnlocalizedName(block.getUnlocalizedName());
        return register(block, itemBlock);

    }

}
