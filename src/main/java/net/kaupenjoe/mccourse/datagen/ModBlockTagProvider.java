package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.util.ModTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.FLUORITE_BLOCK,
                        ModBlocks.FLUORITE_ORE,
                        ModBlocks.FLUORITE_DEEPSLATE_ORE,
                        ModBlocks.FLUORITE_END_ORE,
                        ModBlocks.FLUORITE_NETHER_ORE,
                        ModBlocks.FLUORITE_FENCE,
                        ModBlocks.FLUORITE_FENCE_GATE,
                        ModBlocks.FLUORITE_WALL,
                        ModBlocks.FLUORITE_BUTTON,
                        ModBlocks.FLUORITE_PRESSURE_PLATE);



        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.FLUORITE_NETHER_ORE,
                        ModBlocks.FLUORITE_DEEPSLATE_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.FLUORITE_END_ORE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.FLUORITE_WALL)
                .add(ModBlocks.BLACKWOOD_WALL);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.FLUORITE_FENCE)
                .add(ModBlocks.BLACKWOOD_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.FLUORITE_FENCE_GATE)
                .add(ModBlocks.BLACKWOOD_FENCE_GATE);

        getOrCreateTagBuilder(ModTags.Blocks.PAXEL_MINEABLE)
                .forceAddTag((BlockTags.PICKAXE_MINEABLE))
                .forceAddTag((BlockTags.AXE_MINEABLE))
                .forceAddTag((BlockTags.SHOVEL_MINEABLE));

        getOrCreateTagBuilder(ModTags.Blocks.HAMMER_MINEABLE)
                .forceAddTag(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(BlockTags.SHOVEL_MINEABLE);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.BLACKWOOD_LOG, ModBlocks.BLACKWOOD_WOOD, ModBlocks.STRIPPED_BLACKWOOD_LOG, ModBlocks.STRIPPED_BLACKWOOD_WOOD);


    }
}
