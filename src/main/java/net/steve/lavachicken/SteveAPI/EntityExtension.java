package net.steve.lavachicken.SteveAPI;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class EntityExtension
{
    public static void AddEffect(LivingEntity target, MobEffect effect, int duration,int level, boolean particle)
    {
        AddEffect(target,effect,duration,particle,level,true,false);
    }
    public static void AddEffect(LivingEntity target, MobEffect effect,int duration,boolean particle,int level,boolean icon,boolean potiton)
    {
        MobEffectInstance effectInstance = new MobEffectInstance(
            effect,
            duration * 20,
            level - 1,
            particle,
            icon,
            potiton
        );

        target.addEffect(effectInstance);
    }
}
