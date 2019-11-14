package net.cuddlebat.skygrid.picker;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.Chunk;

/**
 * 
 * Primarily used to place a blockstate at a given position, but
 * it is safe to assume a 3x3x3 cube around given blockpos is within
 * the same chunk and inside the world.
 * @author des
 */
@FunctionalInterface
public interface IBlockPlacer
{
	public void place(IWorld world, Chunk chunk, BlockPos pos, Random rand);
	
	public static IBlockPlacer of(BlockState state)
	{
		return (world, chunk, pos, rand) -> { chunk.setBlockState(pos, state, false); };
	}
	
	public static IBlockPlacer of(Block block)
	{
		return (world, chunk, pos, rand) -> { chunk.setBlockState(pos, block.getDefaultState(), false); };
	}
	
	public static IBlockPlacer ofRandom(BlockState... states)
	{
		return (world, chunk, pos, rand) -> { chunk.setBlockState(pos, states[rand.nextInt(states.length)], false); };
	}
	
	public static IBlockPlacer ofRandom(Block... blocks)
	{
		return (world, chunk, pos, rand) -> { chunk.setBlockState(pos, blocks[rand.nextInt(blocks.length)].getDefaultState(), false); };
	}
	
	public static IBlockPlacer ofDecorated(BlockState state, BlockState on)
	{
		return (world, chunk, pos, rand) ->
		{
			chunk.setBlockState(pos, state, false);
			chunk.setBlockState(pos.offset(Direction.UP), on, false);
		};
	}
	
	public static IBlockPlacer ofDecorated(Block block, Block on)
	{
		return (world, chunk, pos, rand) ->
		{
			chunk.setBlockState(pos, block.getDefaultState(), false);
			chunk.setBlockState(pos.offset(Direction.UP), on.getDefaultState(), false);
		};
	}
	
	public static IBlockPlacer ofRandomDecoration(BlockState state, BlockState... decorations)
	{
		return (world, chunk, pos, rand) ->
		{
			chunk.setBlockState(pos, state, false);
			chunk.setBlockState(pos.offset(Direction.UP), decorations[rand.nextInt(decorations.length)], false);
		};
	}
	
	public static IBlockPlacer ofRandomDecoration(Block block, Block... decorations)
	{
		return (world, chunk, pos, rand) ->
		{
			chunk.setBlockState(pos, block.getDefaultState(), false);
			chunk.setBlockState(pos.offset(Direction.UP), decorations[rand.nextInt(decorations.length)].getDefaultState(), false);
		};
	}
}
