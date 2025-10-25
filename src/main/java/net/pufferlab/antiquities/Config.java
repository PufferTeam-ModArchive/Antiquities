package net.pufferlab.antiquities;

import java.io.File;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class Config {

    public static final String CATEGORY_CONTENT = "content";
    public static final String CATEGORY_RENDERING = "rendering";

    public static String greeting = "Hello World";
    public static boolean renderWithAO;
    public static boolean renderBreakingTexture;
    public static boolean enableRecipes;
    public static float globeMaxSpeed;
    public static float globeSpeedAcceleration;
    public static float globeSpeedDeceleration;
    public static String[] toolsWhitelist;
    public static String[] toolsDefaultWhitelist;
    public static boolean onlyAllowSupportedIngots;

    public static boolean enableChair;
    public static boolean enableTable;
    public static boolean enableShelf;
    public static boolean enableToolRack;
    public static boolean enablePedestal;
    public static boolean enableJar;
    public static boolean enableLabel;
    public static boolean enableClock;
    public static boolean enableGlobe;
    public static boolean enableIngotPile;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        renderWithAO = configuration.getBoolean(
            "renderWithAmbientOcclusion",
            CATEGORY_RENDERING,
            true,
            "Whether to render blocks with Ambient Occlusion.");
        renderBreakingTexture = configuration.getBoolean(
            "renderBreakingTexture",
            CATEGORY_RENDERING,
            true,
            "Whether to render blocks with breaking textures.");

        enableRecipes = configuration
            .getBoolean("enableRecipes", Configuration.CATEGORY_GENERAL, true, "Whether to enable recipes in the mod.");
        globeMaxSpeed = configuration.getFloat(
            "globeMaxSpeed",
            Configuration.CATEGORY_GENERAL,
            35F,
            0F,
            Float.MAX_VALUE,
            "The maximum speed that the globe can spin.");
        globeSpeedAcceleration = configuration.getFloat(
            "globeSpeedAcceleration",
            Configuration.CATEGORY_GENERAL,
            4F,
            0F,
            10F,
            "The speed added to the globe every time a player spins it.");
        globeSpeedDeceleration = configuration.getFloat(
            "globeSpeedDeceleration",
            Configuration.CATEGORY_GENERAL,
            0.15F,
            0F,
            10F,
            "The speed that the globe decelerate every tick.");
        toolsDefaultWhitelist = new String[] { "minecraft:stick", "minecraft:arrow" };
        toolsWhitelist = configuration.getStringList(
            "toolsWhitelist",
            Configuration.CATEGORY_GENERAL,
            toolsDefaultWhitelist,
            "List of items that should be whitelisted for tools-exclusive blocks (Like the Rack and Pedestal).");
        onlyAllowSupportedIngots = configuration.getBoolean(
            "onlyAllowSupportedIngots",
            Configuration.CATEGORY_GENERAL,
            false,
            "Whether if you need to have a ingot with texture support to place it in a pile.");

        enableChair = configuration
            .getBoolean("enableChair", CATEGORY_CONTENT, true, "Whether to enable the chair block.");
        enableTable = configuration
            .getBoolean("enableTable", CATEGORY_CONTENT, true, "Whether to enable the table block.");
        enableShelf = configuration
            .getBoolean("enableShelf", CATEGORY_CONTENT, true, "Whether to enable the shelf block.");
        enableToolRack = configuration
            .getBoolean("enableToolRack", CATEGORY_CONTENT, true, "Whether to enable the tool rack block.");
        enablePedestal = configuration
            .getBoolean("enablePedestal", CATEGORY_CONTENT, true, "Whether to enable the sword pedestal block.");
        enableJar = configuration.getBoolean("enableJar", CATEGORY_CONTENT, true, "Whether to enable the jar block.");
        enableClock = configuration
            .getBoolean("enableClock", CATEGORY_CONTENT, true, "Whether to enable the clock block.");
        enableGlobe = configuration
            .getBoolean("enableGlobe", CATEGORY_CONTENT, true, "Whether to enable the globe block.");
        enableIngotPile = configuration
            .getBoolean("enableIngotPile", CATEGORY_CONTENT, true, "Whether to enable the ingot pile block.");
        enableLabel = configuration
            .getBoolean("enableLabel", CATEGORY_CONTENT, false, "Whether to enable the label block.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    public static void refreshWhitelists() {
        for (String item : toolsWhitelist) {
            ItemStack itemstack = Utils.getItem(item + ":*:*");
            if (itemstack != null) {
                OreDictionary.registerOre("tool", itemstack);
            }
        }
    }
}
