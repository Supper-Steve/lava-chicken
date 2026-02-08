package net.steve.lavachicken.Handlers;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.steve.lavachicken.Items.ItemRegister;

@Mod.EventBusSubscriber
public class LavaChickenHandler
{

    @SubscribeEvent
    public static void onPlayerAttack(LivingAttackEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            ItemStack mainHand = player.getMainHandItem();

            if (!mainHand.isEmpty() && mainHand.getItem() == ItemRegister.LAVA_CHICKEN.get()) {
                if (!event.getEntity().level().isClientSide) {
                    event.getEntity().setSecondsOnFire(7);
                }
            }
        }
    }

    private static boolean checkDamage(DamageSource source,Chicken chicken) {
        return source.is(DamageTypes.LAVA) && chicken.isInLava();
    }

    @SubscribeEvent
    public static void onChickenDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Chicken chicken)) {
            return;
        }
        if (checkDamage(event.getSource(),chicken)) {

            event.setCanceled(true);

            if (!chicken.level().isClientSide) {
                var pos = chicken.position();
                if(event.getEntity().level() instanceof ServerLevel level) {
                    for (int i = 0; i < 5; i++) {
                        level.sendParticles(ParticleTypes.FLAME,
                                pos.x + 0.5f, pos.y + 1f, pos.z + 0.5f,
                                1, Math.random() - 0.5, Math.random(), Math.random() - 0.5, 0);
                    }
                }
                ItemStack item = new ItemStack(ItemRegister.LAVA_CHICKEN.get(), 1);
                chicken.spawnAtLocation(item);
                chicken.level().playSound(null, chicken.blockPosition(),
                        net.minecraft.sounds.SoundEvents.FIRE_EXTINGUISH,
                        net.minecraft.sounds.SoundSource.BLOCKS,
                        0.5F, 1.0F);
                chicken.discard();
            }
        }
    }

}
