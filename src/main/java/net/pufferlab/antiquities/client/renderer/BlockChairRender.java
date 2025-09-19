package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.client.models.ModelChair;
import net.pufferlab.antiquities.tileentities.TileEntityChair;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockChairRender implements ISimpleBlockRenderingHandler {

    Tessellator tess = Tessellator.instance;
    ModelChair model = new ModelChair();
    final int renderID;

    public BlockChairRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        String wood = block2.getType(metadata);
        model.setFacing(0);
        model.render(wood);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        TileEntityChair chair = (TileEntityChair) world.getTileEntity(x, y, z);
        if (chair == null) return false;
        int meta = world.getBlockMetadata(x, y, z);

        model.setFacing(chair.facingMeta);
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
