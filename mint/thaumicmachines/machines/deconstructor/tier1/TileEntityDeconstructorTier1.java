package mint.thaumicmachines.machines.deconstructor.tier1;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.ObjectTags;
import mint.thaumicmachines.core.Reference;
import mint.thaumicmachines.core.TileTM;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityDeconstructorTier1 extends TileTM implements IInventory
{
	private ItemStack[] deconTier1ItemStacks;
	
	public int deconTier1WorkTime = 0;
	public int currentDeconTier1WorkTime = 0;
	

    public TileEntityDeconstructorTier1()
    {
        super();
        deconTier1ItemStacks = new ItemStack[4];
    }
	
	@Override
	public int getSizeInventory()
	{
		return this.deconTier1ItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return deconTier1ItemStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		{
			if (this.deconTier1ItemStacks[slot] != null)
			{
				ItemStack itemstack;

				if (this.deconTier1ItemStacks[slot].stackSize <= count)
				{
					itemstack = this.deconTier1ItemStacks[slot];
					this.deconTier1ItemStacks[slot] = null;
					return itemstack;
				}
				
				else
				{
					itemstack = this.deconTier1ItemStacks[slot].splitStack(count);

					if (this.deconTier1ItemStacks[slot].stackSize == 0)
					{
						this.deconTier1ItemStacks[slot] = null;
					}

					return itemstack;
				}
			}

			else
			{
				return null;
			}
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if(deconTier1ItemStacks[slot] != null)
		{
			ItemStack stack = deconTier1ItemStacks[slot];
			deconTier1ItemStacks[slot] = null;
			return stack;
		}
		
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        this.deconTier1ItemStacks[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }
	
	@Override
	public String getInvName()
	{
		return Reference.MOD_ID + ".container.deconTeir1";
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {/*not used*/}

	@Override
	public void closeChest(){/*not used*/}

	@Override
	public boolean isStackValidForSlot(int slot, ItemStack itemstack)
	{
		if (slot == 0 && (new ObjectTags(itemstack.itemID, itemstack.getItemDamage()).size() < 0)){ return true; }
		return false;
	}
	
    public boolean isWorking()
    {
        return this.deconTier1WorkTime > 0;
    }

    public void readFromNBT(NBTTagCompound tagcompound)
    {
        super.readFromNBT(tagcompound);
        NBTTagList nbttaglist = tagcompound.getTagList("Items");
        this.deconTier1ItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.deconTier1ItemStacks.length)
            {
                this.deconTier1ItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.deconTier1WorkTime = tagcompound.getShort("WorkTime");

    }

    public void writeToNBT(NBTTagCompound tagcompound)
    {
        super.writeToNBT(tagcompound);
        tagcompound.setShort("WorkTime", (short)this.deconTier1WorkTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.deconTier1ItemStacks.length; ++i)
        {
            if (this.deconTier1ItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.deconTier1ItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tagcompound.setTag("Items", nbttaglist);

    }
	
	@SideOnly(Side.CLIENT)
	public int getWorkRemaining(int scaleVal)
	{
		return deconTier1WorkTime * scaleVal / 100;
	}
	
    private boolean canDecon()
    {
        if (this.deconTier1ItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.deconTier1ItemStacks[0]);
            if (itemstack == null) return false;
            if (this.deconTier1ItemStacks[2] == null) return true;
            if (!this.deconTier1ItemStacks[2].isItemEqual(itemstack)) return false;
            int result = deconTier1ItemStacks[2].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }
    
    public void deconItem()
    {
        if (this.canDecon())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.deconTier1ItemStacks[0]);

            if (this.deconTier1ItemStacks[2] == null)
            {
                this.deconTier1ItemStacks[2] = itemstack.copy();
            }
            else if (this.deconTier1ItemStacks[2].isItemEqual(itemstack))
            {
                deconTier1ItemStacks[2].stackSize += itemstack.stackSize;
            }

            --this.deconTier1ItemStacks[0].stackSize;

            if (this.deconTier1ItemStacks[0].stackSize <= 0)
            {
                this.deconTier1ItemStacks[0] = null;
            }
        }
    }
    
    public void updateEntity()
    {
        boolean flag = this.deconTier1WorkTime > 0;
        boolean flag1 = false;

        if (this.deconTier1WorkTime > 0)
        {
            --this.deconTier1WorkTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.deconTier1WorkTime == 0 && this.canDecon())
            {
                this.currentDeconTier1WorkTime = this.deconTier1WorkTime = getItemBurnTime(this.deconTier1ItemStacks[1]);

                if (this.deconTier1WorkTime > 0)
                {
                    flag1 = true;

                    if (this.deconTier1ItemStacks[1] != null)
                    {
                        --this.deconTier1ItemStacks[1].stackSize;

                        if (this.deconTier1ItemStacks[1].stackSize == 0)
                        {
                            this.deconTier1ItemStacks[1] = this.deconTier1ItemStacks[1].getItem().getContainerItemStack(deconTier1ItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isWorking() && this.canDecon())
            {
                ++this.deconTier1WorkTime;

                if (this.deconTier1WorkTime == 200)
                {
                    this.deconTier1WorkTime = 0;
                    this.deconItem();
                    flag1 = true;
                }
            }
            else
            {
                this.deconTier1WorkTime = 0;
            }

            if (flag != this.deconTier1WorkTime > 0)
            {
                flag1 = true;
                BlockFurnace.updateFurnaceBlockState(this.deconTier1WorkTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.onInventoryChanged();
        }
    }
    
    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            int i = par0ItemStack.getItem().itemID;
            Item item = par0ItemStack.getItem();

            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[i] != null)
            {
                Block block = Block.blocksList[i];

                if (block == Block.woodSingleSlab)
                {
                    return 150;
                }

                if (block.blockMaterial == Material.wood)
                {
                    return 300;
                }
            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD")) return 200;
            if (i == Item.stick.itemID) return 100;
            if (i == Item.coal.itemID) return 1600;
            if (i == Item.bucketLava.itemID) return 20000;
            if (i == Block.sapling.blockID) return 100;
            if (i == Item.blazeRod.itemID) return 2400;
            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }
}
