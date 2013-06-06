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
    private TileEntityDeconstructorTier1 tileDeconTier1Inventory;

    public GuiDeconstructorTier1(InventoryPlayer inventoryPlayer, TileEntityDeconstructorTier1 tileentity)
    {
        super(new ContainerDeconstructorTier1(inventoryPlayer, tileentity));
        this.tileDeconTier1Inventory = tileentity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        String containerName = "Thaumic Deonstructor Tier1";
        fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 93, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1f, 1f, 1f, 1f);
		
		mc.renderEngine.bindTexture(Reference.GUI_PATH + "deconTier1.png");
		
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		int i1;
		
		/*if(tileDeconTier1Inventory.isWorking())
		{
			i1 = tileDeconTier1Inventory.getWorkRemaining(12);
			drawTexturedModalRect(x + 56, y + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}
		
		i1 = tileDeconTier1Inventory.getWorkRemaining(24);
		drawTexturedModalRect(x + 79, y + 34, 176, 14, i1 + 1, 16);*/
	}
}
