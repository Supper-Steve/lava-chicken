package net.steve.lavachicken.Items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.steve.lavachicken.MainMod;

public class ItemRegister
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MainMod.MODID);

    public static final RegistryObject<Item> LAVA_CHICKEN = ITEMS.register("lava_chicken",
            () -> new LavaChickenItem());

    public static final RegistryObject<Item> OBSIDIAN_CHICKEN = ITEMS.register("obsidian_chicken",
            () -> new ObsidianChickenItem());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
