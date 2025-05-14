package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.custom.FluoriteLampBlock;
import net.kaupenjoe.mccourse.block.custom.StrawberryCropBlock;
import net.kaupenjoe.mccourse.fluid.ModFluids;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool fluoriteTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.FLUORITE_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_DEEPSLATE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_END_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_NETHER_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);

        fluoriteTexturePool.stairs(ModBlocks.FLUORITE_STAIRS);
        fluoriteTexturePool.slab(ModBlocks.FLUORITE_SLAB);
        fluoriteTexturePool.button(ModBlocks.FLUORITE_BUTTON);
        fluoriteTexturePool.pressurePlate(ModBlocks.FLUORITE_PRESSURE_PLATE);
        fluoriteTexturePool.fence(ModBlocks.FLUORITE_FENCE);
        fluoriteTexturePool.fenceGate(ModBlocks.FLUORITE_FENCE_GATE);
        fluoriteTexturePool.wall(ModBlocks.FLUORITE_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.FLUORITE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.FLUORITE_TRAPDOOR);

        blockStateModelGenerator.registerCrop(ModBlocks.STRAWBERRY_CROP, StrawberryCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.DAHLIA, ModBlocks.POTTED_DAHLIA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSingleton(ModBlocks.COLORED_LEAVES, TexturedModel.LEAVES);

        Identifier lampOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.FLUORITE_LAMP, blockStateModelGenerator.modelCollector);
        Identifier lampOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.FLUORITE_LAMP, "_on", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.FLUORITE_LAMP)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(FluoriteLampBlock.CLICKED, lampOnIdentifier, lampOffIdentifier)));

        blockStateModelGenerator.registerCooker(ModBlocks.CRYSTALLIZER, TexturedModel.ORIENTABLE);

        blockStateModelGenerator.registerLog(ModBlocks.BLACKWOOD_LOG).log(ModBlocks.BLACKWOOD_LOG).wood(ModBlocks.BLACKWOOD_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_BLACKWOOD_LOG).log(ModBlocks.STRIPPED_BLACKWOOD_LOG).wood(ModBlocks.STRIPPED_BLACKWOOD_WOOD);
        blockStateModelGenerator.registerSingleton(ModBlocks.BLACKWOOD_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.BLACKWOOD_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        BlockStateModelGenerator.BlockTexturePool blackwoodTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BLACKWOOD_PLANKS);

        blackwoodTexturePool.stairs(ModBlocks.BLACKWOOD_STAIRS);
        blackwoodTexturePool.slab(ModBlocks.BLACKWOOD_SLAB);
        blackwoodTexturePool.button(ModBlocks.BLACKWOOD_BUTTON);
        blackwoodTexturePool.pressurePlate(ModBlocks.BLACKWOOD_PRESSURE_PLATE);
        blackwoodTexturePool.fence(ModBlocks.BLACKWOOD_FENCE);
        blackwoodTexturePool.fenceGate(ModBlocks.BLACKWOOD_FENCE_GATE);
        blackwoodTexturePool.wall(ModBlocks.BLACKWOOD_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.BLACKWOOD_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.BLACKWOOD_TRAPDOOR);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COAL_GENERATOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TANK);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.FLUORITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_FLUORITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.STARLIGHT_ASHES, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHAINSAW, Models.GENERATED);

        itemModelGenerator.register(ModItems.FLUORITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_PAXEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_HAMMER, Models.HANDHELD);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.FLUORITE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.FLUORITE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.FLUORITE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.FLUORITE_BOOTS);

        itemModelGenerator.register(ModItems.FLUORITE_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.KAUPEN_SMITHING_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.METAL_DETECTOR, Models.GENERATED);

        itemModelGenerator.register(ModItems.BAR_BRAWL_MUSIC_DISC, Models.GENERATED);

        itemModelGenerator.register(ModFluids.FLUORITE_WATER_BUCKET, Models.GENERATED);

        itemModelGenerator.register(ModItems.DODO_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.GIRAFFE_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}
