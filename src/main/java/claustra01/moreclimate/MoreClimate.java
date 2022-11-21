package claustra01.moreclimate;

import org.apache.logging.log4j.Logger;

import claustra01.moreclimate.compat.CookingForBlockheads;
import claustra01.moreclimate.compat.Embers;
import claustra01.moreclimate.compat.immersiveengineering.IEClimateSupport;
import claustra01.moreclimate.compat.immersiveengineering.IECropSupport;
import claustra01.moreclimate.compat.PyroTech;
import claustra01.moreclimate.main.Potions;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = MoreClimate.MOD_ID,
        name = MoreClimate.MOD_NAME,
        version = MoreClimate.MOD_MAJOR + "." + MoreClimate.MOD_MINOR + "." + MoreClimate.MOD_BUILD,
        dependencies = MoreClimate.MOD_REQUIRE
)

public class MoreClimate {

    public static final String MOD_ID = "moreclimate";
    public static final String MOD_NAME = "More Climate";
    public static final int MOD_MAJOR = 1;
    public static final int MOD_MINOR = 4;
    public static final int MOD_BUILD = 0;
    public static final String MOD_REQUIRE = "required-after:dcs_lib@[3.9.3,)";

    public static Logger logger;

    public static boolean CookingForBlockheadsCompat;
    public static boolean EmbersCompat;
    public static boolean ImmersiveEngineeringCompat;
    public static boolean PyroTechCompat;
    public static boolean EnablePackMode;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        logger = event.getModLog();

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        CookingForBlockheadsCompat = config.get(MOD_ID, "CookingForBlockheadsCompat", true).getBoolean();
        EmbersCompat = config.get(MOD_ID, "EmbersCompat", true).getBoolean();
        ImmersiveEngineeringCompat = config.get(MOD_ID, "ImmersiveEngineeringCompat", true).getBoolean();
        PyroTechCompat = config.get(MOD_ID, "PyroTechCompat", true).getBoolean();
        EnablePackMode = config.get(MOD_ID, "EnablePackMode", false).getBoolean();
        config.save();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        Potions.load();

        if (ImmersiveEngineeringCompat && Loader.isModLoaded("immersiveengineering") && Loader.isModLoaded("dcs_climate")) {
            IECropSupport.load();
        }

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        if (CookingForBlockheadsCompat && Loader.isModLoaded("cookingforblockheads")) {
            CookingForBlockheads.load();
        }

        if (EmbersCompat && Loader.isModLoaded("embers")) {
            Embers.load();
        }

        if (ImmersiveEngineeringCompat && Loader.isModLoaded("immersiveengineering")) {
            IEClimateSupport.load();
        }

        if (PyroTechCompat && Loader.isModLoaded("pyrotech")) {
            PyroTech.load();
        }

    }

}
