package net.cuddlebat.skygrid.world;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

import net.cuddlebat.skygrid.Skygrid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.level.LevelGeneratorType;

public class ModGeneratorTypes
{
	public static ChunkGeneratorType<SkygridChunkGeneratorConfig, SkygridChunkGenerator> SKYGRID_CHUNK;
	public static LevelGeneratorType SKYGRID_WORLD;
	
//	public static void init() throws Throwable
//	{
//		initChunkGenType();
//		initLevelGenType();
//	}
	
	@SuppressWarnings({
		"unchecked", "rawtypes"
	})
	public static void initChunkGenType() throws Throwable
	{
		Class<?> iface = Class.forName("net.minecraft.world.gen.chunk.ChunkGeneratorFactory");
		InvocationHandler handler = new GenFactoryInvocationHandler<>(SkygridChunkGenerator::new);
		Object proxy = Proxy.newProxyInstance(iface.getClassLoader(), new Class[] { iface }, handler);
		Constructor<ChunkGeneratorType> ctor = ChunkGeneratorType.class.getConstructor(iface, boolean.class, Supplier.class);
		SKYGRID_CHUNK = ctor.newInstance(proxy, false, (Supplier<SkygridChunkGeneratorConfig>)SkygridChunkGeneratorConfig::new);
		
		Registry.register(Registry.CHUNK_GENERATOR_TYPE, new Identifier("skygrid"), SKYGRID_CHUNK);
	}
	
	public static void initLevelGenType() throws Throwable
	{
		Constructor<LevelGeneratorType> ctor = LevelGeneratorType.class.getDeclaredConstructor(int.class, String.class);
		ctor.setAccessible(true);
		int id = 7; // This is totally good and acceptable to do
		String name = "skygrid";
		SKYGRID_WORLD = ctor.newInstance(id, name);
	}
}
