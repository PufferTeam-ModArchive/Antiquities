package net.pufferlab.antiquities.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BlockAntiquities extends BlockContainer {

    protected BlockAntiquities(Material material) {
        super(material);
    }

    public boolean canRegister() {
        return false;
    }
}
