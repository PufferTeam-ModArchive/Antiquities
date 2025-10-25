package net.pufferlab.antiquities;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;

public class Utils {

    public static String getCapitalized(String string) {
        String[] stringArray = string.split("");
        stringArray[0] = stringArray[0].toUpperCase();
        string = String.join("", stringArray);

        return string;
    }

    public static ItemStack getItem(String mod, String item, int meta, int number) {
        if (GameRegistry.findItem(mod, item) != null) {
            return new ItemStack(GameRegistry.findItem(mod, item), number, meta);
        } else if (GameRegistry.findBlock(mod, item) != null) {
            return new ItemStack(GameRegistry.findBlock(mod, item), number, meta);
        }
        return null;
    }

    public static ItemStack getItem(String s) {
        String[] array = s.split(":");
        String mod = array[0];
        String item = array[1];
        int meta = 0;
        if (array.length > 2) {
            if (array[2].equals("*")) {
                meta = OreDictionary.WILDCARD_VALUE;
            } else {
                meta = Integer.parseInt(array[2]);
            }
        }
        int number = 1;
        if (array.length > 3) {
            if (array[3].equals("*")) {
                number = OreDictionary.WILDCARD_VALUE;
            } else {
                number = Integer.parseInt(array[3]);
            }
        }

        return getItem(mod, item, meta, number);
    }

    public static int getDirectionXZ(int side) {
        if (side == 2) {
            return 1;
        } else if (side == 3) {
            return 3;
        } else if (side == 4) {
            return 2;
        } else if (side == 5) {
            return 4;
        }
        return 0;
    }

    public static int getDirectionXZYaw(int yaw) {
        if (yaw == 0) {
            return 1;
        } else if (yaw == 1) {
            return 4;
        } else if (yaw == 2) {
            return 3;
        } else if (yaw == 3) {
            return 2;
        }

        return 0;
    }

    public static int getBlockX(int side, int x) {
        if (side == 4) {
            x--;
        }
        if (side == 5) {
            x++;
        }
        return x;
    }

    public static int getBlockY(int side, int y) {
        if (side == 0) {
            y--;
        }
        if (side == 1) {
            y++;
        }
        return y;
    }

    public static int getBlockZ(int side, int z) {
        if (side == 2) {
            z--;
        }
        if (side == 3) {
            z++;
        }
        return z;
    }

    public static boolean containsExactMatch(String[] array, String targetString) {
        for (String element : array) {
            if (element.equals(targetString)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsStack(ItemStack wild, ItemStack check) {
        if (wild == null || check == null) {
            return check == wild;
        }

        if (wild.getItem() == check.getItem() && (wild.getItemDamage() == OreDictionary.WILDCARD_VALUE
            || check.getItemDamage() == OreDictionary.WILDCARD_VALUE
            || wild.getItemDamage() == check.getItemDamage())) {
            return true;
        }
        return false;
    }

    public static String getItemFromArray(String[] array, String[] blockArray, String targetString) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(targetString)) {
                return blockArray[i];
            }
        }
        return null;
    }

    public static Block getItemFromArray(String[] array, Block[] blockArray, String targetString) {
        for (int i = 0; i < array.length; i++) {
            if (targetString.equals(array[i])) {
                return blockArray[i];
            }
        }
        return null;
    }

    public static int getItemFromArray(String[] woodType, String wood) {
        for (int i = 0; i < woodType.length; i++) {
            if (woodType[i].equals(wood)) {
                return i;
            }
        }
        return 0;
    }

    public static boolean containsOreDict(ItemStack b, String oreDict) {
        for (int id1 : OreDictionary.getOreIDs(b)) {
            if (id1 == OreDictionary.getOreID(oreDict)) {
                return true;
            }
        }
        return false;
    }

    public static Map<String, String> metalTypes = new HashMap<>();

    public static String getMetalType(ItemStack item) {
        if (item == null) return null;
        Item itemObj = item.getItem();
        int itemMeta = item.getItemDamage();
        String key = getItemKey(itemObj, itemMeta);
        if (metalTypes.containsKey(key)) {
            return metalTypes.get(key);
        }
        int[] oreIDS = OreDictionary.getOreIDs(item);
        for (int oreID : oreIDS) {
            String name = OreDictionary.getOreName(oreID);
            if (name.contains("ingot")) {
                String[] names = name.split("ingot");
                if (names.length > 0) {
                    String metalName = names[1].toLowerCase();
                    metalTypes.put(key, metalName);
                    return metalName;
                }
            }
        }
        return null;
    }

    public static boolean isValidMetal(ItemStack item) {
        String metal = getMetalType(item);
        if (metal == null) return false;
        if (Config.onlyAllowSupportedIngots && !Config.ingotPileMetalsList.contains(metal)) return false;
        return true;
    }

    public static String getItemKey(Item item, int meta) {
        if (item != null) {
            return Item.getIdFromItem(item) + ":" + meta;
        }
        return null;
    }
}
