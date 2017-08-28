package com.noahc3.abilitystones.item;

import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.block.ModBlocks;
import com.noahc3.abilitystones.misc.ItemModelProvider;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class ItemMagicalDust extends ItemBase implements ItemModelProvider {

    public ItemMagicalDust() {
        //super(ModBlocks.cropMagicalDust, Blocks.FARMLAND);
        super("magical_dust");
//        setUnlocalizedName("magical_dust");
//        setRegistryName("magical_dust");
//        setCreativeTab(AbilityStones.creativeTab);
    }

    @Override
    public void registerItemModel(Item item) {
        AbilityStones.proxy.registerItemRenderer(item, 0, "magical_dust");
    }

}
