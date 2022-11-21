package claustra01.moreclimate.compat.immersiveengineering.crops;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedPlantHandler;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class RicePlantHandler extends WeightedPlantHandler {

    public static float seedWeight = 0.25f;
    public static float cropWeight = 1f;
    public static int minDrops = 1;
    public static int maxDrops = 2;

    public RicePlantHandler() {
        this.seed = new ComparableItemStack(new ItemStack(FoodInit.seeds, 1, 0), false, false);
        this.soil = new ComparableItemStack(new ItemStack(Blocks.DIRT), false, false);
        this.crop = FoodInit.cropRice;
    }

    @Override
    public ItemStack[] getOutput(ItemStack seed, ItemStack soil, TileEntity tile) {
        int numDrops = rand.nextInt(maxDrops - minDrops) + minDrops;
        WeightedItemStack[] stacks = new WeightedItemStack[] {
                new WeightedItemStack(new ItemStack(FoodInit.seeds, 1, 0), seedWeight),
                new WeightedItemStack(new ItemStack(FoodInit.crops, numDrops, 0), cropWeight)
        };
        return WeightedItemStack.getRandomDrops(rand, stacks);
    }

}
