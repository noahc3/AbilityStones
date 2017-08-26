package com.noahc3.abilitystones.block;

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


    }

    private static <T extends Block> T register(T block, ItemBlock itemBlock) {

        GameRegistry.register(block);

        if (itemBlock != null) {
            GameRegistry.register(itemBlock);
        }
        if (block instanceof ItemModelProvider) {
            ((ItemModelProvider)block).registerItemModel(itemBlock);
        }

        if (block instanceof BlockTileEntity) {
            GameRegistry.registerTileEntity(((BlockTileEntity<?>)block).getTileEntityClass(), block.getRegistryName().toString());
        }

        return block;
    }

    private static <T extends Block> T register(T block) {

        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return register(block, itemBlock);

    }

}
