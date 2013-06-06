package mint.thaumicmachines.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mint.thaumicmanagement.BlockNodePurifier;
import mint.thaumicmanagement.ConfigHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockHelper
{
	public static Block deconTeir1;
	
	public static void registerBlocks()
	{
		deconTeir1 = new BlockDeconTeir1(989, Material.iron).setUnlocalizedName("BlockDeconTeir1");
		GameRegistry.registerBlock(deconTeir1, "BlockDeconTeir1");
		LanguageRegistry.addName(deconTeir1, "Thaumic Deconstrcutor Tier 1");	
	}
}
