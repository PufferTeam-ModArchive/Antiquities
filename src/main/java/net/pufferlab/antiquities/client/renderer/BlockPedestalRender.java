package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.client.models.ModelPedestal;
import net.pufferlab.antiquities.tileentities.TileEntityPedestal;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockPedestalRender implements ISimpleBlockRenderingHandler {

    Tessellator tess = Tessellator.instance;
    ModelPedestal model = new ModelPedestal();
    final int renderID;

    public BlockPedestalRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        String wood = block2.getType(metadata);
        model.setFacing(0);
        GL11.glPushMatrix();
        GL11.glScalef(1.5F, 1.5F, 1.5F);
        GL11.glTranslated(0.0F, 0.25F, 0.0F);
        model.render(wood);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        TileEntityPedestal pedestal = (TileEntityPedestal) world.getTileEntity(x, y, z);
        if (pedestal == null) return false;
        int meta = world.getBlockMetadata(x, y, z);
        model.setFacing(pedestal.facingMeta);
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
