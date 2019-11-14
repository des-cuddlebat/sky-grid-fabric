package net.cuddlebat.skygrid.picker;

import java.util.ArrayList;
import java.util.Random;

public class BinaryWeightedPicker<T>
{
	protected ArrayList<Entry> all = new ArrayList<>();
	protected ArrayList<InnerEntry> inner = new ArrayList<>();
	protected double total = 0;
	
	public T pick(Random rand)
	{
		double roll = total * rand.nextDouble();
		int index = -1;
		int lowerBound = 0;
		int upperBound = inner.size() - 1;
		
		while(lowerBound <= upperBound)
		{
			int middle = (lowerBound + upperBound) / 2;
			if(inner.get(middle).getMinimum() >= roll)
			{
				upperBound = middle - 1;
			}
			else
			{
				index = middle;
				lowerBound = middle + 1;
			}
		}
		
		if(index == -1)
			throw new IllegalStateException("BakedWeightedPicker#pick() failed binary search");
		return inner.get(index).getResult();
	}
	
	public void add(double weight, T result)
	{
		all.add(new Entry(weight, result));
		inner.add(new InnerEntry(total, result));
		total += weight;
	}
	
	public BinaryWeightedPicker<T> with(double weight, T result)
	{
		add(weight, result);
		return this;
	}
	
	public class Entry
	{
		private double weight;
		private T result;
		
		public Entry(double weight, T result)
		{
			super();
			this.weight = weight;
			this.result = result;
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
	
	public class InnerEntry
	{
		private double minimum;
		private T result;
		
		public InnerEntry(double minimum, T result)
		{
			super();
			this.minimum = minimum;
			this.result = result;
		}

		public double getMinimum()
		{
			return minimum;
		}

		public T getResult()
		{
			return result;
		}
	}
}
