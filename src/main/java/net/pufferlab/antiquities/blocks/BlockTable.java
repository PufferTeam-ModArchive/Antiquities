package net.pufferlab.antiquities.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.client.renderer.BlockFurnitureRender;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

public class BlockTable extends BlockMetaContainer {

    public BlockTable(String... materials) {
        super(Material.wood, materials, "table", Constants.none);
        this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityTable) {
            if (side.equals(ForgeDirection.UP)) {
                return true;
            }
        }
        return false;
    }

    public boolean canRegister() {
        return Config.enableTable;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTable();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public BlockFurnitureRender getRenderBlock() {
        return Antiquities.proxy.getTableRenderBlock();
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List<AxisAlignedBB> list,
        Entity entity) {
        AxisAlignedBB shifted = AxisAlignedBB.getBoundingBox(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F)
            .offset(x, y, z);
        if (mask.intersectsWith(shifted)) {
            list.add(shifted);
        }
    }
}
