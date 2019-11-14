package net.cuddlebat.skygrid.world;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;

public class GenFactoryInvocationHandler<C extends ChunkGeneratorConfig, T extends ChunkGenerator<C>> implements InvocationHandler
{
	private FactoryFunc<C, T> factory;
	
	public GenFactoryInvocationHandler(FactoryFunc<C, T> factory)
	{
		super();
		this.factory = factory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		return factory.create((World)args[0], (BiomeSource)args[1], (C)args[2]);
	}

	public interface FactoryFunc<C extends ChunkGeneratorConfig, T extends ChunkGenerator<C>>
	{
		public T create(World world, BiomeSource biomeSource, C config);
	}
}
