package mint.thaumicmachines.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mint.thaumicmachines.ThaumicMachines;
import mint.thaumicmachines.core.Reference;
import mint.thaumicmachines.core.TabTM;
import mint.thaumicmachines.machines.deconstructor.tier1.TileEntityDeconstructorTier1;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockDeconTeir1 extends BlockTM
{
	public static Icon deconRune;
	public static Icon deconRuneWorking;
	
	Icon topTexture;
	Icon sideTexture;
	Icon bottomTexture;
	
    private Random rand = new Random();

    public BlockDeconTeir1(int id, Material material) {

        super(id, material);
        this.setCreativeTab(TabTM.tabTM);
        this.setHardness(5F);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	deconRune = iconregister.registerIcon(Reference.MOD_ID + ":" + "deconRune");
    	deconRuneWorking = iconregister.registerIcon(Reference.MOD_ID + ":" + "deconRuneWorking");
    	topTexture = iconregister.registerIcon("thaumicmanagement:BlockPurifierTop");
    	sideTexture = iconregister.registerIcon("thaumicmanagement:BlockPurifierSide");
    	bottomTexture = iconregister.registerIcon("thaumicmanagement:BlockPurifierBottom");
    }
    
    public Icon getIcon(int side, int meta)
    {
    	switch (side)
    	{
    		case 1: return topTexture;
    		case 2: return sideTexture;
    		case 3: return sideTexture;
    		case 4: return sideTexture;
    		case 5: return sideTexture;
    		case 6: return bottomTexture;
    	}
    	
    	return bottomTexture;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileEntityDeconstructorTier1();
    }

    @Override
    public boolean renderAsNormalBlock() {

        return true;
    }

    @Override
    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) {

        dropInventory(world, x, y, z);

        super.breakBlock(world, x, y, z, id, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (player.isSneaking())
            return false;
        else
        {
            if (!world.isRemote)
            {
            	TileEntityDeconstructorTier1 TileEntityDeconstructorTier1 = (TileEntityDeconstructorTier1) world.getBlockTileEntity(x, y, z);

                if (TileEntityDeconstructorTier1 != null)
                {
                    player.openGui(ThaumicMachines.instance, 0, world, x, y, z);
                }
            }

            return true;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack) {

        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);

        if (world.getBlockTileEntity(x, y + 1, z) != null && world.getBlockTileEntity(x, y + 1, z) instanceof TileEntityDeconstructorTier1) {

        	TileEntityDeconstructorTier1 tileGlassBell = (TileEntityDeconstructorTier1) world.getBlockTileEntity(x, y + 1, z);

            tileGlassBell.setOrientation(ForgeDirection.UP);

            if (world.getBlockTileEntity(x, y, z) != null && world.getBlockTileEntity(x, y, z) instanceof TileEntityDeconstructorTier1) {

                TileEntityDeconstructorTier1 TileEntityDeconstructorTier1 = (TileEntityDeconstructorTier1) world.getBlockTileEntity(x, y, z);

                ItemStack itemStackGlassBell = tileGlassBell.getStackInSlot(0);

                tileGlassBell.setInventorySlotContents(0, null);

                //TileEntityDeconstructorTier1.setInventorySlotContents(TileEntityDeconstructorTier1.0, itemStackGlassBell);
            }
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        return 0;
    }

    private void dropInventory(World world, int x, int y, int z) {

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {

            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0) {
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, new ItemStack(itemStack.itemID, itemStack.stackSize, itemStack.getItemDamage()));

                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
}