package net.pufferlab.antiquities.client.helper;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class CustomRenderBlocks extends RenderBlocks {

    public ModelRenderer modelrenderer;
    public ModelBox modelbox;

    public int quadXNeg = 0;
    public int quadXPos = 1;
    public int quadYNeg = 2;
    public int quadYPos = 3;
    public int quadZNeg = 4;
    public int quadZPos = 5;

    public int rotateXNeg = 0;
    public int rotateXPos = 0;
    public int rotateYNeg = 0;
    public int rotateYPos = 0;
    public int rotateZNeg = 0;
    public int rotateZPos = 0;

    public int vertex1;
    public int vertex2;
    public int vertex3;
    public int vertex4;

    public boolean mirrorText;

    public void rotateUVNeg(double[] U, double[] V, int rotation) {
        // rotation: 0 = no rotation, 1 = 90 CW, 2 = 180, 3 = 270 CW
        for (int r = 0; r < rotation; r++) {
            double tmpU = U[0], tmpV = V[0];
            U[0] = U[1];
            V[0] = V[1];
            U[1] = U[2];
            V[1] = V[2];
            U[2] = U[3];
            V[2] = V[3];
            U[3] = tmpU;
            V[3] = tmpV;
        }
    }

    public void rotateUVPos(double[] U, double[] V, int rotation) {
        // rotation: 0 = no rotation, 1 = 90 CW, 2 = 180, 3 = 270 CW
        for (int r = 0; r < rotation; r++) {
            double tmpU = U[0], tmpV = V[0];
            U[0] = U[3];
            V[0] = V[3];
            U[3] = U[2];
            V[3] = V[2];
            U[2] = U[1];
            V[2] = V[1];
            U[1] = tmpU;
            V[1] = tmpV;
        }
    }

    public void flipUVNeg(double[] U, double[] V) {
        double tmpU = U[0], tmpV = V[0];
        U[0] = U[3];
        V[0] = V[3]; // top-left ↔ top-right
        U[3] = tmpU;
        V[3] = tmpV;

        tmpU = U[1];
        tmpV = V[1];
        U[1] = U[2];
        V[1] = V[2]; // bottom-left ↔ bottom-right
        U[2] = tmpU;
        V[2] = tmpV;
    }

    public void flipUVPos(double[] U, double[] V) {
        double tmpU = U[0], tmpV = V[0];
        U[0] = U[1];
        V[0] = V[1]; // top-left ↔ bottom-left
        U[1] = tmpU;
        V[1] = tmpV;

        tmpU = U[3];
        tmpV = V[3];
        U[3] = U[2];
        V[3] = V[2]; // top-right ↔ bottom-right
        U[2] = tmpU;
        V[2] = tmpV;
    }

    /**
     * Renders the given texture to the bottom face of the block. Args: block, x, y, z, texture
     */
    public void renderFaceYNeg(Block block, double x, double y, double z, IIcon icon) {
        Tessellator tessellator = Tessellator.instance;

        if (this.hasOverrideBlockTexture()) {
            icon = this.overrideBlockTexture;
        }

        TexturedQuad quad = modelbox.quadList[quadYNeg];

        PositionTextureVertex pos = quad.vertexPositions[vertex1];
        double u = icon.getMinU() + pos.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v = icon.getMinV() + pos.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos2 = quad.vertexPositions[vertex2];
        double u2 = icon.getMinU() + pos2.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v2 = icon.getMinV() + pos2.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos3 = quad.vertexPositions[vertex3];
        double u3 = icon.getMinU() + pos3.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v3 = icon.getMinV() + pos3.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos4 = quad.vertexPositions[vertex4];
        double u4 = icon.getMinU() + pos4.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v4 = icon.getMinV() + pos4.texturePositionY * (icon.getMaxV() - icon.getMinV());

        double[] U = { u, u2, u3, u4 };
        double[] V = { v, v2, v3, v4 };

        rotateUVNeg(U, V, rotateYNeg);
        if (mirrorText) {
            flipUVNeg(U, V);
        }

        double d11 = x + this.renderMinX;
        double d12 = x + this.renderMaxX;
        double d13 = y + this.renderMinY;
        double d14 = z + this.renderMinZ;
        double d15 = z + this.renderMaxZ;

        if (this.renderFromInside) {
            d11 = x + this.renderMaxX;
            d12 = x + this.renderMinX;
        }

        if (this.enableAO) {
            tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            tessellator.setBrightness(this.brightnessTopLeft);
            tessellator.addVertexWithUV(d11, d13, d15, U[0], V[0]);
            tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            tessellator.setBrightness(this.brightnessBottomLeft);
            tessellator.addVertexWithUV(d11, d13, d14, U[1], V[1]);
            tessellator
                .setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            tessellator.setBrightness(this.brightnessBottomRight);
            tessellator.addVertexWithUV(d12, d13, d14, U[2], V[2]);
            tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            tessellator.setBrightness(this.brightnessTopRight);
            tessellator.addVertexWithUV(d12, d13, d15, U[3], V[3]);
        } else {
            tessellator.addVertexWithUV(d11, d13, d15, U[0], V[0]);
            tessellator.addVertexWithUV(d11, d13, d14, U[1], V[1]);
            tessellator.addVertexWithUV(d12, d13, d14, U[2], V[2]);
            tessellator.addVertexWithUV(d12, d13, d15, U[3], V[3]);
        }
    }

    /**
     * Renders the given texture to the top face of the block. Args: block, x, y, z, texture
     */
    public void renderFaceYPos(Block block, double x, double y, double z, IIcon icon) {
        Tessellator tessellator = Tessellator.instance;

        if (this.hasOverrideBlockTexture()) {
            icon = this.overrideBlockTexture;
        }

        TexturedQuad quad = modelbox.quadList[quadYPos];

        PositionTextureVertex pos = quad.vertexPositions[vertex1];
        double u = icon.getMinU() + pos.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v = icon.getMinV() + pos.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos2 = quad.vertexPositions[vertex2];
        double u2 = icon.getMinU() + pos2.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v2 = icon.getMinV() + pos2.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos3 = quad.vertexPositions[vertex3];
        double u3 = icon.getMinU() + pos3.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v3 = icon.getMinV() + pos3.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos4 = quad.vertexPositions[vertex4];
        double u4 = icon.getMinU() + pos4.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v4 = icon.getMinV() + pos4.texturePositionY * (icon.getMaxV() - icon.getMinV());

        double[] U = { u, u2, u3, u4 };
        double[] V = { v, v2, v3, v4 };

        rotateUVPos(U, V, rotateYPos);
        if (mirrorText) {
            flipUVPos(U, V);
        }

        double d11 = x + this.renderMinX;
        double d12 = x + this.renderMaxX;
        double d13 = y + this.renderMaxY;
        double d14 = z + this.renderMinZ;
        double d15 = z + this.renderMaxZ;

        if (this.renderFromInside) {
            d11 = x + this.renderMaxX;
            d12 = x + this.renderMinX;
        }

        if (this.enableAO) {
            tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            tessellator.setBrightness(this.brightnessTopLeft);
            tessellator.addVertexWithUV(d12, d13, d15, U[0], V[0]);
            tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            tessellator.setBrightness(this.brightnessBottomLeft);
            tessellator.addVertexWithUV(d12, d13, d14, U[1], V[1]);
            tessellator
                .setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            tessellator.setBrightness(this.brightnessBottomRight);
            tessellator.addVertexWithUV(d11, d13, d14, U[2], V[2]);
            tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            tessellator.setBrightness(this.brightnessTopRight);
            tessellator.addVertexWithUV(d11, d13, d15, U[3], V[3]);
        } else {
            tessellator.addVertexWithUV(d12, d13, d15, U[0], V[0]);
            tessellator.addVertexWithUV(d12, d13, d14, U[1], V[1]);
            tessellator.addVertexWithUV(d11, d13, d14, U[2], V[2]);
            tessellator.addVertexWithUV(d11, d13, d15, U[3], V[3]);
        }
    }

    /**
     * Renders the given texture to the north (z-negative) face of the block. Args: block, x, y, z, texture
     */
    public void renderFaceZNeg(Block block, double x, double y, double z, IIcon icon) {
        Tessellator tessellator = Tessellator.instance;

        if (this.hasOverrideBlockTexture()) {
            icon = this.overrideBlockTexture;
        }

        TexturedQuad quad = modelbox.quadList[quadZNeg];

        PositionTextureVertex pos = quad.vertexPositions[vertex1];
        double u = icon.getMinU() + pos.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v = icon.getMinV() + pos.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos2 = quad.vertexPositions[vertex2];
        double u2 = icon.getMinU() + pos2.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v2 = icon.getMinV() + pos2.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos3 = quad.vertexPositions[vertex3];
        double u3 = icon.getMinU() + pos3.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v3 = icon.getMinV() + pos3.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos4 = quad.vertexPositions[vertex4];
        double u4 = icon.getMinU() + pos4.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v4 = icon.getMinV() + pos4.texturePositionY * (icon.getMaxV() - icon.getMinV());

        double[] U = { u, u2, u3, u4 };
        double[] V = { v, v2, v3, v4 };

        rotateUVNeg(U, V, rotateZNeg);
        if (!mirrorText) {
            flipUVNeg(U, V);
        }

        double d11 = x + this.renderMinX;
        double d12 = x + this.renderMaxX;
        double d13 = y + this.renderMinY;
        double d14 = y + this.renderMaxY;
        double d15 = z + this.renderMinZ;

        if (this.renderFromInside) {
            d11 = x + this.renderMaxX;
            d12 = x + this.renderMinX;
        }

        if (this.enableAO) {
            tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            tessellator.setBrightness(this.brightnessTopLeft);
            tessellator.addVertexWithUV(d11, d14, d15, U[0], V[0]);
            tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            tessellator.setBrightness(this.brightnessBottomLeft);
            tessellator.addVertexWithUV(d12, d14, d15, U[1], V[1]);
            tessellator
                .setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            tessellator.setBrightness(this.brightnessBottomRight);
            tessellator.addVertexWithUV(d12, d13, d15, U[2], V[2]);
            tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            tessellator.setBrightness(this.brightnessTopRight);
            tessellator.addVertexWithUV(d11, d13, d15, U[3], V[3]);
        } else {
            tessellator.addVertexWithUV(d11, d14, d15, U[0], V[0]);
            tessellator.addVertexWithUV(d12, d14, d15, U[1], V[1]);
            tessellator.addVertexWithUV(d12, d13, d15, U[2], V[2]);
            tessellator.addVertexWithUV(d11, d13, d15, U[3], V[3]);
        }
    }

    /**
     * Renders the given texture to the south (z-positive) face of the block. Args: block, x, y, z, texture
     */
    public void renderFaceZPos(Block block, double x, double y, double z, IIcon icon) {
        Tessellator tessellator = Tessellator.instance;

        if (this.hasOverrideBlockTexture()) {
            icon = this.overrideBlockTexture;
        }

        TexturedQuad quad = modelbox.quadList[quadZPos];

        PositionTextureVertex pos = quad.vertexPositions[vertex1];
        double u = icon.getMinU() + pos.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v = icon.getMinV() + pos.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos2 = quad.vertexPositions[vertex2];
        double u2 = icon.getMinU() + pos2.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v2 = icon.getMinV() + pos2.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos3 = quad.vertexPositions[vertex3];
        double u3 = icon.getMinU() + pos3.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v3 = icon.getMinV() + pos3.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos4 = quad.vertexPositions[vertex4];
        double u4 = icon.getMinU() + pos4.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v4 = icon.getMinV() + pos4.texturePositionY * (icon.getMaxV() - icon.getMinV());

        double[] U = { u, u2, u3, u4 };
        double[] V = { v, v2, v3, v4 };

        rotateUVPos(U, V, rotateZPos);
        if (!mirrorText) {
            flipUVPos(U, V);
        }

        double d11 = x + this.renderMinX;
        double d12 = x + this.renderMaxX;
        double d13 = y + this.renderMinY;
        double d14 = y + this.renderMaxY;
        double d15 = z + this.renderMaxZ;

        if (this.renderFromInside) {
            d11 = x + this.renderMaxX;
            d12 = x + this.renderMinX;
        }

        if (this.enableAO) {
            tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            tessellator.setBrightness(this.brightnessTopLeft);
            tessellator.addVertexWithUV(d11, d14, d15, U[0], V[0]);
            tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            tessellator.setBrightness(this.brightnessBottomLeft);
            tessellator.addVertexWithUV(d11, d13, d15, U[1], V[1]);
            tessellator
                .setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            tessellator.setBrightness(this.brightnessBottomRight);
            tessellator.addVertexWithUV(d12, d13, d15, U[2], V[2]);
            tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            tessellator.setBrightness(this.brightnessTopRight);
            tessellator.addVertexWithUV(d12, d14, d15, U[3], V[3]);
        } else {
            tessellator.addVertexWithUV(d11, d14, d15, U[0], V[0]);
            tessellator.addVertexWithUV(d11, d13, d15, U[1], V[1]);
            tessellator.addVertexWithUV(d12, d13, d15, U[2], V[2]);
            tessellator.addVertexWithUV(d12, d14, d15, U[3], V[3]);
        }
    }

    /**
     * Renders the given texture to the west (x-negative) face of the block. Args: block, x, y, z, texture
     */
    public void renderFaceXNeg(Block block, double x, double y, double z, IIcon icon) {
        Tessellator tessellator = Tessellator.instance;

        if (this.hasOverrideBlockTexture()) {
            icon = this.overrideBlockTexture;
        }

        TexturedQuad quad = modelbox.quadList[quadXNeg];

        PositionTextureVertex pos = quad.vertexPositions[vertex4];
        double u = icon.getMinU() + pos.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v = icon.getMinV() + pos.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos2 = quad.vertexPositions[vertex3];
        double u2 = icon.getMinU() + pos2.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v2 = icon.getMinV() + pos2.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos3 = quad.vertexPositions[vertex2];
        double u3 = icon.getMinU() + pos3.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v3 = icon.getMinV() + pos3.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos4 = quad.vertexPositions[vertex1];
        double u4 = icon.getMinU() + pos4.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v4 = icon.getMinV() + pos4.texturePositionY * (icon.getMaxV() - icon.getMinV());

        double[] U = { u, u2, u3, u4 };
        double[] V = { v, v2, v3, v4 };

        rotateUVNeg(U, V, rotateXNeg);
        if (mirrorText) {
            flipUVNeg(U, V);
        }

        double d11 = x + this.renderMinX;
        double d12 = y + this.renderMinY;
        double d13 = y + this.renderMaxY;
        double d14 = z + this.renderMinZ;
        double d15 = z + this.renderMaxZ;

        if (this.renderFromInside) {
            d14 = z + this.renderMaxZ;
            d15 = z + this.renderMinZ;
        }

        if (this.enableAO) {
            tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            tessellator.setBrightness(this.brightnessTopLeft);
            tessellator.addVertexWithUV(d11, d13, d15, U[0], V[0]);
            tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            tessellator.setBrightness(this.brightnessBottomLeft);
            tessellator.addVertexWithUV(d11, d13, d14, U[1], V[1]);
            tessellator
                .setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            tessellator.setBrightness(this.brightnessBottomRight);
            tessellator.addVertexWithUV(d11, d12, d14, U[2], V[2]);
            tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            tessellator.setBrightness(this.brightnessTopRight);
            tessellator.addVertexWithUV(d11, d12, d15, U[3], V[3]);
        } else {
            tessellator.addVertexWithUV(d11, d13, d15, U[0], V[0]);
            tessellator.addVertexWithUV(d11, d13, d14, U[1], V[1]);
            tessellator.addVertexWithUV(d11, d12, d14, U[2], V[2]);
            tessellator.addVertexWithUV(d11, d12, d15, U[3], V[3]);
        }
    }

    /**
     * Renders the given texture to the east (x-positive) face of the block. Args: block, x, y, z, texture
     */
    public void renderFaceXPos(Block block, double x, double y, double z, IIcon icon) {
        Tessellator tessellator = Tessellator.instance;

        if (this.hasOverrideBlockTexture()) {
            icon = this.overrideBlockTexture;
        }

        TexturedQuad quad = modelbox.quadList[quadXPos];

        PositionTextureVertex pos = quad.vertexPositions[vertex1];
        double u = icon.getMinU() + pos.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v = icon.getMinV() + pos.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos2 = quad.vertexPositions[vertex2];
        double u2 = icon.getMinU() + pos2.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v2 = icon.getMinV() + pos2.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos3 = quad.vertexPositions[vertex3];
        double u3 = icon.getMinU() + pos3.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v3 = icon.getMinV() + pos3.texturePositionY * (icon.getMaxV() - icon.getMinV());

        PositionTextureVertex pos4 = quad.vertexPositions[vertex4];
        double u4 = icon.getMinU() + pos4.texturePositionX * (icon.getMaxU() - icon.getMinU());
        double v4 = icon.getMinV() + pos4.texturePositionY * (icon.getMaxV() - icon.getMinV());

        double[] U = { u, u2, u3, u4 };
        double[] V = { v, v2, v3, v4 };

        rotateUVPos(U, V, rotateXPos);
        if (!mirrorText) {
            flipUVPos(U, V);
        }

        double d11 = x + this.renderMaxX;
        double d12 = y + this.renderMinY;
        double d13 = y + this.renderMaxY;
        double d14 = z + this.renderMinZ;
        double d15 = z + this.renderMaxZ;

        if (this.renderFromInside) {
            d14 = z + this.renderMaxZ;
            d15 = z + this.renderMinZ;
        }

        if (this.enableAO) {
            tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
            tessellator.setBrightness(this.brightnessTopLeft);
            tessellator.addVertexWithUV(d11, d12, d15, U[0], V[0]);
            tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
            tessellator.setBrightness(this.brightnessBottomLeft);
            tessellator.addVertexWithUV(d11, d12, d14, U[1], V[1]);
            tessellator
                .setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
            tessellator.setBrightness(this.brightnessBottomRight);
            tessellator.addVertexWithUV(d11, d13, d14, U[2], V[2]);
            tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
            tessellator.setBrightness(this.brightnessTopRight);
            tessellator.addVertexWithUV(d11, d13, d15, U[3], V[3]);
        } else {
            tessellator.addVertexWithUV(d11, d12, d15, U[0], V[0]);
            tessellator.addVertexWithUV(d11, d12, d14, U[1], V[1]);
            tessellator.addVertexWithUV(d11, d13, d14, U[2], V[2]);
            tessellator.addVertexWithUV(d11, d13, d15, U[3], V[3]);
        }
    }

}
