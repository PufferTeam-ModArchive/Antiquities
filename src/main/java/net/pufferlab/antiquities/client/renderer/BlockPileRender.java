package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.OreDictionary;
import net.pufferlab.antiquities.client.models.ModelPile;
import net.pufferlab.antiquities.tileentities.TileEntityPile;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockPileRender implements ISimpleBlockRenderingHandler {

    ModelPile model = new ModelPile();
    final int renderID;

    public BlockPileRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
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
                int[] oreIDS = OreDictionary.getOreIDs(item);
                String metal = "iron";
                boolean hasMetal = false;
                for (int oreID : oreIDS) {
                    String name = OreDictionary.getOreName(oreID);
                    String[] names = name.split("ingot");
                    if (names.length > 0) {
                        metal = names[1].toLowerCase();
                        hasMetal = true;
                    }
                }
                if (hasMetal) {
                    model.renderIngot(i, metal);
                }
            }
        }
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
