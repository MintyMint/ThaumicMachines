package mint.thaumicmachines.machines.deconstructor.tier1;

import mods.multifurnace.tileentity.TileEntityMultiFurnaceCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerDeconstructorTier1 extends Container
{
	private TileEntityDeconstructorTier1 tileDeconTier1;
	
	private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerDeconstructorTier1(InventoryPlayer playerinventory, TileEntityDeconstructorTier1 tileentity)
	{
		this.tileDeconTier1 = tileentity;
		
		// Input
		addSlotToContainer(new Slot(tileentity, 0, 56, 17));
		
		// Fuel
		addSlotToContainer(new Slot(tileentity, 1, 56, 53));
		
		// Output
		addSlotToContainer(new SlotFurnace(playerinventory.player, tileentity, 2, 116, 35));
		
		bindPlayerInventory(playerinventory);
	}
	
	
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return tileDeconTier1.isUseableByPlayer(entityPlayer);
	}
	
	private void bindPlayerInventory(InventoryPlayer playerInventory)
	{
		// Inventory
		for(int y = 0; y < 3; y++)
			for(int x = 0; x < 9; x++)
				addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
		
		// Action Bar
		for(int x = 0; x < 9; x++)
			addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 142));
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		ItemStack stack = null;
		Slot slotObject = (Slot)inventorySlots.get(slot);
		
		if(slotObject != null && slotObject.getHasStack())
		{
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();
			
			// Merges the item into the player inventory
			if(slot < 3)
			{
				if(!this.mergeItemStack(stackInSlot, 3, 39, true))
					return null;
			}
			else if(!this.mergeItemStack(stackInSlot, 0, 3, false))
				return null;
			
			if(stackInSlot.stackSize == 0)
				slotObject.putStack(null);
			else
				slotObject.onSlotChanged();
			
			if(stackInSlot.stackSize == stack.stackSize)
				return null;
			
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		
		return stack;
	}
}