package net.kaupenjoe.mccourse.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.screen.custom.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {

    public static final ScreenHandlerType<PedestalScreenHandler> PEDESTAL_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MCCourseMod.MOD_ID, "pedestal_screen_handler"),
                    new ExtendedScreenHandlerType<>(PedestalScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<CrystallizerScreenHandler> CRYSTALLIZER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MCCourseMod.MOD_ID, "crystallizer_screen_handler"),
                    new ExtendedScreenHandlerType<>(CrystallizerScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<CoalGeneratorScreenHandler> COAL_GENERATOR_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MCCourseMod.MOD_ID, "coal_generator_screen_handler"),
                    new ExtendedScreenHandlerType<>(CoalGeneratorScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<TankScreenHandler> TANK_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MCCourseMod.MOD_ID, "tank_screen_handler"),
                    new ExtendedScreenHandlerType<>(TankScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<WarturtleScreenHandler> WARTURTLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MCCourseMod.MOD_ID, "warturtle_screen_handler"),
                    new ExtendedScreenHandlerType<>(WarturtleScreenHandler::create, Uuids.PACKET_CODEC));

    public static void registerScreenHandlers(){
        MCCourseMod.LOGGER.info("Registering screen handlers for " + MCCourseMod.MOD_ID);
    }
}
