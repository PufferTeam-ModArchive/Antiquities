package net.pufferlab.antiquities;

import net.pufferlab.antiquities.client.renderer.*;
import net.pufferlab.antiquities.tileentities.*;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    int chairRenderID;
    int tableRenderID;
    int shelfRenderID;
    int globeRenderID;
    int jarRenderID;
    int rackRenderID;
    int clockRenderID;
    int pedestalRenderID;
    int pileRenderID;

    @Override
    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShelf.class, new TileEntityShelfRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJar.class, new TileEntityJarRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRack.class, new TileEntityRackRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new TileEntityPedestalRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlobe.class, new TileEntityGlobeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityClock.class, new TileEntityClockRenderer());

        chairRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockChairRender(chairRenderID));
        tableRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockTableRender(tableRenderID));
        shelfRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockShelfRender(shelfRenderID));
        globeRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockGlobeRender(globeRenderID));
        jarRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockJarRender(jarRenderID));
        rackRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockRackRender(rackRenderID));
        clockRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockClockRender(clockRenderID));
        pedestalRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockPedestalRender(pedestalRenderID));
        pileRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockPileRender(pileRenderID));
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
}
