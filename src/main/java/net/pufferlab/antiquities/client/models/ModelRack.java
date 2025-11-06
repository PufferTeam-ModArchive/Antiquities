package net.pufferlab.antiquities.client.models;

import net.pufferlab.antiquities.client.utils.ModelBox;

public class ModelRack extends ModelFurniture {

    public ModelRack() {
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -7.0F, 6.0F, 12, 3, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 8, 5, -5.0F, -6.0F, 5.0F, 1, 1, 1, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 8, 5, -0.5F, -6.0F, 5.0F, 1, 1, 1, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 8, 5, 4.0F, -6.0F, 5.0F, 1, 1, 1, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 5, -8.0F, -8.0F, 6.0F, 2, 16, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 5, 6.0F, -8.0F, 6.0F, 2, 16, 2, 0.0F));
    }

    public String getName() {
        return "rack";
    }
}
