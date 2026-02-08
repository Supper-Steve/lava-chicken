package net.steve.lavachicken.Handlers;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.steve.lavachicken.Items.ItemRegister;

@Mod.EventBusSubscriber
public class CraftingHandler
{
    @SubscribeEvent
    public static void onCrafting(PlayerEvent.ItemCraftedEvent event)
    {
        if (event.getCrafting().getItem() == ItemRegister.LAVA_CHICKEN.get())
        {
            for (int i = 0; i < event.getInventory().getContainerSize(); i++)
            {
                ItemStack stack = event.getInventory().getItem(i);
                if (stack.getItem() == Items.LAVA_BUCKET) {
                    event.getInventory().setItem(i, new ItemStack(Items.BUCKET));
                }
            }
        }
        else if (event.getCrafting().getItem() == Items.CHICKEN)
        {
            for (int k = 0; k < event.getInventory().getContainerSize(); k++)
            {
                ItemStack stack = event.getInventory().getItem(k);
                if (stack.getItem() == Items.WATER_BUCKET) {
                    event.getInventory().setItem(k, new ItemStack(Items.BUCKET));
                }
            }
        }
        else if (event.getCrafting().getItem() == ItemRegister.OBSIDIAN_CHICKEN.get())
        {
            for (int k = 0; k < event.getInventory().getContainerSize(); k++)
            {
                ItemStack stack = event.getInventory().getItem(k);
                if (stack.getItem() == Items.WATER_BUCKET || stack.getItem() == Items.LAVA_BUCKET) {
                    event.getInventory().setItem(k, new ItemStack(Items.BUCKET));
                }
            }
        }
    }
}
