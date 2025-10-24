package net.pufferlab.antiquities.tileentities;

import net.pufferlab.antiquities.Config;

public class TileEntityJar extends TileEntityInventory {

    public TileEntityJar() {
        super(1);
    }

    public boolean canRegister() {
        return Config.enableJar;
    }
}
