package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.client.models.ModelGlobe;

import org.lwjgl.opengl.GL11;

public class BlockGlobeRender extends BlockFurnitureRender {

    ModelGlobe model = new ModelGlobe();

    public BlockGlobeRender(int blockComplexRenderID) {
        super(blockComplexRenderID);
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
}
