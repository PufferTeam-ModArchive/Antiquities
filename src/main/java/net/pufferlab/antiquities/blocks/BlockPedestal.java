package net.pufferlab.antiquities.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.client.renderer.BlockFurnitureRender;
import net.pufferlab.antiquities.tileentities.TileEntityPedestal;

public class BlockPedestal extends BlockMetaContainer {

    public BlockPedestal(String... materials) {
        super(Material.wood, materials, "pedestal", Constants.none);
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityPedestal();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);

        if (world.getTileEntity(x, y, z) instanceof TileEntityPedestal pedestal) {
            ItemStack heldItem = player.getHeldItem();

            if (heldItem != null) {
                if (Utils.containsOreDict(heldItem, "tool")) {
                    addItem(world, x, y, z, pedestal, player, heldItem, 0);
                    return true;
                }

                if (!(heldItem.getItem() instanceof ItemTool) && !(heldItem.getItem() instanceof ItemSword)
                    && !(heldItem.getItem() instanceof ItemHoe)) {
                    return false;
                }
                if (Block.getBlockFromItem(heldItem.getItem()) instanceof BlockPedestal) {
                    return false;
                }
            }

            addItem(world, x, y, z, pedestal, player, heldItem, 0);
            return true;
        }
        return false;
    }

    @Override
    public void onBlockPreDestroy(World worldIn, int x, int y, int z, int meta) {
        super.onBlockPreDestroy(worldIn, x, y, z, meta);

        dropItems(worldIn, x, y, z);
    }

    private void addItem(World world, int i, int j, int k, TileEntityPedestal tilePedestal, EntityPlayer player,
        ItemStack playerhand, int slotNum) {
        boolean stuffadd = tilePedestal.addItemInSlot(slotNum, playerhand);
        if (stuffadd) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
        } else {
            dropItem(world, i, j, k, slotNum);
            tilePedestal.removeItemsInSlot(slotNum);
        }
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
                spawnEntity(world, entityItem);
                item.stackSize = 0;
            }
        }
    }

    private boolean dropItem(World world, int x, int y, int z, int index) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) return false;
        TileEntityPedestal pedestal = (TileEntityPedestal) tileEntity;
        ItemStack item = pedestal.getInventoryStack(index);
        if (item != null && item.stackSize > 0) {
            EntityItem entityItem = new EntityItem(
                world,
                x + 0.5,
                y + 0.5,
                z + 0.5,
                new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
            if (item.hasTagCompound()) entityItem.getEntityItem()
                .setTagCompound(
                    (NBTTagCompound) item.getTagCompound()
                        .copy());
            entityItem.motionX = 0.0D;
            entityItem.motionY = 0.0D;
            entityItem.motionZ = 0.0D;
            spawnEntity(world, entityItem);
            item.stackSize = 0;
            return true;
        }
        return false;
    }

    public boolean canRegister() {
        return Config.enablePedestal;
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
        return Antiquities.proxy.getPedestalRenderBlock();
    }
}
