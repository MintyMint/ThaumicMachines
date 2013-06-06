package mint.thaumicmachines.core;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigHelper
{
	public static Configuration config;
	
	public static void configureStuff(File configFile)
	{
		config = new Configuration(configFile);
		
		try
		{
			config.load();
			
			config.addCustomCategoryComment
			("Tool Configuration", "Thaumic Management tool configuration settings");


		} 
		
		catch (Exception exception)
		{
			FMLLog.log(Level.SEVERE, exception, Reference.MOD_NAME + " has had a problem loading its configuration!");
		} 
		
		finally
		{
			config.save();
		}
	}
}
