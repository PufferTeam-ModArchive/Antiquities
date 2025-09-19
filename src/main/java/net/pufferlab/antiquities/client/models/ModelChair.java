package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBox;

public class ModelChair extends ModelFurniture {

    public ModelChair() {
        super(64, 64);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 26, 4.0F, -8.0F + 8F, -6.0F, 2, 8, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 8, 26, 4.0F, -8.0F + 8F, 4.0F, 2, 8, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 14, -6.0F, -20.0F + 8F, -6.0F, 12, 10, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -10.0F + 8F, -6.0F, 12, 2, 12, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 16, 26, -6.0F, -8.0F + 8F, 4.0F, 2, 8, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 24, 26, -6.0F, -8.0F + 8F, -6.0F, 2, 8, 2, 0.0F));
    }

    public String getName() {
        return "chair";
    }

    public boolean invertRot() {
        return true;
    }
}
