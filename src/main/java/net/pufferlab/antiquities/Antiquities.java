package net.pufferlab.antiquities;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.pufferlab.antiquities.client.compat.NEIConfig;
import net.pufferlab.antiquities.events.EventHandler;
import net.pufferlab.antiquities.recipes.Recipes;
import net.pufferlab.antiquities.recipes.RecipesBOP;
import net.pufferlab.antiquities.recipes.RecipesTC;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
    modid = Antiquities.MODID,
    version = Tags.VERSION,
    name = Antiquities.MODNAME,
    acceptedMinecraftVersions = "[1.7.10]")
public class Antiquities {

    public static final String MODID = "antiquities";
    public static final String MODNAME = "Antiquities";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "net.pufferlab.antiquities.ClientProxy",
        serverSide = "net.pufferlab.antiquities.CommonProxy")
    public static CommonProxy proxy;
    public static EventHandler eventHandler = new EventHandler();

    public static Registry registry = new Registry();

    @Mod.Instance(Antiquities.MODID)
    public static Antiquities instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        registry.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

        registry.init();
        if (Loader.isModLoaded("NotEnoughItems")) {
            new NEIConfig().loadConfig();
        }
        MinecraftForge.EVENT_BUS.register(eventHandler);
        proxy.registerRenders();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);

        if (Config.enableRecipes) {
            new Recipes().run();
            if (Loader.isModLoaded("BiomesOPlenty")) {
                new RecipesBOP().run();
            }
            if (Loader.isModLoaded("Thaumcraft")) {
                new RecipesTC().run();
            }
            Config.refreshWhitelists();
        }

    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
