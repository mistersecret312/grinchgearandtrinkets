package com.mistersecret312.grinchgeartrinkets;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.Mod.*;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item nightVisionGoggles = new NightVisionGoggles("nv_goggles");
    public static final Item coldRing = new TemperatureProtectiveRing(false);
    public static final Item warmRing = new TemperatureProtectiveRing(true);

    public static final Item rubyAmulet = new GemAmuletItem(GemAmuletItem.Gem.RUBY);
    public static final Item amberAmulet = new GemAmuletItem(GemAmuletItem.Gem.AMBER);
    public static final Item sapphireAmulet = new GemAmuletItem(GemAmuletItem.Gem.SAPPHIRE);
    public static final Item peridotAmulet = new GemAmuletItem(GemAmuletItem.Gem.PERIDOT);
    public static final Item malachiteAmulet = new GemAmuletItem(GemAmuletItem.Gem.MALACHITE);
    public static final Item tanzaniteAmulet = new GemAmuletItem(GemAmuletItem.Gem.TANZANITE);
    public static final Item topazAmulet = new GemAmuletItem(GemAmuletItem.Gem.TOPAZ);
    public static final Item emeraldAmulet = new GemAmuletItem(GemAmuletItem.Gem.EMERALD);
    public static final Item diamondAmulet = new GemAmuletItem(GemAmuletItem.Gem.DIAMOND);
    public static final Item medic = new TF2MedicItem("tf2_medic");
    public static final Item chicken = new ChickenItem();
    public static final Item radprot = new RadProtItem("radprot");

    @SubscribeEvent
    public static void registerEvent(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModel(ModelRegistryEvent event)
    {
        for(Item item : ITEMS)
            Mod.proxy.registerItemRenderer(item, 0, "inventory");
    }

}
