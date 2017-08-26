package com.noahc3.abilitystones.block.abilityInfuser;

import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.guisupport.ModGuiHandler;
import jline.internal.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import com.noahc3.abilitystones.block.BlockTileEntity;

public class BlockAbilityInfuser extends BlockTileEntity<TileEntityAbilityInfuser> {

    public BlockAbilityInfuser() {
        super(Material.ROCK, "ability_infuser");
        this.setHardness(2.0f);
        this.setResistance(5.0f);
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.125F, 0.0F, 0.125F, 0.875F, 0.75F, 0.875F);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @Deprecated
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (player.isSneaking()) {
            } else {
                player.openGui(AbilityStones.instance, ModGuiHandler.ABILITY_INFUSER, world, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityAbilityInfuser tile = getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        for (int i = 0; i < 4; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (stack != null) {
                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                world.spawnEntityInWorld(item);
            }
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public Class<TileEntityAbilityInfuser> getTileEntityClass() {
        return TileEntityAbilityInfuser.class;
    }

    @Nullable
    @Override
    public TileEntityAbilityInfuser createTileEntity(World world, IBlockState state) {
        return new TileEntityAbilityInfuser();
    }

}
