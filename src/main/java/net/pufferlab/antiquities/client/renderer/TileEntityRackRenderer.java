package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.antiquities.blocks.BlockRack;
import net.pufferlab.antiquities.tileentities.TileEntityRack;

import org.lwjgl.opengl.GL11;

public class TileEntityRackRenderer extends TileEntitySpecialRenderer {

    public EntityItem slotEntity = new EntityItem(null, 0.0D, 0.0D, 0.0D);

    private RenderManager renderManager = RenderManager.instance;
    private RenderItem itemRenderer = new RenderItem() {

        public byte getMiniBlockCount(ItemStack stack, byte original) {
            return 1;
        }

        public boolean shouldBob() {
            return false;
        }

        public boolean shouldSpreadItems() {
            return false;
        }
    };

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        World world = tileEntity.getWorldObj();
        TileEntityRack rack = (TileEntityRack) tileEntity;
        Block block = world.getBlock(rack.xCoord, rack.yCoord, rack.zCoord);
        if (!(block instanceof BlockRack)) return;

        this.itemRenderer.setRenderManager(renderManager);

        GL11.glEnable(GL11.GL_LIGHTING);
        ItemStack slot1 = rack.getInventoryStack(0);
        ItemStack slot2 = rack.getInventoryStack(1);
        ItemStack slot3 = rack.getInventoryStack(2);

        double x2 = x + 0.25F;
        double y2 = y + 0.25F;
        double z2 = z + 0.75F;
        double offsetX = 0.33F;
        double offsetY = 0.33F;
        double offsetZ = 0.0F;
        double offsetX2 = -0.33F;
        double offsetZ2 = 0.1F;
        double offsetX3 = -0.05F;
        double offsetZ3 = 0.0F;
        if (rack.facingMeta == 2) {
            x2 = x + 0.75F;
            offsetX = 0.0F;
            offsetZ = -0.33F;
            offsetX2 = 0.1F;
            offsetZ2 = 0.33F;
            offsetX3 = 0.0F;
            offsetZ3 = 0.05F;
        }
        if (rack.facingMeta == 3) {
            x2 = x + 0.75F;
            z2 = z + 0.25F;
            offsetX = -0.33F;
            offsetX2 = 0.33F;
            offsetZ2 = -0.1F;
            offsetX3 = 0.05F;
            offsetZ3 = 0.0F;
        }
        if (rack.facingMeta == 4) {
            x2 = x + 0.25F;
            z2 = z + 0.25F;
            offsetX = 0.0F;
            offsetZ = 0.33F;
            offsetX2 = -0.1F;
            offsetZ2 = -0.33F;
            offsetX3 = 0.0F;
            offsetZ3 = -0.05F;
        }
        renderSlotItem(
            slot3,
            x2 + offsetX + offsetX2 - offsetX3 - 0.001,
            y2 + offsetY,
            z2 + offsetZ + offsetZ2 - offsetZ3 - 0.001,
            rack.facingMeta);
        renderSlotItem(
            slot2,
            x2 + (offsetX * 2) + offsetX2,
            y2 + offsetY,
            z2 + (offsetZ * 2) + offsetZ2,
            rack.facingMeta);
        renderSlotItem(
            slot1,
            x2 + (offsetX * 3) + offsetX2 + offsetX3 + 0.001,
            y2 + offsetY,
            z2 + (offsetZ * 3) + offsetZ2 + offsetZ3 + 0.001,
            rack.facingMeta);
    }

    public void renderSlotItem(ItemStack stack, double xAdjust, double yAdjust, double zAdjust, int facing) {
        GL11.glPushMatrix();
        if (stack != null) {
            this.slotEntity.setEntityItemStack(stack);
            this.slotEntity.hoverStart = 0.0F;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslated(xAdjust, yAdjust, zAdjust);
            if (stack.getItem() != null) {
                GL11.glScalef(1.2F, 1.2F, 1.2F);
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
