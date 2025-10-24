package net.pufferlab.antiquities.events;

import static net.minecraftforge.event.entity.player.PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.pufferlab.antiquities.Registry;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.blocks.BlockPile;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PileHandler {

    @SubscribeEvent
    public void playerInteractEventHandler(PlayerInteractEvent event) {
        if (event.action == RIGHT_CLICK_BLOCK && event.useItem != Event.Result.DENY) {
            Block block = event.world.getBlock(event.x, event.y, event.z);
            int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
            ItemStack heldItem = event.entityPlayer.inventory.getCurrentItem();
            if (heldItem == null || block == null) return;
            if (heldItem.getItem() == null) return;
            if (!Utils.isValidMetal(heldItem)) return;
            if (!(block instanceof BlockPile)) {
                if (!block.hasTileEntity(meta) && event.face == ForgeDirection.UP.ordinal()) {
                    int x2 = Utils.getBlockX(event.face, event.x);
                    int y2 = Utils.getBlockY(event.face, event.y);
                    int z2 = Utils.getBlockZ(event.face, event.z);
                    place(heldItem, event.world, x2, y2, z2, Registry.pile, 0, event);
                }
            } else {
                if (event.entityPlayer.isSneaking()) {
                    block.onBlockActivated(
                        event.world,
                        event.x,
                        event.y,
                        event.z,
                        event.entityPlayer,
                        event.face,
                        0.5F,
                        0.5F,
                        0.5F);
                }
            }

        }
    }

    private void place(ItemStack stack, World world, int x, int y, int z, Block toPlace, int metadata,
        PlayerInteractEvent event) {
        if (world.isAirBlock(x, y, z) && world.isSideSolid(x, y - 1, z, ForgeDirection.UP)) {
            if (world.checkNoEntityCollision(toPlace.getCollisionBoundingBoxFromPool(world, x, y, z))
                && world.setBlock(x, y, z, toPlace, metadata, 3)) {
                world.setBlock(x, y, z, toPlace, metadata, 2);
                toPlace.onBlockPlacedBy(world, x, y, z, event.entityPlayer, stack);
                stack.stackSize -= 1;
                world.playSoundEffect(
                    x + 0.5f,
                    y + 0.5f,
                    z + 0.5f,
                    toPlace.stepSound.func_150496_b(),
                    (toPlace.stepSound.getVolume() + 1.0F) / 2.0F,
                    toPlace.stepSound.getPitch() * 0.8F);
                event.entityPlayer.swingItem();
            }
        }
    }
}
