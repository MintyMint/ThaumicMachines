package mint.thaumicmachines.core;

public class Reference
{
	//mod realted stuff
	public static final String MOD_ID = "tMachines";
	public static final String MOD_NAME = "Thaumic Machines";
	public static final String VERSION = "1.0.0";
	public static final String CLIENT_PROXY = "mint.thaumicmachines.core.ClientProxy";
	public static final String SERVER_PROXY = "mint.thaumicmachines.core.CommonProxy";
	
	//renderer ids
	public static int DECONSTRUCTOR_TIER2_RENDER_ID;
	
	//sounds
	public static String SOUND_LOCATION  = "/mods/" + MOD_ID + "/sounds/";
	public static String SOUND_PREFIX = "mods." + MOD_ID + ".sounds.";
	
	//gui
	public static String GUI_PATH = "/mods/" + MOD_ID + "/textures/gui/";
	
    /* NBT related constants [coppied from EE3, needs changing*/
    public static final String NBT_ITEM_CHARGE_LEVEL_KEY = "itemChargeLevel";
    public static final String NBT_ITEM_MODE_KEY = "itemMode";
    public static final String NBT_ITEM_CRAFTING_GUI_OPEN = "itemCraftingGuiOpen";
    public static final String NBT_ITEM_TRANSMUTATION_GUI_OPEN = "itemTransmutationGuiOpen";
    public static final String NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN = "itemAlchemicalBagGuiOpen";
    public static final String NBT_ITEM_DISPLAY = "display";
    public static final String NBT_ITEM_COLOR = "color";
    public static final String NBT_TE_STATE_KEY = "teState";
    public static final String NBT_TE_CUSTOM_NAME = "CustomName";
    public static final String NBT_TE_DIRECTION_KEY = "teDirection";
}
