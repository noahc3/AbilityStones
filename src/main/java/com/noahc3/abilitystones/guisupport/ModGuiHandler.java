package com.noahc3.abilitystones.guisupport;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import com.noahc3.abilitystones.block.abilityInfuser.ContainerAbilityInfuser;
import com.noahc3.abilitystones.block.abilityInfuser.GuiAbilityInfuser;
import com.noahc3.abilitystones.block.abilityInfuser.TileEntityAbilityInfuser;
import com.noahc3.abilitystones.block.advancedAbilityInfuser.ContainerAdvancedAbilityInfuser;
import com.noahc3.abilitystones.block.advancedAbilityInfuser.GuiAdvancedAbilityInfuser;
import com.noahc3.abilitystones.block.advancedAbilityInfuser.TileEntityAdvancedAbilityInfuser;

public class ModGuiHandler implements IGuiHandler {

    public static final int ABILITY_INFUSER = 0;
    public static final int ADVANCED_ABILITY_INFUSER = 1;

    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case ABILITY_INFUSER:
                return new ContainerAbilityInfuser(player.inventory, (TileEntityAbilityInfuser)world.getTileEntity(new BlockPos(x, y, z)));
            case ADVANCED_ABILITY_INFUSER:
                return new ContainerAdvancedAbilityInfuser(player.inventory, (TileEntityAdvancedAbilityInfuser)world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case ABILITY_INFUSER:
                return new GuiAbilityInfuser(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            case ADVANCED_ABILITY_INFUSER:
                return new GuiAdvancedAbilityInfuser(getServerGuiElement(ID, player, world, x, y, z), player.inventory, (TileEntityAdvancedAbilityInfuser)world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

}

