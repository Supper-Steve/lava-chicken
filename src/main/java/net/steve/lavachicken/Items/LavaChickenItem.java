package net.steve.lavachicken.Items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.steve.lavachicken.SteveAPI.AdvancementHelper;
import net.steve.lavachicken.SteveAPI.EntityExtension;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LavaChickenItem extends Item {
    public LavaChickenItem() {
        super(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(12)
                        .saturationMod(0.9F)
                        .alwaysEat()
                        .build())
                .fireResistant()
                .rarity(Rarity.COMMON)
                .stacksTo(64)
        );
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag flag) {
        tooltipComponents.add(Component.translatable("item.lava_chicken.tooltip"));
    }

    @Override
    public Component getName(ItemStack p_41458_) {
        return Component.translatable("item.lava_chicken.name");
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide)
        {
            if (entity instanceof ServerPlayer player) {
                AdvancementHelper.grantAchievement(
                        player,
                        "lava_chicken"
                );
            }
            entity.setSecondsOnFire(10);
            EntityExtension.AddEffect(entity, MobEffects.MOVEMENT_SPEED,5,1,false);
            EntityExtension.AddEffect(entity, MobEffects.JUMP,5,1,false);
        }

        return result;
    }
}
