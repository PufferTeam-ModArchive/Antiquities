package net.pufferlab.antiquities.blocks;

import java.util.*;

import net.minecraft.block.Block;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.client.renderer.BlockFurnitureRender;
import net.pufferlab.antiquities.tileentities.TileEntityPile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPile extends BlockAntiquities {

    private IIcon[] icons;
    private IIcon[] metalIcons;

    public BlockPile() {
        super(Material.iron);
        this.setStepSound(soundTypeMetal);
        this.setHardness(0.5F);
        this.canBlockGrass = true;
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

    @Override
    public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask,
        List<AxisAlignedBB> list, Entity collider) {
        this.setBlockBoundsBasedOnState(worldIn, x, y, z);
        super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        super.onBlockPlacedBy(worldIn, x, y, z, placer, itemIn);

        int metayaw = Utils.getMetaYaw(placer.rotationYaw);
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
            boolean canPlace = Utils.isValidMetal(heldItem);
            if (canPlace) {
                int m = 1;
                if (player.isSneaking()) {
                    m = heldItem.stackSize;
                }
                boolean success = false;
                for (int i = 0; i < m; i++) {
                    if (!pile.canAddItemInPile()) {
                        int j = getNextPile(world, x, y, z);
                        if (j != 0) {
                            TileEntity te0 = world.getTileEntity(x, y + j, z);
                            TileEntityPile pile2 = null;
                            if (te0 instanceof TileEntityPile) {
                                pile2 = (TileEntityPile) te0;
                            }
                            if (pile2 != null) {
                                success = addItemToPile(world, x, y, z, heldItem, pile2, player);
                            } else {
                                success = place(heldItem, world, x, y + j, z, this, 0, player);
                            }
                        }
                    } else {
                        success = addItemToPile(world, x, y, z, heldItem, pile, player);
                    }
                }

                if (success) {
                    playStepSound(world, x, y, z);
                }
                return success;
            } else {
                if (pile.canRemoveItemInPile()) {
                    int j = getPrevPile(world, x, y, z);
                    TileEntity te0 = world.getTileEntity(x, y + j, z);
                    TileEntityPile pile2 = null;
                    if (te0 instanceof TileEntityPile) {
                        pile2 = (TileEntityPile) te0;
                    }
                    if (pile2 != null) {
                        if (pile2.getNextSlot() != 1) {
                            dropItem(world, x, y + j, z, pile2.getPrevSlot());
                            removeItemToPile(pile2);
                        } else {
                            dropItem(world, x, y + j, z, 0);
                            pile2.setInventorySlotContentsUpdate(0);
                            world.setBlockToAir(x, y + j, z);
                        }
                    }
                    return true;
                }
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

    public boolean addItemToPile(World world, int x, int y, int z, ItemStack heldItem, TileEntityPile pile,
        EntityPlayer player) {
        boolean success = false;
        if (pile.canAddItemInPile()) {
            if (world.checkNoEntityCollision(getNextLayerBB(world, x, y, z))) {
                success = true;
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
        return success;
    }

    public void removeItemToPile(TileEntityPile pile) {
        if (pile.canRemoveItemInPile()) {
            pile.removeItemInPile();
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

    public int getPrevPile(World world, int x, int y, int z) {
        for (int j = 0; j < 10; j++) {
            TileEntity te = world.getTileEntity(x, y + j, z);
            if (te == null) {
                return j - 1;
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

    private boolean place(ItemStack stack, World world, int x, int y, int z, Block toPlace, int metadata,
        EntityPlayer player) {
        boolean success = false;
        if (world.isAirBlock(x, y, z)) {
            if (world.checkNoEntityCollision(toPlace.getCollisionBoundingBoxFromPool(world, x, y, z))
                && world.setBlock(x, y, z, toPlace, metadata, 3)) {
                world.setBlock(x, y, z, toPlace, metadata, 2);
                success = true;
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
        return success;
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

    private boolean dropItem(World world, int x, int y, int z, int index) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) return false;
        TileEntityPile pile = (TileEntityPile) tileEntity;
        ItemStack item = null;
        if ((index < pile.getSizeInventory()) && (index >= 0)) {
            item = pile.getInventoryStack(index);
        }
        if (item != null && item.stackSize > 0) {
            EntityItem entityItem = new EntityItem(
                world,
                x + 0.5,
                y + 0.25F + (0.125F * pile.getLayer()),
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

    private void dropItems(World world, int i, int j, int k) {
        TileEntity tileEntity = world.getTileEntity(i, j, k);
        if (!(tileEntity instanceof IInventory)) return;
        IInventory inventory = (IInventory) tileEntity;
        Map<String, ItemStack> map = new HashMap<>();
        for (int x = 0; x < inventory.getSizeInventory(); x++) {
            ItemStack item = inventory.getStackInSlot(x);
            if (item != null && item.stackSize > 0) {
                Item itemO = item.getItem();
                int itemM = item.getItemDamage();
                String key = Utils.getItemKey(itemO, itemM);
                if (map.containsKey(key)) {
                    ItemStack itemS = map.get(key);
                    itemS.stackSize++;
                } else {
                    map.put(key, item.copy());
                }
            }
        }
        for (ItemStack item : map.values()) {
            EntityItem entityItem = new EntityItem(
                world,
                (i + 0.5),
                (j + 0.5),
                (k + 0.5),
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
        }
    }

    public void spawnEntityClientSensitive(World world, Entity entityItem) {
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity) entityItem);
        }
    }

    public AxisAlignedBB getNextLayerBB(World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityPile pile) {
            float size = 0.125F;
            return AxisAlignedBB.getBoundingBox(
                (double) x + 0.0F,
                (double) y + 0.0F,
                (double) z + 0.0F,
                (double) x + 1.0F,
                (double) y + 0.125F + (size * (pile.getLayer() + 1)),
                (double) z + 1.0F);
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[2];
        metalIcons = new IIcon[Config.ingotPileMetals.length];
        icons[0] = register.registerIcon(Antiquities.MODID + ":pile");
        for (int i = 0; i < Config.ingotPileMetals.length; i++) {
            metalIcons[i] = register.registerIcon(Antiquities.MODID + ":" + Config.ingotPileMetals[i] + "_ingot");
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side > 99) {
            int actualI = side - 100;
            return metalIcons[actualI];
        }
        return icons[0];
    }

    public boolean canRegister() {
        return Config.enableIngotPile;
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
        return Antiquities.proxy.getPileRenderBlock();
    }
}
