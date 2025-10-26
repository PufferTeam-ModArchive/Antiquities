package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.client.models.ModelGlobe;
import net.pufferlab.antiquities.tileentities.TileEntityGlobe;

import org.lwjgl.opengl.GL11;

public class TileEntityGlobeRenderer extends TileEntitySpecialRenderer {

    ModelGlobe model = new ModelGlobe();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        TileEntityGlobe globe = (TileEntityGlobe) tileEntity;

        Block block = tileEntity.getBlockType();
        int metadata = tileEntity.getBlockMetadata();
        String type = "earth";
        if (block instanceof BlockMetaContainer blockmeta) {
            type = blockmeta.getType(metadata);
        }
        float partialRotation = globe.rotation;

        if (globe.speed > 0) {
            partialRotation = globe.rotation + (partialTicks * globe.speed);
        }

        model.setFacing(globe.facingMeta);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        model.render(type);

        if (globe.facingMeta == 2) {
            GL11.glRotatef(-22.5F, 0, 0.0F, 1.0F);
        } else if (globe.facingMeta == 1) {
            GL11.glRotatef(22.5F, 1.0F, 0.0F, 0);
        } else if (globe.facingMeta == 3) {
            GL11.glRotatef(-22.5F, 1.0F, 0.0F, 0);
        } else {
            GL11.glRotatef(22.5F, 0, 0.0F, 1.0F);
        }
        GL11.glRotatef(partialRotation, 0.0F, 1.0F, 0.0F);

        model.render(model.earth_r1, type);

        GL11.glPopMatrix();
    }
}
