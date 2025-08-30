package com.mistersecret312.grinchgeartrinkets;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@net.minecraftforge.fml.common.Mod(modid = References.MOD_ID, name = References.NAME, dependencies = "after:baubles", version = References.VERSION)
public class Mod
{
    public static final String MODID = "grinchgeartrinkets";
    public static final String NAME = "Grinch's Gear and Trinkets";
    public static final String VERSION = "1.0.0";

    private static Logger LOGGER;

    @net.minecraftforge.fml.common.Mod.Instance
    public static Mod instance;

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

}
