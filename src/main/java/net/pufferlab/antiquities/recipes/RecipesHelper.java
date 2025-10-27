package net.pufferlab.antiquities.recipes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.Registry;
import net.pufferlab.antiquities.Utils;

import cpw.mods.fml.common.registry.GameRegistry;

public class RecipesHelper {

    public Block[] woodBlocks;
    public String[] woodTypes;
    public String[] woodTypes2;
    public static final ItemStack glass = new ItemStack(Blocks.glass, 1, 0);
    public static final ItemStack stone_slab = new ItemStack(Blocks.stone_slab, 1, 0);
    public static final ItemStack clock = new ItemStack(Items.clock, 1, 0);

    public RecipesHelper(Block[] woodBlocksE, String[] woodTypesE, String[] woodTypes2E) {
        woodBlocks = woodBlocksE;
        woodTypes = woodTypesE;
        woodTypes2 = woodTypes2E;
    }

    public static final String[] woodNames = new String[] { "log1", "log2", "log3", "log4", "planks", "slab1", "slab2",
        "chair", "table", "shelf_0", "shelf_1", "shelf_2", "shelf_3", "shelf_4", "shelf_5", "jar", "rack" };

    public static void addShapedRecipe(ItemStack output, Object... recipe) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, recipe));
    }

    public static void addShapelessRecipe(ItemStack output, Object... recipe) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, recipe));
    }

    public static String getSpecialMetaString(String[] blocks, String block, String type, int metaCap) {
        int log = Utils.getItemFromArray(blocks, block);
        int logType = (int) Math.floor((double) (log) / (metaCap));

        return type + (logType + 1);
    }

    public static int getSpecialMeta(String[] blocks, String block, int metaCap) {
        int log = Utils.getItemFromArray(blocks, block);

        return log & (metaCap - 1);
    }

    public void addRecipes(String wood) {
        ItemStack slab = getModItem("slab", wood, 1);
        ItemStack planks = getModItem("planks", wood, 1);

        addShapedRecipe(getModItem("chair", wood, 1), "S  ", "SSS", "P P", 'S', slab, 'P', planks);
        addShapedRecipe(getModItem("table", wood, 1), "SSS", "P P", "P P", 'S', slab, 'P', planks);
        addShapedRecipe(getModItem("shelf_0", wood, 1), "PPP", "SSS", "PPP", 'S', slab, 'P', planks);
        addShapedRecipe(getModItem("shelf_1", wood, 1), "PSP", "SSS", "PSP", 'S', slab, 'P', planks);
        addShapedRecipe(getModItem("shelf_2", wood, 1), "P P", "SSS", "P P", 'S', slab, 'P', planks);
        addShapedRecipe(getModItem("shelf_3", wood, 1), "SSS", "PSP", "SSS", 'S', slab, 'P', planks);
        addShapedRecipe(getModItem("shelf_4", wood, 1), "SPS", "SSS", "SPS", 'S', slab, 'P', planks);
        addShapedRecipe(getModItem("shelf_5", wood, 1), "SSS", "SPS", "SSS", 'S', slab, 'P', planks);
        addShapedRecipe(getModItem("jar", wood, 1), "S", "G", 'S', slab, 'G', glass);
        addShapedRecipe(getModItem("rack", wood, 1), "SIS", "PPP", "S S", 'S', slab, 'P', planks, 'I', "ingotIron");

    }

    public void addSpecialRecipes() {
        addShapedRecipe(new ItemStack(Registry.pedestal, 1, 0), " S ", "SSS", 'S', stone_slab);
        addShapedRecipe(new ItemStack(Registry.clock, 1, 0), " W ", "WCW", " W ", 'W', "plankWood", 'C', clock);

        for (int i = 0; i < Constants.colorTypes.length; i++) {
            addShapedRecipe(
                new ItemStack(Registry.couch, 1, i),
                "S  ",
                "SSS",
                "LLL",
                'S',
                new ItemStack(Blocks.wool, 1, i),
                'L',
                "plankWood");
        }
    }

    public ItemStack getModItem(String name, String wood, int number) {
        boolean isVanillaWood = Utils.containsExactMatch(Constants.woodTypes, wood);
        boolean isTCWood = Utils.containsExactMatch(Constants.thaumcraftWoodTypes, wood);
        boolean isBopWood = Utils.containsExactMatch(Constants.bopWoodTypes, wood);

        if (name.equals("planks")) {
            return new ItemStack(
                Utils.getItemFromArray(woodNames, woodBlocks, name),
                number,
                Utils.getItemFromArray(woodTypes2, wood));
        } else if (name.equals("slab")) {
            return new ItemStack(
                Utils.getItemFromArray(woodNames, woodBlocks, getSpecialMetaString(woodTypes, wood, "slab", 8)),
                number,
                getSpecialMeta(woodTypes, wood, 8));
        } else if (name.equals("stairs")) {
            if (isBopWood) {
                if (wood.equals("hellbark")) {
                    wood = "hellBark";
                }
                return Utils.getItem("BiomesOPlenty", wood + "Stairs", 0, number);
            } else if (isVanillaWood) {
                return Utils.getItem("minecraft", wood + "_stairs", 0, number);
            } else if (isTCWood) {
                return Utils.getItem("Thaumcraft", "blockStairs" + Utils.getCapitalized(wood), 0, number);
            }
        } else {
            return new ItemStack(
                Utils.getItemFromArray(woodNames, woodBlocks, name),
                number,
                Utils.getItemFromArray(woodTypes, wood));
        }
        return null;

    }
}
