package com.noahc3.abilitystones.item;

import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.misc.ItemModelProvider;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(AbilityStones.creativeTab);
    }

    public void registerItemModel() {
        AbilityStones.proxy.registerItemRenderer(this, 0, name);
    }

}
