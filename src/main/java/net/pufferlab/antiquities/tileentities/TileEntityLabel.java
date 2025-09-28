package net.pufferlab.antiquities.tileentities;

public class TileEntityLabel extends TileEntityInventory {

    public TileEntityLabel() {
        super(3);
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

}
