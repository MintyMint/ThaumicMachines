package mint.thaumicmachines.core;

import thaumcraft.api.EnumTag;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mint.thaumicmachines.items.ItemHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabShards extends CreativeTabs
{
	public static TabShards tabShards = new TabShards();
	
	public TabShards()
	{
		super(getNextID(), Reference.MOD_ID);
	}
	
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack()
    {
        return new ItemStack(ItemHelper.aspectShard, EnumTag.values().length - 1, 19);
    }
    
    public String getTranslatedTabLabel()
    {
    	return Reference.MOD_NAME + " Aspect Shards";
    }
}
