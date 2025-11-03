package net.pufferlab.antiquities;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class Config {

    public static final String CATEGORY_CONTENT = "content";
    public static final String CATEGORY_RENDERING = "rendering";
    public static final String CATEGORY_TECHNICAL = "technical";
    public static final String CATEGORY_COMPAT = "compat";

    public static String greeting = "Hello World";
    public static boolean renderWithAO;
    public static boolean renderBreakingTexture;
    public static boolean legacyTextures;
    public static boolean enableRecipes;
    public static boolean enableOreDictCompat;
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
    public static boolean enableCouch;

    public static boolean enableIngotPileModification;
    public static String[] ingotPileMetals;

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
        legacyTextures = configuration.getBoolean(
            "legacyTextures",
            CATEGORY_RENDERING,
            false,
            "Whether to render blocks with simple planks texture or to use customized textures.");

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
        enableCouch = configuration
            .getBoolean("enableCouch", CATEGORY_CONTENT, true, "Whether to enable the couch block.");

        enableOreDictCompat = configuration.getBoolean(
            "enableOreDictCompat",
            CATEGORY_COMPAT,
            true,
            "Whether to assign new oredictionary to some mod materials that are by default missing of any.");

        enableIngotPileModification = configuration.getBoolean(
            "enableIngotPileModification",
            CATEGORY_TECHNICAL,
            false,
            "Whether you want to enable modification of ingot pile metal types. By default it's off so metals are always synchronized to the default values.");

        if (enableIngotPileModification) {
            ingotPileMetals = configuration.getStringList(
                "ingotPileMetals",
                CATEGORY_TECHNICAL,
                Constants.metalTypes,
                "The list of all the metal types that the ingot pile will support. Every metal has a texture associated that will be rendered in the ingot pile situated at (antiquities:textures/blocks/[metal name]_ingot.png). Example: Iron has iron_ingot.png as texture. You need to add your own textures with this naming scheme or else its going to show up as missing texture.");
        }
        if (ingotPileMetals == null) {
            ingotPileMetals = Constants.metalTypes;
        }

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
