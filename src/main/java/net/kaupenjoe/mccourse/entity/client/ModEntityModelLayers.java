package net.kaupenjoe.mccourse.entity.client;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import javax.swing.text.html.parser.Entity;

public class ModEntityModelLayers {
    public static final EntityModelLayer DODO =
            new EntityModelLayer(Identifier.of(MCCourseMod.MOD_ID, "dodo"), "main");

    public static final EntityModelLayer GIRAFFE =
            new EntityModelLayer(Identifier.of(MCCourseMod.MOD_ID, "giraffe"), "main");

    public static final EntityModelLayer TOMAHAWK =
            new EntityModelLayer(Identifier.of(MCCourseMod.MOD_ID, "tomahawk"), "main");

    public static final EntityModelLayer WARTURTLE =
            new EntityModelLayer(Identifier.of(MCCourseMod.MOD_ID, "warturtle"), "main");

    public static final EntityModelLayer WARTURTLE_ARMOR =
            new EntityModelLayer(Identifier.of(MCCourseMod.MOD_ID, "warturtle_armor"), "armor");
}
