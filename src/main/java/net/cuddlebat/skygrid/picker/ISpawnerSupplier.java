package net.cuddlebat.skygrid.picker;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.MobSpawnerEntry;

@FunctionalInterface
public interface ISpawnerSupplier
{
	public MobSpawnerEntry get();
	
	public static ISpawnerSupplier of(MobSpawnerEntry entry)
	{
		return () -> { return entry; };
	}
	
	public static ISpawnerSupplier ofMonster(EntityType<? extends LivingEntity> monster)
	{
		CompoundTag tag = new CompoundTag();
		CompoundTag inner = new CompoundTag();
		inner.putString("id", Registry.ENTITY_TYPE.getId(monster).toString());
		tag.put("Entity", inner);
		return () -> { return new MobSpawnerEntry(tag); };
	}
}
