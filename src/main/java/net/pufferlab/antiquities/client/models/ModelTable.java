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
    public ModelRenderer tops;
    public ModelRenderer top2;
    public ModelRenderer top4;
    public ModelRenderer top1;
    public ModelRenderer top3;
    public ModelRenderer ext2;
    public ModelRenderer ext4;
    public ModelRenderer ext1;
    public ModelRenderer ext3;
    public int offset1 = 0;
    public int offset2 = 0;
    public int offset3 = 0;
    public int offset4 = 0;
    public int offset1Y = 0;
    public int offset2Y = 0;
    public int offset3Y = 0;
    public int offset4Y = 0;

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

        side1 = new ModelRenderer(this);
        side1.setRotationPoint(0.0F, 0, 0.0F);
        side1.cubeList.add(new ModelBox(side1, 40, 0, -5.0F, -14.0F + 8F, -6.0F, 10, 2, 0, 0.0F));
        bb_main.addChild(side1);

        side2 = new ModelRenderer(this);
        side2.setRotationPoint(0.0F, 0, 0.0F);
        side2.cubeList.add(new ModelBox(side2, 40, 0, -5.0F, -14.0F + 8F, 6.0F, 10, 2, 0, 0.0F));
        bb_main.addChild(side2);

        side3 = new ModelRenderer(this);
        side3.setRotationPoint(0.0F, 0, 0.0F);
        side3.cubeList.add(new ModelBox(side3, 40, -10, 6.0F, -14.0F + 8F, -5.0F, 0, 2, 10, 0.0F));
        bb_main.addChild(side3);

        side4 = new ModelRenderer(this);
        side4.setRotationPoint(0.0F, 0, 0.0F);
        side4.cubeList.add(new ModelBox(side4, 40, -10, -6.0F, -14.0F + 8F, -5.0F, 0, 2, 10, 0.0F));
        bb_main.addChild(side4);

        tops = new ModelRenderer(this);
        tops.setRotationPoint(0.0F, 0, 0.0F);
        bb_main.addChild(tops);

        ext1 = new ModelRenderer(this);
        ext1.setRotationPoint(0.0F, 0.0F, 0.0F);
        ext1.cubeList.add(new ModelBox(ext1, 60, -6, 6.0F, -14.0F + 8F, -11.0F, 0, 2, 6, 0.0F));
        bb_main.addChild(ext1);

        ext2 = new ModelRenderer(this);
        ext2.setRotationPoint(0.0F, 0.0F, 0.0F);
        ext2.cubeList.add(new ModelBox(ext2, 60, 0, -11.0F, -14.0F + 8F, -6.0F, 6, 2, 0, 0.0F));
        bb_main.addChild(ext2);

        ext3 = new ModelRenderer(this);
        ext3.setRotationPoint(0.0F, 0.0F, 0.0F);
        ext3.cubeList.add(new ModelBox(ext3, 60, -6, -6.0F, -14.0F + 8F, 5.0F, 0, 2, 6, 0.0F));
        bb_main.addChild(ext3);

        ext4 = new ModelRenderer(this);
        ext4.setRotationPoint(0.0F, 0.0F, 0.0F);
        ext4.cubeList.add(new ModelBox(ext4, 60, 0, 5.0F, -14.0F + 8F, 6.0F, 6, 2, 0, 0.0F));
        bb_main.addChild(ext4);

    }

    public void updateCTM() {
        if (tops.childModels != null) {
            if (!tops.childModels.isEmpty()) {
                tops.childModels.clear();
            }
        }

        if (offset1Y > 0) {
            offset1 = 0;
        }
        if (offset2Y > 0) {
            offset2 = 0;
        }
        if (offset3Y > 0) {
            offset3 = 0;
        }
        if (offset4Y > 0) {
            offset4 = 0;
        }
        top1 = new ModelRenderer(this);
        top1.setRotationPoint(0.0F, 0, 0.0F);
        top1.cubeList
            .add(new ModelBox(top1, 32 * offset1, 16 + (40 * offset1Y), -8.0F, -16.0F + 8F, 0.0F, 8, 2, 8, 0.0F));
        tops.addChild(top1);

        top2 = new ModelRenderer(this);
        top2.setRotationPoint(0.0F, 0, 0.0F);
        top2.cubeList
            .add(new ModelBox(top2, 32 * offset2, 26 + (40 * offset2Y), 0.0F, -16.0F + 8F, 0.0F, 8, 2, 8, 0.0F));
        tops.addChild(top2);

        top3 = new ModelRenderer(this);
        top3.setRotationPoint(0.0F, 0, 0.0F);
        top3.cubeList
            .add(new ModelBox(top3, 32 * offset3, 36 + (40 * offset3Y), -8.0F, -16.0F + 8F, -8.0F, 8, 2, 8, 0.0F));
        tops.addChild(top3);

        top4 = new ModelRenderer(this);
        top4.setRotationPoint(0.0F, 0, 0.0F);
        top4.cubeList
            .add(new ModelBox(top4, 32 * offset4, 46 + (40 * offset4Y), 0.0F, -16.0F + 8F, -8.0F, 8, 2, 8, 0.0F));
        tops.addChild(top4);
    }

    public String getName() {
        return "table";
    }
}
