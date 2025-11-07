package net.pufferlab.antiquities;

import net.pufferlab.antiquities.client.renderer.*;
import net.pufferlab.antiquities.events.PacketGlobeUpdate;
import net.pufferlab.antiquities.tileentities.*;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    int chairRenderID;
    int tableRenderID;
    int shelfRenderID;
    int globeRenderID;
    int jarRenderID;
    int labelRenderID;
    int rackRenderID;
    int clockRenderID;
    int pedestalRenderID;
    int pileRenderID;
    int couchRenderID;

    @Override
    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShelf.class, new TileEntityShelfRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJar.class, new TileEntityJarRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRack.class, new TileEntityRackRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new TileEntityPedestalRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlobe.class, new TileEntityGlobeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityClock.class, new TileEntityClockRenderer());

        chairRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(chairRender);
        tableRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(tableRender);
        shelfRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(shelfRender);
        globeRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(globeRender);
        jarRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(jarRender);
        rackRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(rackRender);
        labelRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(labelRender);
        clockRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(clockRender);
        pedestalRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(pedestalRender);
        pileRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(pileRender);
        couchRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(couchRender);
    }

    @Override
    public void registerMessages() {
        Antiquities.NETWORK.registerMessage(PacketGlobeUpdate.class, PacketGlobeUpdate.class, 0, Side.CLIENT);
    }

    @Override
    public int getChairRenderID() {
        return chairRenderID;
    }

    @Override
    public int getTableRenderID() {
        return tableRenderID;
    }

    @Override
    public int getShelfRenderID() {
        return shelfRenderID;
    }

    @Override
    public int getGlobeRenderID() {
        return globeRenderID;
    }

    @Override
    public int getJarRenderID() {
        return jarRenderID;
    }

    @Override
    public int getRackRenderID() {
        return rackRenderID;
    }

    @Override
    public int getLabelRenderID() {
        return labelRenderID;
    }

    @Override
    public int getClockRenderID() {
        return clockRenderID;
    }

    @Override
    public int getPedestalRenderID() {
        return pedestalRenderID;
    }

    @Override
    public int getPileRenderID() {
        return pileRenderID;
    }

    @Override
    public int getCouchRenderID() {
        return couchRenderID;
    }
}
