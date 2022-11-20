package claustra01.moreclimate.compat;

import net.blay09.mods.cookingforblockheads.block.ModBlocks;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.blay09.mods.cookingforblockheads.tile.TileToaster;

import defeatedcrow.hac.api.climate.BlockHeatTierEvent;
import defeatedcrow.hac.api.climate.BlockHeatTierEvent.EventType;
import defeatedcrow.hac.api.climate.DCHeatTier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CookingForBlockheads {

    public static final CookingForBlockheads INSTANCE = new CookingForBlockheads();

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

            if (state.getBlock() == ModBlocks.oven && tile instanceof TileOven && ((TileOven) tile).furnaceBurnTime > 0) {
                if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
                    event.setNewClimate(DCHeatTier.HOT);
                    event.setResult(Result.ALLOW);
                }
            }

            if (state.getBlock() == ModBlocks.fridge) {
                if (event.currentClimate().getTier() <= DCHeatTier.HOT.getTier()) {
                    event.setNewClimate(DCHeatTier.getHeatEnum(Math.max(-1, event.currentClimate().getTier() - 1)));
                    event.setResult(Result.ALLOW);
                }
            }

            if (state.getBlock() == ModBlocks.toaster && tile instanceof TileToaster && ((TileToaster) tile).isActive()) {
                if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.WARM.getTier()) {
                    event.setNewClimate(DCHeatTier.WARM);
                    event.setResult(Result.ALLOW);
                }
            }

        }
    }

}
