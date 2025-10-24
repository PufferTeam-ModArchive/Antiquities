package net.pufferlab.antiquities.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityMetaFacing extends TileEntityAntiquities {

    public int facingMeta = 2;

    public boolean canRegister() {
        return false;
    }

    public int getFacingType() {
        return 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        this.facingMeta = tag.getInteger("facingMeta");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("facingMeta", this.facingMeta);
    }

    public void setFacingMeta(int meta) {
        this.facingMeta = meta;
        this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, this.blockType);
        this.markDirty();
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound dataTag = new NBTTagCompound();

        dataTag.setInteger("facingMeta", this.facingMeta);

        return (Packet) new S35PacketUpdateTileEntity(
            this.xCoord,
            this.yCoord,
            this.zCoord,
            this.blockMetadata,
            dataTag);
    }

    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound nbtData = packet.func_148857_g();
        this.facingMeta = nbtData.getInteger("facingMeta");
    }
}
