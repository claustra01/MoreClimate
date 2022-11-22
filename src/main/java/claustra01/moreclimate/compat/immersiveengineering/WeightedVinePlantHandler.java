package claustra01.moreclimate.compat.immersiveengineering;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.food.block.crop.BlockGrape;
import defeatedcrow.hac.food.block.crop.BlockWisteria;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public abstract class WeightedVinePlantHandler extends WeightedPlantHandler {

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState[] getRenderedPlant(ItemStack seed, ItemStack soil, float growth, TileEntity tile) {
        List<IBlockState> states = new ArrayList<IBlockState>();
        states.add(crop.getDefaultState());

        IBlockState[] ret = new IBlockState[states.size()];
        for (int i = 0; i < states.size(); i++)
            if (states.get(i) != null)

                if (states.get(i).getBlock() instanceof BlockWisteria || states.get(i).getBlock() instanceof BlockGrape) {
                    ret[i] = (states.get(i).getBlock()).getDefaultState().withProperty(DCState.STAGE4, Math.min(3, Math.round(3 * growth)));
                }

        return ret;

    }

}