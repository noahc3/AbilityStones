package com.noahc3.abilitystones;

import com.noahc3.abilitystones.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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

@Mod(modid = AbilityStones.modID, name = AbilityStones.name, version = AbilityStones.version, acceptedMinecraftVersions = "[1.12, 1.12.1]")
public class AbilityStones {

    public static final String modID = "abilstones";
    public static final String name = "Ability Stones";
    public static final String version = "12.2.0.0";

    public static final AbilityStonesTab creativeTab = new AbilityStonesTab();

    public static int defaultTimeInSeconds = 180;
    public static int amplificationTimeInSeconds = 60;

    @Mod.Instance(modID)
    public static AbilityStones instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is initializing...");
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        //CraftingRecipes.init();
        InfuserCraftingManager.generateRecipeList();
        AdvancedInfuserCraftingManager.generateRecipeList();
        proxy.init();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SidedProxy(serverSide = "com.noahc3.abilitystones.proxy.CommonProxy", clientSide = "com.noahc3.abilitystones.proxy.ClientProxy")
    public static CommonProxy proxy;


    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerItemModels();
        }
    }

}
