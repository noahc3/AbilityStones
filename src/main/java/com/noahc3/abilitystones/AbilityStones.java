package com.noahc3.abilitystones;

import com.noahc3.abilitystones.block.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.noahc3.abilitystones.guisupport.ModGuiHandler;
import com.noahc3.abilitystones.item.ModItems;
import com.noahc3.abilitystones.misc.AbilityStonesTab;
import com.noahc3.abilitystones.proxy.CommonProxy;
import com.noahc3.abilitystones.recipe.AdvancedInfuserCraftingManager;
import com.noahc3.abilitystones.recipe.CraftingRecipes;
import com.noahc3.abilitystones.recipe.InfuserCraftingManager;
import com.noahc3.abilitystones.world.ModWorldGen;

@Mod(modid = AbilityStones.modID, name = AbilityStones.name, version = AbilityStones.version, acceptedMinecraftVersions = "[1.10.2]")
public class AbilityStones {

    public static final String modID = "abilstones";
    public static final String name = "Ability Stones";
    public static final String version = "10.2.0.0 beta 2";

    public static final AbilityStonesTab creativeTab = new AbilityStonesTab();

    public static int defaultTimeInSeconds = 180;
    public static int amplificationTimeInSeconds = 60;
    public static ItemStack blankItemStack = null;

    @Mod.Instance(modID)
    public static AbilityStones instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is initializing...");
        ModBlocks.init();
        ModItems.init();
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        CraftingRecipes.init();
        InfuserCraftingManager.generateRecipeList();
        AdvancedInfuserCraftingManager.generateRecipeList();
        proxy.init();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SidedProxy(serverSide = "com.noahc3.abilitystones.proxy.CommonProxy", clientSide = "com.noahc3.abilitystones.proxy.ClientProxy")
    public static CommonProxy proxy;
}
