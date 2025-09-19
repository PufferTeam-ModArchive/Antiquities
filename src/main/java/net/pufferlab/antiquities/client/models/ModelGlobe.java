package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.pufferlab.antiquities.Constants;

public class ModelGlobe extends ModelFurniture {

    private final ModelRenderer group;
    public ModelRenderer earth_r1;
    private final ModelRenderer cube_r1;

    public ModelGlobe() {
        super(64, 64);

        group = new ModelRenderer(this);
        group.setRotationPoint(-7.5F, 7.0F, 7.5F);
        group.cubeList.add(new ModelBox(group, 0, 0, 4.5F, 15.0F - 16F, -10.5F, 6, 2, 6, 0.0F));
        group.cubeList.add(new ModelBox(group, 14, 8, 6.5F, 13.0F - 16F, -8.5F, 2, 2, 2, 0.0F));
        bb_main.addChild(group);

        earth_r1 = new ModelRenderer(this);
        earth_r1.cubeList.add(new ModelBox(earth_r1, 0, 32, -4.0F, -13.25F + 8F, -4.0F, 8, 8, 8, 0.0F));

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(7.5F, 8.0F, -7.5F);
        group.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.3927F, 0.0F, 0.0F);
        cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 8, 0.0F, -5.0F - 15F, -0.5F - 5F, 0, 10, 7, 0.0F));
    }

    public void render() {
        bindTex("globe_model");
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        bb_main.render(Constants.ModelConstant);
    }

    public void renderGlobe() {
        bindTex("globe_model");
        earth_r1.rotateAngleX = (float) Math.toRadians(180);
        earth_r1.render(Constants.ModelConstant);
    }

    public String getName() {
        return "globe";
    }
}
