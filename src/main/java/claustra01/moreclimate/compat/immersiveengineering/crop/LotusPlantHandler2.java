package claustra01.moreclimate.compat.immersiveengineering.crop;

import biomesoplenty.api.block.BOPBlocks;
import blusunrize.immersiveengineering.api.ComparableItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedUnderWaterPlantHandler;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.Loader;

public class LotusPlantHandler2 extends WeightedUnderWaterPlantHandler {

    public static float seedWeight = 0f;
    public static float cropWeight = 1f;
    public static int numDrops = 1;
    public static ItemStack itemSeeds = new ItemStack(FoodInit.petals, 1, 0);
    public static ItemStack itemDrops = new ItemStack(FoodInit.crops, numDrops, 10);
    public static ItemStack blockDirt = new ItemStack(Blocks.DIRT);

    public LotusPlantHandler2() {
        this.seed = new ComparableItemStack(itemSeeds, false, false);
        this.soil = new ComparableItemStack(blockDirt, false, false);
        this.crop = Loader.isModLoaded("biomesoplenty") ? BOPBlocks.waterlily : Blocks.WATERLILY;
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
