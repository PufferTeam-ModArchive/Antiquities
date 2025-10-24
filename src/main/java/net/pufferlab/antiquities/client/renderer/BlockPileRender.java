package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.client.models.ModelPile;
import net.pufferlab.antiquities.tileentities.TileEntityPile;

public class BlockPileRender extends BlockFurnitureRender {

    ModelPile model = new ModelPile();

    public BlockPileRender(int blockComplexRenderID) {
        super(blockComplexRenderID);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        Tessellator tess = Tessellator.instance;
        TileEntityPile pile = (TileEntityPile) world.getTileEntity(x, y, z);
        if (pile == null) return false;
        int meta = world.getBlockMetadata(x, y, z);

        model.resetFacing();
        model.setFacing(3);

        for (int i = 0; i < pile.getSizeInventory(); i++) {
            ItemStack item = pile.getInventoryStack(i);
            if (item != null) {
                String metal = Utils.getMetalType(item);
                if (metal == null) {
                    metal = "iron";
                }
                model.renderIngot(i, metal);
            }
        }
        model.render(renderer, tess, block, meta, x, y, z);

        return true;
    }
}
