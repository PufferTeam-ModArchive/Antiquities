package net.pufferlab.antiquities.client.renderer;

import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.client.models.ModelFurniture;
import net.pufferlab.antiquities.client.models.ModelJar;

public class BlockJarRender extends BlockFurnitureRender {

    ModelJar model = new ModelJar();

    @Override
    public int getRenderId() {
        return Antiquities.proxy.getJarRenderID();
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelJar[] { model };
    }
}
