package net.kaupenjoe.mccourse;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.block.entity.renderer.PedestalBlockEntityRenderer;
import net.kaupenjoe.mccourse.block.entity.renderer.TankBlockEntityRenderer;
import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.entity.client.*;
import net.kaupenjoe.mccourse.fluid.ModFluids;
import net.kaupenjoe.mccourse.screen.ModScreenHandlers;
import net.kaupenjoe.mccourse.screen.custom.*;
import net.kaupenjoe.mccourse.util.ModModelPredicates;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.world.biome.FoliageColors;

public class MccourseModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_FLUORITE_WATER, ModFluids.FLOWING_FLUORITE_WATER,
                SimpleFluidRenderHandler.coloredWater(0xA1E038D0));

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACKWOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACKWOOD_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAWBERRY_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DAHLIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DAHLIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACKWOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_FLUORITE_WATER, ModFluids.FLOWING_FLUORITE_WATER);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TANK, RenderLayer.getTranslucent());

        BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.TANK_BE, TankBlockEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.DODO, DodoModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DODO, DoDoRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.GIRAFFE, GiraffeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.GIRAFFE, GiraffeRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.WARTURTLE, WarturtleModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.WARTURTLE, WarturtleRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.TOMAHAWK, TomahawkProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.TOMAHAWK, TomahawkProjectileRenderer::new);



        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ?
                BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), ModBlocks.COLORED_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), ModBlocks.COLORED_LEAVES);
        ModModelPredicates.registerModelPredicates();

        HandledScreens.register(ModScreenHandlers.PEDESTAL_SCREEN_HANDLER, PedestalScreen::new);
        HandledScreens.register(ModScreenHandlers.CRYSTALLIZER_SCREEN_HANDLER, CrystallizerScreen::new);
        HandledScreens.register(ModScreenHandlers.COAL_GENERATOR_SCREEN_HANDLER, CoalGeneratorScreen::new);
        HandledScreens.register(ModScreenHandlers.TANK_SCREEN_HANDLER, TankScreen::new);
        HandledScreens.register(ModScreenHandlers.WARTURTLE_SCREEN_HANDLER, WarturtleScreen::new);
    }
}
