package net.kaupenjoe.mccourse;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.components.ModDataComponentTypes;
import net.kaupenjoe.mccourse.event.AttackEntityHandler;
import net.kaupenjoe.mccourse.item.ModItemGroups;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.util.HammerUsageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCCourseMod implements ModInitializer {
    public static final String MOD_ID = "mccourse";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        ModBlocks.registerModBlocks();
        ModDataComponentTypes.registerDataComponentTypes();

        FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);

        PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
        AttackEntityCallback.EVENT.register(new AttackEntityHandler());
    }
}