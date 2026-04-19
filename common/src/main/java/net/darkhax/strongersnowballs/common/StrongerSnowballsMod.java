package net.darkhax.strongersnowballs.common;

import net.darkhax.pricklemc.common.api.config.ConfigManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StrongerSnowballsMod {

    public static final String MOD_ID = "strongersnowballs";
    public static final String MOD_NAME = "Stronger Snowballs";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);
    public static final Config CFG = ConfigManager.load(MOD_ID, new Config());
    public static final TagKey<EntityType<?>> HURT_BY_SNOW = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "hurt_by_snow"));

    public static boolean tryPercentage(double percent) {
        return Math.random() < percent;
    }
}