package net.pufferlab.antiquities;

import net.pufferlab.antiquities.events.PacketGlobeUpdate;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        Antiquities.LOG.info(Config.greeting);
        Antiquities.LOG.info("Antiquities is at version " + Tags.VERSION);
    }

    public void registerRenders() {}

    public void registerMessages() {
        int id = 0;
        Antiquities.NETWORK.registerMessage(PacketGlobeUpdate.class, PacketGlobeUpdate.class, id++, Side.CLIENT);
    }

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
}
