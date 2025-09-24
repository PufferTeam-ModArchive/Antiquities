package net.pufferlab.antiquities.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.pufferlab.antiquities.Config;

public class TileEntityGlobe extends TileEntityMetaFacing {

    public float rotation;
    public float speed;

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        this.rotation = tag.getFloat("rotation");
        this.speed = tag.getFloat("speed");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setFloat("rotation", this.rotation);
        tag.setFloat("speed", this.speed);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound dataTag = new NBTTagCompound();

        dataTag.setInteger("facingMeta", this.facingMeta);
        dataTag.setFloat("rotation", this.rotation);
        dataTag.setFloat("speed", this.speed);

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
        this.rotation = nbtData.getFloat("rotation");
        this.speed = nbtData.getFloat("speed");
    }

    public void addSpeed() {
        if (this.speed < Config.globeMaxSpeed) {
            this.speed = this.speed + Config.globeSpeedAcceleration;
        }
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) {
            if (this.speed > 0) {
                this.speed = this.speed - Config.globeSpeedDeceleration;
            } else {
                this.speed = 0;
            }
            this.rotation = (this.rotation + this.speed) % 360.0F;
        }
    }
}
