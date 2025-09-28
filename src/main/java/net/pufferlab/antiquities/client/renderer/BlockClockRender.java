package net.pufferlab.antiquities.client.renderer;

import net.pufferlab.antiquities.client.models.ModelClock;
import net.pufferlab.antiquities.client.models.ModelFurniture;

public class BlockClockRender extends BlockFurnitureRender {

    ModelClock model = new ModelClock();

    public BlockClockRender(int blockComplexRenderID) {
        super(blockComplexRenderID);
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelClock[] { model };
    }
}
