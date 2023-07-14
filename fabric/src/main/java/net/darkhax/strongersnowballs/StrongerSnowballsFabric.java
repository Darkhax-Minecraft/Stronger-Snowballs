package net.darkhax.strongersnowballs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class StrongerSnowballsFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        new StrongerSnowballsCommon(FabricLoader.getInstance().getConfigDir().resolve(Constants.MOD_ID + ".json").toFile());
    }
}