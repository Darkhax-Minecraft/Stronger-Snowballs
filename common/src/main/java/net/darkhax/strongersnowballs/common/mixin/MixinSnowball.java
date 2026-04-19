package net.darkhax.strongersnowballs.common.mixin;

import net.darkhax.strongersnowballs.common.Config;
import net.darkhax.strongersnowballs.common.StrongerSnowballsMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Snowball.class)
public class MixinSnowball {

    @Inject(method = "onHitEntity", at = @At("RETURN"))
    private void onHitEntity(EntityHitResult hitResult, CallbackInfo cbi) {
        if (hitResult.getEntity() instanceof LivingEntity living && living.canFreeze() && !(living instanceof Player player && player.getAbilities().instabuild)) {
            final Config config = StrongerSnowballsMod.CFG;
            if (config.damage_all_mobs || living.is(StrongerSnowballsMod.HURT_BY_SNOW)) {
                final Snowball self = (Snowball) (Object) this;
                living.hurt(self.level().damageSources().thrown(self, self.getOwner()), config.snowball_damage);
            }
            if (config.slowness_effect.enabled && StrongerSnowballsMod.tryPercentage(config.slowness_effect.chance)) {
                living.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, config.slowness_effect.duration, config.slowness_effect.amplifier));
            }
            if (config.freeze_effect.enabled) {
                if (StrongerSnowballsMod.tryPercentage(config.freeze_effect.instant_freeze_chance)) {
                    living.setTicksFrozen(living.getTicksRequiredToFreeze() + 120);
                }
                else if (config.freeze_effect.freeze_amount > 0 && StrongerSnowballsMod.tryPercentage(config.freeze_effect.freeze_chance)) {
                    living.setTicksFrozen(Math.min(living.getTicksRequiredToFreeze() + 120, living.getTicksFrozen() + config.freeze_effect.freeze_amount));
                }
            }
        }
    }
}