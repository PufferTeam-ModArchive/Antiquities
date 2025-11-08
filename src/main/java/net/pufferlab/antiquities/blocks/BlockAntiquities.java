package net.pufferlab.antiquities.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.client.renderer.BlockFurnitureRender;
import net.pufferlab.antiquities.tileentities.TileEntityAntiquities;
import net.pufferlab.antiquities.tileentities.TileEntityMetaFacing;

public abstract class BlockAntiquities extends BlockContainer {

    protected BlockAntiquities(Material material) {
        super(material);
    }

    public boolean canRegister() {
        return false;
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List<AxisAlignedBB> list,
        Entity entity) {
        TileEntity te = world.getTileEntity(x, y, z);
        BlockFurnitureRender renderer = this.getRenderBlock();
        if (te instanceof TileEntityAntiquities) {
            List<AxisAlignedBB> list2 = renderer.buildBlockBounds(world, x, y, z, te.getBlockType());
            if (list2 != null) {
                List<AxisAlignedBB> safeList = new ArrayList<>(list2);
                for (AxisAlignedBB bb : safeList) {
                    if (bb != null) {
                        AxisAlignedBB shifted = bb.copy()
                            .offset(x, y, z);
                        if (mask.intersectsWith(shifted)) {
                            list.add(shifted);
                        }
                    }
                }
            } else {
                super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        super.onNeighborBlockChange(world, x, y, z, neighbor);
        world.markBlockForUpdate(x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        super.onBlockPlacedBy(worldIn, x, y, z, placer, itemIn);

        int metayaw = Utils.getMetaYaw(placer.rotationYaw);
        TileEntity te = worldIn.getTileEntity(x, y, z);
        if (te instanceof TileEntityMetaFacing tef) {
            tef.setFacingMeta(metayaw);
        }
    }

    public void spawnEntity(World world, Entity entityItem) {
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity) entityItem);
        }
    }

    @Override
    public int getRenderType() {
        BlockFurnitureRender renderer = getRenderBlock();
        if (renderer == null) return 0;
        return renderer.getRenderId();
    }

    public BlockFurnitureRender getRenderBlock() {
        return null;
    }
}
