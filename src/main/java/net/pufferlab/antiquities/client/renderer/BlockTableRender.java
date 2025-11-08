package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.client.helper.DirectionHelper;
import net.pufferlab.antiquities.client.models.ModelFurniture;
import net.pufferlab.antiquities.client.models.ModelTable;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

public class BlockTableRender extends BlockFurnitureRender {

    ModelTable model = new ModelTable();

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
        model.ignore = true;
        model.updateCTM();
        model.render(wood);
        model.ignore = false;
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        TileEntityTable table = (TileEntityTable) world.getTileEntity(x, y, z);
        if (table == null) return false;
        int meta = world.getBlockMetadata(table.xCoord, table.yCoord, table.zCoord);
        Tessellator tess = Tessellator.instance;

        mutateModel(world, x, y, z, 0);

        if (Config.legacyTextures) {
            model.render(renderer, tess, block, meta, x, y, z);
        } else {
            model.renderWithoutAO(renderer, tess, block, meta, x, y, z);
        }

        return true;
    }

    @Override
    public void mutateModel(IBlockAccess world, int x, int y, int z, int facing) {
        boolean connectNorth = DirectionHelper.isConnected(world, x, y, z, ForgeDirection.NORTH);
        boolean connectNorthWest = DirectionHelper
            .isConnected(world, x, y, z, ForgeDirection.NORTH, ForgeDirection.WEST);
        boolean connectSouth = DirectionHelper.isConnected(world, x, y, z, ForgeDirection.SOUTH);
        boolean connectSouthWest = DirectionHelper
            .isConnected(world, x, y, z, ForgeDirection.SOUTH, ForgeDirection.WEST);
        boolean connectWest = DirectionHelper.isConnected(world, x, y, z, ForgeDirection.WEST);
        boolean connectNorthEast = DirectionHelper
            .isConnected(world, x, y, z, ForgeDirection.NORTH, ForgeDirection.EAST);
        boolean connectEast = DirectionHelper.isConnected(world, x, y, z, ForgeDirection.EAST);
        boolean connectSouthEast = DirectionHelper
            .isConnected(world, x, y, z, ForgeDirection.SOUTH, ForgeDirection.EAST);

        model.side3.isHidden = connectNorth;
        model.side4.isHidden = connectSouth;
        model.side1.isHidden = connectEast;
        model.side2.isHidden = connectWest;

        model.ext2.isHidden = !connectSouth || (connectEast && connectSouthEast);
        model.ext1.isHidden = !connectEast || (connectNorth && connectNorthEast);
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
        if (connectNorth && connectEast && !connectNorthEast) {
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
        if (connectSouth && connectEast && !connectSouthEast) {
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
    }

    @Override
    public int getRenderId() {
        return Antiquities.proxy.getTableRenderID();
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelTable[] { model };
    }
}
