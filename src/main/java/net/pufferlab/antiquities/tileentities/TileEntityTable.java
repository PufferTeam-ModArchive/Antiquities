package net.pufferlab.antiquities.tileentities;

import net.pufferlab.antiquities.Config;

public class TileEntityTable extends TileEntityAntiquities {

    public void updateConnections() {
        this.worldObj.markBlockRangeForRenderUpdate(
            this.xCoord - 1,
            this.yCoord,
            this.zCoord - 1,
            this.xCoord + 1,
            this.yCoord,
            this.zCoord + 1);
    }

    public boolean canRegister() {
        return Config.enableTable;
    }
}
