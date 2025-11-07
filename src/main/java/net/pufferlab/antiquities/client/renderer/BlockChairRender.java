package net.pufferlab.antiquities.client.renderer;

import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.client.models.ModelChair;
import net.pufferlab.antiquities.client.models.ModelFurniture;

public class BlockChairRender extends BlockFurnitureRender {

    ModelChair model = new ModelChair();

    @Override
    public int getRenderId() {
        return Antiquities.proxy.getChairRenderID();
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelChair[] { model };
    }
}
