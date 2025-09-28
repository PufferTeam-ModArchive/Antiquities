package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBox;

public class ModelLabel extends ModelFurniture {

    public ModelLabel() {
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -7.0F + 7F, 7.0F, 8, 5, 1, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 8, 3.01F, -7.01F + 7F, 6.5F, 1, 5, 1, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 6, -4.02F, -7.02F + 7F, 6.49F, 8, 1, 1, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 6, -4.02F, -3.02F + 7F, 6.49F, 8, 1, 1, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 8, -4.01F, -7.01F + 7F, 6.5F, 1, 5, 1, 0.0F));
    }

    public String getName() {
        return "label";
    }
}
