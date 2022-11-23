package claustra01.moreclimate.compat;

import claustra01.moreclimate.MoreClimate;
import defeatedcrow.hac.api.blockstate.DCState;
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

        if (MoreClimate.BonsaiTreesCompat) {

            TreeTypeSimple treeTypeLemon = new TreeTypeSimple(MoreClimate.MOD_ID + ":lemon", new ItemStack(FoodInit.saplings, 1, 0));
            TreeTypeSimple treeTypeOlive = new TreeTypeSimple(MoreClimate.MOD_ID + ":olive", new ItemStack(FoodInit.saplings, 1, 1));
            TreeTypeSimple treeTypeMorus = new TreeTypeSimple(MoreClimate.MOD_ID + ":morus", new ItemStack(FoodInit.saplings, 1, 3));
            TreeTypeSimple treeTypeWalnut = new TreeTypeSimple(MoreClimate.MOD_ID + ":walnut", new ItemStack(FoodInit.saplings2, 1, 0));
            TreeTypeSimple treeTypeDate = new TreeTypeSimple(MoreClimate.MOD_ID + ":date", new ItemStack(FoodInit.saplings2, 1, 1));

            treeTypeLemon.addDrop(new ItemStack(FoodInit.saplings, saplingAmount, 0), saplingChance);
            treeTypeLemon.addDrop(new ItemStack(Items.STICK, stickAmount), stickChance);
            treeTypeLemon.addDrop(new ItemStack(Blocks.LOG, logAmount, 0), logChance);
            treeTypeLemon.addDrop(new ItemStack(FoodInit.leavesLemon, leafAmount), leafChance);
            treeTypeLemon.addDrop(new ItemStack(FoodInit.crops, fruitAmount, 6), fruitChance);

            treeTypeOlive.addDrop(new ItemStack(FoodInit.saplings, saplingAmount, 1), saplingChance);
            treeTypeOlive.addDrop(new ItemStack(Items.STICK, stickAmount), stickChance);
            treeTypeOlive.addDrop(new ItemStack(Blocks.LOG, logAmount, 0), logChance);
            treeTypeOlive.addDrop(new ItemStack(FoodInit.leavesOlive, leafAmount), leafChance);
            treeTypeOlive.addDrop(new ItemStack(FoodInit.crops, fruitAmount, 7), fruitChance);

            treeTypeMorus.addDrop(new ItemStack(FoodInit.saplings, saplingAmount, 3), saplingChance);
            treeTypeMorus.addDrop(new ItemStack(Items.STICK, stickAmount), stickChance);
            treeTypeMorus.addDrop(new ItemStack(Blocks.LOG, logAmount, 0), logChance);
            treeTypeMorus.addDrop(new ItemStack(FoodInit.leavesMorus, leafAmount), leafChance);
            treeTypeMorus.addDrop(new ItemStack(FoodInit.crops, fruitAmount, 11), fruitChance);

            treeTypeWalnut.addDrop(new ItemStack(FoodInit.saplings2, saplingAmount, 0), saplingChance);
            treeTypeWalnut.addDrop(new ItemStack(Items.STICK, stickAmount), stickChance);
            treeTypeWalnut.addDrop(new ItemStack(Blocks.LOG2, logAmount, 1), logChance);
            treeTypeWalnut.addDrop(new ItemStack(FoodInit.leavesWalnut, leafAmount), leafChance);
            treeTypeWalnut.addDrop(new ItemStack(FoodInit.crops, fruitAmount, 16), fruitChance);

            treeTypeDate.addDrop(new ItemStack(FoodInit.saplings2, saplingAmount, 1), saplingChance);
            treeTypeDate.addDrop(new ItemStack(Items.STICK, stickAmount), stickChance);
            treeTypeDate.addDrop(new ItemStack(Blocks.LOG, logAmount, 3), logChance);
            treeTypeDate.addDrop(new ItemStack(FoodInit.leavesDates, leafAmount), leafChance);
            treeTypeDate.addDrop(new ItemStack(FoodInit.crops, fruitAmount, 17), fruitChance);

            /*
            registry.registerTreeType(this, treeTypeLemon);
            registry.registerTreeType(this, treeTypeOlive);
            registry.registerTreeType(this, treeTypeMorus);
            registry.registerTreeType(this, treeTypeWalnut);
            registry.registerTreeType(this, treeTypeDate);
            */

        }

    }

    @Override
    public void generateTree(IBonsaiTreeType type, World world, BlockPos pos, Random rand) {

        if (MoreClimate.BonsaiTreesCompat) {

            IBlockState saplingStateLemon = FoodInit.saplings.getDefaultState().withProperty(DCState.STAGE4, 0);
            IBlockState saplingStateOlive = FoodInit.saplings.getDefaultState().withProperty(DCState.STAGE4, 1);
            IBlockState saplingStateMorus = FoodInit.saplings.getDefaultState().withProperty(DCState.STAGE4, 3);
            IBlockState saplingStateWalnut = FoodInit.saplings2.getDefaultState().withProperty(DCState.STAGE4, 0);
            IBlockState saplingStateDate = FoodInit.saplings2.getDefaultState().withProperty(DCState.STAGE4, 1);

            world.setBlockState(pos, saplingStateLemon);
            world.setBlockState(pos, saplingStateOlive);
            world.setBlockState(pos, saplingStateMorus);
            world.setBlockState(pos, saplingStateWalnut);
            world.setBlockState(pos, saplingStateDate);

            BlockSapling saplingBlock = (BlockSapling) FoodInit.saplings;
            BlockSapling saplingBlock2 = (BlockSapling) FoodInit.saplings2;

            /*
            saplingBlock.generateTree(world, pos, saplingStateLemon, rand);
            saplingBlock.generateTree(world, pos, saplingStateOlive, rand);
            saplingBlock.generateTree(world, pos, saplingStateMorus, rand);
            saplingBlock2.generateTree(world, pos, saplingStateWalnut, rand);
            saplingBlock2.generateTree(world, pos, saplingStateDate, rand);
            */

        }

    }
}
