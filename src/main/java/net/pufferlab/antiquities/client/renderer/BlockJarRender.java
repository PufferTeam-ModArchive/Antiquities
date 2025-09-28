package net.pufferlab.antiquities.client.renderer;

import net.pufferlab.antiquities.client.models.ModelFurniture;
import net.pufferlab.antiquities.client.models.ModelJar;

public class BlockJarRender extends BlockFurnitureRender {

    ModelJar model = new ModelJar();

    public BlockJarRender(int blockComplexRenderID) {
        super(blockComplexRenderID);
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelJar[] { model };
    }
}
