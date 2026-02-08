package net.steve.lavachicken.Items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.steve.lavachicken.SteveAPI.EntityExtension;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ObsidianChickenItem extends SwordItem {
    public ObsidianChickenItem() {
        super(Tiers.DIAMOND, 6, -2.4f,
                new Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(7)
                        .saturationMod(1.4F)
                        .alwaysEat()
                        .build())
                .fireResistant()
                .rarity(Rarity.COMMON)
                .stacksTo(1)
        );
    }
    @Override
    public int getUseDuration(ItemStack  p_41458_) {
        return 64;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag flag) {
        tooltipComponents.add(Component.translatable("item.obsidian_chicken.tooltip"));
    }
    @Override
    public Component getName(ItemStack p_41458_) {
        return Component.translatable("item.obsidian_chicken.name");
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        if (!level.isClientSide)
        {
            EntityExtension.AddEffect(entity, MobEffects.POISON,5,1,false);
        }
        return result;
    }
}
