package mint.thaumicmachines.core;

import java.util.logging.Level;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLLog;

public class SoundHandler
{
	public static final String path = "mint/quantumcoins/";
	public static final String soundpath = "mint.quantumcoins.";
	
	String[] sounds = {path + "conversion.ogg"};
	
    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event)
    {

        // For each custom sound file we have defined in Sounds
        for (String soundFile : sounds)
        {
            // Try to add the custom sound file to the pool of sounds
            try
            {
                event.manager.soundPoolSounds.addSound(soundFile, this.getClass().getResource("/" + soundFile));
            }
            // If we cannot add the custom sound file to the pool, log the exception
            catch (Exception e)
            {
            	FMLLog.log(Level.WARNING, "Failed loading sound file: " + soundFile);
            }
        }
    }
}
