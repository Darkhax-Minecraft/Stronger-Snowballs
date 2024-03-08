package net.darkhax.strongersnowballs.mixin;

import net.darkhax.strongersnowballs.Config;
import net.darkhax.strongersnowballs.StrongerSnowballsCommon;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Snowball.class)
public class MixinSnowball {

    @Inject(method = "onHitEntity", at = @At("RETURN"))
    private void onHitEntity(EntityHitResult hit, CallbackInfo cbi) {

        final Entity hitEntity = hit.getEntity();

        if (hitEntity instanceof LivingEntity living && living.canFreeze()) {

            final Config config = StrongerSnowballsCommon.instance.config;

            if (config.damageAllMobs || hitEntity.getType().is(StrongerSnowballsCommon.instance.HURT_BY_SNOW)) {

                final Snowball self = (Snowball) (Object) this;
                living.hurt(self.level().damageSources().thrown(self, self.getOwner()), config.snowballDamage);
            }

            if (config.slownessEffect.enabled && StrongerSnowballsCommon.tryPercentage(config.slownessEffect.chance)) {

                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, config.slownessEffect.durationTicks, config.slownessEffect.amplifier));
            }

            if (config.freezeEffect.enabled) {

                if (living.getRandom().nextDouble() < config.freezeEffect.instantFreezeChance) {

                    living.setTicksFrozen(living.getTicksRequiredToFreeze() + 120);
                }

                else if (config.freezeEffect.freezeAmount > 0 && living.getRandom().nextDouble() < config.freezeEffect.freezeChance) {

                    living.setTicksFrozen(Math.min(living.getTicksRequiredToFreeze() + 120, living.getTicksFrozen() + config.freezeEffect.freezeAmount));
                }
            }
        }
    }
}
