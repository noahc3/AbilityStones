package com.noahc3.abilitystones.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import com.noahc3.abilitystones.misc.ASEventHandler;


public class CommonProxy {

    public void init() {
        MinecraftForge.EVENT_BUS.register(new ASEventHandler());
    }

    public void registerItemRenderer(Item item, int meta, String id) {
    }

    //public String localize(String unlocalized, Object... args) {
    //    return I18n.translateToLocalFormatted(unlocalized, args);
    //}

}
