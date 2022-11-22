package claustra01.moreclimate.compat.immersiveengineering;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.food.block.crop.BlockSeaweed;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public abstract class WeightedUnderWaterPlantHandler extends WeightedPlantHandler {

    @Override
    @SideOnly(Side.CLIENT)
    public float getRenderSize(ItemStack seed, ItemStack soil, float growth, TileEntity tile) {
        if (crop instanceof BlockSeaweed && Math.round(3 * growth) >= 1) {
            return .75f;
        }
        return .875f;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState[] getRenderedPlant(ItemStack seed, ItemStack soil, float growth, TileEntity tile) {
        List<IBlockState> states = new ArrayList<IBlockState>();
        states.add(crop.getDefaultState());

        if (crop instanceof BlockSeaweed && Math.round(3 * growth) >= 3) {
            states.add(crop.getDefaultState());
        }

        IBlockState[] ret = new IBlockState[states.size()];
        for (int i = 0; i < states.size(); i++)
            if (states.get(i) != null)

                if (states.get(i).getBlock() instanceof BlockLilyPad) {
                    ret[i] = (states.get(i).getBlock()).getDefaultState();
                }

                else if (states.get(i).getBlock() instanceof BlockSeaweed) {
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
                }

        return ret;

    }

}