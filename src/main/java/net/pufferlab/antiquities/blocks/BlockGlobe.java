package net.pufferlab.antiquities.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.Registry;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.tileentities.TileEntityGlobe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGlobe extends BlockAntiquities {

    private IIcon[] icons;

    public BlockGlobe() {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundTypeWood);
        this.setBlockName(Antiquities.MODID + ".globe");
        this.setCreativeTab(Registry.creativeTab);
        this.canBlockGrass = true;
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

    @Override
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        super.onBlockPlacedBy(worldIn, x, y, z, placer, itemIn);

        int yaw = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int metayaw = Utils.getDirectionXZYaw(yaw);
        TileEntityGlobe globe = (TileEntityGlobe) worldIn.getTileEntity(x, y, z);
        if (globe != null) {
            globe.setFacingMeta(metayaw);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[1];
        icons[0] = register.registerIcon(Antiquities.MODID + ":globe");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return icons[0];
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
    public int getRenderType() {
        return Antiquities.proxy.getGlobeRenderID();
    }

}
