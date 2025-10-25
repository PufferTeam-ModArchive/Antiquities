package net.pufferlab.antiquities.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.tileentities.TileEntityLabel;

public class BlockLabel extends BlockMetaContainer {

    public BlockLabel(String... materials) {
        super(Material.wood, materials, "label", Constants.none);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityLabel();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        super.onBlockPlacedBy(worldIn, x, y, z, placer, itemIn);

        int yaw = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int metayaw = Utils.getDirectionXZYaw(yaw);
        TileEntityLabel Label = (TileEntityLabel) worldIn.getTileEntity(x, y, z);
        if (Label != null) {
            Label.setFacingMeta(metayaw);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);

        if (world.getTileEntity(x, y, z) instanceof TileEntityLabel label) {
            ItemStack heldItem = player.getHeldItem();

            if (heldItem != null) {
                if (Block.getBlockFromItem(heldItem.getItem()) instanceof BlockLabel) {
                    return false;
                }
            }

            addItem(world, x, y, z, label, player, heldItem, 0);
            return true;
        }
        return false;
    }

    public boolean addItemToPile(World world, int x, int y, int z, ItemStack heldItem, TileEntityLabel label,
        EntityPlayer player) {
        boolean success = false;
        if (label.canAddItemInPile()) {
            success = true;
            label.addItemInPile(heldItem);
            if (heldItem.stackSize > 0) {
                player.getHeldItem().stackSize--;
            } else {
                player.inventory.setInventorySlotContents(
                    player.inventory.currentItem,
                    new ItemStack(Item.getItemFromBlock(Blocks.air)));
            }
        }
        return success;
    }

    @Override
    public void onBlockPreDestroy(World worldIn, int x, int y, int z, int meta) {
        super.onBlockPreDestroy(worldIn, x, y, z, meta);

        dropItems(worldIn, x, y, z);
    }

    private void addItem(World world, int i, int j, int k, TileEntityLabel tileLabel, EntityPlayer player,
        ItemStack playerhand, int slotNum) {
        boolean stuffadd = tileLabel.addItemInSlot(slotNum, playerhand);
        if (stuffadd) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
        } else {
            dropItem(world, i, j, k, slotNum);
            tileLabel.removeItemsInSlot(slotNum);
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
                spawnEntityClientSensitive(world, entityItem);
                item.stackSize = 0;
            }
        }
    }

    private boolean dropItem(World world, int x, int y, int z, int index) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) return false;
        TileEntityLabel Label = (TileEntityLabel) tileEntity;
        ItemStack item = Label.getInventoryStack(index);
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
            spawnEntityClientSensitive(world, entityItem);
            item.stackSize = 0;
            return true;
        }
        return false;
    }

    public void spawnEntityClientSensitive(World world, Entity entityItem) {
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity) entityItem);
        }
    }

    public boolean canRegister() {
        return Config.enableLabel;
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
    public int getRenderType() {
        return Antiquities.proxy.getLabelRenderID();
    }
}
