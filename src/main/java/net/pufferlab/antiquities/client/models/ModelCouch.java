package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelCouch extends ModelFurniture {

    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer side1;
    public ModelRenderer side2;

    public ModelCouch() {
        super(64, 64);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 17, -8.0F, -9.0F + 8F, -8.0F, 16, 6, 16, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -20.0F + 8F, 3.0F, 16, 11, 5, 0.0F));

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg1.cubeList.add(new ModelBox(leg1, 43, 0, 5.0F, -3.0F + 8F, -8.0F, 3, 3, 3, 0.0F));
        bb_main.addChild(leg1);

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg2.cubeList.add(new ModelBox(leg2, 43, 0, -8.0F, -3.0F + 8F, -8.0F, 3, 3, 3, 0.0F));
        bb_main.addChild(leg2);

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg3.cubeList.add(new ModelBox(leg3, 43, 0, 5.0F, -3.0F + 8F, 5.0F, 3, 3, 3, 0.0F));
        bb_main.addChild(leg3);

        leg4 = new ModelRenderer(this);
        leg4.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg4.cubeList.add(new ModelBox(leg4, 43, 0, -8.0F, -3.0F + 8F, 5.0F, 3, 3, 3, 0.0F));
        bb_main.addChild(leg4);

        side1 = new ModelRenderer(this);
        side1.setRotationPoint(0.0F, 0.0F, 0.0F);
        side1.cubeList.add(new ModelBox(side1, 0, 39, 7.0F, -14.0F + 8F, -7.5F, 4, 7, 15, 0.0F));
        bb_main.addChild(side1);

        side2 = new ModelRenderer(this);
        side2.setRotationPoint(0.0F, 0.0F, 0.0F);
        side2.cubeList.add(new ModelBox(side2, 0, 39, -11.0F, -14.0F + 8F, -7.5F, 4, 7, 15, 0.0F));
        bb_main.addChild(side2);
    }

    public String getName() {
        return "couch";
    }
}
