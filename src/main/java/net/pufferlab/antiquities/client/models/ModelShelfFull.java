package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBox;

public class ModelShelfFull extends ModelFurniture {

    public ModelShelfFull() {
        super(64, 64);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 28, -8.0F, -7.0F + 8F, 0.0F, 1, 6, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 28, -8.0F, -15.0F + 8F, 0.0F, 1, 6, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 28, 7.0F, -15.0F + 8F, 0.0F, 1, 6, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 28, 7.0F, -7.0F + 8F, 0.0F, 1, 6, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 50, -8.0F, -16.0F + 8F, 0.0F, 16, 1, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 42, -8.0F, -1.0F + 8F, 0.0F, 16, 1, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 42, -8.0F, -9.0F + 8F, 0.0F, 16, 2, 8, 0.0F));
    }

    public String getName() {
        return "shelf";
    }
}
