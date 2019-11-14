package net.cuddlebat.skygrid.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.cuddlebat.skygrid.world.ModGeneratorTypes;
import net.cuddlebat.skygrid.world.SkygridChunkGenerator;
import net.cuddlebat.skygrid.world.SkygridChunkGeneratorConfig;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSourceConfig;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.TheNetherDimension;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.level.LevelGeneratorType;

@Mixin(TheNetherDimension.class)
public abstract class NetherDimensionMixin extends Dimension
{
	private NetherDimensionMixin(World world_1, DimensionType dimensionType_1)
	{
		super(world_1, dimensionType_1);
	}

	@Inject(at = @At("HEAD"), cancellable = true, method = "createChunkGenerator")
	private void createChunkGeneratorMixin(CallbackInfoReturnable<ChunkGenerator<? extends ChunkGeneratorConfig>> cir)
	{
		LevelGeneratorType generatorType = this.world.getGeneratorType();
		if (generatorType == ModGeneratorTypes.SKYGRID_WORLD)
		{
			SkygridChunkGeneratorConfig settings = ModGeneratorTypes.SKYGRID_CHUNK.createSettings();
			FixedBiomeSource biomes = BiomeSourceType.FIXED.applyConfig(((FixedBiomeSourceConfig)BiomeSourceType.FIXED.getConfig()).setBiome(Biomes.NETHER));
			SkygridChunkGenerator generator = ModGeneratorTypes.SKYGRID_CHUNK.create(this.world, biomes, settings);
			cir.setReturnValue(generator);
		}
	}
}
