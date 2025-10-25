package net.pufferlab.antiquities.recipes;

import net.minecraft.block.Block;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.Registry;

import cpw.mods.fml.common.registry.GameRegistry;

public class RecipesWT extends RecipesHelper {

    public static Block[] woodBlocks = new Block[] { GameRegistry.findBlock("witchery", "witchlog"), null, null, null,
        GameRegistry.findBlock("witchery", "witchwood"), GameRegistry.findBlock("witchery", "witchwoodslab"), null,
        Registry.chair_wt, Registry.table_wt, Registry.shelf_0_wt, Registry.shelf_1_wt, Registry.shelf_2_wt,
        Registry.shelf_3_wt, Registry.shelf_4_wt, Registry.shelf_5_wt, Registry.jar_wt, Registry.rack_wt };
    public static String[] woodTypes = Constants.witcheryWoodTypes;

    public RecipesWT() {
        super(woodBlocks, woodTypes, woodTypes);
    }

    public void run() {
        for (String wood : Constants.witcheryWoodTypes) {
            addRecipes(wood);
        }
    }
}
