package mint.thaumicmachines.items;

import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApi;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class ItemHelper
{
	public static Item singularity;
	public static Item aspectShard;
	
	public static void registerItems()
	{
		singularity = new ItemSingularity(12000).setUnlocalizedName("singularity");
		GameRegistry.registerItem(singularity, "singularity");
		
		aspectShard = new ItemAspectShard(12001).setUnlocalizedName("aspectShard");
		GameRegistry.registerItem(aspectShard, "aspectShard");
	}
	
	public static void addAspects()
	{
		for (EnumTag tag : EnumTag.values())
		{
			if (EnumTag.get(tag.id) != EnumTag.UNKNOWN)
			{
				ThaumcraftApi.registerObjectTag(aspectShard.itemID , tag.id, new ObjectTags().add(tag, 1));
			}
		}
	}
}
