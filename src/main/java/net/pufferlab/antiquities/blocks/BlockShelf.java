package net.pufferlab.antiquities.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.tileentities.TileEntityShelf;

public class BlockShelf extends BlockMetaContainer {

    private int elementType;

    public BlockShelf(int shelfType, String... materials) {
        super(Material.wood, materials, "shelf_" + shelfType, Constants.none);

        this.elementType = shelfType;

        this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityShelf shelf) {
            if (shelf.facingMeta == 1) {
                this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            } else if (shelf.facingMeta == 2) {
                this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else if (shelf.facingMeta == 3) {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            } else if (shelf.facingMeta == 4) {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);

        if (world.getTileEntity(x, y, z) instanceof TileEntityShelf shelf) {
            ItemStack heldItem = player.getHeldItem();
            int slot = 0;
            int axis = 0;
            int facing = shelf.facingMeta;
            if (facing == 1 || facing == 3) {
                axis = 1;
            }
            if (facing == 2 || facing == 4) {
                axis = 2;
            }
            float slotX = hitX;
            float slotY = hitY;
            float slotZ = hitZ;
            if (axis == 1) {
                slot = getSlotFromFace(slotX, slotY, facing);
            }
            if (axis == 2) {
                slot = getSlotFromFace(slotZ, slotY, facing);
            }

            if (heldItem != null) {
                if (Block.getBlockFromItem(heldItem.getItem()) instanceof BlockShelf) {
                    return false;
                }
            }
            addItem(world, x, y, z, shelf, player, heldItem, (slot - 1), hitX, hitY, hitZ);
            return true;
        }
        return false;
    }

    public int getSlotFromFace(float number1, float number2, float facing) {
        boolean rowH = false;
        boolean rowV = false;
        int slot = 3;
        if (facing == 1 || facing == 4) {
            number1 = 1 - number1;
        }
        if (number1 < 0.5) {
            rowH = true;
        }
        if (number2 < 0.5) {
            rowV = true;
        }
        if (!rowV && rowH) {
            slot = 1;
        }
        if (rowV && !rowH) {
            slot = 4;
        }
        if (!rowV && !rowH) {
            slot = 2;
        }

        return slot;
    }

    @Override
    public void onBlockPreDestroy(World worldIn, int x, int y, int z, int meta) {
        super.onBlockPreDestroy(worldIn, x, y, z, meta);

        dropItems(worldIn, x, y, z);
    }

    private void addItem(World world, int i, int j, int k, TileEntityShelf tileShelf, EntityPlayer player,
        ItemStack playerhand, int slotNum, double hitX, double hitY, double hitZ) {
        boolean stuffadd = tileShelf.addItemInSlot(slotNum, playerhand);
        if (stuffadd) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
        } else {
            dropItem(world, i, j, k, slotNum, hitX, hitY, hitZ);
            tileShelf.removeItemsInSlot(slotNum);
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

    private boolean dropItem(World world, int x, int y, int z, int index, double hitX, double hitY, double hitZ) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) return false;
        TileEntityShelf shelf = (TileEntityShelf) tileEntity;
        ItemStack item = shelf.getInventoryStack(index);
        int facing = shelf.facingMeta;
        double hitN = 0.25;
        if (facing == 3 || facing == 4) {
            hitN = 0.75;
        }
        if (facing == 1 || facing == 3) {
            hitZ = hitN;
        }
        if (facing == 2 || facing == 4) {
            hitX = hitN;
        }
        if (item != null && item.stackSize > 0) {
            EntityItem entityItem = new EntityItem(
                world,
                x + hitX,
                y + hitY,
                z + hitZ,
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

    @Override
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        super.onBlockPlacedBy(worldIn, x, y, z, placer, itemIn);

        int yaw = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int metayaw = Utils.getDirectionXZYaw(yaw);
        TileEntityShelf shelf = (TileEntityShelf) worldIn.getTileEntity(x, y, z);
        if (shelf != null) {
            shelf.setFacingMeta(metayaw);
        }
    }

    public void spawnEntityClientSensitive(World world, Entity entityItem) {
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity) entityItem);
        }
    }

    @Override
    public int getBlockType() {
        return this.elementType;
    }

    public boolean canRegister() {
        return Config.enableShelf;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityShelf();
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
        return Antiquities.proxy.getShelfRenderID();
    }
}
