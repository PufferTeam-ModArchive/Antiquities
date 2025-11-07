package net.pufferlab.antiquities.client.renderer;

import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.client.models.ModelClock;
import net.pufferlab.antiquities.client.models.ModelFurniture;

public class BlockClockRender extends BlockFurnitureRender {

    ModelClock model = new ModelClock();

    @Override
    public int getRenderId() {
        return Antiquities.proxy.getClockRenderID();
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelClock[] { model };
    }
}
