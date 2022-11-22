package claustra01.moreclimate.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import claustra01.moreclimate.compat.immersiveengineering.WeightedItemStack;
import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.ClimateCropBase;
import defeatedcrow.hac.core.base.ClimateDoubleCropBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class WeightedPlantHandler implements BelljarHandler.IPlantHandler {

    protected ComparableItemStack seed;
    protected WeightedItemStack[] drops;
    protected Block crop;
    protected ComparableItemStack soil;
    protected Random rand = new Random();

    @Override
    public boolean isCorrectSoil(ItemStack seed, ItemStack soil) {
        return new ComparableItemStack(soil, false, false).equals(this.soil);
    }

    @Override
    public float getGrowthStep(ItemStack seed, ItemStack soil, float growth, TileEntity tile, float fertilizer, boolean render) {
        return 0.003125f * fertilizer;
    }

    @Override
    public ItemStack[] getOutput(ItemStack seed, ItemStack soil, TileEntity tile) {
        return WeightedItemStack.getRandomDrops(rand, drops);
    }

    @Override
    public boolean isValid(ItemStack seed) {
        return new ComparableItemStack(seed, false, false).equals(this.seed);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getRenderSize(ItemStack seed, ItemStack soil, float growth, TileEntity tile) {
        if (crop instanceof ClimateDoubleCropBase && Math.round(7 * growth) >= 4) {
            return .75f;
        }
        return .875f;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState[] getRenderedPlant(ItemStack seed, ItemStack soil, float growth, TileEntity tile) {
        List<IBlockState> states = new ArrayList<IBlockState>();
        states.add(crop.getDefaultState());

        if (crop instanceof ClimateDoubleCropBase && Math.round(7 * growth) >= 4) {
            states.add(crop.getDefaultState());
        }

        IBlockState[] ret = new IBlockState[states.size()];
        for (int i = 0; i < states.size(); i++)
            if (states.get(i) != null)

                if (states.get(i).getBlock() instanceof ClimateCropBase) {
                    ret[i] = (states.get(i).getBlock()).getDefaultState().withProperty(DCState.STAGE4, Math.min(3, Math.round(3 * growth)));
                }

                else if (states.get(i).getBlock() instanceof ClimateDoubleCropBase) {
                    int growLevel = Math.min(7, Math.round(7 * growth));
                    if (Math.round(7 * growth) >= 4) {
                        ret[0] = (states.get(i).getBlock()).getDefaultState().withProperty(DCState.STAGE8, growLevel).withProperty(DCState.DOUBLE, true);
                        ret[1] = (states.get(i).getBlock()).getDefaultState().withProperty(DCState.STAGE8, growLevel);
                    }
                    else {
                        ret[0] = (states.get(i).getBlock()).getDefaultState().withProperty(DCState.STAGE8, growLevel);
                    }
                }

        return ret;

    }

}