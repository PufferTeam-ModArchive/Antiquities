package net.pufferlab.antiquities.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.client.renderer.BlockFurnitureRender;
import net.pufferlab.antiquities.entity.EntitySeat;
import net.pufferlab.antiquities.tileentities.TileEntityChair;

public class BlockChair extends BlockMetaContainer {

    public BlockChair(String... materials) {
        super(Material.wood, materials, "chair", Constants.none);
        this.setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 1F, 0.9F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        if (world.isRemote) return true;

        if (player.isSneaking()) return false;
        if (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN)) return false;

        List<EntitySeat> seats = world
            .getEntitiesWithinAABB(EntitySeat.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
        if (!seats.isEmpty()) return true;

        EntitySeat seat = new EntitySeat(world, x + 0.5, y + 0.0, z + 0.5);
        world.spawnEntityInWorld(seat);

        player.mountEntity(seat);

        return true;

    }

    @Override
    public void onBlockPreDestroy(World worldIn, int x, int y, int z, int meta) {
        super.onBlockPreDestroy(worldIn, x, y, z, meta);

        List<EntityPlayer> players = worldIn
            .getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));

        if (!players.isEmpty()) {
            for (EntityPlayer player : players) {
                Entity entity = player.ridingEntity;
                if (entity instanceof EntitySeat) {
                    player.setSneaking(true);
                }
            }
        }
    }

    public boolean canRegister() {
        return Config.enableChair;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityChair();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public BlockFurnitureRender getRenderBlock() {
        return Antiquities.proxy.getChairRenderBlock();
    }
}
