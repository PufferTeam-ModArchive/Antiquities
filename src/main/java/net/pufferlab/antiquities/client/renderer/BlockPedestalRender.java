package net.pufferlab.antiquities.client.renderer;

import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.client.models.ModelFurniture;
import net.pufferlab.antiquities.client.models.ModelPedestal;

public class BlockPedestalRender extends BlockFurnitureRender {

    ModelPedestal model = new ModelPedestal();

    @Override
    public int getRenderId() {
        return Antiquities.proxy.getPedestalRenderID();
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelPedestal[] { model };
    }

    @Override
    public boolean hasBigInventoryModel() {
        return true;
    }

    @Override
    public float getOffsetY() {
        return 0.25F;
    }
}
