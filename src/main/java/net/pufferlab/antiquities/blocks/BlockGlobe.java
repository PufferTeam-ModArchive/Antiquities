package net.pufferlab.antiquities.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pufferlab.antiquities.*;
import net.pufferlab.antiquities.client.renderer.BlockFurnitureRender;
import net.pufferlab.antiquities.tileentities.TileEntityGlobe;

public class BlockGlobe extends BlockMetaContainer {

    public BlockGlobe(String... materials) {
        super(Material.wood, materials, "globe", Constants.none);
        this.setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 1F, 0.9F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);

        if (world.getTileEntity(x, y, z) instanceof TileEntityGlobe globe) {
            globe.addSpeed();
            return true;
        }
        return false;
    }

    public boolean canRegister() {
        return Config.enableGlobe;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityGlobe();
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
        return Antiquities.proxy.getGlobeRenderBlock();
    }
}
