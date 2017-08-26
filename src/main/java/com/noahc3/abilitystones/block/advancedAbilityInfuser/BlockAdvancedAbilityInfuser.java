package com.noahc3.abilitystones.block.advancedAbilityInfuser;

import com.noahc3.abilitystones.AbilityStones;
import com.noahc3.abilitystones.block.ModBlocks;
import com.noahc3.abilitystones.guisupport.ModGuiHandler;
import jline.internal.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import com.noahc3.abilitystones.block.BlockTileEntity;

import java.util.Random;

public class BlockAdvancedAbilityInfuser extends BlockTileEntity<TileEntityAdvancedAbilityInfuser> {

    public BlockAdvancedAbilityInfuser(String name) {


        super(Material.ROCK, name);
        this.setHardness(2.0f);
        this.setResistance(5.0f);

        if (this == ModBlocks.advancedAbilityInfuserTop) {
            this.translucent = true;
        }
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
        if (this == ModBlocks.advancedAbilityInfuser) {
            return new AxisAlignedBB(0, 0, 0, 1, 1.85F, 1);
        } else {
            return new AxisAlignedBB(0, -1, 0, 1, 0.85F, 1);
        }

    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @Deprecated
    public EnumBlockRenderType getRenderType(IBlockState state) {

        if (this == ModBlocks.advancedAbilityInfuser) {
            return EnumBlockRenderType.MODEL;
        } else {
            return EnumBlockRenderType.INVISIBLE;
        }

    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return pos.getY() >= worldIn.getHeight() - 1 ? false : super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (player.isSneaking()) {
            } else {
                if (this == ModBlocks.advancedAbilityInfuser) {
                    player.openGui(AbilityStones.instance, ModGuiHandler.ADVANCED_ABILITY_INFUSER, world, pos.getX(), pos.getY(), pos.getZ());
                } else if (this == ModBlocks.advancedAbilityInfuserTop) {
                    player.openGui(AbilityStones.instance, ModGuiHandler.ADVANCED_ABILITY_INFUSER, world, pos.getX(), pos.getY() - 1, pos.getZ());
                }

            }
        }
        return true;
    }

    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

        if (this == ModBlocks.advancedAbilityInfuser) {
            world.setBlockState(pos.up(), ModBlocks.advancedAbilityInfuserTop.getDefaultState());
        }

        return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);

    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (this == ModBlocks.advancedAbilityInfuserTop) {
            world.destroyBlock(pos.down(), true);
        }
        else {
            world.destroyBlock(pos.up(), false);
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityAdvancedAbilityInfuser tile = getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        for (int i = 0; i < 7; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (stack != null) {
                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                world.spawnEntityInWorld(item);
            }
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public Class<TileEntityAdvancedAbilityInfuser> getTileEntityClass() {
        return TileEntityAdvancedAbilityInfuser.class;
    }

    @Nullable
    @Override
    public TileEntityAdvancedAbilityInfuser createTileEntity(World world, IBlockState state) {
        return new TileEntityAdvancedAbilityInfuser();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (this == ModBlocks.advancedAbilityInfuserTop) {
            return null;
        } else {
            return new ItemStack(ModBlocks.advancedAbilityInfuser).getItem();
        }

    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(ModBlocks.advancedAbilityInfuser);
    }


}
