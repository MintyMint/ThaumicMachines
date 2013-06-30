package mint.thaumicmachines.machines.deconstructor.tier1;

import mint.thaumicmachines.blocks.BlockDeconTeir1;
import mint.thaumicmachines.core.Reference;
import mint.thaumicmanagement.BlockNodePurifier;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.Icon;
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
    	ySize = 239;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	String containerName = decon1Inventory.isInvNameLocalized() ? decon1Inventory.getInvName() : StatCollector.translateToLocal(decon1Inventory.getInvName());
    	fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
    	fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 93, 4210752);
    	
    	drawRune();
    }
    
    protected void drawRune()
    {
    	Icon deconRune = BlockDeconTeir1.deconRune;
    	Icon deconRuneWorking = BlockDeconTeir1.deconRuneWorking;
    	mc.renderEngine.bindTexture("/terrain.png");
    	
    	if (this.decon1Inventory.isWorking())
    	{
    		
    		this.drawTexturedModelRectFromIcon(72, 68, deconRuneWorking, 32, 32);
    	}
    	
    	else
    	{
    		this.drawTexturedModelRectFromIcon(72, 68, deconRune, 32, 32);
    	}
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	mc.renderEngine.bindTexture(Reference.GUI_PATH + "deconTier1.png");
    	int xStart = (width - xSize) / 2;
    	int yStart = (height - ySize) / 2;
    	this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);  	
        int i1;

        if (this.decon1Inventory.isWorking())
        {
            i1 = this.decon1Inventory.getWorkProgressScaled(25);
            this.drawTexturedModalRect(xStart + 75, yStart + 98 - i1, 176, 26 - i1, 26, i1 - 2);
        }
    }   
}
