package net.steve.lavachicken.Handlers;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.steve.lavachicken.Items.ItemRegister;

@Mod.EventBusSubscriber
public class LavaChickenSoundHandler {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getItemStack().getItem() == ItemRegister.LAVA_CHICKEN.get() && event.getLevel().getBlockState(event.getPos()).getBlock() == Blocks.JUKEBOX) {
            event.setCanceled(true);


            if (!event.getLevel().isClientSide()) {
                var level = (ServerLevel) event.getLevel();
                var pos = event.getPos();
                var server = level.getServer();

                level.destroyBlock(pos, true);

                event.getItemStack().shrink(1);

                server.execute(() -> {

                    for (int i = 0; i < 5; i++) {
                        level.sendParticles(ParticleTypes.EXPLOSION,
                                pos.getX() + 0.5f, pos.getY() + 1f, pos.getZ() + 0.5f,
                                1, Math.random() - 0.5, Math.random(), Math.random() - 0.5, 0);
                    }

                    var chicken = EntityType.CHICKEN.create(level);
                    var zombie = EntityType.ZOMBIE.create(level);

                    if (chicken != null && zombie != null) {
                        chicken.setPos(pos.getX() + 0.5f, pos.getY() + 1f, pos.getZ() + 0.5f);
                        zombie.setPos(pos.getX() + 0.5f, pos.getY() + 1f, pos.getZ() + 0.5f);
                        zombie.setBaby(true);

                        zombie.startRiding(chicken);
                        level.addFreshEntity(zombie);
                        level.addFreshEntity(chicken);
                    }
                });
            }
        }
    }
}
