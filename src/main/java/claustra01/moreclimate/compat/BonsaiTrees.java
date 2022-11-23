package claustra01.moreclimate.compat;

import claustra01.moreclimate.MoreClimate;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.dave.bonsaitrees.api.*;

import java.util.Random;

import static org.dave.bonsaitrees.api.BonsaiDropChances.*;
import static org.dave.bonsaitrees.api.BonsaiDropChances.fruitChance;

@BonsaiIntegration(mod = "dcs_climate")
public class BonsaiTrees implements IBonsaiIntegration {
    @Override
    public void registerTrees(ITreeTypeRegistry registry) {

        TreeTypeSimple treeTypeLemon = new TreeTypeSimple(MoreClimate.MOD_ID + ":lemon", new ItemStack(FoodInit.saplings, 1, 0));

        treeTypeLemon.addDrop(new ItemStack(FoodInit.saplings, saplingAmount, 0), saplingChance);
        treeTypeLemon.addDrop(new ItemStack(Items.STICK, stickAmount), stickChance);
        treeTypeLemon.addDrop(new ItemStack(Blocks.LOG, logAmount, 0), logChance);
        treeTypeLemon.addDrop(new ItemStack(FoodInit.leavesLemon, stickAmount), leafChance);
        treeTypeLemon.addDrop(new ItemStack(FoodInit.crops, fruitAmount, 6), fruitChance);

        if (MoreClimate.BonsaiTreesCompat) {
            registry.registerTreeType(this, treeTypeLemon);
        }

    }

    @Override
    public void generateTree(IBonsaiTreeType type, World world, BlockPos pos, Random rand) {

        IBlockState saplingStateLemon = FoodInit.saplings.getStateFromMeta(0);
        world.setBlockState(pos, saplingStateLemon);
        BlockSapling saplingBlock = (BlockSapling) FoodInit.saplings;

        if (MoreClimate.BonsaiTreesCompat) {
            saplingBlock.generateTree(world, pos, saplingStateLemon, rand);
        }

    }
}
