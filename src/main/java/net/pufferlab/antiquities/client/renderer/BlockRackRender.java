package net.pufferlab.antiquities.client.renderer;

import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.client.models.ModelFurniture;
import net.pufferlab.antiquities.client.models.ModelRack;

public class BlockRackRender extends BlockFurnitureRender {

    ModelRack model = new ModelRack();

    @Override
    public int getRenderId() {
        return Antiquities.proxy.getRackRenderID();
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelRack[] { model };
    }

}
