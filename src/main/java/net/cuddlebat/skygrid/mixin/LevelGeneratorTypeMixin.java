package net.cuddlebat.skygrid.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.cuddlebat.skygrid.world.ModGeneratorTypes;
import net.cuddlebat.skygrid.world.SkygridChunkGenerator;
import net.cuddlebat.skygrid.world.SkygridChunkGeneratorConfig;
import net.fabricmc.loader.game.MinecraftGameProvider;
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

@Mixin(LevelGeneratorType.class)
public abstract class LevelGeneratorTypeMixin
{
	@Inject(at = @At("RETURN"), method = "<clinit>")
	private static void clinitMixin(CallbackInfo ci)
	{
		try
		{
			ModGeneratorTypes.initLevelGenType();
			System.out.println("Yay for " + LevelGeneratorType.TYPES[7].getName());
		} catch (Throwable e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
