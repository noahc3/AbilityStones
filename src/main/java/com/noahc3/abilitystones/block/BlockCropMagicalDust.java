package com.noahc3.abilitystones.block;

import com.noahc3.abilitystones.item.ModItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockCropMagicalDust extends BlockCrops{

    public BlockCropMagicalDust() {

        setUnlocalizedName("magical_dust_crop");
        setRegistryName("magical_dust_crop");

    }

    @Override
    @MethodsReturnNonnullByDefault
    protected Item getSeed() {
        return ModItems.magicalDust;
    }

    @Override
    @MethodsReturnNonnullByDefault
    protected Item getCrop() {
        return ModItems.magicalDust;
    }

}
