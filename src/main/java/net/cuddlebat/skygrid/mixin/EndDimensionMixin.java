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
import net.minecraft.world.biome.source.TheEndBiomeSource;
import net.minecraft.world.biome.source.TheEndBiomeSourceConfig;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.TheEndDimension;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.level.LevelGeneratorType;

@Mixin(TheEndDimension.class)
public abstract class EndDimensionMixin extends Dimension
{
	private EndDimensionMixin(World world_1, DimensionType dimensionType_1)
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
			TheEndBiomeSource biomes = BiomeSourceType.THE_END.applyConfig(((TheEndBiomeSourceConfig)BiomeSourceType.THE_END.getConfig()).setSeed(this.world.getSeed()));
			SkygridChunkGenerator generator = ModGeneratorTypes.SKYGRID_CHUNK.create(this.world, biomes, settings);
			cir.setReturnValue(generator);
		}
	}
}
