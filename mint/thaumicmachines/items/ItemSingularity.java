package mint.thaumicmachines.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mint.quantumcoins.ConfigHelper;
import mint.thaumicmachines.core.Reference;
import mint.thaumicmachines.core.TabTM;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemSingularity extends Item
{
	public Icon unstable;
	public Icon stable;
	
	public ItemSingularity(int par1)
	{
		super(par1);
		setCreativeTab(TabTM.tabTM);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
		unstable = iconregister.registerIcon(Reference.MOD_ID + ":" + "ItemUnstableSingularity");
		stable = iconregister.registerIcon(Reference.MOD_ID + ":" + "ItemStabilizedSingularity");
    }
	
    public String getItemDisplayName(ItemStack itemstack)
    {
    	return itemstack.getItemDamage() == 0 ? "Unstable Arcane Singularity" : "Stabilized Arcane Singularity";
    }
	
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta)
	{
	    return meta == 0 ? unstable : stable;
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creativetab, List itemlist)
    {
    	itemlist.add(new ItemStack(id, 1, 0));
    	itemlist.add(new ItemStack(id, 1, 1));
    }
}
