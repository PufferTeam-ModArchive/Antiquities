package net.pufferlab.antiquities.client.models;

import net.pufferlab.antiquities.client.utils.ModelBox;

public class ModelPedestal extends ModelFurniture {

    public ModelPedestal() {
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, 5.0F, -2.0F, 8, 3, 4, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 7, 4.0F, 6.0F, -2.0F, 1, 2, 4, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 7, -5.0F, 6.0F, -2.0F, 1, 2, 4, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 7, 5.0F, 7.0F, -2.0F, 1, 1, 4, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 7, -6.0F, 7.0F, -2.0F, 1, 1, 4, 0.0F));
    }

    public String getName() {
        return "pedestal";
    }
}
