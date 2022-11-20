package claustra01.moreclimate.main;

import claustra01.moreclimate.MoreClimate;
import defeatedcrow.hac.core.DCInit;
import net.minecraft.init.Items;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Potions {

	static PotionType prevFreezeLongType;

	public static void load() {

		prevFreezeLongType = new PotionType("dcs.freeze_res_long", new PotionEffect[] {
			new PotionEffect(DCInit.prevFreeze, 9600, 0) }).setRegistryName(MoreClimate.MOD_ID, "dcs.freeze_res_long");
		ForgeRegistries.POTION_TYPES.register(prevFreezeLongType);

		PotionHelper.addMix(DCInit.prevFreezeType, Items.REDSTONE, prevFreezeLongType);

	}

}
