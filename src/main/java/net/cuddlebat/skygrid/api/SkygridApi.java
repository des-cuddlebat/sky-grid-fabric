package net.cuddlebat.skygrid.api;

import net.cuddlebat.skygrid.DimensionPickers;
import net.cuddlebat.skygrid.GridPopulatorPickers;
import net.cuddlebat.skygrid.picker.IBlockPlacer;
import net.cuddlebat.skygrid.picker.ILootTableSupplier;
import net.cuddlebat.skygrid.picker.ISpawnerSupplier;
import net.minecraft.world.dimension.DimensionType;

public abstract class SkygridApi
{
	private SkygridApi()
	{
	}
	
	public static void addBlockPlacer(DimensionType dim, double weight, IBlockPlacer placer)
	{
		GridPopulatorPickers.DIMENSIONS.get(DimensionType.getId(dim)).getPlacers().add(weight, placer);
	}
	
	public static void addLoot(DimensionType dim, double weight, ILootTableSupplier loot)
	{
		GridPopulatorPickers.DIMENSIONS.get(DimensionType.getId(dim)).getLoot().add(weight, loot);
	}
	
	public static void addLoot(DimensionType dim, double weight, ISpawnerSupplier spawner)
	{
		GridPopulatorPickers.DIMENSIONS.get(DimensionType.getId(dim)).getSpawners().add(weight, spawner);
	}
	
	/**
	 * Do note that you still must make sure the dimension provides SkygridChunkGenerator
	 * for Skygrid world type. This mod ensures this for all three vanilla dimensions.
	 * @param dim The dimension to add.
	 */
	public static void addDimension(DimensionType dim)
	{
		GridPopulatorPickers.DIMENSIONS.put(DimensionType.getId(dim), new DimensionPickers());
	}
}
