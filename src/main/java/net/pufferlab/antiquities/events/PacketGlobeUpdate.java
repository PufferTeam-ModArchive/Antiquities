package net.pufferlab.antiquities.events;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.antiquities.tileentities.TileEntityGlobe;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketGlobeUpdate implements IMessage, IMessageHandler<PacketGlobeUpdate, IMessage> {

    private int x, y, z;
    private float speed;

    public PacketGlobeUpdate() {}

    public PacketGlobeUpdate(TileEntityGlobe tile) {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
        this.speed = tile.speed;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        speed = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeFloat(speed);
    }

    @Override
    public IMessage onMessage(PacketGlobeUpdate message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().theWorld;
        if (world == null) return null;

        TileEntity tile = world.getTileEntity(message.x, message.y, message.z);
        if (tile instanceof TileEntityGlobe) {
            TileEntityGlobe globe = (TileEntityGlobe) tile;
            globe.speed = message.speed;
        }
        return null;
    }

}
