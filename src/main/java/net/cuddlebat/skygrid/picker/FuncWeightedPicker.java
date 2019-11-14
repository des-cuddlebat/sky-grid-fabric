package net.cuddlebat.skygrid.picker;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class FuncWeightedPicker<T>
{
	private ArrayList<Entry> all = new ArrayList<>();
	
	public T pick(Chunk chunk, BlockPos pos, Random rand)
	{
		double roll = 0.0;
		for(Entry entry : all)
		{
			roll += entry.getWeight(chunk, pos); 
		}
		roll *= rand.nextDouble();
		for(Entry entry : all)
		{
			roll -= entry.getWeight(chunk, pos);
			if(roll <= 0)
				return entry.getResult();
		}
		throw new IllegalStateException("WeightedPicker failed to pick an item, "
			+ "which is mathematically impossible, but compiler needs this exception.");
	}
	
	public void add(IWeightGetter weight, T result)
	{
		all.add(new Entry(weight, result));
	}
	
	public FuncWeightedPicker<T> with(IWeightGetter weight, T result)
	{
		add(weight, result);
		return this;
	}
	
	public class Entry
	{
		private IWeightGetter weight;
		private T result;
		
		public Entry(IWeightGetter weight, T result)
		{
			super();
			this.weight = weight;
			this.result = result;
		}

		public double getWeight(Chunk chunk, BlockPos pos)
		{
			return weight.get(chunk, pos);
		}

		public T getResult()
		{
			return result;
		}
	}
	
	public class BakedEntry
	{
		private double weight;
		private T result;
		
		public BakedEntry(Entry entry, Chunk chunk, BlockPos pos)
		{
			super();
			this.weight = entry.getWeight(chunk, pos);
			this.result = entry.getResult();
		}

		public double getWeight()
		{
			return weight;
		}

		public T getResult()
		{
			return result;
		}
	}
}
