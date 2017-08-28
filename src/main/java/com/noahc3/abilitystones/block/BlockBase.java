package com.noahc3.abilitystones.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.misc.ItemModelProvider;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements ItemModelProvider{

    protected String name;

    public BlockBase(Material material, String name) {

        super(material);

        this.name = name;

        setUnlocalizedName(name);
        setRegistryName(name);

        setCreativeTab(AbilityStones.creativeTab);

    }

    @Override
    public void registerItemModel(Item itemBlock) {

        AbilityStones.proxy.registerItemRenderer(itemBlock, 0, name);

    }

    public Item createItemBlock() {
        return new ItemBlock(this);
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }


}
