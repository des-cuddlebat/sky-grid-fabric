package net.cuddlebat.skygrid;

import net.cuddlebat.skygrid.picker.BinaryWeightedPicker;
import net.cuddlebat.skygrid.picker.IBlockPlacer;
import net.cuddlebat.skygrid.picker.ILootTableSupplier;
import net.cuddlebat.skygrid.picker.ISpawnerSupplier;

public class DimensionPickers
{
	private final BinaryWeightedPicker<IBlockPlacer> placers = new BinaryWeightedPicker<>();
	private final BinaryWeightedPicker<ILootTableSupplier> loot = new BinaryWeightedPicker<>();
	private final BinaryWeightedPicker<ISpawnerSupplier> spawners = new BinaryWeightedPicker<>();
	
	public BinaryWeightedPicker<IBlockPlacer> getPlacers()
	{
		return placers;
	}

	public BinaryWeightedPicker<ILootTableSupplier> getLoot()
	{
		return loot;
	}

	public BinaryWeightedPicker<ISpawnerSupplier> getSpawners()
	{
		return spawners;
	}
}
