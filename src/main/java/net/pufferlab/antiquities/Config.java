package net.pufferlab.antiquities;

import java.io.File;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class Config {

    public static final String CATEGORY_CONTENT = "content";

    public static String greeting = "Hello World";
    public static boolean renderWithAO;
    public static boolean enableRecipes;
    public static float globeMaxSpeed;
    public static float globeSpeedAcceleration;
    public static float globeSpeedDeceleration;
    public static String[] toolsWhitelist;
    public static String[] toolsDefaultWhitelist;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        renderWithAO = configuration.getBoolean(
            "renderWithAmbientOcclusion",
            Configuration.CATEGORY_GENERAL,
            true,
            "Whether to render blocks with Ambient Occlusion.");
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
