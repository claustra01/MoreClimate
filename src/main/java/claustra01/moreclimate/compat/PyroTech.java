package claustra01.moreclimate.compat;

import com.codetaylor.mc.pyrotech.modules.ignition.ModuleIgnition;
import com.codetaylor.mc.pyrotech.modules.ignition.block.BlockTorchFiber;
import com.codetaylor.mc.pyrotech.modules.ignition.block.BlockTorchStone;
import com.codetaylor.mc.pyrotech.modules.tech.basic.ModuleTechBasic;
import com.codetaylor.mc.pyrotech.modules.tech.basic.block.BlockCampfire;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.ModuleTechBloomery;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.block.BlockPileSlag;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.tile.TileBloomery;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.tile.TileWitherForge;
import com.codetaylor.mc.pyrotech.modules.tech.machine.ModuleTechMachine;
import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.TileBrickCrucible;
import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.TileBrickKiln;
import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.TileBrickOven;
import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.TileBrickSawmill;
import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.TileStoneCrucible;
import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.TileStoneKiln;
import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.TileStoneOven;
import com.codetaylor.mc.pyrotech.modules.tech.machine.tile.TileStoneSawmill;

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

public class PyroTech {

	public static final PyroTech INSTANCE = new PyroTech();

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

			if (state.getBlock() == ModuleIgnition.Blocks.TORCH_FIBER && state.getValue(BlockTorchFiber.TYPE) == BlockTorchFiber.EnumType.LIT) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
					event.setNewClimate(DCHeatTier.HOT);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleIgnition.Blocks.TORCH_STONE && state.getValue(BlockTorchStone.TYPE) == BlockTorchStone.EnumType.LIT) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
					event.setNewClimate(DCHeatTier.HOT);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechBasic.Blocks.CAMPFIRE && state.getValue(BlockCampfire.VARIANT) == BlockCampfire.EnumType.LIT) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechBloomery.Blocks.BLOOMERY && tile instanceof TileBloomery && ((TileBloomery) tile).isActive()) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.KILN.getTier()) {
					event.setNewClimate(DCHeatTier.KILN);
					event.setResult(Result.ALLOW);
				}
			}
		
			if (state.getBlock() == ModuleTechBloomery.Blocks.WITHER_FORGE && tile instanceof TileWitherForge && ((TileWitherForge) tile).isActive()) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.SMELTING.getTier()) {
					event.setNewClimate(DCHeatTier.SMELTING);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechBloomery.Blocks.BLOOM) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.KILN.getTier()) {
					event.setNewClimate(DCHeatTier.KILN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechBloomery.Blocks.PILE_SLAG && state.getValue(BlockPileSlag.MOLTEN)) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechMachine.Blocks.STONE_KILN && tile instanceof TileStoneKiln && ((TileStoneKiln) tile).workerIsActive()) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechMachine.Blocks.STONE_OVEN && tile instanceof TileStoneOven && ((TileStoneOven) tile).workerIsActive()) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechMachine.Blocks.STONE_SAWMILL && tile instanceof TileStoneSawmill && ((TileStoneSawmill) tile).workerIsActive()) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechMachine.Blocks.STONE_CRUCIBLE && tile instanceof TileStoneCrucible && ((TileStoneCrucible) tile).workerIsActive()) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechMachine.Blocks.BRICK_KILN && tile instanceof TileBrickKiln && ((TileBrickKiln) tile).workerIsActive()) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechMachine.Blocks.BRICK_OVEN && tile instanceof TileBrickOven && ((TileBrickOven) tile).workerIsActive()) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechMachine.Blocks.BRICK_SAWMILL && tile instanceof TileBrickSawmill && ((TileBrickSawmill) tile).workerIsActive()) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == ModuleTechMachine.Blocks.BRICK_CRUCIBLE && tile instanceof TileBrickCrucible && ((TileBrickCrucible) tile).workerIsActive()) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

		}
	}

}
