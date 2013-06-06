package mint.thaumicmachines.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class ItemHelper
{
	public static Item singularity;
	
	public static void registerItems()
	{
		singularity = new ItemSingularity(12000).setUnlocalizedName("unstableSingularity");
		GameRegistry.registerItem(singularity, "unstableSingularity");
	}
	
	public static void addAspects()
	{
		
	}
}
