package net.pufferlab.antiquities.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.tileentities.TileEntityPile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPile extends BlockContainer {

    private IIcon[] icons;
    public ItemStack heldItem;

    public BlockPile() {
        super(Material.iron);
        this.setStepSound(soundTypeMetal);
        this.setBlockName(Antiquities.MODID + ".pile");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityPile();
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityPile pile) {
            float size = 0.125F;
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F + (size * pile.getLayer()), 1.0F);
        }
    }

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
        TileEntityPile pile = (TileEntityPile) worldIn.getTileEntity(x, y, z);
        pile.setInventorySlotContentsUpdate(0, placer.getHeldItem());
        if (pile != null) {
            pile.setFacingMeta(metayaw);
        }
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityPile) {
            TileEntityPile pile = (TileEntityPile) te;
            if (pile.getNextSlot() == -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        super.onNeighborBlockChange(worldIn, x, y, z, neighbor);

        Block block = worldIn.getBlock(x, y, z);
        if (!worldIn.isSideSolid(x, y - 1, z, ForgeDirection.UP)) {
            worldIn.setBlockToAir(x, y, z);
            block.onBlockPreDestroy(worldIn, x, y, z, worldIn.getBlockMetadata(x, y, z));
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);

        if (world.getTileEntity(x, y, z) instanceof TileEntityPile pile) {
            ItemStack heldItem = player.getHeldItem();
            int[] oreIDS = OreDictionary.getOreIDs(heldItem);
            boolean canPlace = false;
            for (int oreID : oreIDS) {
                String oreName = OreDictionary.getOreName(oreID);
                if (oreName.contains("ingot")) {
                    canPlace = true;
                }
            }
            if (canPlace) {
                int m = 1;
                if (player.isSneaking()) {
                    m = heldItem.stackSize;
                }
                playStepSound(world, x, y, z);
                for (int i = 0; i < m; i++) {
                    if (!pile.canAddItemInPile()) {
                        int j = getNextPile(world, x, y, z);
                        if (j != 0) {
                            TileEntityPile pile2 = (TileEntityPile) world.getTileEntity(x, y + j, z);
                            if (pile2 != null) {
                                addItemToPile(heldItem, pile2, player);
                            } else {
                                place(heldItem, world, x, y + j, z, this, 0, player);
                            }
                        }
                    } else {
                        addItemToPile(heldItem, pile, player);
                    }
                }

                return true;
            }

        }
        return false;
    }

    public void playStepSound(World world, int x, int y, int z) {
        world.playSoundEffect(
            x + 0.5f,
            y + 0.5f,
            z + 0.5f,
            this.stepSound.func_150496_b(),
            (this.stepSound.getVolume() + 1.0F) / 2.0F,
            this.stepSound.getPitch() * 0.8F);
    }

    public void addItemToPile(ItemStack heldItem, TileEntityPile pile, EntityPlayer player) {
        if (pile.canAddItemInPile()) {
            pile.addItemInPile(heldItem);
            if (heldItem.stackSize > 0) {
                player.getHeldItem().stackSize--;
            } else {
                player.inventory.setInventorySlotContents(
                    player.inventory.currentItem,
                    new ItemStack(Item.getItemFromBlock(Blocks.air)));
            }
        }
    }

    public int getNextPile(World world, int x, int y, int z) {
        for (int j = 0; j < 10; j++) {
            TileEntity te = world.getTileEntity(x, y + j, z);
            if (te instanceof TileEntityPile) {
                TileEntityPile pile2 = (TileEntityPile) te;
                if (pile2.getNextSlot() != -1) {
                    return j;
                }
            }
            if (te == null) {
                return j;
            }

        }
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        if (worldIn.getTileEntity(x, y, z) instanceof TileEntityPile te) {
            return te.getLastItem();
        }
        return null;
    }

    @Override
    public int getDamageValue(World worldIn, int x, int y, int z) {
        if (worldIn.getTileEntity(x, y, z) instanceof TileEntityPile te) {
            return te.getLastItemMeta();
        }
        return 0;
    }

    private void place(ItemStack stack, World world, int x, int y, int z, Block toPlace, int metadata,
        EntityPlayer player) {
        if (world.isAirBlock(x, y, z)) {
            if (world.checkNoEntityCollision(toPlace.getCollisionBoundingBoxFromPool(world, x, y, z))
                && world.setBlock(x, y, z, toPlace, metadata, 3)) {
                world.setBlock(x, y, z, toPlace, metadata, 2);
                player.getHeldItem().stackSize--;
                toPlace.onBlockPlacedBy(world, x, y, z, player, stack);
                world.playSoundEffect(
                    x + 0.5f,
                    y + 0.5f,
                    z + 0.5f,
                    toPlace.stepSound.func_150496_b(),
                    (toPlace.stepSound.getVolume() + 1.0F) / 2.0F,
                    toPlace.stepSound.getPitch() * 0.8F);
            }
        }

    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return null;
    }

    @Override
    public void onBlockPreDestroy(World worldIn, int x, int y, int z, int meta) {
        super.onBlockPreDestroy(worldIn, x, y, z, meta);

        dropItems(worldIn, x, y, z);
    }

    private void dropItems(World world, int i, int j, int k) {
        Random rando = new Random();
        TileEntity tileEntity = world.getTileEntity(i, j, k);
        if (!(tileEntity instanceof IInventory)) return;
        IInventory inventory = (IInventory) tileEntity;
        for (int x = 0; x < inventory.getSizeInventory(); x++) {
            ItemStack item = inventory.getStackInSlot(x);
            if (item != null && item.stackSize > 0) {
                float ri = rando.nextFloat() * 0.8F + 0.1F;
                float rj = rando.nextFloat() * 0.8F + 0.1F;
                float rk = rando.nextFloat() * 0.8F + 0.1F;
                EntityItem entityItem = new EntityItem(
                    world,
                    (i + ri),
                    (j + rj),
                    (k + rk),
                    new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
                if (item.hasTagCompound()) entityItem.getEntityItem()
                    .setTagCompound(
                        (NBTTagCompound) item.getTagCompound()
                            .copy());
                float factor = 0.05F;
                entityItem.motionX = rando.nextGaussian() * factor;
                entityItem.motionY = rando.nextGaussian() * factor + 0.20000000298023224D;
                entityItem.motionZ = rando.nextGaussian() * factor;
                spawnEntityClientSensitive(world, entityItem);
                item.stackSize = 0;
            }
        }
    }

    public void spawnEntityClientSensitive(World world, Entity entityItem) {
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity) entityItem);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[2];
        icons[0] = register.registerIcon(Antiquities.MODID + ":pile");
        icons[1] = register.registerIcon(Antiquities.MODID + ":null_pile");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 99) {
            return icons[0];
        }
        return icons[1];
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
        return Antiquities.proxy.getPileRenderID();
    }
}
