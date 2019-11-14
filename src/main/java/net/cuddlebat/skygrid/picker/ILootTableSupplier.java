package net.cuddlebat.skygrid.picker;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.Chunk;

/**
 * 
 * @author des
 * Primarily used to place a blockstate at a given position, but
 * it is safe to assume a 3x3x3 cube around given blockpos is within
 * the same chunk and inside the world.
 */
@FunctionalInterface
public interface ILootTableSupplier
{
	public Identifier get(Random rand);
	
	public static ILootTableSupplier of(Identifier table)
	{
		return (rand) -> { return table; };
	}
	
	public static ILootTableSupplier ofRandom(Identifier... tables)
	{
		return (rand) -> { return tables[rand.nextInt(tables.length)]; };
	}
}
