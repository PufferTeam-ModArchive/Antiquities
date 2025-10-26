package net.pufferlab.antiquities.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Config;
import net.pufferlab.antiquities.events.PacketGlobeUpdate;

import cpw.mods.fml.common.network.NetworkRegistry;

public class TileEntityGlobe extends TileEntityMetaFacing {

    public float rotation;
    public float speed;

    public boolean canRegister() {
        return Config.enableGlobe;
    }

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

    public void sendUpdate() {
        if (!worldObj.isRemote) {
            Antiquities.NETWORK.sendToAllAround(
                new PacketGlobeUpdate(this),
                new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64.0D));
        }
    }

    public void addSpeed() {
        if (!worldObj.isRemote) {
            if (this.speed < Config.globeMaxSpeed) {
                this.speed = this.speed + Config.globeSpeedAcceleration;
                sendUpdate();
            }
        }
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (this.speed > 0) {
                this.speed = this.speed - Config.globeSpeedDeceleration;
                sendUpdate();
            } else {
                this.speed = 0;
            }
        }
        if (worldObj.isRemote) {
            this.rotation = (this.rotation + this.speed) % 360.0F;
        }
    }
}
