package claustra01.moreclimate.compat.immersiveengineering.crop;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedPlantHandler;
import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static biomesoplenty.common.block.BlockBOPLilypad.VARIANT;

public class SeaweedPlantHandler extends WeightedPlantHandler {

    public static float seedWeight = 0.25f;
    public static float cropWeight = 1f;
    public static int minDrops = 1;
    public static int maxDrops = 2;
    public static int numDrops = new Random().nextInt(maxDrops - minDrops) + minDrops;
    public static ItemStack itemSeeds = new ItemStack(FoodInit.seeds, 1, 8);
    public static ItemStack itemDrops = new ItemStack(FoodInit.seeds, numDrops, 8);
    public static ItemStack blockDirt = new ItemStack(Blocks.DIRT);

    public SeaweedPlantHandler() {
        this.seed = new ComparableItemStack(itemSeeds, false, false);
        this.soil = new ComparableItemStack(blockDirt, false, false);
        this.crop = FoodInit.cropSeaweed;
    }

    @Override
    public ItemStack[] getOutput(ItemStack seed, ItemStack soil, TileEntity tile) {
        WeightedItemStack[] stacks = new WeightedItemStack[] {
                new WeightedItemStack(itemSeeds, seedWeight),
                new WeightedItemStack(itemDrops, cropWeight)
        };
        return WeightedItemStack.getRandomDrops(rand, stacks);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getRenderSize(ItemStack seed, ItemStack soil, float growth, TileEntity tile) {
        if (Math.round(3 * growth) >= 1) {
            return .75f;
        }
        return .875f;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState[] getRenderedPlant(ItemStack seed, ItemStack soil, float growth, TileEntity tile) {
        List<IBlockState> states = new ArrayList<IBlockState>();
        states.add(crop.getDefaultState());

        if (Math.round(3 * growth) >= 3) {
            states.add(crop.getDefaultState());
        }

        IBlockState[] ret = new IBlockState[states.size()];
        for (int i = 0; i < states.size(); i++)
            if (states.get(i) != null)

                if (Math.round(3 * growth) >= 3) {
                    ret[0] = (states.get(i).getBlock()).getDefaultState().withProperty(DCState.STAGE4, 2);
                    ret[1] = (states.get(i).getBlock()).getDefaultState().withProperty(DCState.STAGE4, 3);
                }
                else if (Math.round(3 * growth) >= 2) {
                    ret[0] = (states.get(i).getBlock()).getDefaultState().withProperty(DCState.STAGE4, 1);
                }
                else if (Math.round(3 * growth) >= 1) {
                    ret[0] = (states.get(i).getBlock()).getDefaultState().withProperty(DCState.STAGE4, 3);
                }
                else {
                    ret[0] = (states.get(i).getBlock()).getDefaultState().withProperty(DCState.STAGE4, 0);
                }

        return ret;

    }

}
