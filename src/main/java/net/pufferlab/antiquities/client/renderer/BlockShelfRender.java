package net.pufferlab.antiquities.client.renderer;

import net.pufferlab.antiquities.client.models.*;

public class BlockShelfRender extends BlockFurnitureRender {

    ModelShelfFull model0 = new ModelShelfFull();
    ModelShelf model1 = new ModelShelf();
    ModelShelfLong model2 = new ModelShelfLong();
    ModelShelfFullCase model3 = new ModelShelfFullCase();
    ModelShelfCase model4 = new ModelShelfCase();
    ModelShelfLongCase model5 = new ModelShelfLongCase();

    public BlockShelfRender(int blockComplexRenderID) {
        super(blockComplexRenderID);
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelFurniture[] { model0, model1, model2, model3, model4, model5 };
    }
}
