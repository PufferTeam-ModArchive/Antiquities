package net.pufferlab.antiquities.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.pufferlab.antiquities.Utils;

import cpw.mods.fml.common.Loader;

public class OreDictCompat {

    public void run() {
        addOreDict("witchery:ingredient:150", "ingotKoboldite");
        if (Loader.isModLoaded("Botania")) {
            addOreDict("Botania:manaResource:14", "ingotGaia");
        }
        if (Loader.isModLoaded("ChromatiCraft")) {
            addOreDict("ChromatiCraft:chromaticraft_item_crafting:11", "ingotChromaAlloy");
            addOreDict("ChromatiCraft:chromaticraft_item_crafting:16", "ingotFiraxite");
            addOreDict("ChromatiCraft:chromaticraft_item_crafting:17", "ingotResonating");
            addOreDict("ChromatiCraft:chromaticraft_item_crafting:18", "ingotFluidic");
            addOreDict("ChromatiCraft:chromaticraft_item_crafting:19", "ingotRadiative");
            addOreDict("ChromatiCraft:chromaticraft_item_crafting:20", "ingotAuraConduct");
            addOreDict("ChromatiCraft:chromaticraft_item_crafting:22", "ingotSpatial");
        }
        if (Loader.isModLoaded("ReactorCraft")) {
            addOreDict("ReactorCraft:reactorcraft_item_crafting:3", "ingotCdInAg");
            addOreDict("ReactorCraft:reactorcraft_item_crafting:10", "ingotFerromagnetic");
            addOreDict("ReactorCraft:reactorcraft_item_crafting:17", "ingotTungstenCarbide");
        }
        if (Loader.isModLoaded("RotaryCraft")) {
            addOreDict("RotaryCraft:rotarycraft_item_compacts:6", "ingotInductive");
            addOreDict("RotaryCraft:rotarycraft_item_compacts:9", "ingotSpringSteel");
            addOreDict("RotaryCraft:rotarycraft_item_compacts:11", "ingotAlloyAluminum");
            addOreDict("RotaryCraft:rotarycraft_item_compacts:12", "ingotAlloyTungsten");
        }
        if (Loader.isModLoaded("TConstruct")) {
            addOreDict("TConstruct:materials:2", "ingotBrickSeared");
            addOreDict("TConstruct:materials:37", "ingotBrickSearedNether");
        }
    }

    public void addOreDict(String name, String oreDict) {
        ItemStack item = Utils.getItem(name);
        if (item == null) return;
        OreDictionary.registerOre(oreDict, item);
    }
}
