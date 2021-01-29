package net.darkhax.strongersnowballs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class Configuration {
    
    private final ForgeConfigSpec spec;
    
    private final BooleanValue slownessEnabled;
    private final IntValue slownessStrength;
    private final DoubleValue slownessChance;
    private final IntValue slownessDuration;
    
    private final BooleanValue damageEnabled;
    private final DoubleValue damageChance;
    private final DoubleValue damageAmount;
    
    public Configuration() {
        
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        // General Configs
        builder.comment("General settings for the mod.");
        builder.push("general");
        
        // Slowness
        builder.comment("Hitting mobs with snowballs can slow them temporarily.");
        builder.push("slowness");
        
        builder.comment("Should the slowness effect be enabled?");
        this.slownessEnabled = builder.define("enabled", true);
        
        builder.comment("The chance that this happens. 1 = 100%, 0.5 = 50%, 0 = 0% etc.");
        this.slownessChance = builder.defineInRange("chance", 1d, 0d, 1d);
        
        builder.comment("The strength/amplifier of the slowness effect.");
        this.slownessStrength = builder.defineInRange("strength", 10, 0, 128);
        
        builder.comment("The duration of the effect in ticks. 20 ticks = 1 second.");
        this.slownessDuration = builder.defineInRange("duration", 35, 0, Short.MAX_VALUE);
        builder.pop();
        
        // damage
        builder.comment("Mobs in the strongersnowballs:hurt_by_snow can be damaged by snowballs.");
        builder.push("damage");
        
        builder.comment("Should the damage effect be enabled?.");
        this.damageEnabled = builder.define("enabled", true);
        
        builder.comment("The chance that this happens. 1 = 100%, 0.5 = 50%, 0 = 0% etc.");
        this.damageChance = builder.defineInRange("chance", 1d, 0d, 1d);
        
        builder.comment("The amount of damage to do.");
        this.damageAmount = builder.defineInRange("amount", 1d, 0d, Short.MAX_VALUE);
        
        builder.pop();
        this.spec = builder.build();
    }
    
    public ForgeConfigSpec getSpec () {
        
        return this.spec;
    }
    
    public boolean getSlownessEnabled () {
        
        return this.slownessEnabled.get();
    }
    
    public int getSlownessStrength () {
        
        return this.slownessStrength.get();
    }
    
    public double getSlownessChance () {
        
        return this.slownessChance.get();
    }
    
    public int getSlownessDuration () {
        
        return this.slownessDuration.get();
    }
    
    public boolean getDamageEnabled () {
        
        return this.damageEnabled.get();
    }
    
    public double getDamageChance () {
        
        return this.damageChance.get();
    }
    
    public float getDamageAmount () {
        
        return this.damageAmount.get().floatValue();
    }
}