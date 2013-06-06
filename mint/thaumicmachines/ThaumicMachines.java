package mint.thaumicmachines;

import mint.thaumicmachines.blocks.BlockHelper;
import mint.thaumicmachines.core.CommonProxy;
import mint.thaumicmachines.core.ConfigHelper;
import mint.thaumicmachines.core.RecipeHelper;
import mint.thaumicmachines.core.Reference;
import mint.thaumicmachines.core.ResearchHelper;
import mint.thaumicmachines.items.ItemHelper;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ThaumicMachines
{
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static CommonProxy proxy;
	
	@Instance(Reference.MOD_ID)
	public static ThaumicMachines instance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHelper.configureStuff(event.getSuggestedConfigurationFile());
		
		BlockHelper.registerBlocks();
		
		ItemHelper.registerItems();
		
		proxy.registerSoundHandler();
	}

	@Init
	public void init(FMLInitializationEvent event)
	{
		proxy.registerTileEntities();
		proxy.registerRenderers();
		
		RecipeHelper.registerRecipes();
		
		RecipeHelper.registerThaumcraftRecipes();
		
		//BlockHelper.addAspects; not sure if im going to add blocks other then machines
		
		ItemHelper.addAspects();
		
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event)
	{
		ResearchHelper.registerResearch();
		
		ResearchHelper.registerResearchXML();
		
		FMLLog.info(Reference.MOD_NAME + ": Looks like everything ran correctly!");
	}
}
