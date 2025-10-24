package net.pufferlab.antiquities.client.renderer;

import net.pufferlab.antiquities.client.models.ModelChair;
import net.pufferlab.antiquities.client.models.ModelFurniture;

public class BlockChairRender extends BlockFurnitureRender {

    ModelChair model = new ModelChair();

    public BlockChairRender(int blockComplexRenderID) {
        super(blockComplexRenderID);
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelChair[] { model };
    }
}
