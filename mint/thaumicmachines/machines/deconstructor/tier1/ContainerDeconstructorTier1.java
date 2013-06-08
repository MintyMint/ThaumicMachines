package mint.thaumicmachines.machines.deconstructor.tier1;

import mint.thaumicmachines.core.SlotOutOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDeconstructorTier1 extends Container
{
	private TileEntityDeconstructorTier1 tileDeconTier1;
	private int lastWorkTime = 0;

    public ContainerDeconstructorTier1(InventoryPlayer playerinventory, TileEntityDeconstructorTier1 tileDeconTier1) {

        this.addSlotToContainer(new Slot(tileDeconTier1, 1, 80, 18));
        this.addSlotToContainer(new Slot(tileDeconTier1, 2, 29, 105));
        this.addSlotToContainer(new Slot(tileDeconTier1, 3, 130, 105));

        this.addSlotToContainer(new Slot(tileDeconTier1, 0, 80, 76));

        this.bindPlayerInventory(playerinventory);
    }
    
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tileDeconTier1.isUseableByPlayer(player);
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
    	ItemStack itemstack = null;

    	return itemstack;
    }
    
    public void bindPlayerInventory(InventoryPlayer playerinventory)
    {
    	//binds the players "pack" inventory  
    	for (int row = 0; row < 3; row++)
    	{   	      
    		for (int column = 0; column < 9; column++)
    		{
    			this.addSlotToContainer(new Slot(playerinventory, column + row * 9 + 9, 8 + column * 18, 158 + row * 18));
    		}

    	}
    		
    	//binds the players hotbar inventory
    	for (int barslot = 0; barslot < 9; barslot++)
    	{
    		this.addSlotToContainer(new Slot(playerinventory, barslot, 8 + barslot * 18, 216));
    	}
	}
}