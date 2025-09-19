package net.pufferlab.antiquities.tileentities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TileEntityPile extends TileEntityInventory {

    public TileEntityPile() {
        super(64);
    }

    public int getFacingType() {
        return -1;
    }

    public boolean addItemInPile(ItemStack ingot) {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null) {
                setInventorySlotContentsUpdate(i, ingot);
                return true;
            }
        }
        return false;

    }

    public boolean canAddItemInPile() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null) {
                return true;
            }
        }
        return false;

    }

    public Item getLastItem() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null && i != 0) {
                return getInventoryStack(i - 1).getItem();
            }
        }
        return getInventoryStack(getSizeInventory() - 1).getItem();
    }

    public int getLastItemMeta() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null && i != 0) {
                return getInventoryStack(i - 1).getItemDamage();
            }
        }
        return getInventoryStack(getSizeInventory() - 1).getItemDamage();
    }

    public int getLayer() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null) {
                return (int) (double) ((i - 1) / 8);
            }
        }
        return 7;
    }

    public int getNextSlot() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }
}
