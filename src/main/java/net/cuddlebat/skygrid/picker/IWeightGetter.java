package net.cuddlebat.skygrid.picker;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

@FunctionalInterface
public interface IWeightGetter
{
	public double get(Chunk chunk, BlockPos pos);
	
	public static IWeightGetter of(double weight)
	{
		return (chunk, pos) -> { return weight; };
	}
	
	public static IWeightGetter ofBounded(double weight, int minY, int maxY)
	{
		return (chunk, pos) -> { return pos.getY() >= minY && pos.getY() <= maxY ? weight : 0; };
	}
}
