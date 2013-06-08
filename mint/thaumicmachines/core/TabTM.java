package mint.thaumicmachines.core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mint.thaumicmachines.items.ItemHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabTM extends CreativeTabs
{
	public static TabTM tabTM = new TabTM();
	
	public TabTM()
	{
		super(getNextID(), Reference.MOD_ID);
	}
	
	@SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
    	return ItemHelper.singularity;
    }
    
    public String getTranslatedTabLabel()
    {
    	return Reference.MOD_NAME;
    }
}
