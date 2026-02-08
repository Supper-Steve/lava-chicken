package net.steve.lavachicken.Other;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.steve.lavachicken.Items.ItemRegister;
import net.steve.lavachicken.Items.LavaChickenItem;
import net.steve.lavachicken.MainMod;

public class TabRegister {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MainMod.MODID);
    public static final RegistryObject<CreativeModeTab> MY_TAB = CREATIVE_MODE_TABS.register("creativetab.lavachicken",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.lavachicken.creativetab"))
                    .icon(() -> new ItemStack(ItemRegister.LAVA_CHICKEN.get()))
                    .displayItems((params, output) -> {
                        output.accept(ItemRegister.LAVA_CHICKEN.get());
                        output.accept(ItemRegister.OBSIDIAN_CHICKEN.get());
                    })
                    .build()
    );
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
