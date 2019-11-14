package net.cuddlebat.skygrid;

import java.util.HashMap;
import java.util.HashSet;

import net.cuddlebat.skygrid.picker.BinaryWeightedPicker;
import net.cuddlebat.skygrid.picker.IBlockPlacer;
import net.cuddlebat.skygrid.picker.ILootTableSupplier;
import net.cuddlebat.skygrid.picker.ISpawnerSupplier;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.loot.LootTables;

public class GridPopulatorPickers
{
	public static final HashMap<Identifier, DimensionPickers> DIMENSIONS = new HashMap<>();
	
//	public static final BinaryWeightedPicker<IBlockPlacer> OVERWORLD = new BinaryWeightedPicker<>();
//	public static final BinaryWeightedPicker<IBlockPlacer> NETHER = new BinaryWeightedPicker<>();
//	public static final BinaryWeightedPicker<IBlockPlacer> THE_END = new BinaryWeightedPicker<>();

//	public static final BinaryWeightedPicker<ILootTableSupplier> OVERWORLD_LOOT = new BinaryWeightedPicker<>();
//	public static final BinaryWeightedPicker<ILootTableSupplier> NETHER_LOOT = new BinaryWeightedPicker<>();
//	public static final BinaryWeightedPicker<ILootTableSupplier> THE_END_LOOT = new BinaryWeightedPicker<>();
	
	public static void doInit()
	{
		Identifier overworldId = DimensionType.getId(DimensionType.OVERWORLD); 
		Identifier netherId = DimensionType.getId(DimensionType.THE_NETHER);
		Identifier endId = DimensionType.getId(DimensionType.THE_END);
		
		DIMENSIONS.put(overworldId, new DimensionPickers()); 
		DIMENSIONS.put(netherId, new DimensionPickers()); 
		DIMENSIONS.put(endId, new DimensionPickers());
		
		BinaryWeightedPicker<IBlockPlacer> overworldPlacers = DIMENSIONS.get(overworldId).getPlacers();		
		BinaryWeightedPicker<IBlockPlacer> netherPlacers = DIMENSIONS.get(netherId).getPlacers();		
		BinaryWeightedPicker<IBlockPlacer> endPlacers = DIMENSIONS.get(endId).getPlacers();
		BinaryWeightedPicker<ILootTableSupplier> overworldLoot = DIMENSIONS.get(overworldId).getLoot();		
		BinaryWeightedPicker<ILootTableSupplier> netherLoot = DIMENSIONS.get(netherId).getLoot();		
		BinaryWeightedPicker<ILootTableSupplier> endLoot = DIMENSIONS.get(endId).getLoot();
		BinaryWeightedPicker<ISpawnerSupplier> overworldSpawners = DIMENSIONS.get(overworldId).getSpawners();		
		BinaryWeightedPicker<ISpawnerSupplier> netherSpawners = DIMENSIONS.get(netherId).getSpawners();		
		BinaryWeightedPicker<ISpawnerSupplier> endSpawners = DIMENSIONS.get(endId).getSpawners();
		
		overworldPlacers.add(4.0, IBlockPlacer.ofRandom(
			Blocks.STONE, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL));
		overworldPlacers.add(1.0, IBlockPlacer.ofRandom(
			Blocks.ANDESITE, Blocks.DIORITE, Blocks.GRANITE));
		overworldPlacers.add(1.0, IBlockPlacer.ofRandom(
			Blocks.ACACIA_LOG, Blocks.BIRCH_LOG, Blocks.DARK_OAK_LOG,
			Blocks.JUNGLE_LOG, Blocks.OAK_LOG, Blocks.SPRUCE_LOG));
		overworldPlacers.add(0.4, IBlockPlacer.ofRandomDecoration(
			Blocks.GRASS_BLOCK,
			Blocks.OAK_SAPLING, Blocks.ACACIA_SAPLING, Blocks.BIRCH_SAPLING,
			Blocks.DARK_OAK_SAPLING, Blocks.JUNGLE_SAPLING, Blocks.SPRUCE_SAPLING));
		overworldPlacers.add(0.2, IBlockPlacer.ofRandom(
			Blocks.BLACK_WOOL, Blocks.BLUE_WOOL, Blocks.BROWN_WOOL, Blocks.CYAN_WOOL,
			Blocks.GRAY_WOOL, Blocks.GREEN_WOOL, Blocks.LIGHT_BLUE_WOOL, Blocks.LIGHT_GRAY_WOOL,
			Blocks.LIME_WOOL, Blocks.MAGENTA_WOOL, Blocks.ORANGE_WOOL, Blocks.PINK_WOOL,
			Blocks.PURPLE_WOOL, Blocks.RED_WOOL, Blocks.WHITE_WOOL, Blocks.YELLOW_WOOL));
		overworldPlacers.add(0.2, IBlockPlacer.of(Blocks.WATER));
		overworldPlacers.add(0.1, IBlockPlacer.of(Blocks.LAVA));
		overworldPlacers.add(0.4, IBlockPlacer.of(Blocks.CLAY));
		overworldPlacers.add(0.2, IBlockPlacer.of(Blocks.TERRACOTTA));
		overworldPlacers.add(0.2, IBlockPlacer.ofRandom(
			Blocks.BLACK_GLAZED_TERRACOTTA, Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.BROWN_GLAZED_TERRACOTTA, Blocks.CYAN_GLAZED_TERRACOTTA,
			Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.GREEN_GLAZED_TERRACOTTA, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA,
			Blocks.LIME_GLAZED_TERRACOTTA, Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.PINK_GLAZED_TERRACOTTA,
			Blocks.PURPLE_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA, Blocks.WHITE_GLAZED_TERRACOTTA, Blocks.YELLOW_GLAZED_TERRACOTTA));
		overworldPlacers.add(0.4, IBlockPlacer.of(Blocks.COAL_ORE));
		overworldPlacers.add(0.2, IBlockPlacer.of(Blocks.IRON_ORE));
		overworldPlacers.add(0.2, IBlockPlacer.of(Blocks.REDSTONE_ORE));
		overworldPlacers.add(0.1, IBlockPlacer.of(Blocks.GOLD_ORE));
		overworldPlacers.add(0.1, IBlockPlacer.of(Blocks.LAPIS_ORE));
		overworldPlacers.add(.02, IBlockPlacer.of(Blocks.DIAMOND_ORE));
		overworldPlacers.add(.02, IBlockPlacer.of(Blocks.EMERALD_ORE));
		overworldPlacers.add(.04, IBlockPlacer.of(Blocks.CHEST));
		overworldPlacers.add(.02, IBlockPlacer.of(Blocks.SPAWNER));
		overworldPlacers.add(.04, IBlockPlacer.of(Blocks.TNT));
		overworldPlacers.add(.04, IBlockPlacer.of(Blocks.BOOKSHELF));
		overworldPlacers.add(0.2, IBlockPlacer.of(Blocks.COBWEB));
		overworldPlacers.add(0.4, IBlockPlacer.of(Blocks.GLASS));
		overworldPlacers.add(0.2, IBlockPlacer.ofRandom(
			Blocks.BLACK_STAINED_GLASS, Blocks.BLUE_STAINED_GLASS, Blocks.BROWN_STAINED_GLASS, Blocks.CYAN_STAINED_GLASS,
			Blocks.GRAY_STAINED_GLASS, Blocks.GREEN_STAINED_GLASS, Blocks.LIGHT_BLUE_STAINED_GLASS, Blocks.LIGHT_GRAY_STAINED_GLASS,
			Blocks.LIME_STAINED_GLASS, Blocks.MAGENTA_STAINED_GLASS, Blocks.ORANGE_STAINED_GLASS, Blocks.PINK_STAINED_GLASS,
			Blocks.PURPLE_STAINED_GLASS, Blocks.RED_STAINED_GLASS, Blocks.WHITE_STAINED_GLASS, Blocks.YELLOW_STAINED_GLASS));
		overworldPlacers.add(.05, IBlockPlacer.ofDecorated(
			Blocks.GRAVEL, Blocks.STONE_PRESSURE_PLATE));
		
		overworldLoot.add(1.0,ILootTableSupplier.of(
			LootTables.ABANDONED_MINESHAFT_CHEST));		
		overworldLoot.add(1.0,ILootTableSupplier.of(
			LootTables.DESERT_PYRAMID_CHEST));		
		overworldLoot.add(1.0,ILootTableSupplier.of(
			LootTables.JUNGLE_TEMPLE_CHEST));
		overworldLoot.add(1.0,ILootTableSupplier.ofRandom(
			LootTables.VILLAGE_ARMORER_CHEST, LootTables.VILLAGE_BUTCHER_CHEST,
			LootTables.VILLAGE_CARTOGRAPHER_CHEST, LootTables.VILLAGE_FLETCHER_CHEST,
			LootTables.VILLAGE_TOOLSMITH_CHEST, LootTables.VILLAGE_WEAPONSMITH_CHEST));
		
		overworldSpawners.add(1.0, ISpawnerSupplier.ofMonster(EntityType.ZOMBIE));
		overworldSpawners.add(1.0, ISpawnerSupplier.ofMonster(EntityType.SKELETON));
		overworldSpawners.add(1.0, ISpawnerSupplier.ofMonster(EntityType.SPIDER));
		overworldSpawners.add(0.4, ISpawnerSupplier.ofMonster(EntityType.SLIME));
		overworldSpawners.add(0.4, ISpawnerSupplier.ofMonster(EntityType.PIG));
		overworldSpawners.add(0.4, ISpawnerSupplier.ofMonster(EntityType.COW));
		overworldSpawners.add(0.4, ISpawnerSupplier.ofMonster(EntityType.SHEEP));
		overworldSpawners.add(0.4, ISpawnerSupplier.ofMonster(EntityType.CHICKEN));
		
		netherPlacers.add(1.0, IBlockPlacer.of(Blocks.NETHERRACK));
		netherPlacers.add(1.0, IBlockPlacer.of(Blocks.SOUL_SAND));
		netherPlacers.add(0.4, IBlockPlacer.of(Blocks.NETHER_BRICKS));
		netherPlacers.add(0.2, IBlockPlacer.of(Blocks.NETHER_QUARTZ_ORE));
		netherPlacers.add(0.2, IBlockPlacer.of(Blocks.GLOWSTONE));
		netherPlacers.add(0.2, IBlockPlacer.of(Blocks.GRAVEL));
		netherPlacers.add(0.1, IBlockPlacer.of(Blocks.LAVA));
		netherPlacers.add(0.1, IBlockPlacer.of(Blocks.QUARTZ_BLOCK));
		netherPlacers.add(.05, IBlockPlacer.ofDecorated(
			Blocks.SOUL_SAND, Blocks.NETHER_WART));
		netherPlacers.add(.01, IBlockPlacer.of(Blocks.CHEST));
		netherPlacers.add(.01, IBlockPlacer.of(Blocks.SPAWNER));
		
		netherLoot.add(1.0, ILootTableSupplier.of(LootTables.NETHER_BRIDGE_CHEST));

		netherSpawners.add(1.0, ISpawnerSupplier.ofMonster(EntityType.WITHER_SKELETON));
		netherSpawners.add(1.0, ISpawnerSupplier.ofMonster(EntityType.ZOMBIE_PIGMAN));
		netherSpawners.add(0.4, ISpawnerSupplier.ofMonster(EntityType.MAGMA_CUBE));
		netherSpawners.add(0.4, ISpawnerSupplier.ofMonster(EntityType.CHICKEN));

		endPlacers.add(1.0, IBlockPlacer.of(Blocks.END_STONE));
		endPlacers.add(0.4, IBlockPlacer.of(Blocks.OBSIDIAN));
		endPlacers.add(0.1, IBlockPlacer.ofDecorated(
			Blocks.END_STONE, Blocks.CHORUS_FLOWER));
		endPlacers.add(.01, IBlockPlacer.ofRandom(
			Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX,
			Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX,
			Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX,
			Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX));
		endPlacers.add(.01, IBlockPlacer.of(Blocks.CHEST));
		endPlacers.add(.01, IBlockPlacer.of(Blocks.SPAWNER));

		endLoot.add(1.0, ILootTableSupplier.of(LootTables.END_CITY_TREASURE_CHEST));
		
		endSpawners.add(1.0, ISpawnerSupplier.ofMonster(EntityType.ENDERMAN));
		endSpawners.add(0.4, ISpawnerSupplier.ofMonster(EntityType.ENDERMITE));
	}
	
//	private static void placeLootChest(IWorld world, Chunk chunk, BlockPos pos, Random rand)
//	{
//		BlockPos inWorld = pos.add(chunk.getPos().x, 0, chunk.getPos().z);
//		world.setBlockState(pos, Blocks.CHEST.getDefaultState()
//			.with(ChestBlock.FACING, Direction.fromHorizontal(rand.nextInt(4))), 0);
//		ChestBlockEntity entity = new ChestBlockEntity();
//		ChestBlockEntity entity = (ChestBlockEntity) world.getBlockEntity(inWorld);
//		entity.setLootTable(LootTables.ABANDONED_MINESHAFT_CHEST, rand.nextLong());
//		chunk.setBlockEntity(pos, entity);
//		ChestBlockEntity entity = (ChestBlockEntity) chunk.getBlockEntity(pos);
//		((ChestBlockEntity)chunk.getBlockEntity(pos));
//		CompoundTag compound = new CompoundTag();
//		compound.putString("LootTable", LootTables.ABANDONED_MINESHAFT_CHEST.toString());
//		chunk.getBlockEntity(pos).fromTag(compound);
//	}
}
