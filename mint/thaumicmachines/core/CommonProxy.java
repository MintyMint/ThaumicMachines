package mint.thaumicmachines.core;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import mint.thaumicmachines.machines.deconstructor.tier1.ContainerDeconstructorTier1;
import mint.thaumicmachines.machines.deconstructor.tier1.TileEntityDeconstructorTier1;
import mint.thaumicmachines.machines.deconstructor.tier2.TileEntityDeconstructorTier2;
import mods.multifurnace.tileentity.ContainerMultiFurnace;
import mods.multifurnace.tileentity.TileEntityMultiFurnaceCore;
import mods.multifurnace.tileentity.TileEntityMultiFurnaceDummy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;

public class CommonProxy implements IGuiHandler
{
	public void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityDeconstructorTier1.class, "tileEntityDeconstructorTier1");
		GameRegistry.registerTileEntity(TileEntityDeconstructorTier2.class, "tileEntityDeconstructorTier2");
	}
	
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {

    	if (ID == 0)
    	{
    		TileEntityDeconstructorTier1 tileDeconTeir1 = (TileEntityDeconstructorTier1) world.getBlockTileEntity(x, y, z);
            return new ContainerDeconstructorTier1(player.inventory, tileDeconTeir1);
    	}
		
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	
	public void registerRenderers(){}

	public void registerSoundHandler() {}
}
