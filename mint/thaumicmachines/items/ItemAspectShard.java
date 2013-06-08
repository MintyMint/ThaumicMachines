package mint.thaumicmachines.items;

import java.util.List;

import mint.thaumicmachines.core.Reference;
import mint.thaumicmachines.core.TabShards;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.EnumTag;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAspectShard extends Item
{
	public ItemAspectShard(int ID)
	{
		super(ID);
		setHasSubtypes(true);
		setMaxStackSize(64);
		setMaxDamage(0);
		setCreativeTab(TabShards.tabShards);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
		this.itemIcon = iconregister.registerIcon(Reference.MOD_ID + ":" + "ItemAspectShard");
    }
	
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return "Aspect Shard of " + EnumTag.get(par1ItemStack.getItemDamage()).name;
	}
	
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int meta)
    {
    	return EnumTag.get(itemstack.getItemDamage()).color;
    }

	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs creativetabs, List list)
	{
		for (EnumTag tag : EnumTag.values())
		{
			if (EnumTag.get(tag.id) != EnumTag.UNKNOWN)
			{
				list.add(new ItemStack(this, 1, tag.id));
			}
		}
	}
}
