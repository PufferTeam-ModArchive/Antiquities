package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.Utils;

public class ModelPile extends ModelFurniture {

    private ModelRenderer layer0;
    private ModelRenderer layer1;

    public ModelPile() {
        super(64, 64);

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
        int texY = 5 * Utils.getItemFromArray(Constants.metalTypes, metal);
        int texX = 0;
        if (Utils.containsExactMatch(Constants.metalTypes2, metal)) {
            texX = 18;
            texY = 5 * Utils.getItemFromArray(Constants.metalTypes2, metal);
        }
        if (Utils.containsExactMatch(Constants.metalTypes3, metal)) {
            texX = 36;
            texY = 5 * Utils.getItemFromArray(Constants.metalTypes3, metal);
        }
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
        layer.cubeList.add(new ModelBox(bb_main, texX, texY, (float) posX, (float) posY, (float) posZ, 6, 2, 3, 0.0F));
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
