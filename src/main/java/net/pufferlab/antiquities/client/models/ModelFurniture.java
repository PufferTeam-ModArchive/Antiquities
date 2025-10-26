package net.pufferlab.antiquities.client.models;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.client.helper.ModelTESS;

public abstract class ModelFurniture extends ModelBase {

    public ModelRenderer bb_main;
    public ModelTESS modelTESS;

    public ModelFurniture(int width, int height) {
        modelTESS = new ModelTESS();

        textureWidth = width;
        textureHeight = height;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);
        bb_main.rotateAngleY = 0;
    }

    public ModelFurniture() {
        this(32, 32);
    }

    public void render(String type) {
        if (type == null) {
            bindTex(getName() + "_model");
        } else {
            bindTex(type + "_" + getName());
        }
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        bb_main.render(Constants.ModelConstant);
    }

    public void render(ModelRenderer bb_second, String type) {
        if (type == null) {
            bindTex(getName() + "_model");
        } else {
            bindTex(type + "_" + getName());
        }
        bb_second.rotateAngleX = (float) Math.toRadians(180);
        bb_second.render(Constants.ModelConstant);
    }

    public void render(RenderBlocks renderblocks, Tessellator tess, Block block, int meta, int x, int y, int z) {
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        modelTESS.render(renderblocks, tess, block, bb_main, Constants.ModelConstant, x, y, z, meta);
    }

    public void renderWithoutAO(RenderBlocks renderblocks, Tessellator tess, Block block, int meta, int x, int y,
        int z) {
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        modelTESS.renderBlock(renderblocks, tess, block, bb_main, Constants.ModelConstant, x, y, z, meta);
    }

    double add = 0;

    public void setFacing(int meta) {
        int meta2 = meta + 1;
        if (invertRot()) {
            add = Math.toRadians(180);
        }
        bb_main.rotateAngleY = (float) ((Math.toRadians(90 * meta2) + add) % 360);
    }

    public void bindTex(String fileName) {
        Minecraft.getMinecraft().renderEngine
            .bindTexture(Antiquities.asResource("textures/blocks/" + fileName + ".png"));
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public String getName() {
        return null;
    }

    public boolean invertRot() {
        return false;
    }

}
