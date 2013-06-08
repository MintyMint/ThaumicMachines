package mint.thaumicmachines.core;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOutOnly extends Slot
{

	public SlotOutOnly(IInventory inventory, int x, int y, int z)
	{
		super(inventory, x, y, z);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return false;
	}
}
