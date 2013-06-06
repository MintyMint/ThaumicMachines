package mint.thaumicmachines.blocks;

import mint.thaumicmachines.ThaumicMachines;
import mint.thaumicmachines.core.TabThaumicMachines;
import mint.thaumicmachines.machines.deconstructor.tier1.TileEntityDeconstructorTier1;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDeconTeir1 extends BlockTM
{

	public BlockDeconTeir1(int id, Material material)
	{
		super(id, material);
		setCreativeTab(TabThaumicMachines.tabTM);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityDeconstructorTier1();
	}
	
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (player.isSneaking())
            return false;
        else {
            if (!world.isRemote)
            {
            	TileEntityDeconstructorTier1 tileDeconTeir1 = (TileEntityDeconstructorTier1) world.getBlockTileEntity(x, y, z);

                if (tileDeconTeir1 != null)
                {
                    player.openGui(ThaumicMachines.instance, 0, world, x, y, z);
                }
            }

            return true;
        }
    }

}
