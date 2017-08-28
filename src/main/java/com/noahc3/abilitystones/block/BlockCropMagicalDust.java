package com.noahc3.abilitystones.block;

import com.noahc3.abilitystones.item.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockCropMagicalDust extends BlockCrops{

    public BlockCropMagicalDust() {

        setUnlocalizedName("magical_dust_crop");
        setRegistryName("magical_dust_crop");

    }

    @Override
    protected Item getSeed() {
        return ModItems.magicalDust;
    }

    @Override
    protected Item getCrop() {
        return ModItems.magicalDust;
    }

}
