package net.pufferlab.antiquities.tileentities;

import net.pufferlab.antiquities.Config;

public class TileEntityChair extends TileEntityMetaFacing {

    public boolean canRegister() {
        return Config.enableChair;
    }

    @Override
    public int getFacingType() {
        return 1;
    }
}
