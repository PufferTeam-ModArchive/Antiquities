package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.pufferlab.antiquities.Constants;

public class ModelClock extends ModelFurniture {

    public ModelRenderer clock_hand;
    public ModelRenderer clock_hand_hour;

    public ModelClock() {
        super(64, 64);

        clock_hand = new ModelRenderer(this);
        clock_hand.setRotationPoint(0.0F, 0.0F, 0.0F);
        clock_hand.cubeList.add(new ModelBox(clock_hand, 1, 18, -6.0F, -8.5F + 8F, 5.5F, 7, 1, 0, 0.0F));

        clock_hand_hour = new ModelRenderer(this);
        clock_hand_hour.setRotationPoint(0.0F, 0.0F, 0.0F);
        clock_hand_hour.cubeList.add(new ModelBox(clock_hand_hour, 16, 18, 0.0F, -8.5F + 8F, 5.5F, 5, 1, 0, 0.0F));

        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -16.0F + 8F, 6.0F, 16, 16, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 19, -1.0F, -9.0F + 8F, 5.0F, 2, 2, 1, 0.0F));
    }

    public void render() {
        bindTex("clock_model");
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        bb_main.render(Constants.ModelConstant);
    }

    public void renderClockHand() {
        bindTex("clock_model");
        clock_hand.rotateAngleX = (float) Math.toRadians(180);
        clock_hand.render(Constants.ModelConstant);
    }

    public void renderClockHandHour() {
        bindTex("clock_model");
        clock_hand_hour.rotateAngleX = (float) Math.toRadians(180);
        clock_hand_hour.render(Constants.ModelConstant);
    }

    public void setFacing(int meta) {
        int meta2 = meta + 1;
        bb_main.rotateAngleY = (float) ((Math.toRadians(90 * meta2)) % 360);
        clock_hand.rotateAngleY = (float) ((Math.toRadians(90 * meta2)) % 360);
        clock_hand_hour.rotateAngleY = (float) ((Math.toRadians(90 * meta2)) % 360);
    }

    public String getName() {
        return "clock";
    }
}
