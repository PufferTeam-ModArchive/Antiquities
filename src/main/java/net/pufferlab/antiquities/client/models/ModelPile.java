package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.client.helper.ModelBoxSpecial;

public class ModelPile extends ModelFurniture {

    private ModelRenderer layer0;
    private ModelRenderer layer1;

    public ModelPile() {
        super(32, 32);

        layer0 = new ModelRenderer(this);
        layer0.setRotationPoint(0.0F, 0.0F, 0.0F);
        bb_main.addChild(layer0);

        layer1 = new ModelRenderer(this);
        layer1.setRotationPoint(0.0F, 0.0F, 0.0F);
        bb_main.addChild(layer1);
    }

    public void renderIngot(int i, String metal) {
        ModelRenderer layer;
        double posX = -6.5;
        double posY = -2.0 + 8;
        double posZ = 4.5;
        int i2 = i % 4;
        int i3 = i % 8;
        int i5 = (int) (double) (i / 8);
        int index = Utils.getItemFromArray(Config.ingotPileMetals, metal) + 100;
        float angle = 90;
        if (i5 % 2 == 0) {
            layer = layer1;
            layer.rotateAngleY = ((bb_main.rotateAngleY + (float) Math.toRadians(angle)));
        } else {
            layer = layer0;
            layer.rotateAngleY = ((bb_main.rotateAngleY));
        }
        posY = posY - (2 * i5);
        if (i3 > 3) {
            posX = posX + 7;
        }
        posZ = posZ - (4 * i2);
        layer.cubeList
            .add(new ModelBoxSpecial(bb_main, 0, 0, (float) posX, (float) posY, (float) posZ, 6, 2, 3, 0.0F, index));
    }

    public void resetFacing() {
        layer0.cubeList.clear();
        layer1.cubeList.clear();
        layer0.rotateAngleY = 0;
        layer1.rotateAngleY = 0;
    }

    public String getName() {
        return "pile";
    }

    public boolean invertRot() {
        return true;
    }
}
