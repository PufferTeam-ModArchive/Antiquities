package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.client.models.ModelFurniture;
import net.pufferlab.antiquities.tileentities.TileEntityMetaFacing;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public abstract class BlockFurnitureRender implements ISimpleBlockRenderingHandler {

    final int renderID;
    ModelFurniture[] model;

    public BlockFurnitureRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        if (model == null) {
            model = this.getModelObj();
        }
        int num = 0;
        String wood = null;
        if (block instanceof BlockMetaContainer blockmeta) {
            num = blockmeta.getBlockType();
            wood = blockmeta.getType(metadata);
        }
        if (model[num] != null) {
            if (hasBigInventoryModel()) {
                model[num].setFacing(0);
                GL11.glPushMatrix();
                GL11.glScalef(1.5F, 1.5F, 1.5F);
                GL11.glTranslated(this.getOffsetX(), this.getOffsetY(), this.getOffsetZ());
                model[num].render(wood);
                GL11.glPopMatrix();
            } else {
                model[num].setFacing(0);
                model[num].render(wood);
            }

        }
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (model == null) {
            model = this.getModelObj();
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (te == null) return false;
        int meta = world.getBlockMetadata(x, y, z);
        Tessellator tess = Tessellator.instance;
        int num = 0;
        if (block instanceof BlockMetaContainer blockmeta) {
            num = blockmeta.getBlockType();
        }
        if (model[num] != null) {
            if (te instanceof TileEntityMetaFacing tef) {
                model[num].setFacing(tef.facingMeta);
            }
            if (useAORender()) {
                model[num].render(renderer, tess, block, meta, x, y, z);
            } else {
                model[num].renderWithoutAO(renderer, tess, block, meta, x, y, z);
            }
            return true;
        }

        return false;
    }

    public boolean useAORender() {
        return true;
    }

    public boolean hasBigInventoryModel() {
        return false;
    }

    public float getOffsetX() {
        return 0;
    }

    public float getOffsetY() {
        return 0;
    }

    public float getOffsetZ() {
        return 0;
    }

    public ModelFurniture[] getModelObj() {
        return null;
    }
}
