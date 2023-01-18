package net.darkhax.strongersnowballs;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.io.File;

public class StrongerSnowballsCommon {

    public static StrongerSnowballsCommon instance;

    public final Config config;
    public final TagKey<EntityType<?>> HURT_BY_SNOW;

    public StrongerSnowballsCommon(File configFile) {

        config = Config.load(configFile);
        HURT_BY_SNOW = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(Constants.MOD_ID, "hurt_by_snow"));

        instance = this;
    }

    public static boolean tryPercentage(double percent) {

        return Math.random() < percent;
    }
}