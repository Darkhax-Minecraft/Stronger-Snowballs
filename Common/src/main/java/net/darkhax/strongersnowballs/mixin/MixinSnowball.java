package net.darkhax.strongersnowballs.mixin;

import net.darkhax.strongersnowballs.Config;
import net.darkhax.strongersnowballs.StrongerSnowballsCommon;
import net.minecraft.world.damagesource.DamageSource;
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

        if (hitEntity instanceof LivingEntity living) {

            final Snowball self = (Snowball)(Object)this;
            final Config config = StrongerSnowballsCommon.instance.config;
            living.hurt(DamageSource.thrown(self, self.getOwner()), config.snowballDamage);

            if (config.slownessEffect.enabled && StrongerSnowballsCommon.tryPercentage(config.slownessEffect.chance)) {

                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, config.slownessEffect.durationTicks, config.slownessEffect.amplifier));
            }
        }
    }
}
