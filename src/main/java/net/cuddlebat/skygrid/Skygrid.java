package net.cuddlebat.skygrid;

import net.cuddlebat.skygrid.world.ModGeneratorTypes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.level.LevelGeneratorType;

public class Skygrid implements ModInitializer {
	
	public static final String MODID = "skygrid";
	
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
//		try
//		{
//			ModGeneratorTypes.init();
//		} catch (Throwable e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		try
		{
			ModGeneratorTypes.initChunkGenType();
		} catch (Throwable e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GridPopulatorPickers.doInit();
		
		System.out.println("Yay for " + LevelGeneratorType.TYPES[7].getName());
	}
}
