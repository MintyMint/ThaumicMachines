package mint.thaumicmachines.core;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import mint.thaumicmachines.machines.deconstructor.tier1.ContainerDeconstructorTier1;
import mint.thaumicmachines.machines.deconstructor.tier1.GuiDeconstructorTier1;
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
		GameRegistry.registerTileEntity(TileEntityDeconstructorTier1.class, "tileDeconTier1");
		GameRegistry.registerTileEntity(TileEntityDeconstructorTier2.class, "tileDeconTier2");
	}
	
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {

    	if (ID == 0)
    	{
    		TileEntityDeconstructorTier1 tileDeconTier1 = (TileEntityDeconstructorTier1) world.getBlockTileEntity(x, y, z);
            return new ContainerDeconstructorTier1(player.inventory, tileDeconTier1);
    	}
		
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {

    	if (ID == 0)
    	{
    		TileEntityDeconstructorTier1 tileDeconTier1 = (TileEntityDeconstructorTier1) world.getBlockTileEntity(x, y, z);
            return new GuiDeconstructorTier1(player.inventory, tileDeconTier1);
    	}
		
		return null;
	}
	
	public void registerRenderers(){}

	public void registerSoundHandler() {}
}
