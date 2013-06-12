package mint.thaumicmachines.machines.deconstructor.tier1;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, tileDeconTier1.decon1WorkTime);
    }
    
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < crafters.size(); ++var1)
        {
            ICrafting iCrafting = (ICrafting) crafters.get(var1);

            if (lastWorkTime != tileDeconTier1.decon1WorkTime)
            {
                iCrafting.sendProgressBarUpdate(this, 0, tileDeconTier1.decon1WorkTime);
            }
        }

        lastWorkTime = tileDeconTier1.decon1WorkTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int ID, int value)
    {
        if (ID == 0)
        {
        	tileDeconTier1.decon1WorkTime = value;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
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