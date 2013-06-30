package mint.thaumicmachines.machines.deconstructor.tier1;

import java.util.Random;

import mint.thaumicmachines.core.TileTM;
import mint.thaumicmachines.items.ItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;

public class TileEntityDeconstructorTier1 extends TileTM implements IInventory
{
    ObjectTags itemTags;
    EnumTag[] containedAspects;
	
	private ItemStack[] decon1Inventory;
    
    public int decon1WorkTime = 0;
    
    public Random chance = new Random();

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
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null)
        {
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        decon1Inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
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
    
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
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

                if (this.decon1WorkTime == 200)
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
    	if (this.decon1Inventory[0] == null)
        {
            return false;
        }

        else
        {
            ItemStack itemstack = this.getStackInSlot(0);
            itemTags = new ObjectTags(itemstack.itemID, itemstack.getItemDamage());
            containedAspects = itemTags.getAspectsSorted();
            
            if (itemTags == null) return false;
            
            int[] slotSize = new int[3];
            	
            if (this.getStackInSlot(1) != null) slotSize[0] = this.getStackInSlot(1).stackSize;
            if (this.getStackInSlot(2) != null) slotSize[1] = this.getStackInSlot(2).stackSize;
            if (this.getStackInSlot(3) != null) slotSize[2] = this.getStackInSlot(3).stackSize;
            
            if (   (slotSize[0] + itemTags.getAmount(containedAspects[0]) > this.getInventoryStackLimit())
            	|| (slotSize[1] + itemTags.getAmount(containedAspects[1]) > this.getInventoryStackLimit())
            	|| (slotSize[2] + itemTags.getAmount(containedAspects[2]) > this.getInventoryStackLimit())
            	) return false;
            
            else return true;
         }
    }
    
    public void deconItem()
    {
        if (this.canDecon())
        {
            ItemStack itemstack = this.getStackInSlot(0);
            itemTags = new ObjectTags(itemstack.itemID, itemstack.getItemDamage());
            containedAspects = itemTags.getAspectsSorted();
            
            for (int slot = 0; slot < 3; slot++)
            { 	
        		int meta = containedAspects[slot].id;
        		int amount = itemTags.getAmount(containedAspects[slot]);
        		
            	if (this.decon1Inventory[slot+1] == null)
            	{
            		this.decon1Inventory[slot+1] = new ItemStack(ItemHelper.aspectShard, chance.nextInt(amount+1), meta);
            	}
            
            	else if (this.decon1Inventory[slot+1].isItemEqual(this.getStackInSlot(slot+1)))
            	{
            		decon1Inventory[slot+1].stackSize += chance.nextInt(amount+1);
            	}
            }

            --this.decon1Inventory[0].stackSize;

            if (this.decon1Inventory[0].stackSize <= 0)
            {
            	this.decon1Inventory[0] = null;
            }
        }
        
        //empty variables to make sure the next item populates the correct aspects
        itemTags = null;
        containedAspects = null;
    }
}