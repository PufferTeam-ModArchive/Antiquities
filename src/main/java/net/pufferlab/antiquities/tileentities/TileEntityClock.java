package net.pufferlab.antiquities.tileentities;

import net.pufferlab.antiquities.Config;

public class TileEntityClock extends TileEntityMetaFacing {

    public boolean canRegister() {
        return Config.enableClock;
    }

    @Override
    public int getFacingType() {
        return 1;
    }
}
