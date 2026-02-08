package net.steve.lavachicken.SteveAPI;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.steve.lavachicken.MainMod;

public class AdvancementHelper
{
    public static void grantAchievement(ServerPlayer player,String id) {
        var advancement = player.server.getAdvancements()
                .getAdvancement(ResourceLocation.fromNamespaceAndPath(MainMod.MODID, id));

        if (advancement != null) {
            var progress = player.getAdvancements().getOrStartProgress(advancement);
            for (String criterion : progress.getRemainingCriteria()) {
                player.getAdvancements().award(advancement, criterion);
            }
        }
    }
}
