package net.darkhax.strongersnowballs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("strongersnowballs")
public class StrongerSnowballs {
    
    public static final Tag<EntityType<?>> HURT_BY_SNOW = new EntityTypeTags.Wrapper(new ResourceLocation("strongersnowballs", "hurt_by_snow"));
    
    public StrongerSnowballs() {
        
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurt);
    }
    
    private void onLivingHurt (LivingHurtEvent event) {
        
        if (event.getSource().getImmediateSource() instanceof SnowballEntity) {
            
            final LivingEntity target = event.getEntityLiving();
            
            target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 35, 10));
            
            if (HURT_BY_SNOW.contains(target.getType())) {
                
                event.setAmount(event.getAmount() + 1);
            }
        }
    }
}