package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.blocks.BlockShelf;
import net.pufferlab.antiquities.client.models.*;
import net.pufferlab.antiquities.tileentities.TileEntityShelf;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockShelfRender implements ISimpleBlockRenderingHandler {

    ModelShelfFull model0 = new ModelShelfFull();
    ModelShelf model1 = new ModelShelf();
    ModelShelfLong model2 = new ModelShelfLong();
    ModelShelfFullCase model3 = new ModelShelfFullCase();
    ModelShelfCase model4 = new ModelShelfCase();
    ModelShelfLongCase model5 = new ModelShelfLongCase();
    final int renderID;

    public BlockShelfRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        BlockShelf block2 = (BlockShelf) block;
        String wood = block2.getType(metadata);
        int type = block2.getShelfType();

        if (type == 0) {
            model0.setFacing(0);
            model0.render(wood);
        } else if (type == 1) {
            model1.setFacing(0);
            model1.render(wood);
        } else if (type == 2) {
            model2.setFacing(0);
            model2.render(wood);
        } else if (type == 3) {
            model3.setFacing(0);
            model3.render(wood);
        } else if (type == 4) {
            model4.setFacing(0);
            model4.render(wood);
        } else if (type == 5) {
            model5.setFacing(0);
            model5.render(wood);
        }
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        TileEntityShelf shelf = (TileEntityShelf) world.getTileEntity(x, y, z);
        if (shelf == null) return false;
        BlockShelf block2 = (BlockShelf) block;
        int meta = world.getBlockMetadata(shelf.xCoord, shelf.yCoord, shelf.zCoord);
        Tessellator tess = Tessellator.instance;

        int type = block2.getShelfType();

        if (type == 0) {
            model0.setFacing(shelf.facingMeta);
            model0.render(renderer, tess, block, meta, x, y, z);
        } else if (type == 1) {
            model1.setFacing(shelf.facingMeta);
            model1.render(renderer, tess, block, meta, x, y, z);
        } else if (type == 2) {
            model2.setFacing(shelf.facingMeta);
            model2.render(renderer, tess, block, meta, x, y, z);
        } else if (type == 3) {
            model3.setFacing(shelf.facingMeta);
            model3.render(renderer, tess, block, meta, x, y, z);
        } else if (type == 4) {
            model4.setFacing(shelf.facingMeta);
            model4.render(renderer, tess, block, meta, x, y, z);
        } else if (type == 5) {
            model5.setFacing(shelf.facingMeta);
            model5.render(renderer, tess, block, meta, x, y, z);
        }

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
