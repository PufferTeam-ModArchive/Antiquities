package net.pufferlab.antiquities.client.helper;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class DirectionHelper {

    public static boolean isConnected(IBlockAccess world, int x, int y, int z, ForgeDirection... direction) {
        Block block = world.getBlock(x, y, z);
        int offsetX = 0;
        int offsetY = 0;
        int offsetZ = 0;
        for (ForgeDirection dir : direction) {
            offsetX = offsetX + dir.offsetX;
            offsetY = offsetY + dir.offsetY;
            offsetZ = offsetZ + dir.offsetZ;
        }
        Block aBlock = world.getBlock(x + offsetX, y + offsetY, z + offsetZ);
        return block.getClass()
            .isInstance(aBlock);
    }

    public static ForgeDirection getSelf(int metaFacing) {
        return switch (metaFacing) {
            case 1 -> ForgeDirection.NORTH;
            case 2 -> ForgeDirection.WEST;
            case 3 -> ForgeDirection.SOUTH;
            case 4 -> ForgeDirection.EAST;
            default -> null;
        };
    }
}
