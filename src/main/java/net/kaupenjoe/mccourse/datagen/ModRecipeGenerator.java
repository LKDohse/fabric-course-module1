package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.util.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> FLUORITE_SMELTABLES = List.of(ModItems.RAW_FLUORITE,
                ModBlocks.FLUORITE_ORE,
                ModBlocks.FLUORITE_DEEPSLATE_ORE,
                ModBlocks.FLUORITE_END_ORE,
                ModBlocks.FLUORITE_NETHER_ORE);

        offerSmelting(recipeExporter, FLUORITE_SMELTABLES, RecipeCategory.MISC, ModItems.FLUORITE, 0.2f, 200, "fluorite");
        offerBlasting(recipeExporter, FLUORITE_SMELTABLES, RecipeCategory.MISC, ModItems.FLUORITE, 0.2f, 100, "fluorite");
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModItems.FLUORITE, RecipeCategory.DECORATIONS, ModBlocks.FLUORITE_BLOCK);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_FLUORITE)
                .pattern("SSS")
                .pattern("SFS")
                .pattern("SSS")
                .input('S', Blocks.STONE)
                .input('F', ModItems.FLUORITE)
                .criterion(hasItem(Blocks.STONE), conditionsFromItem(Blocks.STONE))
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_FLUORITE)
                .pattern("SFS")
                .pattern("SFS")
                .pattern("SFS")
                .input('S', Blocks.STONE)
                .input('F', ModItems.FLUORITE)
                .criterion(hasItem(Blocks.STONE), conditionsFromItem(Blocks.STONE))
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter, "raw_fluorite_2");

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_PICKAXE)
                .pattern("FFF")
                .pattern(" S ")
                .pattern(" S ")
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_AXE)
                .pattern("FF ")
                .pattern("FS ")
                .pattern(" S ")
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_SHOVEL)
                .pattern(" F ")
                .pattern(" S ")
                .pattern(" S ")
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_HOE)
                .pattern("FF ")
                .pattern(" S ")
                .pattern(" S ")
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLUORITE_SWORD)
                .pattern(" F ")
                .pattern(" F ")
                .pattern(" S ")
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLUORITE_HELMET)
                .pattern("   ")
                .pattern("FFF")
                .pattern("F F")
                .input('F', ModItems.FLUORITE)
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLUORITE_CHESTPLATE)
                .pattern("F F")
                .pattern("FFF")
                .pattern("FFF")
                .input('F', ModItems.FLUORITE)
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLUORITE_LEGGINGS)
                .pattern("FFF")
                .pattern("F F")
                .pattern("F F")
                .input('F', ModItems.FLUORITE)
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLUORITE_BOOTS)
                .pattern("   ")
                .pattern("F F")
                .pattern("F F")
                .input('F', ModItems.FLUORITE)
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(recipeExporter);

        offerSmithingTrimRecipe(recipeExporter, ModItems.KAUPEN_SMITHING_TEMPLATE, Identifier.of(MCCourseMod.MOD_ID, "kaupen"));

        offerPlanksRecipe(recipeExporter, ModBlocks.BLACKWOOD_PLANKS, ModTags.Items.CAN_BLACKWOOD_PLANK, 4);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACKWOOD_SLAB,ModBlocks.BLACKWOOD_PLANKS);
        offerPressurePlateRecipe(recipeExporter, ModBlocks.BLACKWOOD_PRESSURE_PLATE,ModBlocks.BLACKWOOD_PLANKS);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACKWOOD_STAIRS, 4)
                .pattern("P  ")
                .pattern("PP ")
                .pattern("PPP")
                .input('P', ModBlocks.BLACKWOOD_PLANKS)
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS.asItem()), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS.asItem()))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACKWOOD_DOOR, 2)
                .pattern("PP ")
                .pattern("PP ")
                .pattern("PP ")
                .input('P', ModBlocks.BLACKWOOD_PLANKS)
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS.asItem()), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS.asItem()))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACKWOOD_BUTTON,2)
                .pattern("   ")
                .pattern(" P ")
                .pattern("   ")
                .input('P', ModBlocks.BLACKWOOD_PLANKS)
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS.asItem()), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS.asItem()))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACKWOOD_FENCE, 4)
                .pattern("   ")
                .pattern("PSP")
                .pattern("PSP")
                .input('P', ModBlocks.BLACKWOOD_PLANKS)
                .input('S', Items.STICK)
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS.asItem()), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS.asItem()))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACKWOOD_FENCE_GATE,2)
                .pattern("   ")
                .pattern("SPS")
                .pattern("SPS")
                .input('P', ModBlocks.BLACKWOOD_PLANKS)
                .input('S', Items.STICK)
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS.asItem()), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS.asItem()))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACKWOOD_TRAPDOOR, 2)
                .pattern("   ")
                .pattern("PPP")
                .pattern("PPP")
                .input('P', ModBlocks.BLACKWOOD_PLANKS)
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS.asItem()), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS.asItem()))
                .offerTo(recipeExporter);
    }

}
