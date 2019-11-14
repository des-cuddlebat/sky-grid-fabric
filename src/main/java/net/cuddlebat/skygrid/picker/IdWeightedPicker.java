package net.cuddlebat.skygrid.picker;

import java.util.HashMap;

import net.minecraft.util.Identifier;

public class IdWeightedPicker<T> extends BinaryWeightedPicker<T>
{
	protected HashMap<Identifier, Integer> identifiers = new HashMap<>();
	
	public void add(Identifier id, double weight, T result)
	{
		identifiers.put(id, inner.size());
		add(weight, result);
	}
	
	public IdWeightedPicker<T> with(Identifier id, double weight, T result)
	{
		add(id, weight, result);
		return this;
	}
	
//	public void changeWeight(Identifier id, double weight, T result)
//	{
//		id = 
//	}
}
