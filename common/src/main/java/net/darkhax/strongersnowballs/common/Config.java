package net.darkhax.strongersnowballs.common;

import net.darkhax.pricklemc.common.api.annotations.RangedFloat;
import net.darkhax.pricklemc.common.api.annotations.RangedInt;
import net.darkhax.pricklemc.common.api.annotations.Value;

public class Config {

    @Value(comment = "The amount of damage dealt by snowballs.")
    @RangedFloat(min = 0f)
    public float snowball_damage = 3f;

    @Value(comment = "Properties of the slowness debug inflicted by snowballs.", writeDefault = false)
    public SlownessEffect slowness_effect = new SlownessEffect();

    @Value(comment = "Determines if all mobs are hurt, or just those weak to snow/water.")
    public boolean damage_all_mobs = false;

    @Value(comment = "Properties of the freeze effect inflicted by snowballs.", writeDefault = false)
    public FreezeEffect freeze_effect = new FreezeEffect();

    public static class FreezeEffect {

        @Value(comment = "Should mobs receive a freeze effect when hit by snowballs?")
        public boolean enabled = true;

        @Value(comment = "The chance that the mob will receive a freeze effect when hit by a snowball.")
        @RangedFloat(min = 0f, max = 1f)
        public float freeze_chance = 0.8f;

        @Value(comment = "The amount of freeze ticks to apply.")
        @RangedInt(min = 0)
        public int freeze_amount = 40;

        @Value(comment = "The chance of a mob instantly freezing when hit by a snowball.")
        @RangedFloat(min = 0f, max = 1f)
        public float instant_freeze_chance = 0f;
    }
    
    public static class SlownessEffect {

        @Value(comment = "Should entities receive a slowness debuff when hit by snowballs?")
        public boolean enabled = true;

        @Value(comment = "The duration of the slowness debuff in ticks.")
        @RangedInt(min = 0)
        public int duration = 15;

        @Value(comment = "The strength of the slowness effect.")
        @RangedInt(min = 0)
        public int amplifier = 1;

        @Value(comment = "The chance of the entity receiving a slowness effect when hit by a snowball.")
        @RangedFloat(min = 0f, max = 1f)
        public float chance = 0.4f;
    }
}
