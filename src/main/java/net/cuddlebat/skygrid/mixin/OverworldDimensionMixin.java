package net.cuddlebat.skygrid.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.cuddlebat.skygrid.world.ModGeneratorTypes;
import net.cuddlebat.skygrid.world.SkygridChunkGenerator;
import net.cuddlebat.skygrid.world.SkygridChunkGeneratorConfig;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSourceConfig;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.level.LevelGeneratorType;

@Mixin(OverworldDimension.class)
public abstract class OverworldDimensionMixin extends Dimension
{
	private OverworldDimensionMixin(World world_1, DimensionType dimensionType_1)
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
			VanillaLayeredBiomeSourceConfig biomes = (BiomeSourceType.VANILLA_LAYERED.getConfig())
				.setLevelProperties(this.world.getLevelProperties())
				.setGeneratorSettings(settings);
			SkygridChunkGenerator generator = ModGeneratorTypes.SKYGRID_CHUNK.create(this.world,
				BiomeSourceType.VANILLA_LAYERED.applyConfig(biomes), settings);
			cir.setReturnValue(generator);
		}
	}
}
