package net.pufferlab.antiquities;

import net.pufferlab.antiquities.client.renderer.*;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    BlockChairRender chairRender = new BlockChairRender();
    BlockTableRender tableRender = new BlockTableRender();
    BlockShelfRender shelfRender = new BlockShelfRender();
    BlockGlobeRender globeRender = new BlockGlobeRender();
    BlockJarRender jarRender = new BlockJarRender();
    BlockRackRender rackRender = new BlockRackRender();
    BlockLabelRender labelRender = new BlockLabelRender();
    BlockClockRender clockRender = new BlockClockRender();
    BlockPedestalRender pedestalRender = new BlockPedestalRender();
    BlockPileRender pileRender = new BlockPileRender();
    BlockCouchRender couchRender = new BlockCouchRender();

    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        Antiquities.LOG.info(Config.greeting);
        Antiquities.LOG.info("Antiquities is at version " + Tags.VERSION);
    }

    public void registerRenders() {}

    public void registerMessages() {}

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}

    public void serverStarting(FMLServerStartingEvent event) {}

    public int getChairRenderID() {
        return 0;
    }

    public int getTableRenderID() {
        return 0;
    }

    public int getShelfRenderID() {
        return 0;
    }

    public int getGlobeRenderID() {
        return 0;
    }

    public int getJarRenderID() {
        return 0;
    }

    public int getLabelRenderID() {
        return 0;
    }

    public int getRackRenderID() {
        return 0;
    }

    public int getClockRenderID() {
        return 0;
    }

    public int getPedestalRenderID() {
        return 0;
    }

    public int getPileRenderID() {
        return 0;
    }

    public int getCouchRenderID() {
        return 0;
    }

    public BlockFurnitureRender getChairRenderBlock() {
        return chairRender;
    }

    public BlockFurnitureRender getTableRenderBlock() {
        return tableRender;
    }

    public BlockFurnitureRender getShelfRenderBlock() {
        return shelfRender;
    }

    public BlockFurnitureRender getGlobeRenderBlock() {
        return globeRender;
    }

    public BlockFurnitureRender getJarRenderBlock() {
        return jarRender;
    }

    public BlockFurnitureRender getLabelRenderBlock() {
        return labelRender;
    }

    public BlockFurnitureRender getRackRenderBlock() {
        return rackRender;
    }

    public BlockFurnitureRender getClockRenderBlock() {
        return clockRender;
    }

    public BlockFurnitureRender getPedestalRenderBlock() {
        return pedestalRender;
    }

    public BlockFurnitureRender getPileRenderBlock() {
        return pileRender;
    }

    public BlockFurnitureRender getCouchRenderBlock() {
        return couchRender;
    }

}
