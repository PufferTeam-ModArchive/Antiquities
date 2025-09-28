package net.pufferlab.antiquities.tileentities;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TileEntityPile extends TileEntityInventory {

    public TileEntityPile() {
        super(64);
    }

    public int getFacingType() {
        return -1;
    }

    public Item getLastItem() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null) {
                if (i < 1) {
                    return Items.iron_ingot;
                }
                return getInventoryStack(i - 1).getItem();
            }
        }
        return getInventoryStack(getSizeInventory() - 1).getItem();
    }

    public int getLastItemMeta() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null) {
                if (i < 1) {
                    return 0;
                }
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

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }
}
