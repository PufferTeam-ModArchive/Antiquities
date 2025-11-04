package net.pufferlab.antiquities.client.helper;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.antiquities.tileentities.TileEntityMetaFacing;

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
        return aBlock.getClass()
            .isInstance(block);
    }

    public static boolean isConnected(IBlockAccess world, int x, int y, int z, boolean checkDirection,
        ForgeDirection... direction) {
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
        if (aBlock.getClass()
            .isInstance(block)) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (checkDirection) {
                if (te instanceof TileEntityMetaFacing teMeta) {
                    TileEntity aTe = world.getTileEntity(x + offsetX, y + offsetY, z + offsetZ);
                    if (aTe instanceof TileEntityMetaFacing aTeMeta) {
                        ForgeDirection teDir = getSelf(teMeta.facingMeta);
                        ForgeDirection aTeDir = getSelf(aTeMeta.facingMeta);
                        if (teDir.getOpposite() == aTeDir || teDir == aTeDir) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }
        return false;
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
