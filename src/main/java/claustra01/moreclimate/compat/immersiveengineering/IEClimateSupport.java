package claustra01.moreclimate.compat.immersiveengineering;

import defeatedcrow.hac.api.climate.BlockHeatTierEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class IEClimateSupport {

    public static final IEClimateSupport INSTANCE = new IEClimateSupport();

    public static void load() {

        MinecraftForge.EVENT_BUS.register(INSTANCE);
    }

    @SubscribeEvent
    public void onBlockTempEvent(BlockHeatTierEvent event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        if (world != null && pos != null) {
            IBlockState state = world.getBlockState(pos);
            TileEntity tile = world.getTileEntity(pos);

        }
    }

}
