package mint.thaumicmachines.core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mint.thaumicmachines.items.ItemHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabThaumicMachines extends CreativeTabs
{
	public static TabThaumicMachines tabTM = new TabThaumicMachines();
	
	public TabThaumicMachines()
	{
		super(getNextID(), Reference.MOD_ID);
	}
	
    public Item getTabIconItem()
    {
    	return ItemHelper.singularity;
    }
    
    public String getTranslatedTabLabel()
    {
    	return Reference.MOD_NAME;
    }
}
