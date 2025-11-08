package net.pufferlab.antiquities.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.Registry;
import net.pufferlab.antiquities.client.renderer.BlockFurnitureRender;
import net.pufferlab.antiquities.tileentities.TileEntityClock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockClock extends BlockAntiquities {

    private IIcon[] icons;
    private IIcon[] icons_model;

    public BlockClock() {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundTypeWood);
        this.setBlockName(Antiquities.MODID + ".clock");
        this.setCreativeTab(Registry.creativeTab);
        this.canBlockGrass = true;
        this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityClock clock) {
            float size = 0.125F;
            if (clock.facingMeta == 1) {
                this.setBlockBounds(0.0F, 0.0F, 1 - size, 1.0F, 1.0F, 1.0F);
            } else if (clock.facingMeta == 2) {
                this.setBlockBounds(1 - size, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else if (clock.facingMeta == 3) {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, size);
            } else if (clock.facingMeta == 4) {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, size, 1.0F, 1.0F);
            } else {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[1];
        icons_model = new IIcon[1];
        icons[0] = register.registerIcon(Antiquities.MODID + ":clock");
        icons_model[0] = register.registerIcon(Antiquities.MODID + ":clock_model");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 99) {
            return icons_model[0];
        }
        return icons[0];
    }

    public boolean canRegister() {
        return Config.enableClock;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityClock();
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
        return Antiquities.proxy.getClockRenderBlock();
    }
}
