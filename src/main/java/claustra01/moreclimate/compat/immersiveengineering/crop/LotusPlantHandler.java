package claustra01.moreclimate.compat.immersiveengineering.crop;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPLilypad;
import blusunrize.immersiveengineering.api.ComparableItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedItemStack;
import claustra01.moreclimate.compat.immersiveengineering.WeightedPlantHandler;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static biomesoplenty.common.block.BlockBOPLilypad.VARIANT;

public class LotusPlantHandler extends WeightedPlantHandler {

    public static float seedWeight = 0.25f;
    public static float cropWeight = 1f;
    public static int minDrops = 1;
    public static int maxDrops = 2;
    public static int numDrops = new Random().nextInt(maxDrops - minDrops) + minDrops;
    public static ItemStack itemSeeds = new ItemStack(FoodInit.seeds, 1, 6);
    public static ItemStack itemDrops = new ItemStack(FoodInit.petals, numDrops, 0);
    public static ItemStack blockDirt = new ItemStack(Blocks.DIRT);

    public LotusPlantHandler() {
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

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState[] getRenderedPlant(ItemStack seed, ItemStack soil, float growth, TileEntity tile) {
        List<IBlockState> states = new ArrayList<IBlockState>();
        states.add(crop.getDefaultState());

        IBlockState[] ret = new IBlockState[states.size()];
        for (int i = 0; i < states.size(); i++)
            if (states.get(i) != null)

                if (Loader.isModLoaded("biomesoplenty") && states.get(i).getBlock() instanceof BlockBOPLilypad) {
                    if (Math.round(2 * growth) >= 2) {
                        ret[i] = (states.get(i).getBlock()).getDefaultState().withProperty(VARIANT, BlockBOPLilypad.LilypadType.MEDIUM);
                    }
                    else if (Math.round(2 * growth) >= 1) {
                        ret[i] = (states.get(i).getBlock()).getDefaultState().withProperty(VARIANT, BlockBOPLilypad.LilypadType.SMALL);
                    }
                    else {
                        ret[i] = (states.get(i).getBlock()).getDefaultState().withProperty(VARIANT, BlockBOPLilypad.LilypadType.TINY);
                    }
                }
                else if (states.get(i).getBlock() instanceof BlockLilyPad) {
                    ret[i] = (states.get(i).getBlock()).getDefaultState();
                }

        return ret;

    }

}
