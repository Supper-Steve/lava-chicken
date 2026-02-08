package net.steve.lavachicken;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.steve.lavachicken.Items.ItemRegister;
import net.steve.lavachicken.Other.TabRegister;
import org.slf4j.Logger;

@Mod(MainMod.MODID)
public class MainMod
{

    public static final String MODID = "lavachicken";

    public static final Logger Logger = LogUtils.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);


    public MainMod(FMLJavaModLoadingContext context)
    {
        Logger.info("成功注册模组LavaChicken");

        IEventBus modEventBus = context.getModEventBus();

        BLOCKS.register(modEventBus);

        TabRegister.register(modEventBus);
        ItemRegister.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
