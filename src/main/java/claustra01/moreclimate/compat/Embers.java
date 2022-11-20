package claustra01.moreclimate.compat;

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
import teamroots.embers.RegistryManager;
import teamroots.embers.tileentity.TileEntityActivatorTop;
import teamroots.embers.tileentity.TileEntityBeamCannon;
import teamroots.embers.tileentity.TileEntityBoilerTop;
import teamroots.embers.tileentity.TileEntityCinderPlinth;
import teamroots.embers.tileentity.TileEntityEmberBore;
import teamroots.embers.tileentity.TileEntityEmberInjector;
import teamroots.embers.tileentity.TileEntityFurnaceTop;
import teamroots.embers.tileentity.TileEntityHeatCoil;
import teamroots.embers.tileentity.TileEntityInfernoForge;
import teamroots.embers.tileentity.TileEntityMiniBoiler;
import teamroots.embers.tileentity.TileEntityReactionChamber;
import teamroots.embers.tileentity.TileEntityReactor;

public class Embers {

	public static final Embers INSTANCE = new Embers();

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

			if (state.getBlock() == RegistryManager.ember_activator && tile instanceof TileEntityActivatorTop && ((TileEntityActivatorTop) tile).capability.getEmber() > 0) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
					event.setNewClimate(DCHeatTier.HOT);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.boiler && tile instanceof TileEntityBoilerTop && ((TileEntityBoilerTop) tile).capability.getEmber() > 0) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
					event.setNewClimate(DCHeatTier.HOT);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.reactor && tile instanceof TileEntityReactor && ((TileEntityReactor) tile).capability.getEmber() > 0) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.BOIL.getTier()) {
					event.setNewClimate(DCHeatTier.BOIL);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.ember_bore && tile instanceof TileEntityEmberBore && ((TileEntityEmberBore) tile).ticksFueled > 400) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
					event.setNewClimate(DCHeatTier.HOT);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.crystal_cell) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
					event.setNewClimate(DCHeatTier.HOT);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.block_furnace && tile instanceof TileEntityFurnaceTop && ((TileEntityFurnaceTop) tile).getFluidStack() != null) {
				int fluidTemp = ((TileEntityFurnaceTop) tile).getFluidStack().getFluid().getTemperature();
				DCHeatTier fluidHeat = DCHeatTier.getTypeByTemperature(fluidTemp);
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < fluidHeat.getTier()) {
					event.setNewClimate(fluidHeat);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.heat_coil && tile instanceof TileEntityHeatCoil && ((TileEntityHeatCoil) tile).heat > 0) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.mini_boiler && tile instanceof TileEntityMiniBoiler && ((TileEntityMiniBoiler) tile).getGasAmount() > 0) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.BOIL.getTier()) {
					event.setNewClimate(DCHeatTier.BOIL);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.reaction_chamber && tile instanceof TileEntityReactionChamber && ((TileEntityReactionChamber) tile).getGasAmount() > 0) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.BOIL.getTier()) {
					event.setNewClimate(DCHeatTier.BOIL);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.cinder_plinth && tile instanceof TileEntityCinderPlinth && ((TileEntityCinderPlinth) tile).capability.getEmber() > 0) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
					event.setNewClimate(DCHeatTier.HOT);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.beam_cannon && tile instanceof TileEntityBeamCannon && ((TileEntityBeamCannon) tile).capability.getEmber() > 0) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
					event.setNewClimate(DCHeatTier.HOT);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.ember_injector && tile instanceof TileEntityEmberInjector && ((TileEntityEmberInjector) tile).capability.getEmber() > 0) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
					event.setNewClimate(DCHeatTier.HOT);
					event.setResult(Result.ALLOW);
				}
			}

			if (state.getBlock() == RegistryManager.inferno_forge && tile instanceof TileEntityInfernoForge && ((TileEntityInfernoForge) tile).capability.getEmber() > 0) {
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.HOT.getTier()) {
					event.setNewClimate(DCHeatTier.HOT);
					event.setResult(Result.ALLOW);
				}
			}

		}
	}

}
