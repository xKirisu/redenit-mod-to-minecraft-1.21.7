package redenit.modid;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.jukebox.JukeboxSongs;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import redenit.modid.class_extensions.RedenitRod;

import javax.tools.Tool;
import java.util.Map;

public class RedenitItemCollection {


    // Items
    public static final Item REDENIT_WASTE = ToolsToMod.register("redenit_waste", Item::new, new Item.Settings());
    public static final Item REDENIT_NUGGET = ToolsToMod.register("redenit_nugget", Item::new, new Item.Settings());
    public static final Item REDENIT_INGOT = ToolsToMod.register("redenit_ingot", Item::new, new Item.Settings());

    public static final Item DISC_SHARD = ToolsToMod.register("disc_shard", Item::new, new Item.Settings().rarity(Rarity.UNCOMMON));
    public static final Item DISC_RK77 = ToolsToMod.register("disc_rk77", Item::new, (new Item.Settings()).maxCount(1));//.JukeboxSongs.THIRTEEN));

    // Food
    public static final Item REDELON_SEEDS = ToolsToMod.register(
            "redelon_seeds",
            Item::new,
        new Item.Settings()
    );

    public static final Item REDELON = ToolsToMod.register(
      "redelon_slice",
        Item::new,
        new Item.Settings().food(new FoodComponent.Builder().nutrition(8).saturationModifier(1.0f).build())
    );

    public static final Block REDELON_BLOCK = ToolsToMod.register(
            "redelon_block",
            Block::new,
            AbstractBlock.Settings
                    .create()
                    .strength(3.0f, 1.0f)
                    .sounds(BlockSoundGroup.WOOD)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true
    );

    // Blocks
    public static final Block REDENIT_ORE = ToolsToMod.register(
            "redenit_ore",
            Block::new,
            AbstractBlock.Settings
                    .create()
                    .requiresTool()
                    .strength(4.5F, 4.0F),
            true
    );

    public static final Block DEEPSLATE_REDENIT_ORE = ToolsToMod.register(
            "deepslate_redenit_ore",
            Block::new,
            AbstractBlock.Settings
                    .create()
                    .requiresTool()
                    .strength(5.5F, 5.0F),
            true
    );

    public static final Block REDENIT_BLOCK = ToolsToMod.register(
            "redenit_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(7.0F, 9.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .instrument(NoteBlockInstrument.GUITAR)
                    .mapColor(DyeColor.RED),
            true
    );

    public static final Block REDENIT_WASTE_BLOCK = ToolsToMod.register(
            "redenit_waste_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.5F, 7.0F)
                    .instrument(NoteBlockInstrument.BIT)
                    .mapColor(DyeColor.RED),
            true
    );

    public static final Block ASH_LOG_BLOCK = ToolsToMod.register(
            "ash_log_block",
            PillarBlock::new,
            //createLogSettings(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN, BlockSoundGroup.WOOD)
            AbstractBlock.Settings.create(),
            true
            );

    public static final Block ASH_PLANK_BLOCK = ToolsToMod.register(
      "ash_plank_block",
        Block::new,
        AbstractBlock.Settings.create()    ,
            true
    );


    // Equipment
    public static final RegistryKey<EquipmentAsset> REDENIT_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(RedenitMod.MOD_ID, "redenit_armor"));

    public static final TagKey<Item> REDENIT_INGREDIENT = TagKey.of(RegistryKeys.ITEM, Identifier.of(RedenitMod.MOD_ID, "redenit_ingredient"));

    private static final int[] BASE_ARMOR_DURABILITY = {12, 14, 15, 10};
    private static final int[] BASE_ARMOR_PROTECTION = {4, 7, 5, 2};

    public static final ArmorMaterial REDENIT_ARMOR_MATERIAL = new ArmorMaterial(
            100,
            Map.of(
                    EquipmentType.HELMET, 4,
                    EquipmentType.CHESTPLATE, 7,
                    EquipmentType.LEGGINGS, 5,
                    EquipmentType.BOOTS, 2
            ),
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            REDENIT_INGREDIENT,
            REDENIT_ARMOR_MATERIAL_KEY
    );

    public static final ToolMaterial REDENIT_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            455,
            5.0F,
            1.5F,
            22,
            REDENIT_INGREDIENT
    );


    // Tools
    public static final Item REDENIT_SWORD = ToolsToMod.register(
            "redenit_sword",
            Item::new,
            new Item.Settings().sword(REDENIT_TOOL_MATERIAL, 3.0F, -2.4F)
    );

    public static final Item REDENIT_AXE = ToolsToMod.register(
            "redenit_axe",
            settings -> new AxeItem(REDENIT_TOOL_MATERIAL, 1.0f, 1.0f, settings),
            new Item.Settings()
    );

    public static final Item REDENIT_PICKAXE = ToolsToMod.register(
            "redenit_pickaxe",
            Item::new,
            new Item.Settings().pickaxe(REDENIT_TOOL_MATERIAL, 1.0f, 1.0f)
    );

    public static final Item REDENIT_SHOVEL = ToolsToMod.register(
      "redenit_shovel",
        settings -> new ShovelItem(REDENIT_TOOL_MATERIAL, 1.0f, 1.0f, settings),
        new Item.Settings()
    );

    public static final Item REDENIT_HOE = ToolsToMod.register(
      "redenit_hoe",
      settings -> new HoeItem(REDENIT_TOOL_MATERIAL, 1.0f, 1.0f, settings),
      new Item.Settings()
    );


    //Armor
    public static final Item REDENIT_HELMET = ToolsToMod.register(
      "redenit_helmet",
            Item::new,
            new Item.Settings()
                    .armor(REDENIT_ARMOR_MATERIAL, EquipmentType.HELMET)
                    .maxDamage(EquipmentType.HELMET.getMaxDamage(1))

    );

    public static final Item REDENIT_CHESTPLATE = ToolsToMod.register(
            "redenit_chestplate",
            Item::new,
            new Item.Settings()
                    .armor(REDENIT_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(1))
    );

    public static final Item REDENIT_LEGGINGS = ToolsToMod.register(
            "redenit_leggings",
            Item::new,
            new Item.Settings()
                    .armor(REDENIT_ARMOR_MATERIAL, EquipmentType.LEGGINGS)
                    .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(1))
    );

    public static final Item REDENIT_BOOTS = ToolsToMod.register(
            "redenit_boots",
            Item::new,
            new Item.Settings()
                    .armor(REDENIT_ARMOR_MATERIAL, EquipmentType.BOOTS)
                    .maxDamage(EquipmentType.BOOTS.getMaxDamage(1))
    );

    public static final Item REDENIT_ROD = ToolsToMod.register(
      "redenit_rod",
            RedenitRod::new,
            (new Item.Settings())
                    .maxDamage(64)
                    .enchantable(4)
    );


    // Group
    public static final RegistryKey<ItemGroup> REDENIT_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(RedenitMod.MOD_ID, "redenit_group"));

    public static final ItemGroup REDENIT_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            REDENIT_GROUP_KEY,
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.redenit_group"))
                    .icon(() -> new ItemStack(REDENIT_INGOT))
                    .entries((displayContext, entries) -> {

                        entries.add(REDENIT_WASTE);
                        entries.add(REDENIT_NUGGET);
                        entries.add(REDENIT_INGOT);

                        entries.add(REDENIT_ORE.asItem());
                        entries.add(DEEPSLATE_REDENIT_ORE.asItem());
                        entries.add(REDENIT_BLOCK.asItem());
                        entries.add(REDENIT_WASTE_BLOCK.asItem());

                        entries.add(REDENIT_SWORD);
                        entries.add(REDENIT_AXE);
                        entries.add(REDENIT_PICKAXE);
                        entries.add(REDENIT_SHOVEL);
                        entries.add(REDENIT_HOE);

                        entries.add(REDENIT_HELMET);
                        entries.add(REDENIT_CHESTPLATE);
                        entries.add(REDENIT_LEGGINGS);
                        entries.add(REDENIT_BOOTS);

                        entries.add(REDENIT_ROD);

                        entries.add(REDELON_SEEDS);
                        entries.add(REDELON);
                        entries.add(REDELON_BLOCK);

                        entries.add(DISC_SHARD);
                        entries.add(DISC_RK77);

                        entries.add(ASH_LOG_BLOCK);
                        entries.add(ASH_PLANK_BLOCK);

                        // ...
                    })
                    .build()
    );

    // Initalize
    public static void initialize() {

    }
}
