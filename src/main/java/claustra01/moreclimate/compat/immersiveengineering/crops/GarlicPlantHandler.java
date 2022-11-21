package claustra01.moreclimate.compat.immersiveengineering.crops;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedPlantHandler;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class GarlicPlantHandler extends WeightedPlantHandler {

    public static float seedWeight = 0.25f;
    public static float cropWeight = 1f;
    public static int numDrops = 1;
    public static ItemStack itemSeeds = new ItemStack(FoodInit.seeds, 1, 12);
    public static ItemStack itemDrops = new ItemStack(FoodInit.crops, numDrops, 14);
    public static ItemStack blockDirt = new ItemStack(Blocks.DIRT);

    public GarlicPlantHandler() {
        this.seed = new ComparableItemStack(itemSeeds, false, false);
        this.soil = new ComparableItemStack(blockDirt, false, false);
        this.crop = FoodInit.cropGarlic;
    }

    @Override
    public ItemStack[] getOutput(ItemStack seed, ItemStack soil, TileEntity tile) {
        WeightedItemStack[] stacks = new WeightedItemStack[] {
                new WeightedItemStack(itemSeeds, seedWeight),
                new WeightedItemStack(itemDrops, cropWeight)
        };
        return WeightedItemStack.getRandomDrops(rand, stacks);
    }

}
