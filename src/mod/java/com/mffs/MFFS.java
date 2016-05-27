package com.mffs;

import com.mffs.api.SharedLoader;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = MFFS.MODID, name = MFFS.MOD_NAME, version = MFFS.VERSION)
public class MFFS
{
    public static final String MODID = "modular_forcefield";
    public static final String VERSION = "1.0";
    public static final String MOD_NAME = "Modular_Forcefields";

    @Mod.Instance
    public static MFFS mffs_mod;

    @SidedProxy(clientSide="com.mffs.client.ClientInit", serverSide="com.mffs.server.ServerInit")
    public static SharedLoader initialize;

    /* This is the communication channel of the mod */
    public static SimpleNetworkWrapper channel;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        channel = new SimpleNetworkWrapper(MODID);
        initialize.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		initialize.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        initialize.postInit(event);
    }
}