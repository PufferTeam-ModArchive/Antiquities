package net.pufferlab.antiquities.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.EnumSkyBlock;

public class TileEntityInventory extends TileEntityMetaFacing implements IInventory {

    private ItemStack[] inventory;
    private int maxSize;

    public TileEntityInventory(int slots) {
        this.inventory = new ItemStack[slots];
        this.maxSize = slots;
    }

    public int getSize() {
        return this.inventory.length;
    }

    @Override
    public int getFacingType() {
        return 0;
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList tagList = compound.getTagList("inventory", 10);
        this.inventory = new ItemStack[getSize()];
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < this.inventory.length) this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.inventory.length; i++) {
            ItemStack stack = this.inventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag((NBTBase) tag);
            }
        }
        compound.setTag("inventory", (NBTBase) itemList);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.inventory.length; i++) {
            ItemStack stack = this.inventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag((NBTBase) tag);
            }
        }

        NBTTagCompound dataTag = new NBTTagCompound();

        dataTag.setInteger("facingMeta", this.facingMeta);
        dataTag.setTag("inventory", (NBTBase) itemList);

        return (Packet) new S35PacketUpdateTileEntity(
            this.xCoord,
            this.yCoord,
            this.zCoord,
            this.blockMetadata,
            dataTag);
    }

    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound nbtData = packet.func_148857_g();
        this.facingMeta = nbtData.getInteger("facingMeta");
        NBTTagList tagList = nbtData.getTagList("inventory", 10);
        this.inventory = new ItemStack[getSize()];
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < this.inventory.length) this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
        }
        this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
    }

    public ItemStack getInventoryStack(int slot) {
        ItemStack thing = this.inventory[slot];
        if (thing != null) return thing;
        return null;
    }

    public boolean removeItemsInSlot(int slot) {
        if (this.inventory[slot] != null) {
            setInventorySlotContents(slot, null);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            return true;
        }
        return false;
    }

    public boolean addItemInSlot(int slot, ItemStack stack) {
        if (this.inventory[slot] == null) {
            setInventorySlotContents(slot, stack);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            return true;
        }
        return false;
    }

    @Override
    public int getSizeInventory() {
        return this.maxSize;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn) {
        return this.inventory[slotIn];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.inventory[index] != null) {
            ItemStack itemstack;

            if (this.inventory[index].stackSize <= count) {
                itemstack = this.inventory[index];
                this.inventory[index] = null;
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.inventory[index].splitStack(count);

                if (this.inventory[index].stackSize == 0) {
                    this.inventory[index] = null;
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.inventory[index] != null) {
            ItemStack itemstack = this.inventory[index];
            this.inventory[index] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public void setInventorySlotContentsUpdate(int index, ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.stackSize = getInventoryStackLimit();
        this.inventory[index] = copy;
        this.worldObj.markBlockRangeForRenderUpdate(
            this.xCoord,
            this.yCoord,
            this.zCoord,
            this.xCoord,
            this.yCoord,
            this.zCoord);
    }

    public void setInventorySlotContentsUpdate(int index) {
        this.inventory[index] = null;
        this.worldObj.markBlockRangeForRenderUpdate(
            this.xCoord,
            this.yCoord,
            this.zCoord,
            this.xCoord,
            this.yCoord,
            this.zCoord);
    }

    @Override
    public String getInventoryName() {
        return "Shelf";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
            : player
                .getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D)
                <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }
}
