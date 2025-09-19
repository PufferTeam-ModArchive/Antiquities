package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.client.models.ModelGlobe;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockGlobeRender implements ISimpleBlockRenderingHandler {

    ModelGlobe model = new ModelGlobe();
    final int renderID;

    public BlockGlobeRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        int facingMeta = 0;
        model.setFacing(facingMeta);

        GL11.glPushMatrix();
        model.render();

        if (facingMeta == 2) {
            GL11.glRotatef(-22.5F, 0, 0.0F, 1.0F);
        } else if (facingMeta == 1) {
            GL11.glRotatef(22.5F, 1.0F, 0.0F, 0);
        } else if (facingMeta == 3) {
            GL11.glRotatef(-22.5F, 1.0F, 0.0F, 0);
        } else {
            GL11.glRotatef(22.5F, 0, 0.0F, 1.0F);
        }
        GL11.glRotatef(0F, 0.0F, 1.0F, 0.0F);

        model.renderGlobe();

        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderID;
    }
}
