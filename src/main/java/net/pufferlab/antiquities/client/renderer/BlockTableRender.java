package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.blocks.BlockTable;
import net.pufferlab.antiquities.client.models.ModelTable;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockTableRender implements ISimpleBlockRenderingHandler {

    ModelTable model = new ModelTable();
    final int renderID;

    public BlockTableRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        String wood = block2.getType(metadata);
        model.setFacing(0);
        model.side3.isHidden = false;
        model.side4.isHidden = false;
        model.side1.isHidden = false;
        model.side2.isHidden = false;

        model.ext2.isHidden = true;
        model.ext1.isHidden = true;
        model.ext4.isHidden = true;
        model.ext3.isHidden = true;

        model.leg1.isHidden = false;
        model.leg2.isHidden = false;
        model.leg3.isHidden = false;
        model.leg4.isHidden = false;

        model.offset1 = 0;
        model.offset2 = 0;
        model.offset3 = 0;
        model.offset4 = 0;
        model.offset1Y = 0;
        model.offset2Y = 0;
        model.offset3Y = 0;
        model.offset4Y = 0;
        model.updateCTM();
        model.render(wood);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        TileEntityTable table = (TileEntityTable) world.getTileEntity(x, y, z);
        if (table == null) return false;
        int meta = world.getBlockMetadata(table.xCoord, table.yCoord, table.zCoord);
        Tessellator tess = Tessellator.instance;

        boolean connectNorth = false;
        Block blockNorth = world.getBlock(x, y, z - 1);
        if (blockNorth instanceof BlockTable) {
            connectNorth = true;
        }
        Block blockNorthWest = world.getBlock(x - 1, y, z - 1);
        boolean connectNorthWest = false;
        if (blockNorthWest instanceof BlockTable) {
            connectNorthWest = true;
        }
        boolean connectSouth = false;
        Block blockSouth = world.getBlock(x, y, z + 1);
        if (blockSouth instanceof BlockTable) {
            connectSouth = true;
        }
        Block blockSouthWest = world.getBlock(x - 1, y, z + 1);
        boolean connectSouthWest = false;
        if (blockSouthWest instanceof BlockTable) {
            connectSouthWest = true;
        }
        boolean connectWest = false;
        Block blockWest = world.getBlock(x - 1, y, z);
        if (blockWest instanceof BlockTable) {
            connectWest = true;
        }
        Block blockNorthEast = world.getBlock(x + 1, y, z + 1);
        boolean connectNorthEast = false;
        if (blockNorthEast instanceof BlockTable) {
            connectNorthEast = true;
        }
        boolean connectEast = false;
        Block blockEast = world.getBlock(x + 1, y, z);
        if (blockEast instanceof BlockTable) {
            connectEast = true;
        }
        Block blockSouthEast = world.getBlock(x + 1, y, z - 1);
        boolean connectSouthEast = false;
        if (blockSouthEast instanceof BlockTable) {
            connectSouthEast = true;
        }

        model.side3.isHidden = connectNorth;
        model.side4.isHidden = connectSouth;
        model.side1.isHidden = connectEast;
        model.side2.isHidden = connectWest;

        model.ext2.isHidden = !connectSouth || (connectEast && connectNorthEast);
        model.ext1.isHidden = !connectEast || (connectNorth && connectSouthEast);
        model.ext4.isHidden = !connectNorth || (connectWest && connectNorthWest);
        model.ext3.isHidden = !connectWest || (connectSouth && connectSouthWest);

        model.leg1.isHidden = connectNorth || connectEast;
        model.leg2.isHidden = connectSouth || connectEast;
        model.leg3.isHidden = connectSouth || connectWest;
        model.leg4.isHidden = connectNorth || connectWest;

        model.offset1 = 0;
        model.offset1Y = 0;
        if (connectSouth && connectWest && !connectSouthWest) {
            model.offset1 = 3;
        } else if (connectSouth && connectWest) {
            model.offset1Y = 1;
        } else if (connectSouth) {
            model.offset1 = 1;
        } else if (connectWest) {
            model.offset1 = 2;
        }

        model.offset2 = 0;
        model.offset2Y = 0;
        if (connectNorth && connectWest && !connectNorthWest) {
            model.offset2 = 3;
        } else if (connectNorth && connectWest) {
            model.offset2Y = 1;
        } else if (connectNorth) {
            model.offset2 = 1;
        } else if (connectWest) {
            model.offset2 = 2;
        }

        model.offset4 = 0;
        model.offset4Y = 0;
        if (connectNorth && connectEast && !connectSouthEast) {
            model.offset4 = 3;
        } else if (connectNorth && connectEast) {
            model.offset4Y = 1;
        } else if (connectNorth) {
            model.offset4 = 1;
        } else if (connectEast) {
            model.offset4 = 2;
        }

        model.offset3 = 0;
        model.offset3Y = 0;
        if (connectSouth && connectEast && !connectNorthEast) {
            model.offset3 = 3;
        } else if (connectSouth && connectEast) {
            model.offset3Y = 1;
        } else if (connectSouth) {
            model.offset3 = 1;
        } else if (connectEast) {
            model.offset3 = 2;
        }

        model.updateCTM();

        model.setFacing(0);
        model.renderWithoutAO(renderer, tess, block, meta, x, y, z);

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
