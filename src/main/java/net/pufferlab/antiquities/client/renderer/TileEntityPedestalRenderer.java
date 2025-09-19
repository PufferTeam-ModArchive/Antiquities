package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.antiquities.blocks.BlockPedestal;
import net.pufferlab.antiquities.tileentities.TileEntityPedestal;

import org.lwjgl.opengl.GL11;

public class TileEntityPedestalRenderer extends TileEntitySpecialRenderer {

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
        TileEntityPedestal pedestal = (TileEntityPedestal) tileEntity;
        Block block = world.getBlock(pedestal.xCoord, pedestal.yCoord, pedestal.zCoord);
        if (!(block instanceof BlockPedestal)) return;

        this.itemRenderer.setRenderManager(renderManager);

        GL11.glEnable(GL11.GL_LIGHTING);
        ItemStack slot1 = pedestal.getInventoryStack(0);
        double dx = x + 0.5;
        double dy = y + 0.85;
        double dz = z + 0.5;
        if (pedestal.facingMeta == 1) {
            dx = dx + 0.15;
        }
        if (pedestal.facingMeta == 2) {
            dz = dz - 0.15;
        }
        if (pedestal.facingMeta == 3) {
            dx = dx - 0.15;
        }
        if (pedestal.facingMeta == 4) {
            dz = dz + 0.15;
        }
        renderSlotItem(slot1, dx, dy, dz, pedestal.facingMeta);
    }

    public void renderSlotItem(ItemStack stack, double xAdjust, double yAdjust, double zAdjust, int facing) {
        GL11.glPushMatrix();
        if (stack != null) {
            this.slotEntity.setEntityItemStack(stack);
            this.slotEntity.hoverStart = 0.0F;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslated(xAdjust, yAdjust, zAdjust);
            if (stack.getItem() != null) {
                GL11.glScalef(2.0F, 2.0F, 2.0F);
            }
            if (facing == 1 || facing == 3) {
                float angle = 180 + 45;
                if (facing == 1) {
                    angle = 90 + 45;
                }
                GL11.glRotatef(angle, 0.0F, 0.0F, 1.0F);
            }
            if (facing == 2 || facing == 4) {
                float angle = 180 + 45;
                if (facing == 2) {
                    angle = 90 + 45;
                }
                GL11.glRotatef(angle, 1.0F, 0.0F, 0.0F);
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
