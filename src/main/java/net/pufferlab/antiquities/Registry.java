package net.pufferlab.antiquities;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.pufferlab.antiquities.blocks.*;
import net.pufferlab.antiquities.entity.EntitySeat;
import net.pufferlab.antiquities.itemblocks.ItemBlockMeta;
import net.pufferlab.antiquities.tileentities.*;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Registry {

    public static Block chair;
    public static Block table;
    public static Block shelf_0;
    public static Block shelf_1;
    public static Block shelf_2;
    public static Block shelf_3;
    public static Block shelf_4;
    public static Block shelf_5;
    public static Block jar;
    public static Block label;
    public static Block rack;
    public static Block chair_bop;
    public static Block table_bop;
    public static Block shelf_0_bop;
    public static Block shelf_1_bop;
    public static Block shelf_2_bop;
    public static Block shelf_3_bop;
    public static Block shelf_4_bop;
    public static Block shelf_5_bop;
    public static Block jar_bop;
    public static Block label_bop;
    public static Block rack_bop;

    public static Block chair_tc;
    public static Block table_tc;
    public static Block shelf_0_tc;
    public static Block shelf_1_tc;
    public static Block shelf_2_tc;
    public static Block shelf_3_tc;
    public static Block shelf_4_tc;
    public static Block shelf_5_tc;
    public static Block jar_tc;
    public static Block label_tc;
    public static Block rack_tc;

    public static Block chair_wt;
    public static Block table_wt;
    public static Block shelf_0_wt;
    public static Block shelf_1_wt;
    public static Block shelf_2_wt;
    public static Block shelf_3_wt;
    public static Block shelf_4_wt;
    public static Block shelf_5_wt;
    public static Block jar_wt;
    public static Block label_wt;
    public static Block rack_wt;

    public static Block pedestal;
    public static Block globe;
    public static Block clock;
    public static Block pile;
    public static Block couch;

    public static final CreativeTabs creativeTab = new CreativeTabs(Antiquities.MODID + "CreativeTab") {

        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(chair);
        }

        @Override
        public String getTranslatedTabLabel() {
            return Antiquities.MODNAME;
        }
    };

    public void preInit(FMLPreInitializationEvent event) {
        chair = new BlockChair(Constants.woodTypes);
        table = new BlockTable(Constants.woodTypes);
        shelf_0 = new BlockShelf(0, Constants.woodTypes);
        shelf_1 = new BlockShelf(1, Constants.woodTypes);
        shelf_2 = new BlockShelf(2, Constants.woodTypes);
        shelf_3 = new BlockShelf(3, Constants.woodTypes);
        shelf_4 = new BlockShelf(4, Constants.woodTypes);
        shelf_5 = new BlockShelf(5, Constants.woodTypes);
        jar = new BlockJar(Constants.woodTypes);
        label = new BlockLabel(Constants.woodTypes);
        rack = new BlockRack(Constants.woodTypes);

        if (Loader.isModLoaded("BiomesOPlenty")) {
            chair_bop = new BlockChair(Constants.bopWoodTypes);
            table_bop = new BlockTable(Constants.bopWoodTypes);
            shelf_0_bop = new BlockShelf(0, Constants.bopWoodTypes);
            shelf_1_bop = new BlockShelf(1, Constants.bopWoodTypes);
            shelf_2_bop = new BlockShelf(2, Constants.bopWoodTypes);
            shelf_3_bop = new BlockShelf(3, Constants.bopWoodTypes);
            shelf_4_bop = new BlockShelf(4, Constants.bopWoodTypes);
            shelf_5_bop = new BlockShelf(5, Constants.bopWoodTypes);
            jar_bop = new BlockJar(Constants.bopWoodTypes);
            label_bop = new BlockLabel(Constants.bopWoodTypes);
            rack_bop = new BlockRack(Constants.bopWoodTypes);
        }
        if (Loader.isModLoaded("Thaumcraft")) {
            chair_tc = new BlockChair(Constants.thaumcraftWoodTypes);
            table_tc = new BlockTable(Constants.thaumcraftWoodTypes);
            shelf_0_tc = new BlockShelf(0, Constants.thaumcraftWoodTypes);
            shelf_1_tc = new BlockShelf(1, Constants.thaumcraftWoodTypes);
            shelf_2_tc = new BlockShelf(2, Constants.thaumcraftWoodTypes);
            shelf_3_tc = new BlockShelf(3, Constants.thaumcraftWoodTypes);
            shelf_4_tc = new BlockShelf(4, Constants.thaumcraftWoodTypes);
            shelf_5_tc = new BlockShelf(5, Constants.thaumcraftWoodTypes);
            jar_tc = new BlockJar(Constants.thaumcraftWoodTypes);
            label_tc = new BlockLabel(Constants.thaumcraftWoodTypes);
            rack_tc = new BlockRack(Constants.thaumcraftWoodTypes);
        }
        if (Loader.isModLoaded("witchery")) {
            chair_wt = new BlockChair(Constants.witcheryWoodTypes);
            table_wt = new BlockTable(Constants.witcheryWoodTypes);
            shelf_0_wt = new BlockShelf(0, Constants.witcheryWoodTypes);
            shelf_1_wt = new BlockShelf(1, Constants.witcheryWoodTypes);
            shelf_2_wt = new BlockShelf(2, Constants.witcheryWoodTypes);
            shelf_3_wt = new BlockShelf(3, Constants.witcheryWoodTypes);
            shelf_4_wt = new BlockShelf(4, Constants.witcheryWoodTypes);
            shelf_5_wt = new BlockShelf(5, Constants.witcheryWoodTypes);
            jar_wt = new BlockJar(Constants.witcheryWoodTypes);
            label_wt = new BlockLabel(Constants.witcheryWoodTypes);
            rack_wt = new BlockRack(Constants.witcheryWoodTypes);
        }
        pedestal = new BlockPedestal(Constants.stoneTypes);
        globe = new BlockGlobe(Constants.globeTypes);
        clock = new BlockClock();
        pile = new BlockPile();
        couch = new BlockCouch(Constants.colorTypes);
        register(TileEntityChair.class, "chair");
        register(TileEntityTable.class, "table");
        register(TileEntityShelf.class, "shelf");
        register(TileEntityJar.class, "jar");
        register(TileEntityLabel.class, "label");
        register(TileEntityRack.class, "rack");
        register(TileEntityPedestal.class, "pedestal");
        register(TileEntityGlobe.class, "globe");
        register(TileEntityClock.class, "clock");
        register(TileEntityPile.class, "pile");
        register(TileEntityCouch.class, "couch");

        register(chair, "chair");
        register(shelf_0, "shelf");
        register(shelf_1, "shelf_1");
        register(shelf_2, "shelf_2");
        register(shelf_3, "shelf_3");
        register(shelf_4, "shelf_4");
        register(shelf_5, "shelf_5");
        register(table, "table");
        register(jar, "jar");
        register(label, "label");
        register(rack, "rack");

        if (Loader.isModLoaded("BiomesOPlenty")) {
            register(chair_bop, "chair_bop");
            register(shelf_0_bop, "shelf_bop");
            register(shelf_1_bop, "shelf_bop_1");
            register(shelf_2_bop, "shelf_bop_2");
            register(shelf_3_bop, "shelf_bop_3");
            register(shelf_4_bop, "shelf_bop_4");
            register(shelf_5_bop, "shelf_bop_5");
            register(table_bop, "table_bop");
            register(jar_bop, "jar_bop");
            register(label_bop, "label_bop");
            register(rack_bop, "rack_bop");
        }
        if (Loader.isModLoaded("Thaumcraft")) {
            register(chair_tc, "chair_tc");
            register(shelf_0_tc, "shelf_tc");
            register(shelf_1_tc, "shelf_tc_1");
            register(shelf_2_tc, "shelf_tc_2");
            register(shelf_3_tc, "shelf_tc_3");
            register(shelf_4_tc, "shelf_tc_4");
            register(shelf_5_tc, "shelf_tc_5");
            register(table_tc, "table_tc");
            register(jar_tc, "jar_tc");
            register(label_tc, "label_tc");
            register(rack_tc, "rack_tc");
        }
        if (Loader.isModLoaded("witchery")) {
            register(chair_wt, "chair_wt");
            register(shelf_0_wt, "shelf_wt");
            register(shelf_1_wt, "shelf_wt_1");
            register(shelf_2_wt, "shelf_wt_2");
            register(shelf_3_wt, "shelf_wt_3");
            register(shelf_4_wt, "shelf_wt_4");
            register(shelf_5_wt, "shelf_wt_5");
            register(table_wt, "table_wt");
            register(jar_wt, "jar_wt");
            register(label_wt, "label_wt");
            register(rack_wt, "rack_wt");
        }

        register(pedestal, "pedestal");
        register(globe, "globe");
        register(clock, "clock");
        register(pile, "pile");
        register(couch, "couch");
    }

    public void init() {
        int id = 0;
        EntityRegistry
            .registerModEntity(EntitySeat.class, "EntitySeatAntiquities", id++, Antiquities.instance, 64, 20, true);
    }

    public void register(Class<? extends TileEntityAntiquities> tileEntityClass, String id) {
        try {
            TileEntityAntiquities instance = tileEntityClass.getDeclaredConstructor()
                .newInstance();
            if (instance.canRegister()) {
                GameRegistry.registerTileEntity(tileEntityClass, Antiquities.MODID + "_" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(Block block, String name) {
        BlockAntiquities instance = (BlockAntiquities) block;
        if (instance.canRegister()) {
            if (block instanceof BlockMetaContainer) {
                GameRegistry.registerBlock(block, ItemBlockMeta.class, name);
            } else {
                if (block instanceof BlockPile) {
                    GameRegistry.registerBlock(block, null, name);
                } else {
                    GameRegistry.registerBlock(block, name);
                }
            }
        }
    }
}
