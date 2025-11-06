package net.pufferlab.antiquities.client.helper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.blocks.BlockChair;
import net.pufferlab.antiquities.blocks.BlockRack;
import net.pufferlab.antiquities.blocks.BlockShelf;
import net.pufferlab.antiquities.blocks.BlockTable;
import net.pufferlab.antiquities.client.utils.*;
import net.pufferlab.antiquities.tileentities.TileEntityMetaFacing;

public class ModelTESS {

    static double epsilon = 2e-5;

    public void render(RenderBlocks renderblocks, Tessellator tess, Block block, ModelRenderer renderer, float scale,
        int x, int y, int z, int meta) {
        if (Config.renderWithAO) {
            CustomRenderBlocks renderblocks2 = new CustomRenderBlocks(this);
            renderBlockAO(renderblocks, renderblocks2, block, renderer, scale, x, y, z, meta);
        } else {
            renderBlock(renderblocks, tess, block, renderer, scale, x, y, z, meta);
        }
    }

    public void renderBlock(RenderBlocks renderblocks, Tessellator tess, Block block, ModelRenderer renderer,
        float scale, int x, int y, int z, int meta) {
        if (block instanceof BlockTable) {
            if (renderblocks.hasOverrideBlockTexture() && Config.renderBreakingTexture) {
                renderblocks.setRenderBounds(0.0F, 0.875F, 0.0F, 1.0F, 1.0F, 1.0F);
                renderblocks.renderStandardBlock(block, x, y, z);
                return;
            }
        }

        if (!renderer.isHidden && renderer.showModel) {

            // Render children first
            if (renderer.childModels != null) {
                for (int i = 0; i < renderer.childModels.size(); ++i) {
                    ModelRenderer child = (ModelRenderer) renderer.childModels.get(i);

                    // Backup child state
                    float oldRotateX = child.rotateAngleX;
                    float oldRotateY = child.rotateAngleY;
                    float oldRotateZ = child.rotateAngleZ;
                    float oldPivotX = child.rotationPointX;
                    float oldPivotY = child.rotationPointY;
                    float oldPivotZ = child.rotationPointZ;

                    // Apply parent rotation/pivot to child
                    child.rotateAngleX += renderer.rotateAngleX;
                    child.rotateAngleY += renderer.rotateAngleY;
                    child.rotateAngleZ += renderer.rotateAngleZ;

                    child.rotationPointX += renderer.rotationPointX;
                    child.rotationPointY += renderer.rotationPointY;
                    child.rotationPointZ += renderer.rotationPointZ;

                    // Recurse
                    renderBlock(renderblocks, tess, block, child, scale, x, y, z, meta);

                    // Restore child state
                    child.rotateAngleX = oldRotateX;
                    child.rotateAngleY = oldRotateY;
                    child.rotateAngleZ = oldRotateZ;
                    child.rotationPointX = oldPivotX;
                    child.rotationPointY = oldPivotY;
                    child.rotationPointZ = oldPivotZ;
                }
            }

            if (renderer.cubeList != null) {
                for (int i = 0; i < renderer.cubeList.size(); ++i) {
                    ModelBox box = renderer.cubeList.get(i);

                    int index = 99;
                    if (box instanceof ModelBoxSpecial) {
                        index = ((ModelBoxSpecial) box).iconIndex;
                    }
                    IIcon icon = block.getIcon(index, meta);

                    for (int j = 0; j < box.quadList.length; ++j) {
                        TexturedQuad quad = box.quadList[j];

                        // --- Base normal (unrotated) ---
                        Vec3 v10 = quad.vertexPositions[1].vector3D.subtract(quad.vertexPositions[0].vector3D);
                        Vec3 v20 = quad.vertexPositions[1].vector3D.subtract(quad.vertexPositions[2].vector3D);
                        Vec3 normal = v20.crossProduct(v10)
                            .normalize();

                        // --- Block brightness + color ---
                        tess.setBrightness(block.getMixedBrightnessForBlock(renderblocks.blockAccess, x, y, z));
                        int i1 = block.colorMultiplier(renderblocks.blockAccess, x, y, z);
                        float f = (float) (i1 >> 16 & 255) / 255.0F;
                        float f1 = (float) (i1 >> 8 & 255) / 255.0F;
                        float f2 = (float) (i1 & 255) / 255.0F;

                        if (EntityRenderer.anaglyphEnable) {
                            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
                            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
                            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
                            f = f3;
                            f1 = f4;
                            f2 = f5;
                        }

                        // --- Rotation angles (cos/sin) ---
                        double cosX = Math.cos(renderer.rotateAngleX), sinX = Math.sin(renderer.rotateAngleX);
                        double cosY = Math.cos(renderer.rotateAngleY), sinY = Math.sin(renderer.rotateAngleY);
                        double cosZ = Math.cos(renderer.rotateAngleZ), sinZ = Math.sin(renderer.rotateAngleZ);

                        // --- Rotate the normal ---
                        double nx = normal.xCoord, ny = normal.yCoord, nz = normal.zCoord;

                        double ny1 = ny * cosX - nz * sinX;
                        double nz1 = ny * sinX + nz * cosX;

                        double nx2 = nx * cosY + nz1 * sinY;
                        double nz2 = -nx * sinY + nz1 * cosY;

                        double nx3 = nx2 * cosZ - ny1 * sinZ;
                        double ny3 = nx2 * sinZ + ny1 * cosZ;

                        double len = Math.sqrt(nx3 * nx3 + ny3 * ny3 + nz2 * nz2);
                        if (len > 0) {
                            nx3 /= len;
                            ny3 /= len;
                            nz2 /= len;
                        }

                        tess.setNormal((float) nx3, (float) ny3, (float) nz2);

                        // --- Shading based on rotated normal ---
                        float shade = 1.0F;
                        if (ny3 > 0.5F) shade = 1.0F; // top
                        else if (ny3 < -0.5F) shade = 0.5F; // bottom
                        else if (nx3 > 0.5F || nx3 < -0.5F) shade = 0.6F; // east/west
                        else if (nz2 > 0.5F || nz2 < -0.5F) shade = 0.8F; // north/south

                        tess.setColorOpaque_F(f * shade, f1 * shade, f2 * shade);

                        PositionTextureVertex pos1 = quad.vertexPositions[0];
                        double u1 = icon.getMinU() + pos1.texturePositionX * (icon.getMaxU() - icon.getMinU());
                        double v1 = icon.getMinV() + pos1.texturePositionY * (icon.getMaxV() - icon.getMinV());

                        PositionTextureVertex pos2 = quad.vertexPositions[1];
                        double u2 = icon.getMinU() + pos2.texturePositionX * (icon.getMaxU() - icon.getMinU());
                        double v2 = icon.getMinV() + pos2.texturePositionY * (icon.getMaxV() - icon.getMinV());

                        PositionTextureVertex pos3 = quad.vertexPositions[2];
                        double u3 = icon.getMinU() + pos3.texturePositionX * (icon.getMaxU() - icon.getMinU());
                        double v3 = icon.getMinV() + pos3.texturePositionY * (icon.getMaxV() - icon.getMinV());

                        PositionTextureVertex pos4 = quad.vertexPositions[3];
                        double u4 = icon.getMinU() + pos4.texturePositionX * (icon.getMaxU() - icon.getMinU());
                        double v4 = icon.getMinV() + pos4.texturePositionY * (icon.getMaxV() - icon.getMinV());

                        double[] U = { u1, u2, u3, u4 };
                        double[] V = { v1, v2, v3, v4 };

                        U = addEpsilonOffset(U);
                        V = addEpsilonOffset(V);

                        // --- Add rotated vertices ---
                        for (int p = 0; p < 4; ++p) {
                            PositionTextureVertex pos = quad.vertexPositions[p];
                            double u = U[p];
                            double v = V[p];

                            double px = pos.vector3D.xCoord * scale;
                            double py = pos.vector3D.yCoord * scale;
                            double pz = pos.vector3D.zCoord * scale;

                            // --- Translate into pivot space ---
                            px -= renderer.rotationPointX * scale;
                            py -= renderer.rotationPointY * scale;
                            pz -= renderer.rotationPointZ * scale;

                            // --- Rotate vertex same as normal ---
                            double y1 = py * cosX - pz * sinX;
                            double z1 = py * sinX + pz * cosX;

                            double x2 = px * cosY + z1 * sinY;
                            double z2v = -px * sinY + z1 * cosY;

                            double x3 = x2 * cosZ - y1 * sinZ;
                            double y3 = x2 * sinZ + y1 * cosZ;

                            // --- Translate back from pivot ---
                            x3 += renderer.rotationPointX * scale;
                            y3 += renderer.rotationPointY * scale;
                            z2v += renderer.rotationPointZ * scale;

                            // --- Apply world offset ---
                            double vx = x3 + x + 0.5;
                            double vy = y3 + y + 0.5;
                            double vz = z2v + z + 0.5;

                            tess.addVertexWithUV(vx, vy, vz, u, v);
                        }
                    }
                }
            }
        }
    }

    public void renderBlockAO(RenderBlocks renderblocks, CustomRenderBlocks renderblocks2, Block block,
        ModelRenderer renderer, float scale, int x, int y, int z, int meta) {
        if (!renderer.isHidden && renderer.showModel) {

            // Render children first
            if (renderer.childModels != null) {
                for (int i = 0; i < renderer.childModels.size(); ++i) {
                    ModelRenderer child = (ModelRenderer) renderer.childModels.get(i);

                    // Backup child state
                    float oldRotateX = child.rotateAngleX;
                    float oldRotateY = child.rotateAngleY;
                    float oldRotateZ = child.rotateAngleZ;
                    float oldPivotX = child.rotationPointX;
                    float oldPivotY = child.rotationPointY;
                    float oldPivotZ = child.rotationPointZ;

                    // Apply parent rotation/pivot to child
                    child.rotateAngleX += renderer.rotateAngleX;
                    child.rotateAngleY += renderer.rotateAngleY;
                    child.rotateAngleZ += renderer.rotateAngleZ;

                    child.rotationPointX += renderer.rotationPointX;
                    child.rotationPointY += renderer.rotationPointY;
                    child.rotationPointZ += renderer.rotationPointZ;

                    // Recurse
                    renderBlockAO(renderblocks, renderblocks2, block, child, scale, x, y, z, meta);

                    // Restore child state
                    child.rotateAngleX = oldRotateX;
                    child.rotateAngleY = oldRotateY;
                    child.rotateAngleZ = oldRotateZ;
                    child.rotationPointX = oldPivotX;
                    child.rotationPointY = oldPivotY;
                    child.rotationPointZ = oldPivotZ;
                }
            }

            if (renderer.cubeList != null) {
                for (int i = 0; i < renderer.cubeList.size(); ++i) {
                    ModelBox box = renderer.cubeList.get(i);

                    double[] bounds = MatrixHelper.getBounds(renderer, box, scale);

                    double minX = bounds[0];
                    double minY = bounds[1];
                    double minZ = bounds[2];
                    double maxX = bounds[3];
                    double maxY = bounds[4];
                    double maxZ = bounds[5];

                    renderblocks2.modelbox = box;
                    renderblocks2.modelrenderer = renderer;
                    renderblocks2.blockAccess = renderblocks.blockAccess;

                    renderblocks2.vertex1 = 0;
                    renderblocks2.vertex2 = 1;
                    renderblocks2.vertex3 = 2;
                    renderblocks2.vertex4 = 3;

                    renderblocks2.rotateYPos = 1;
                    renderblocks2.rotateYNeg = 3;
                    renderblocks2.rotateXPos = 2;
                    renderblocks2.rotateXNeg = 0;
                    renderblocks2.rotateZNeg = 0;
                    renderblocks2.rotateZPos = 3;
                    renderblocks2.mirrorText = true;

                    renderblocks2.quadXNeg = 0;
                    renderblocks2.quadXPos = 1;
                    renderblocks2.quadYNeg = 2;
                    renderblocks2.quadYPos = 3;
                    renderblocks2.quadZNeg = 4;
                    renderblocks2.quadZPos = 5;

                    if (renderblocks.blockAccess.getTileEntity(x, y, z) instanceof TileEntityMetaFacing) {
                        TileEntityMetaFacing facing = (TileEntityMetaFacing) renderblocks.blockAccess
                            .getTileEntity(x, y, z);
                        int facingMeta = facing.facingMeta;
                        int facingType = facing.getFacingType();

                        int rotateMeta = 0;
                        if (facingType == -1) {
                            int angleToRotate = ((int) Math.round(Math.toDegrees(renderer.rotateAngleY) / 90)) % 4;
                            if (angleToRotate == 1) {
                                rotateMeta = 3;
                            } else if (angleToRotate == 2) {
                                rotateMeta = 1;
                            } else if (angleToRotate == 3) {
                                rotateMeta = 2;
                            }
                        } else {
                            if ((facingMeta == 2 && facingType == 1) || (facingMeta == 4 && facingType == 0)) {
                                rotateMeta = 1;
                            } else if ((facingMeta == 3 && facingType == 1) || (facingMeta == 1 && facingType == 0)) {
                                rotateMeta = 2;
                            } else if ((facingMeta == 4 && facingType == 1) || (facingMeta == 2 && facingType == 0)) {
                                rotateMeta = 3;
                            }
                        }

                        if (rotateMeta == 1) {
                            renderblocks2.quadXNeg = 4;
                            renderblocks2.quadXPos = 5;
                            renderblocks2.quadZNeg = 0;
                            renderblocks2.quadZPos = 1;
                            renderblocks2.rotateYPos = 0;
                            renderblocks2.rotateYNeg = 2;

                        }

                        if (rotateMeta == 2) {
                            renderblocks2.quadXNeg = 1;
                            renderblocks2.quadXPos = 0;
                            renderblocks2.quadZNeg = 5;
                            renderblocks2.quadZPos = 4;
                            renderblocks2.rotateYPos = 3;
                            renderblocks2.rotateYNeg = 1;
                        }

                        if (rotateMeta == 3) {
                            renderblocks2.quadXNeg = 5;
                            renderblocks2.quadXPos = 4;
                            renderblocks2.quadZNeg = 1;
                            renderblocks2.quadZPos = 0;
                            renderblocks2.rotateYPos = 2;
                            renderblocks2.rotateYNeg = 0;
                        }
                    }

                    renderblocks2.renderAllFaces = true;
                    int index = 99;
                    if (box instanceof ModelBoxSpecial) {
                        index = ((ModelBoxSpecial) box).iconIndex;
                    }
                    renderblocks2.overrideBlockTexture = block.getIcon(index, meta);
                    if (renderblocks.hasOverrideBlockTexture() && Config.renderBreakingTexture) {
                        renderblocks.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
                        renderblocks.renderStandardBlock(block, x, y, z);
                    } else if (Config.legacyTextures && (block instanceof BlockTable || block instanceof BlockShelf
                        || block instanceof BlockChair
                        || block instanceof BlockRack)) {
                            renderblocks.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
                            renderblocks.renderStandardBlock(block, x, y, z);
                        } else {
                            renderblocks2.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
                            renderblocks2.renderStandardBlock(block, x, y, z);
                        }
                    renderblocks2.uvRotateEast = 0;
                    renderblocks2.uvRotateWest = 0;
                    renderblocks2.uvRotateSouth = 0;
                    renderblocks2.uvRotateNorth = 0;
                    renderblocks2.uvRotateTop = 0;
                    renderblocks2.uvRotateBottom = 0;
                    renderblocks2.renderAllFaces = false;
                }
            }
        }
    }

    public double[] addEpsilonOffset(double[] coords) {
        double min = coords[0];
        double max = coords[0];
        for (int i = 1; i < coords.length; i++) {
            if (coords[i] < min) min = coords[i];
            if (coords[i] > max) max = coords[i];
        }

        for (int i = 0; i < coords.length; i++) {
            if (Math.abs(coords[i] - min) < 1e-9) {
                coords[i] = min + epsilon;
            } else if (Math.abs(coords[i] - max) < 1e-9) {
                coords[i] = max - epsilon;
            }
        }

        return coords;
    }
}
