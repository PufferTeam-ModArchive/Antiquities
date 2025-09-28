package net.pufferlab.antiquities.client.renderer;

import net.pufferlab.antiquities.client.models.ModelFurniture;
import net.pufferlab.antiquities.client.models.ModelRack;

public class BlockRackRender extends BlockFurnitureRender {

    ModelRack model = new ModelRack();

    public BlockRackRender(int blockComplexRenderID) {
        super(blockComplexRenderID);
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelRack[] { model };
    }

}
