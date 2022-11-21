package claustra01.moreclimate.compat.immersiveengineering;


import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.Random;

public class WeightedItemStack
{
    private final ItemStack stack;
    private final float weight;

    public static ItemStack[] getRandomDrops(final Random random, final WeightedItemStack[] weightedStacks) {
        final NonNullList<ItemStack> drops = NonNullList.create();
        for (final WeightedItemStack stack : weightedStacks) {
            for (float chance = stack.weight; chance > 0.0f; --chance) {
                if (chance > 1.0f) {
                    drops.add(stack.getStack());
                }
                else if (random.nextFloat() <= chance) {
                    drops.add(stack.getStack());
                }
            }
        }
        return (ItemStack[])drops.toArray(new ItemStack[0]);
    }

    public WeightedItemStack(final ItemStack stackIn, final float weightIn) {
        this.stack = stackIn;
        this.weight = weightIn;
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public float getWeight() {
        return this.weight;
    }

    public float getPercent() {
        return this.weight * 100.0f;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WeightedItemStack)) {
            return false;
        }
        final WeightedItemStack otherWIS = (WeightedItemStack)obj;
        return (this.stack == otherWIS.stack || (this.stack != null && this.stack.equals(otherWIS.stack))) && Float.floatToIntBits(this.weight) == Float.floatToIntBits(otherWIS.weight);
    }
}