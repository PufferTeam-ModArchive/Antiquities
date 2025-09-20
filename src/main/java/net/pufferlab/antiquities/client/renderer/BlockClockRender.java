package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.client.models.ModelClock;
import net.pufferlab.antiquities.tileentities.TileEntityClock;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockClockRender implements ISimpleBlockRenderingHandler {

    ModelClock model = new ModelClock();
    final int renderID;

    public BlockClockRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        int facingMeta = 0;
        model.setFacing(facingMeta);

        GL11.glPushMatrix();
        model.render();

        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        TileEntityClock clock = (TileEntityClock) world.getTileEntity(x, y, z);
        if (clock == null) return false;
        int meta = world.getBlockMetadata(x, y, z);
        Tessellator tess = Tessellator.instance;

        model.setFacing(clock.facingMeta);

        model.render(renderer, tess, block, meta, x, y, z);
        return true;
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
