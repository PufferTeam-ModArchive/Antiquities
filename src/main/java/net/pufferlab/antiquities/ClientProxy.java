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
        RenderingRegistry.registerBlockHandler(new BlockChairRender());
        tableRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockTableRender());
        shelfRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockShelfRender());
        globeRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockGlobeRender());
        jarRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockJarRender());
        rackRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockRackRender());
        labelRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockLabelRender());
        clockRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockClockRender());
        pedestalRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockPedestalRender());
        pileRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockPileRender());
        couchRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockCouchRender());
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
