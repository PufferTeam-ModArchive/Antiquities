package net.pufferlab.antiquities.tileentities;

import net.pufferlab.antiquities.Config;

public class TileEntityCouch extends TileEntityMetaFacing {

    public boolean canRegister() {
        return Config.enableCouch;
    }

    @Override
    public int getFacingType() {
        return 1;
    }
}
