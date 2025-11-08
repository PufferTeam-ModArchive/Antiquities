package net.pufferlab.antiquities.client.utils;

import net.minecraft.client.renderer.Tessellator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModelBox {

    /** The (x,y,z) vertex positions and (u,v) texture coordinates for each of the 8 points on a cube */
    private PositionTextureVertex[] vertexPositions;
    /** An array of 6 TexturedQuads, one for each face of a cube */
    public TexturedQuad[] quadList;
    /** X vertex coordinate of lower box corner */
    public final float posX1;
    /** Y vertex coordinate of lower box corner */
    public final float posY1;
    /** Z vertex coordinate of lower box corner */
    public final float posZ1;
    /** X vertex coordinate of upper box corner */
    public final float posX2;
    /** Y vertex coordinate of upper box corner */
    public final float posY2;
    /** Z vertex coordinate of upper box corner */
    public final float posZ2;
    public String field_78247_g;

    public ModelBox(ModelRenderer body, int U, int V, float xMin, float yMin, float zMin, int xWidth, int yWidth,
        int zWidth, float inflate) {
        this.posX1 = xMin;
        this.posY1 = yMin;
        this.posZ1 = zMin;
        this.posX2 = xMin + (float) xWidth;
        this.posY2 = yMin + (float) yWidth;
        this.posZ2 = zMin + (float) zWidth;
        this.vertexPositions = new PositionTextureVertex[8];
        this.quadList = new TexturedQuad[6];
        float f4 = xMin + (float) xWidth;
        float f5 = yMin + (float) yWidth;
        float f6 = zMin + (float) zWidth;
        xMin -= inflate;
        yMin -= inflate;
        zMin -= inflate;
        f4 += inflate;
        f5 += inflate;
        f6 += inflate;

        if (body.mirror) {
            float f7 = f4;
            f4 = xMin;
            xMin = f7;
        }

        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(xMin, yMin, zMin, 0.0F, 0.0F);
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f4, yMin, zMin, 0.0F, 8.0F);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f4, f5, zMin, 8.0F, 8.0F);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(xMin, f5, zMin, 8.0F, 0.0F);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(xMin, yMin, f6, 0.0F, 0.0F);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f4, yMin, f6, 0.0F, 8.0F);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f4, f5, f6, 8.0F, 8.0F);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(xMin, f5, f6, 8.0F, 0.0F);
        this.vertexPositions[0] = positiontexturevertex7;
        this.vertexPositions[1] = positiontexturevertex;
        this.vertexPositions[2] = positiontexturevertex1;
        this.vertexPositions[3] = positiontexturevertex2;
        this.vertexPositions[4] = positiontexturevertex3;
        this.vertexPositions[5] = positiontexturevertex4;
        this.vertexPositions[6] = positiontexturevertex5;
        this.vertexPositions[7] = positiontexturevertex6;
        this.quadList[0] = new TexturedQuad(
            new PositionTextureVertex[] { positiontexturevertex4, positiontexturevertex, positiontexturevertex1,
                positiontexturevertex5 },
            U + zWidth + xWidth,
            V + zWidth,
            U + zWidth + xWidth + zWidth,
            V + zWidth + yWidth,
            body.textureWidth,
            body.textureHeight);
        this.quadList[1] = new TexturedQuad(
            new PositionTextureVertex[] { positiontexturevertex7, positiontexturevertex3, positiontexturevertex6,
                positiontexturevertex2 },
            U,
            V + zWidth,
            U + zWidth,
            V + zWidth + yWidth,
            body.textureWidth,
            body.textureHeight);
        this.quadList[2] = new TexturedQuad(
            new PositionTextureVertex[] { positiontexturevertex4, positiontexturevertex3, positiontexturevertex7,
                positiontexturevertex },
            U + zWidth,
            V,
            U + zWidth + xWidth,
            V + zWidth,
            body.textureWidth,
            body.textureHeight);
        this.quadList[3] = new TexturedQuad(
            new PositionTextureVertex[] { positiontexturevertex1, positiontexturevertex2, positiontexturevertex6,
                positiontexturevertex5 },
            U + zWidth + xWidth,
            V + zWidth,
            U + zWidth + xWidth + xWidth,
            V,
            body.textureWidth,
            body.textureHeight);
        this.quadList[4] = new TexturedQuad(
            new PositionTextureVertex[] { positiontexturevertex, positiontexturevertex7, positiontexturevertex2,
                positiontexturevertex1 },
            U + zWidth,
            V + zWidth,
            U + zWidth + xWidth,
            V + zWidth + yWidth,
            body.textureWidth,
            body.textureHeight);
        this.quadList[5] = new TexturedQuad(
            new PositionTextureVertex[] { positiontexturevertex3, positiontexturevertex4, positiontexturevertex5,
                positiontexturevertex6 },
            U + zWidth + xWidth + zWidth,
            V + zWidth,
            U + zWidth + xWidth + zWidth + xWidth,
            V + zWidth + yWidth,
            body.textureWidth,
            body.textureHeight);

        if (body.mirror) {
            for (int j1 = 0; j1 < this.quadList.length; ++j1) {
                this.quadList[j1].flipFace();
            }
        }
    }

    public ModelBox(ModelRenderer body, int U, int V, float xMin, float yMin, float zMin, int xWidth, int yWidth,
        int zWidth, float inflate, boolean mirror) {
        this(setMirror(body, mirror), U, V, xMin, yMin, zMin, xWidth, yWidth, zWidth, inflate);
    }

    static ModelRenderer setMirror(ModelRenderer in, boolean mirrorToBe) {
        in.mirror = mirrorToBe;
        return in;
    }

    /**
     * Draw the six sided box defined by this ModelBox
     */
    @SideOnly(Side.CLIENT)
    public void render(Tessellator p_78245_1_, float p_78245_2_) {
        for (int i = 0; i < this.quadList.length; ++i) {
            this.quadList[i].draw(p_78245_1_, p_78245_2_);
        }
    }

    public ModelBox func_78244_a(String p_78244_1_) {
        this.field_78247_g = p_78244_1_;
        return this;
    }
}
