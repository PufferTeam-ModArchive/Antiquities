package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBox;

public class ModelJar extends ModelFurniture {

    public ModelJar() {
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 17, -4.0F, -2.0F, -4.0F, 8, 1, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -1.0F, -4.0F, 8, 9, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 26, -2.0F, -3.0F, -1.0F, 4, 1, 2, 0.0F));
    }

    public String getName() {
        return "jar";
    }
}
