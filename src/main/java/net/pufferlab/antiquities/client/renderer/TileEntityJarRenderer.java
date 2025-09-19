package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.antiquities.blocks.BlockJar;
import net.pufferlab.antiquities.tileentities.TileEntityJar;

import org.lwjgl.opengl.GL11;

public class TileEntityJarRenderer extends TileEntitySpecialRenderer {

    public EntityItem slotEntity = new EntityItem(null, 0.0D, 0.0D, 0.0D);

    private RenderManager renderManager = RenderManager.instance;
    private RenderItem itemRenderer = new RenderItem() {

        public boolean shouldBob() {
            return false;
        }
    };

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        World world = tileEntity.getWorldObj();
        TileEntityJar jar = (TileEntityJar) tileEntity;
        Block block = world.getBlock(jar.xCoord, jar.yCoord, jar.zCoord);
        if (!(block instanceof BlockJar)) return;

        this.itemRenderer.setRenderManager(renderManager);

        GL11.glEnable(GL11.GL_LIGHTING);
        ItemStack slot1 = jar.getInventoryStack(0);
        double dx = x + 0.5;
        double dy = y + 0.1;
        double dz = z + 0.5;
        if (slot1 != null) {
            if (slot1.stackSize > 5) {
                if (jar.facingMeta == 1) {
                    dx = dx - 0.06;
                }
                if (jar.facingMeta == 2) {
                    dz = dz + 0.06;
                }
                if (jar.facingMeta == 3) {
                    dx = dx + 0.06;
                }
                if (jar.facingMeta == 4) {
                    dz = dz - 0.06;
                }
            }
        }
        renderSlotItem(slot1, dx, dy, dz, jar.facingMeta);
    }

    public void renderSlotItem(ItemStack stack, double xAdjust, double yAdjust, double zAdjust, int facing) {
        GL11.glPushMatrix();
        if (stack != null) {
            this.slotEntity.setEntityItemStack(stack);
            this.slotEntity.hoverStart = 0.0F;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslated(xAdjust, yAdjust, zAdjust);
            if (stack.getItem() != null) {
                GL11.glScalef(0.75F, 0.75F, 0.75F);
            }
            setFacing(facing);
            RenderItem.renderInFrame = true;
            this.itemRenderer.doRender(slotEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            RenderItem.renderInFrame = false;
        }
        GL11.glPopMatrix();
    }

    public void setFacing(int meta) {
        int meta2 = meta + 1;
        GL11.glRotatef(90 * meta2, 0, 1.0F, 0.0F);
    }

}
