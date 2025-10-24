package net.pufferlab.antiquities.tileentities;

import net.pufferlab.antiquities.Config;

public class TileEntityPedestal extends TileEntityInventory {

    public TileEntityPedestal() {
        super(1);
    }

    public boolean canRegister() {
        return Config.enablePedestal;
    }
}
