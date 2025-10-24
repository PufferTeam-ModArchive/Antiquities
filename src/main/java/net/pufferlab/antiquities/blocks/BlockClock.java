package net.pufferlab.antiquities.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.Registry;
import net.pufferlab.antiquities.Utils;
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

    @Override
    public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask,
        List<AxisAlignedBB> list, Entity collider) {
        this.setBlockBoundsBasedOnState(worldIn, x, y, z);
        super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        super.onBlockPlacedBy(worldIn, x, y, z, placer, itemIn);

        int yaw = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int metayaw = Utils.getDirectionXZYaw(yaw);
        TileEntityClock clock = (TileEntityClock) worldIn.getTileEntity(x, y, z);
        if (clock != null) {
            clock.setFacingMeta(metayaw);
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
    public int getRenderType() {
        return Antiquities.proxy.getClockRenderID();
    }

}
