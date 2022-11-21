package claustra01.moreclimate.compat.immersiveengineering.crops;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedPlantHandler;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TomatoPlantHandler extends WeightedPlantHandler {

    public static float seedWeight = 0.25f;
    public static float cropWeight = 1f;
    public static int minDrops = 1;
    public static int maxDrops = 2;
    public static int numDrops = new Random().nextInt(maxDrops - minDrops) + minDrops;
    public static ItemStack itemSeeds = new ItemStack(FoodInit.seeds, 1, 3);
    public static ItemStack itemDrops = new ItemStack(FoodInit.crops, numDrops, 3);
    public static ItemStack blockDirt = new ItemStack(Blocks.DIRT);

    public TomatoPlantHandler() {
        this.seed = new ComparableItemStack(itemSeeds, false, false);
        this.soil = new ComparableItemStack(blockDirt, false, false);
        this.crop = FoodInit.cropTomato;
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
