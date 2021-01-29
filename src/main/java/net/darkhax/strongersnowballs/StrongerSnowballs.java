package net.darkhax.strongersnowballs;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@Mod("strongersnowballs")
public class StrongerSnowballs {
    
    public static final INamedTag<EntityType<?>> HURT_BY_SNOW = EntityTypeTags.getTagById("strongersnowballs:hurt_by_snow");
    
    public StrongerSnowballs() {
        
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
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