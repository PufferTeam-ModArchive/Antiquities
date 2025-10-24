package net.pufferlab.antiquities.tileentities;

import net.pufferlab.antiquities.Config;

public class TileEntityShelf extends TileEntityInventory {

    public TileEntityShelf() {
        super(4);
    }

    public boolean canRegister() {
        return Config.enableShelf;
    }
}
