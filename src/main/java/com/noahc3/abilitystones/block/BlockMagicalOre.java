package com.noahc3.abilitystones.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import com.noahc3.abilitystones.item.ModItems;

import java.util.Random;

public class BlockMagicalOre extends BlockOre{


    public BlockMagicalOre(String name) {
        super(name);
    }

    @Override
    @MethodsReturnNonnullByDefault
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.magicalDust;
    }

    @Override
    public int quantityDropped(Random rand){
        return 1 + rand.nextInt(3);
    }

}
