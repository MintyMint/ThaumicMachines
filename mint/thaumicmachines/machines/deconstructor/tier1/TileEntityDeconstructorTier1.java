package mint.thaumicmachines.machines.deconstructor.tier1;

import java.util.Random;

import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import mint.quantumcoins.ConfigHelper;
import mint.thaumicmachines.core.TileTM;
import mint.thaumicmachines.items.ItemHelper;
import net.minecraft.block.BlockFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityDeconstructorTier1 extends TileTM implements IInventory
{
    private ItemStack[] decon1Inventory;
    
    public int decon1WorkTime = 0;
    
    public Random chance;

    public TileEntityDeconstructorTier1()
    {
        decon1Inventory = new ItemStack[4];
    }

    @Override
    public int getSizeInventory()
    {
        return decon1Inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return decon1Inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) 
        {
            if (itemStack.stackSize <= amount) 
            {
                setInventorySlotContents(slot, null);
            }
            
            else
            {
                itemStack = itemStack.splitStack(amount);
                
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slot, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

        decon1Inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName()
    {
        return this.hasCustomName() ? this.getCustomName() : "Thaumic Deconstructor";
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void openChest() { }

    @Override
    public void closeChest() { }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items");
        
        decon1Inventory = new ItemStack[this.getSizeInventory()];
        
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            
            byte slot = tagCompound.getByte("Slot");
            
            if (slot >= 0 && slot < decon1Inventory.length)
            {
                decon1Inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        
        for (int currentIndex = 0; currentIndex < decon1Inventory.length; ++currentIndex)
        {
            if (decon1Inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                decon1Inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        
        nbtTagCompound.setTag("Items", tagList);
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return this.hasCustomName();
    }

    @Override
    public boolean isStackValidForSlot(int i, ItemStack itemstack)
    {
        return true;
    }
    
    public boolean isWorking()
    {
        return this.decon1WorkTime > 0;
    }
    
    public int getWorkTime()
    {
    	return decon1WorkTime;
    }
    
    public void setWorkTime(int time)
    {
    	decon1WorkTime = time;
    }
    
    public int getWorkProgressScaled(int scale)
    {
        return decon1WorkTime * scale / 200;
    }
    
    public void updateEntity()
    {
    	boolean deconed = false;
    	
    	if (!this.worldObj.isRemote)
        {
            if (this.canDecon())
            {
                ++this.decon1WorkTime;
                System.out.println("" + this.isWorking());

                if (this.decon1WorkTime == 64)
                {
                    this.decon1WorkTime = 0;
                    this.deconItem();
                    deconed = true;
                }
            }
            
            else
            {
                this.decon1WorkTime = 0;
            }

        }

        if (deconed)
        {
            this.onInventoryChanged();
        }
    }
    
    private boolean canDecon()
    {
        //ItemStack itemstack = this.getStackInSlot(0);
        //EnumTag[] containedAspects = new ObjectTags(itemstack.itemID, itemstack.getItemDamage()).getAspects();
    	
    	if (this.decon1Inventory[0] == null)
        {
            return false;
        }
        
        //else if (containedAspects.length == 0) return false;
        
        else
        {
        	if (this.decon1Inventory[1] == null || this.decon1Inventory[2] == null || this.decon1Inventory[3] == null) return true;
            if (this.decon1Inventory[2].isItemEqual(this.getStackInSlot(1)) == false
             || this.decon1Inventory[2].isItemEqual(this.getStackInSlot(2)) == false
             || this.decon1Inventory[2].isItemEqual(this.getStackInSlot(3)) == false) return false;
            
            //for (int slot = 0; slot < 3; slot++)
            //{
            //	int result = decon1Inventory[slot].stackSize + this.getStackInSlot(slot).stackSize;
            //	return (result <= getInventoryStackLimit() && result <= this.getStackInSlot(slot).getMaxStackSize());
            //}
        }
        
        return false;
    }
    
    public void deconItem()
    {
        if (this.canDecon())
        {
            ItemStack itemstack = this.getStackInSlot(0);
            ObjectTags itemTags = new ObjectTags(itemstack.itemID, itemstack.getItemDamage());
            EnumTag[] containedAspects = itemTags.getAspectsSorted();
            
            for (int slot = 0; slot < 3; slot++)
            { 	
        		int meta = containedAspects[slot].id;
        		int amount = itemTags.getAmount(containedAspects[slot]);
        		
            	System.out.println("slot " + slot);
            	if (this.decon1Inventory[slot+1] == null)
            	{
            		this.decon1Inventory[slot+1] = new ItemStack(ItemHelper.aspectShard, amount, meta);
            	}
            
            	else if (this.decon1Inventory[slot+1].isItemEqual(itemstack))
            	{
            		decon1Inventory[slot+1].stackSize += amount;
            	}
            }

            --this.decon1Inventory[0].stackSize;

            if (this.decon1Inventory[0].stackSize <= 0)
            {
            	this.decon1Inventory[0] = null;
            }
        }
    }
}