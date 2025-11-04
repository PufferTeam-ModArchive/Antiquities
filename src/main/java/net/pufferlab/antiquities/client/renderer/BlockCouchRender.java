package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.client.helper.DirectionHelper;
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
        model.corners.isHidden = true;
        model.leg1.isHidden = false;
        model.leg2.isHidden = false;
        model.leg3.isHidden = false;
        model.leg4.isHidden = false;
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

        boolean connectNorth = DirectionHelper.isConnected(world, x, y, z, ForgeDirection.NORTH);
        boolean connectSouth = DirectionHelper.isConnected(world, x, y, z, ForgeDirection.SOUTH);
        boolean connectWest = DirectionHelper.isConnected(world, x, y, z, ForgeDirection.WEST);
        boolean connectEast = DirectionHelper.isConnected(world, x, y, z, ForgeDirection.EAST);

        model.setFacing(facing);

        model.side1.isHidden = false;
        model.side2.isHidden = false;

        model.corners.isHidden = false;
        model.corner1.isHidden = true;
        model.corner2.isHidden = true;

        model.leg1.isHidden = false;
        model.leg2.isHidden = false;
        model.leg3.isHidden = false;
        model.leg4.isHidden = false;

        if (facing == 1) {
            if (connectEast) {
                model.side2.isHidden = true;
                model.leg2.isHidden = true;
                model.leg4.isHidden = true;
            }
            if (connectWest) {
                model.side1.isHidden = true;
                model.leg1.isHidden = true;
                model.leg3.isHidden = true;
            }
            if (connectEast && connectNorth) {
                model.corner2.isHidden = false;
            }
            if (connectWest && connectNorth) {
                model.corner1.isHidden = false;
            }
        }
        if (facing == 2) {
            if (connectNorth) {
                model.side2.isHidden = true;
                model.leg2.isHidden = true;
                model.leg4.isHidden = true;
            }
            if (connectSouth) {
                model.side1.isHidden = true;
                model.leg1.isHidden = true;
                model.leg3.isHidden = true;
            }
            if (connectNorth && connectWest) {
                model.corner2.isHidden = false;
            }
            if (connectSouth && connectWest) {
                model.corner1.isHidden = false;
            }
        }
        if (facing == 3) {
            if (connectEast) {
                model.side1.isHidden = true;
                model.leg1.isHidden = true;
                model.leg3.isHidden = true;
            }
            if (connectWest) {
                model.side2.isHidden = true;
                model.leg2.isHidden = true;
                model.leg4.isHidden = true;
            }
            if (connectSouth && connectWest) {
                model.corner2.isHidden = false;
            }
            if (connectSouth && connectEast) {
                model.corner1.isHidden = false;
            }
        }
        if (facing == 4) {
            if (connectNorth) {
                model.side1.isHidden = true;
                model.leg1.isHidden = true;
                model.leg3.isHidden = true;
            }
            if (connectSouth) {
                model.side2.isHidden = true;
                model.leg2.isHidden = true;
                model.leg4.isHidden = true;
            }
            if (connectSouth && connectEast) {
                model.corner2.isHidden = false;
            }
            if (connectNorth && connectEast) {
                model.corner1.isHidden = false;
            }
        }
        if (!model.corner2.isHidden) {
            model.side1.isHidden = true;
        }
        if (!model.corner1.isHidden) {
            model.side2.isHidden = true;
        }
        model.render(renderer, tess, block, meta, x, y, z);

        return false;
    }

    @Override
    public ModelFurniture[] getModelObj() {
        return new ModelCouch[] { model };
    }
}
