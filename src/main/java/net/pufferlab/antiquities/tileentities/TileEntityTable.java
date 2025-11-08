package net.pufferlab.antiquities.tileentities;

import net.pufferlab.antiquities.Config;

public class TileEntityTable extends TileEntityAntiquities {

    public boolean canRegister() {
        return Config.enableTable;
    }
}
