package net.pufferlab.antiquities;

import java.io.File;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class Config {

    public static String greeting = "Hello World";
    public static boolean renderWithAO;
    public static boolean enableRecipes;
    public static float globeMaxSpeed;
    public static float globeSpeedAddition;
    public static float globeSpeedDeceleration;
    public static String[] toolRackWhitelist;
    public static String[] toolRackDefaultWhitelist;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        renderWithAO = configuration.getBoolean(
            "renderWithAO",
            Configuration.CATEGORY_GENERAL,
            true,
            "Render most blocks with AO, if you put this to false, it will render the blocks in a different way that will not have AO but may be less buggy and consistent.");
        enableRecipes = configuration.getBoolean(
            "enableRecipes",
            Configuration.CATEGORY_GENERAL,
            true,
            "Enable recipes for blocks in the mod, put the false if you want the mod to have no recipes.");
        globeMaxSpeed = configuration.getFloat(
            "globeMaxSpeed",
            Configuration.CATEGORY_GENERAL,
            35F,
            0F,
            Float.MAX_VALUE,
            "The maximum speed that the globe can spin.");
        globeSpeedAddition = configuration.getFloat(
            "globeSpeedAddition",
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
        toolRackDefaultWhitelist = new String[] { "minecraft:stick", "minecraft:arrow" };
        toolRackWhitelist = configuration.getStringList(
            "toolRackWhitelist",
            Configuration.CATEGORY_GENERAL,
            toolRackDefaultWhitelist,
            "Add items that the toolrack should treat as tools");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    public static void refreshWhitelists() {
        for (String item : toolRackWhitelist) {
            ItemStack itemstack = Utils.getItem(item + ":*:*");
            if (itemstack != null) {
                OreDictionary.registerOre("tool", itemstack);
            }
        }
    }
}
