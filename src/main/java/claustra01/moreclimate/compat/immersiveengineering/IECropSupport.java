package claustra01.moreclimate.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import claustra01.moreclimate.compat.immersiveengineering.crops.*;

public class IECropSupport {

    public static void load() {

        BelljarHandler.registerHandler(new RicePlantHandler());
        BelljarHandler.registerHandler(new OnionPlantHandler());
        BelljarHandler.registerHandler(new SpinachPlantHandler());
        BelljarHandler.registerHandler(new TomatoPlantHandler());
        BelljarHandler.registerHandler(new CoffeePlantHandler());
        BelljarHandler.registerHandler(new CottonPlantHandler());
        // BelljarHandler.registerHandler(new LotusPlantHandler()); // texture missing, many drop items
        BelljarHandler.registerHandler(new HerbPlantHandler());
        // BelljarHandler.registerHandler(new SeaweedPlantHandler());
        BelljarHandler.registerHandler(new SoyPlantHandler());
        BelljarHandler.registerHandler(new BeanPlantHandler());
        BelljarHandler.registerHandler(new ChiliPlantHandler());
        BelljarHandler.registerHandler(new GarlicPlantHandler());
        BelljarHandler.registerHandler(new LettucePlantHandler());
        BelljarHandler.registerHandler(new WisteriaPlantHandler());
        BelljarHandler.registerHandler(new GingerPlantHandler());
        BelljarHandler.registerHandler(new GrapePlantHandler());

    }

}