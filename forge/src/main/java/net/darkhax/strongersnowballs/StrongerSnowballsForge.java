package net.darkhax.strongersnowballs;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Constants.MOD_ID)
public class StrongerSnowballsForge {

    public StrongerSnowballsForge() {

        new StrongerSnowballsCommon(FMLPaths.CONFIGDIR.get().resolve(Constants.MOD_ID + ".json").toFile());
    }
}