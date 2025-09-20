package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelTable extends ModelFurniture {

    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;

    public ModelRenderer side1;
    public ModelRenderer side2;
    public ModelRenderer side3;
    public ModelRenderer side4;

    public ModelRenderer top1;
    public ModelRenderer top2;
    public ModelRenderer top3;
    public ModelRenderer top4;

    public ModelRenderer top1B;
    public ModelRenderer top2B;
    public ModelRenderer top3B;
    public ModelRenderer top4B;

    public ModelRenderer top1C;
    public ModelRenderer top1CB;
    public ModelRenderer top2C;
    public ModelRenderer top1LB;
    public ModelRenderer top2LB;
    public ModelRenderer top3LB;
    public ModelRenderer top4LB;

    public ModelTable() {
        super(128, 128);

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg1.cubeList.add(new ModelBox(bb_main, 0, 0, 6.0F, -6.0F, -8.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg1);

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg2.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -6.0F, -8.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg2);

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg3.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -6.0F, 6.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg3);

        leg4 = new ModelRenderer(this);
        leg4.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg4.cubeList.add(new ModelBox(bb_main, 0, 0, 6.0F, -6.0F, 6.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg4);

        side1 = new ModelRenderer(this);
        side1.setRotationPoint(0.0F, 0.0F, 0.0F);
        side1.cubeList.add(new ModelBox(bb_main, 0, 41, -8.0F, -6.0F, -7.0F, 16, 2, 0, 0.0F));
        bb_main.addChild(side1);

        side2 = new ModelRenderer(this);
        side2.setRotationPoint(0.0F, 0.0F, 0.0F);
        side2.cubeList.add(new ModelBox(bb_main, 0, 25, -7.0F, -6.0F, -8.0F, 0, 2, 16, 0.0F));
        bb_main.addChild(side2);

        side3 = new ModelRenderer(this);
        side3.setRotationPoint(0.0F, 0.0F, 0.0F);
        side3.cubeList.add(new ModelBox(bb_main, 0, 41, -8.0F, -6.0F, 7.0F, 16, 2, 0, 0.0F));
        bb_main.addChild(side3);

        side4 = new ModelRenderer(this);
        side4.setRotationPoint(0.0F, 0.0F, 0.0F);
        side4.cubeList.add(new ModelBox(bb_main, 0, 25, 7.0F, -6.0F, -8.0F, 0, 2, 16, 0.0F));
        bb_main.addChild(side4);

        bb_main.cubeList.add(new ModelBox(bb_main, 0, 2, -7.0F, -7.99F, -7.0F, 14, 2, 14, 0.0F));

        top1 = new ModelRenderer(this);
        top1.setRotationPoint(0.0F, 0.0F, 0.0F);
        top1.cubeList.add(new ModelBox(bb_main, 2, 21, -9.0F, -7.99F, -7.0F, 2, 2, 14, 0.0F));
        bb_main.addChild(top1);

        top1B = new ModelRenderer(this);
        top1B.setRotationPoint(0.0F, 0.0F, 0.0F);
        top1B.cubeList.add(new ModelBox(bb_main, 14, 19, -9.0F, -7.99F, 7.0F, 2, 2, 2, 0.0F));
        bb_main.addChild(top1B);

        top1LB = new ModelRenderer(this);
        top1LB.setRotationPoint(0.0F, 0.0F, 0.0F);
        top1LB.cubeList.add(new ModelBox(bb_main, 22, 19, -9.0F, -7.99F, 7.0F, 2, 2, 2, 0.0F));
        bb_main.addChild(top1LB);

        top1C = new ModelRenderer(this);
        top1C.setRotationPoint(0.0F, 0.0F, 0.0F);
        top1C.cubeList.add(new ModelBox(bb_main, 4, 45, -9.0F, -7.99F, -7.0F, 2, 2, 14, 0.0F));
        bb_main.addChild(top1C);

        top1CB = new ModelRenderer(this);
        top1CB.setRotationPoint(0.0F, 0.0F, 0.0F);
        top1CB.cubeList.add(new ModelBox(bb_main, 16, 43, -9.0F, -7.99F, 7.0F, 2, 2, 2, 0.0F));
        bb_main.addChild(top1CB);

        top2 = new ModelRenderer(this);
        top2.setRotationPoint(0.0F, 0.0F, 0.0F);
        top2.cubeList.add(new ModelBox(bb_main, 2, 21, -9.0F, -7.99F, -7.0F, 2, 2, 14, 0.0F));
        top2.rotateAngleY = (float) Math.toRadians(90);
        bb_main.addChild(top2);

        top2B = new ModelRenderer(this);
        top2B.setRotationPoint(0.0F, 0.0F, 0.0F);
        top2B.cubeList.add(new ModelBox(bb_main, 14, 19, -9.0F, -7.99F, 7.0F, 2, 2, 2, 0.0F));
        top2B.rotateAngleY = (float) Math.toRadians(90);
        bb_main.addChild(top2B);

        top2LB = new ModelRenderer(this);
        top2LB.setRotationPoint(0.0F, 0.0F, 0.0F);
        top2LB.cubeList.add(new ModelBox(bb_main, 22, 19, -9.0F, -7.99F, 7.0F, 2, 2, 2, 0.0F));
        top2LB.rotateAngleY = (float) Math.toRadians(90);
        bb_main.addChild(top2LB);

        top2C = new ModelRenderer(this);
        top2C.setRotationPoint(0.0F, 0.0F, 0.0F);
        top2C.cubeList.add(new ModelBox(bb_main, 4, 45, -9.0F, -7.99F, -7.0F, 2, 2, 14, 0.0F));
        top2C.rotateAngleY = (float) Math.toRadians(90);
        bb_main.addChild(top2C);

        top3 = new ModelRenderer(this);
        top3.setRotationPoint(0.0F, 0.0F, 0.0F);
        top3.cubeList.add(new ModelBox(bb_main, 2, 21, -9.0F, -7.99F, -7.0F, 2, 2, 14, 0.0F));
        top3.rotateAngleY = (float) Math.toRadians(180);
        bb_main.addChild(top3);

        top3B = new ModelRenderer(this);
        top3B.setRotationPoint(0.0F, 0.0F, 0.0F);
        top3B.cubeList.add(new ModelBox(bb_main, 14, 19, -9.0F, -7.99F, 7.0F, 2, 2, 2, 0.0F));
        top3B.rotateAngleY = (float) Math.toRadians(180);
        bb_main.addChild(top3B);

        top3LB = new ModelRenderer(this);
        top3LB.setRotationPoint(0.0F, 0.0F, 0.0F);
        top3LB.cubeList.add(new ModelBox(bb_main, 22, 19, -9.0F, -7.99F, 7.0F, 2, 2, 2, 0.0F));
        top3LB.rotateAngleY = (float) Math.toRadians(180);
        bb_main.addChild(top3LB);

        top4 = new ModelRenderer(this);
        top4.setRotationPoint(0.0F, 0.0F, 0.0F);
        top4.cubeList.add(new ModelBox(bb_main, 2, 21, -9.0F, -7.99F, -7.0F, 2, 2, 14, 0.0F));
        top4.rotateAngleY = (float) Math.toRadians(270);
        bb_main.addChild(top4);

        top4B = new ModelRenderer(this);
        top4B.setRotationPoint(0.0F, 0.0F, 0.0F);
        top4B.cubeList.add(new ModelBox(bb_main, 14, 19, -9.0F, -7.99F, 7.0F, 2, 2, 2, 0.0F));
        top4B.rotateAngleY = (float) Math.toRadians(270);
        bb_main.addChild(top4B);

        top4LB = new ModelRenderer(this);
        top4LB.setRotationPoint(0.0F, 0.0F, 0.0F);
        top4LB.cubeList.add(new ModelBox(bb_main, 22, 19, -9.0F, -7.99F, 7.0F, 2, 2, 2, 0.0F));
        top4LB.rotateAngleY = (float) Math.toRadians(270);
        bb_main.addChild(top4LB);
    }

    public String getName() {
        return "table";
    }
}
