package net.darkhax.strongersnowballs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Config {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Expose
    public float snowballDamage = 3f;

    @Expose
    public SlownessEffect slownessEffect = new SlownessEffect();

    @Expose
    public boolean damageAllMobs = false;

    @Expose
    public FreezeEffect freezeEffect = new FreezeEffect();

    public static class FreezeEffect {

        @Expose
        public boolean enabled = true;

        @Expose
        public float freezeChance = 0.8f;

        @Expose
        public int freezeAmount = 40;

        @Expose
        public float instantFreezeChance = 0f;
    }
    
    public static class SlownessEffect {

        @Expose
        public boolean enabled = true;

        @Expose
        public int durationTicks = 15;

        @Expose
        public int amplifier = 1;

        @Expose
        public float chance = 0.4f;
    }

    public static Config load(File configFile) {

        Config config = new Config();

        // Attempt to load existing config file
        if (configFile.exists()) {

            try (FileReader reader = new FileReader(configFile)) {

                config = GSON.fromJson(reader, Config.class);
                Constants.LOG.info("Loaded config file.");
            }

            catch (Exception e) {

                Constants.LOG.error("Could not read config file {}. Defaults will be used.", configFile.getAbsolutePath());
                Constants.LOG.catching(e);
            }
        }

        else {

            Constants.LOG.info("Creating a new config file at {}.", configFile.getAbsolutePath());
            configFile.getParentFile().mkdirs();
        }

        try (FileWriter writer = new FileWriter(configFile)) {

            GSON.toJson(config, writer);
            Constants.LOG.info("Saved config file.");
        }

        catch (Exception e) {

            Constants.LOG.error("Could not write config file '{}'!", configFile.getAbsolutePath());
            Constants.LOG.catching(e);
        }

        return config;
    }
}
