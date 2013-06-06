package mint.thaumicmachines.core;

import cpw.mods.fml.client.registry.RenderingRegistry;
import mint.thaumicmachines.machines.deconstructor.tier1.ContainerDeconstructorTier1;
import mint.thaumicmachines.machines.deconstructor.tier1.TileEntityDeconstructorTier1;
import mods.multifurnace.client.gui.GuiMultiFurnace;
import mods.multifurnace.tileentity.TileEntityMultiFurnaceCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{   
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {

    	if (ID == 0)
    	{
    		TileEntityDeconstructorTier1 tileDeconTeir1 = (TileEntityDeconstructorTier1)world.getBlockTileEntity(x, y, z);
            return new ContainerDeconstructorTier1(player.inventory, tileDeconTeir1);
    	}
		
		return null;
	}
	
	@Override
    public void registerRenderers()
    {
		//PurifierBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		//RenderingRegistry.registerBlockHandler(new RenderPurifierBlock());
    }
	
	public void registerSoundHandler()
	{
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
	}
   
}
