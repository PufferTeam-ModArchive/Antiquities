package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.blocks.BlockCouch;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.client.models.ModelCouch;
import net.pufferlab.antiquities.client.models.ModelFurniture;
import net.pufferlab.antiquities.tileentities.TileEntityCouch;

public class BlockCouchRender extends BlockFurnitureRender {

    ModelCouch model = new ModelCouch();

    public BlockCouchRender(int blockComplexRenderID) {
        super(blockComplexRenderID);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        String wood = block2.getType(metadata);

        model.setFacing(0);
        model.side1.isHidden = false;
        model.side2.isHidden = false;
        model.render(wood);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        TileEntityCouch couch = (TileEntityCouch) world.getTileEntity(x, y, z);
        if (couch == null) return false;
        int meta = world.getBlockMetadata(couch.xCoord, couch.yCoord, couch.zCoord);
        Tessellator tess = Tessellator.instance;
        int facing = couch.facingMeta;

        boolean connectNorth = false;
        Block blockNorth = world.getBlock(x, y, z - 1);
        if (blockNorth instanceof BlockCouch) {
            connectNorth = true;
        }
        boolean connectSouth = false;
        Block blockSouth = world.getBlock(x, y, z + 1);
        if (blockSouth instanceof BlockCouch) {
            connectSouth = true;
        }
        boolean connectWest = false;
        Block blockWest = world.getBlock(x - 1, y, z);
        if (blockWest instanceof BlockCouch) {
            connectWest = true;
        }
        boolean connectEast = false;
        Block blockEast = world.getBlock(x + 1, y, z);
        if (blockEast instanceof BlockCouch) {
            connectEast = true;
        }
        model.setFacing(facing);

        model.side1.isHidden = false;
        model.side2.isHidden = false;

        if (facing == 1) {
            if (connectEast) {
                model.side2.isHidden = true;
            }
            if (connectWest) {
                model.side1.isHidden = true;
            }
        }
        if (facing == 2) {
            if (connectNorth) {
                model.side2.isHidden = true;
            }
            if (connectSouth) {
                model.side1.isHidden = true;
            }
        }
        if (facing == 3) {
            if (connectEast) {
                model.side1.isHidden = true;
            }
            if (connectWest) {
                model.side2.isHidden = true;
            }
        }
        if (facing == 4) {
            if (connectNorth) {
                model.side1.isHidden = true;
            }
            if (connectSouth) {
                model.side2.isHidden = true;
            }
        }
        model.render(renderer, tess, block, meta, x, y, z);

        return false;
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelCouch[] { model };
    }
}
