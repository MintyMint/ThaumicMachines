package mint.thaumicmachines.machines.deconstructor.tier1;

import mint.thaumicmachines.core.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDeconstructorTier1 extends GuiContainer
{
    private TileEntityDeconstructorTier1 decon1Inventory;

    public GuiDeconstructorTier1(InventoryPlayer inventoryPlayer, TileEntityDeconstructorTier1 tileDeconTier1)
    {
    	super(new ContainerDeconstructorTier1(inventoryPlayer, tileDeconTier1));
    	this.decon1Inventory = tileDeconTier1;
    	//xSize = 176;
    	ySize = 239;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	String containerName = decon1Inventory.isInvNameLocalized() ? decon1Inventory.getInvName() : StatCollector.translateToLocal(decon1Inventory.getInvName());
    	fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
    	//fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	mc.renderEngine.bindTexture(Reference.GUI_PATH + "deconTier1.png");
    	int xStart = (width - xSize) / 2;
    	int yStart = (height - ySize) / 2;
    	this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    	
        if (this.decon1Inventory.isWorking())
        {
        	fontRenderer.drawString(StatCollector.translateToLocal("Working..."), 8, ySize - 93, 4210752);
        }
        
        else
        {
        	fontRenderer.drawString(StatCollector.translateToLocal("Idle..."), 8, ySize - 93, 4210752);
        }
    }
}
