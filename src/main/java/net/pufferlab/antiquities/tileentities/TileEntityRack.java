package net.pufferlab.antiquities.tileentities;

import net.pufferlab.antiquities.Config;

public class TileEntityRack extends TileEntityInventory {

    public TileEntityRack() {
        super(3);
    }

    public boolean canRegister() {
        return Config.enableToolRack;
    }
}
