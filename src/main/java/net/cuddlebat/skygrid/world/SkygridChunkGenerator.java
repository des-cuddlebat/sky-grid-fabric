package net.cuddlebat.skygrid.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.cuddlebat.skygrid.GridPopulatorPickers;
import net.cuddlebat.skygrid.picker.IBlockPlacer;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GenerationStep.Carver;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class SkygridChunkGenerator extends ChunkGenerator<SkygridChunkGeneratorConfig>
{
	SkygridChunkGeneratorConfig config;
	
	HashMap<ChunkPos, ArrayList<BlockPos>> chests = new HashMap<>();
	HashMap<ChunkPos, ArrayList<BlockPos>> spawners = new HashMap<>();

	public SkygridChunkGenerator(IWorld world, BiomeSource biomeSourcr,
		SkygridChunkGeneratorConfig config)
	{
		super(world, biomeSourcr, config);
	}

	@Override
	public void buildSurface(Chunk chunk)
	{
		// no op (probs)
	}

	@Override
	public int getSpawnHeight()
	{
		return 127;
	}

	@Override
	public void populateNoise(IWorld world, Chunk chunk)
	{
//		BlockPos.Mutable mutable = new BlockPos.Mutable();
//		Random rand = new Random(world.getSeed() ^ (chunk.getPos().x << 32) + chunk.getPos().z);
//		for(int i = 0; i < 512; i++)
//		{
//			final int x = 1 + 4 * (i % 4);
//			final int z = 1 + 4 * (i / 4 % 4);
//			final int y = 1 + 4 * (i / 16);
//			mutable.set(x, y, z);
//			chunk.setBlockState(mutable, Blocks.STONE.getDefaultState(), false);
//		}
		
		Identifier dim = DimensionType.getId(this.world.getDimension().getType());
		
		System.out.println("populateNoise: " + chunk.getPos());
		long ms = System.currentTimeMillis();

		BlockPos.Mutable pos = new BlockPos.Mutable();
		ArrayList<BlockPos> chests = new ArrayList<>();
		ArrayList<BlockPos> spawners = new ArrayList<>();
		Random rand = new Random(world.getSeed() ^ ((chunk.getPos().x << 32) + chunk.getPos().z));
		for(int i = 511; i >= 0; i--)
		{
			final int x = 1 + 4 * (i % 4);
			final int z = 1 + 4 * (i / 4 % 4);
			final int y = 1 + 4 * (i / 16);
			pos.set(x, y, z);
			IBlockPlacer pick = GridPopulatorPickers.DIMENSIONS.get(dim).getPlacers().pick(rand);
			pick.place(world, chunk, pos, rand);
			if(chunk.getBlockState(pos).getBlock() instanceof ChestBlock)
				chests.add(new BlockPos(pos));
			if(chunk.getBlockState(pos).getBlock() instanceof SpawnerBlock)
				spawners.add(new BlockPos(pos));
		}
		this.chests.put(chunk.getPos(), chests);
		this.spawners.put(chunk.getPos(), spawners);

		ms = System.currentTimeMillis() - ms;
		System.out.println("took " + ms + "ms");
	}

	@Override
	public int getHeightOnGround(int var1, int var2, Type var3)
	{
		// TODO Auto-generated method stub
		return 127;
	}

	@Override
	public void carve(Chunk chunk_1, Carver generationStep$Carver_1)
	{
		// no op
	}

	@Override
	public void generateFeatures(ChunkRegion region)
	{
		Identifier dim = DimensionType.getId(this.world.getDimension().getType());
		
		ChunkPos cp = new ChunkPos(region.getCenterChunkX(), region.getCenterChunkZ());
		Random rand = new Random(world.getSeed() ^ (cp.x << 32) + cp.z);
		for(BlockPos pos : chests.get(cp))
		{
			BlockPos inWorld = pos.add(cp.x * 16, 0, cp.z * 16);
			region.setBlockState(inWorld, Blocks.CHEST.getDefaultState(), 0);
			ChestBlockEntity entity = (ChestBlockEntity) region.getBlockEntity(inWorld);
			entity.setLootTable(GridPopulatorPickers.DIMENSIONS.get(dim).getLoot().pick(rand).get(rand), rand.nextLong());
			
			System.out.println("chest: " + cp + " " + pos);
		}
		
		for(BlockPos pos : spawners.get(cp))
		{
			BlockPos inWorld = pos.add(cp.x * 16, 0, cp.z * 16);
			region.setBlockState(inWorld, Blocks.SPAWNER.getDefaultState(), 0);
			MobSpawnerBlockEntity entity = (MobSpawnerBlockEntity) region.getBlockEntity(inWorld);
			entity.getLogic().setSpawnEntry(GridPopulatorPickers.DIMENSIONS.get(dim).getSpawners().pick(rand).get());
			
			System.out.println("spawn: " + cp + " " + pos);
		}
		chests.remove(cp);
		spawners.remove(cp);
	}

	@Override
	public void setStructureStarts(Chunk chunk_1, ChunkGenerator<?> chunkGenerator_1,
		StructureManager structureManager_1)
	{
		// no op
	}

	@Override
	public void addStructureReferences(IWorld iWorld_1, Chunk chunk_1)
	{
		// no op
	}

	@Override
	public void populateEntities(ChunkRegion chunkRegion_1)
	{
		// no op
	}

}
