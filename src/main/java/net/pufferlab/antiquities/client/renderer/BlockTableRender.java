package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.blocks.BlockShelf;
import net.pufferlab.antiquities.blocks.BlockTable;
import net.pufferlab.antiquities.client.models.ModelTable;
import net.pufferlab.antiquities.tileentities.TileEntityShelf;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

import org.lwjgl.opengl.GL11;

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
        model.render(wood);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        TileEntityTable table = (TileEntityTable) world.getTileEntity(x, y, z);
        int meta = world.getBlockMetadata(table.xCoord, table.yCoord, table.zCoord);
        Tessellator tess = Tessellator.instance;

        boolean connectNorth = false;
        TileEntity teNorth = world.getTileEntity(x, y, z - 1);
        if(teNorth instanceof TileEntityTable) {
            connectNorth = true;
        }
        boolean connectSouth = false;
        TileEntity teSouth = world.getTileEntity(x, y, z + 1);
        if(teSouth instanceof TileEntityTable) {
            connectSouth = true;
        }
        boolean connectWest = false;
        TileEntity teWest = world.getTileEntity(x - 1, y, z);
        if(teWest instanceof TileEntityTable) {
            connectWest = true;
        }
        boolean connectEast = false;
        TileEntity teEast = world.getTileEntity(x + 1, y, z);
        if(teEast instanceof TileEntityTable) {
            connectEast = true;
        }

        model.side_n.isHidden = !connectNorth;
        model.side_s.isHidden = !connectSouth;
        model.side_w.isHidden = !connectWest;
        model.side_e.isHidden = !connectEast;

        model.leg1.isHidden = connectNorth && connectWest;
        model.leg2.isHidden = connectSouth && connectEast;
        model.leg3.isHidden = connectSouth && connectEast;
        model.leg4.isHidden = connectSouth && connectWest;

        model.setFacing(0);
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
