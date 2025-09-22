package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelTable extends ModelFurniture {

    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer side_n;
    public ModelRenderer side_s;
    public ModelRenderer side_w;
    public ModelRenderer side_e;
    public ModelRenderer top2;
    public ModelRenderer top4;
    public ModelRenderer top1;
    public ModelRenderer top3;
    public int offset = 0;

    public ModelTable() {
        super(128, 128);

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(0.0F, 0, 0.0F);
        leg1.cubeList.add(new ModelBox(leg1, 32, 0, 5.0F, -14.0F + 8F, -7.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg1);

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(0.0F, 0, 0.0F);
        leg2.cubeList.add(new ModelBox(leg2, 32, 0, -7.0F, -14.0F + 8F, -7.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg2);

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(0.0F, 0, 0.0F);
        leg3.cubeList.add(new ModelBox(leg3, 32, 0, -7.0F, -14.0F + 8F, 5.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg3);

        leg4 = new ModelRenderer(this);
        leg4.setRotationPoint(0.0F, 0, 0.0F);
        leg4.cubeList.add(new ModelBox(leg4, 32, 0, 5.0F, -14.0F + 8F, 5.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg4);

        side_n = new ModelRenderer(this);
        side_n.setRotationPoint(0.0F, 0, 0.0F);
        side_n.cubeList.add(new ModelBox(side_n, 40, 0, -5.0F, -14.0F + 8F, -6.0F, 10, 2, 0, 0.0F));
        bb_main.addChild(side_n);

        side_s = new ModelRenderer(this);
        side_s.setRotationPoint(0.0F, 0, 0.0F);
        side_s.cubeList.add(new ModelBox(side_s, 40, 0, -5.0F, -14.0F + 8F, 6.0F, 10, 2, 0, 0.0F));
        bb_main.addChild(side_s);

        side_w = new ModelRenderer(this);
        side_w.setRotationPoint(0.0F, 0, 0.0F);
        side_w.cubeList.add(new ModelBox(side_w, 40, -10, 6.0F, -14.0F + 8F, -5.0F, 0, 2, 10, 0.0F));
        bb_main.addChild(side_w);

        side_e = new ModelRenderer(this);
        side_e.setRotationPoint(0.0F, 0, 0.0F);
        side_e.cubeList.add(new ModelBox(side_e, 40, -10, -6.0F, -14.0F + 8F, -5.0F, 0, 2, 10, 0.0F));
        bb_main.addChild(side_e);

        top2 = new ModelRenderer(this);
        top2.setRotationPoint(0.0F, 0, 0.0F);
        top2.cubeList.add(new ModelBox(top2, 32 * offset, 26, 0.0F, -16.0F + 8F, 0.0F, 8, 2, 8, 0.0F));
        bb_main.addChild(top2);

        top4 = new ModelRenderer(this);
        top4.setRotationPoint(0.0F, 0, 0.0F);
        top4.cubeList.add(new ModelBox(top4, 32 * offset, 46, 0.0F, -16.0F + 8F, -8.0F, 8, 2, 8, 0.0F));
        bb_main.addChild(top4);

        top1 = new ModelRenderer(this);
        top1.setRotationPoint(0.0F, 0, 0.0F);
        top1.cubeList.add(new ModelBox(top1, 32 * offset, 16, -8.0F, -16.0F + 8F, 0.0F, 8, 2, 8, 0.0F));
        bb_main.addChild(top1);

        top3 = new ModelRenderer(this);
        top3.setRotationPoint(0.0F, 0, 0.0F);
        top3.cubeList.add(new ModelBox(top3, 32 * offset, 36, -8.0F, -16.0F + 8F, -8.0F, 8, 2, 8, 0.0F));
        bb_main.addChild(top3);
    }

    public String getName() {
        return "table";
    }
}
