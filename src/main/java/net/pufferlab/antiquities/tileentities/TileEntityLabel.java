package net.pufferlab.antiquities.tileentities;

import net.pufferlab.antiquities.Config;

public class TileEntityLabel extends TileEntityInventory {

    public TileEntityLabel() {
        super(3);
    }

    public boolean canRegister() {
        return Config.enableLabel;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

}
