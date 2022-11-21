package claustra01.moreclimate.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import claustra01.moreclimate.compat.immersiveengineering.crops.RicePlantHandler;

public class IECropSupport {

    public static void load() {

        BelljarHandler.registerHandler(new RicePlantHandler());
    }

}