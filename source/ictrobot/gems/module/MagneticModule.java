package ictrobot.gems.module;

import ictrobot.core.Core;
import ictrobot.core.block.BasicBlock;
import ictrobot.core.block.Ore;
import ictrobot.core.helper.config.ConfigHelper;
import ictrobot.core.helper.register.Register;
import ictrobot.core.helper.tool.ToolMaterials;
import ictrobot.core.item.BasicItem;
import ictrobot.core.item.CraftingIngredient;
import ictrobot.core.item.Ingot;
import ictrobot.core.item.Powder;
import ictrobot.core.world.Dim0WorldGenerator;
import ictrobot.gems.Gems;
import ictrobot.gems.magnetic.armor.CreativeJetpack;
import ictrobot.gems.magnetic.armor.Jetpack;
import ictrobot.gems.magnetic.armor.JetpackKeybind;
import ictrobot.gems.magnetic.armor.LongFallBoots;
import ictrobot.gems.magnetic.block.CompressedTNT;
import ictrobot.gems.magnetic.block.MagneticBlock;
import ictrobot.gems.magnetic.item.ChillRing;
import ictrobot.gems.magnetic.item.ExplosionRing;
import ictrobot.gems.magnetic.item.FlightRing;
import ictrobot.gems.magnetic.item.HeatRing;
import ictrobot.gems.magnetic.item.ItemRing;
import ictrobot.gems.magnetic.item.RepelPlayer;
import ictrobot.gems.magnetic.item.TeleportRing;
import ictrobot.gems.magnetic.tickhandlers.JetpackTickHandler;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


public class MagneticModule {
  
  
   public static int flightPower;
   public static int explosionPower;
   public static int teleportPower;
   public static int itemPower;
   public static int heatPower;
   public static int chillPower;
   //Define IDs - Colour Gems
   public static int positiveID;
   public static int negativeID;
   public static int magneticPowderID;
   public static int magneticIngotID;
   public static int orePositiveID;
   public static int oreNegativeID;
   public static int repelPlayerLvl1ID;
   public static int repelPlayerLvl2ID;
   public static int repelPlayerLvl3ID;
   public static int repelPlayerLvl4ID;
   public static int repelPlayerLvl5ID;
   public static int blockPositiveID;
   public static int blockNegativeID;
   public static int magneticBlockID;
   public static int magnetID;
   public static int jetpackID;
   public static int creativeJetpackID;
   public static int longFallID;
   public static int flightRingID;
   public static int explosionRingID;
   public static int teleportRingID;
   public static int itemRingID;
   public static int heatRingID;
   public static int chillRingID;
   public static int TNTlvl1ID;
   public static int ringID;
   
   //Define Blocks - Colour Gems
   public static Block orePositive;
   public static Block oreNegative;
   public static Block blockPositive;
   public static Block blockNegative;
   public static Block magneticBlock;
   public static Block TNTlvl1;
   
   //Define Items - Colour Gems
   public static Item positive;
   public static Item negative;
   public static Item repelPlayerLvl1;
   public static Item repelPlayerLvl2;
   public static Item repelPlayerLvl3;
   public static Item repelPlayerLvl4;
   public static Item repelPlayerLvl5;
   public static Item magneticPowder;
   public static Item magneticIngot;
   public static Item magnet;
   public static Item jetpack;
   public static Item creativeJetpack;
   public static Item longFall;
   public static Item flightRing;
   public static Item explosionRing;
   public static Item teleportRing;
   public static Item itemRing;
   public static Item ring;
   public static Item heatRing;
   public static Item chillRing;

   public static Dim0WorldGenerator worldPositive;
   public static Dim0WorldGenerator worldNegative;
   
   public static void Config(int ID) {
      ConfigHelper.file("MagneticModule", ID);
      //Items
      positiveID = ConfigHelper.item("positiveID");
      negativeID = ConfigHelper.item("negativeID");
      repelPlayerLvl1ID = ConfigHelper.item("repelPlayerLvl1ID");
      repelPlayerLvl2ID = ConfigHelper.item("repelPlayerLvl2ID");
      repelPlayerLvl3ID = ConfigHelper.item("repelPlayerLvl3ID");
      repelPlayerLvl4ID = ConfigHelper.item("repelPlayerLvl4ID");
      repelPlayerLvl5ID = ConfigHelper.item("repelPlayerLvl5ID");
      magneticPowderID = ConfigHelper.item("magneticPowderID");
      magneticIngotID = ConfigHelper.item("magneticIngotID");
      magnetID = ConfigHelper.item("magnetID");
      jetpackID = ConfigHelper.item("jetpackID");
      creativeJetpackID = ConfigHelper.item("creativeJetpackID");
      longFallID = ConfigHelper.item("longFallID");
      ringID = ConfigHelper.item("ringID");
      flightRingID = ConfigHelper.item("flightRingID");
      explosionRingID = ConfigHelper.item("explosionRingID");
      teleportRingID = ConfigHelper.item("teleportRingID");
      itemRingID = ConfigHelper.item("itemRingID");
      heatRingID = ConfigHelper.item("heatRingID");
      chillRingID = ConfigHelper.item("chillRingID");
      
      blockPositiveID = ConfigHelper.block("blockPositiveID");
      blockNegativeID = ConfigHelper.block("blockNegativeID");
      orePositiveID = ConfigHelper.block("orePositiveID");
      oreNegativeID = ConfigHelper.block("oreNegativeID");
      magneticBlockID = ConfigHelper.block("magneticBlockID");
      TNTlvl1ID = ConfigHelper.block("TNTlvl1ID");
      
      flightPower = ConfigHelper.other("Flight Ring", "Max Magnetic Jump Level", 5);
      explosionPower = ConfigHelper.other("Explosion Ring", "Max Explosion Level", 8);
      teleportPower = ConfigHelper.other("Teleport Ring", "Range for Teleport", 10);
      itemPower = ConfigHelper.other("Item Ring", "Range", 10);
      heatPower = ConfigHelper.other("Heat Ring", "Range", 3);
      chillPower = ConfigHelper.other("Chill Ring", "Range", 3);
      ConfigHelper.save();
      
      //Define World Gen
      worldPositive = new Dim0WorldGenerator(orePositiveID, 40, 5, 4, 8);
      worldNegative = new Dim0WorldGenerator(oreNegativeID, 40, 5, 4, 8);
      
      
      //For Jetpack
      if (Core.isClient()) {
        KeyBinding[] up = {new KeyBinding("Jetpack Up", Keyboard.KEY_Z)};
        boolean[] repeat = {false};
        KeyBindingRegistry.registerKeyBinding(new JetpackKeybind(up, repeat));
        TickRegistry.registerTickHandler(new JetpackTickHandler(EnumSet.of(TickType.PLAYER)), Side.CLIENT);
      }
    }

    public static void Settings() {
      //Ore
      orePositive = (new Ore(orePositiveID, "Positive", positiveID));
      oreNegative = (new Ore(oreNegativeID, "Negative", negativeID));
      //Items
      positive = (new Powder(positiveID, "Positive"));
      negative = (new Powder(negativeID, "Negative"));
      magneticPowder = (new Powder(magneticPowderID, "Magnetic"));
      repelPlayerLvl1 = (new RepelPlayer(repelPlayerLvl1ID, ToolMaterials.RepelPlayer, 1));
      repelPlayerLvl2 = (new RepelPlayer(repelPlayerLvl2ID, ToolMaterials.RepelPlayer, 2));
      repelPlayerLvl3 = (new RepelPlayer(repelPlayerLvl3ID, ToolMaterials.RepelPlayer, 3));
      repelPlayerLvl4 = (new RepelPlayer(repelPlayerLvl4ID, ToolMaterials.RepelPlayer, 4));
      repelPlayerLvl5 = (new RepelPlayer(repelPlayerLvl5ID, ToolMaterials.RepelPlayer, 5));
      magneticIngot = (new Ingot(magneticIngotID, "Magnetic"));
      magnet = (new BasicItem(magnetID, "Magnet")).setMaxStackSize(1);
      jetpack = (new Jetpack(jetpackID, Gems.proxy.addArmor("Jetpack"))).setUnlocalizedName("Jetpack");
      creativeJetpack = (new CreativeJetpack(creativeJetpackID, Gems.proxy.addArmor("creativeJetpack"))).setUnlocalizedName("CreativeJetpack");
      longFall = (new LongFallBoots(longFallID, Gems.proxy.addArmor("LongFall"))).setUnlocalizedName("LongFall");
      flightRing = (new FlightRing(flightRingID, flightPower));
      explosionRing =(new ExplosionRing(explosionRingID, explosionPower));
      teleportRing = (new TeleportRing(teleportRingID, teleportPower));
      itemRing = (new ItemRing(itemRingID, itemPower));
      heatRing = (new HeatRing(heatRingID, heatPower));
      chillRing = (new ChillRing(chillRingID, chillPower));
      ring =(new CraftingIngredient(ringID, "Ring"));
      //Function Blocks
      magneticBlock = (new MagneticBlock(magneticBlockID, "MagneticBlock", Material.iron));  
      TNTlvl1 = (new CompressedTNT(TNTlvl1ID, 1));
      //StorageBlocks
      blockPositive = (new BasicBlock(blockPositiveID, "Positiveblock", Material.rock)).setHardness(4.0F).setResistance(7.5F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BlockPositive").setCreativeTab(CreativeTabs.tabBlock);
      blockNegative = (new BasicBlock(blockNegativeID, "Negativeblock", Material.rock)).setHardness(4.0F).setResistance(7.5F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BlockNegative").setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public static void WorldGen() {
      GameRegistry.registerWorldGenerator(worldPositive);
      GameRegistry.registerWorldGenerator(worldNegative);
    }
    
    public static void Register(){
    	
      GameRegistry.addRecipe(new ItemStack(repelPlayerLvl1), "n n", "p p", "m m", 'm', new ItemStack(Block.pistonBase), 'p', new ItemStack(positive), 'n', new ItemStack(negative));
      GameRegistry.addRecipe(new ItemStack(repelPlayerLvl1), "p p", "n n", "m m", 'm', new ItemStack(Block.pistonBase), 'p', new ItemStack(positive), 'n', new ItemStack(negative));
      GameRegistry.addRecipe(new ItemStack(repelPlayerLvl2), "   ", "m m", "pip", 'p', new ItemStack(Block.pistonBase), 'm', new ItemStack(repelPlayerLvl1), 'i', new ItemStack(Block.blockIron));
      GameRegistry.addRecipe(new ItemStack(repelPlayerLvl3), "   ", "mdm", "pip", 'p', new ItemStack(Block.pistonBase), 'm', new ItemStack(repelPlayerLvl2), 'i', new ItemStack(Block.blockIron), 'd', new ItemStack(Item.diamond));
      GameRegistry.addRecipe(new ItemStack(repelPlayerLvl4), "   ", "mdm", "pip", 'p', new ItemStack(Block.pistonBase), 'm', new ItemStack(repelPlayerLvl3), 'i', new ItemStack(Block.blockIron), 'd', new ItemStack(Item.diamond));
      GameRegistry.addRecipe(new ItemStack(repelPlayerLvl5), "   ", "mdm", "pip", 'p', new ItemStack(Block.pistonBase), 'm', new ItemStack(repelPlayerLvl4), 'i', new ItemStack(Block.blockIron), 'd', new ItemStack(Block.blockDiamond));
      GameRegistry.addRecipe(new ItemStack(blockPositive), "ddd", "ddd", "ddd", 'd', new ItemStack(positive));
      GameRegistry.addRecipe(new ItemStack(blockNegative), "ddd", "ddd", "ddd", 'd', new ItemStack(negative));
      GameRegistry.addRecipe(new ItemStack(magnet), "p n", "d d", "d d", 'd', new ItemStack(magneticIngot), 'p', new ItemStack(positive), 'n', new ItemStack(negative));
      GameRegistry.addRecipe(new ItemStack(magnet), "n p", "d d", "d d", 'd', new ItemStack(magneticIngot), 'p', new ItemStack(positive), 'n', new ItemStack(negative));
      GameRegistry.addRecipe(new ItemStack(magneticBlock), "ddd", "ddd", "ddd", 'd', new ItemStack(magneticIngot));
      GameRegistry.addRecipe(new ItemStack(flightRing), "dj ", "cd ", "  r", 'r', new ItemStack(ring), 'd', new ItemStack(Block.blockDiamond), 'c', new ItemStack(creativeJetpack), 'j', new ItemStack(repelPlayerLvl5));
      GameRegistry.addRecipe(new ItemStack(flightRing), "dc ", "jd ", "  r", 'r', new ItemStack(ring), 'd', new ItemStack(Block.blockDiamond), 'c', new ItemStack(creativeJetpack), 'j', new ItemStack(repelPlayerLvl5));
      GameRegistry.addRecipe(new ItemStack(explosionRing), "ddt", "ddt", "ttr", 't', new ItemStack(TNTlvl1), 'd', new ItemStack(Block.blockDiamond), 'r', new ItemStack(ring));
      GameRegistry.addRecipe(new ItemStack(teleportRing), "dde", "dde", "eer", 'e', new ItemStack(Item.enderPearl), 'd', new ItemStack(Block.blockDiamond), 'r', new ItemStack(ring));
      GameRegistry.addRecipe(new ItemStack(itemRing), "mme", "mme", "eer", 'e', new ItemStack(Item.enderPearl), 'm', new ItemStack(magneticBlock), 'r', new ItemStack(ring));
      GameRegistry.addRecipe(new ItemStack(ring, 2), "mmm", "mrm", "mmm",'m', new ItemStack(magneticBlock), 'r', new ItemStack(ring));
      GameRegistry.addRecipe(new ItemStack(jetpack), "mmm", "mmm", "dmd", 'm', new ItemStack(magneticBlock), 'd', new ItemStack(Block.blockDiamond));
      GameRegistry.addRecipe(new ItemStack(creativeJetpack), "mmm", "mjm", "dmd", 'm', new ItemStack(magneticBlock), 'd', new ItemStack(Block.blockDiamond), 'j', new ItemStack(jetpack));
      GameRegistry.addShapelessRecipe(new ItemStack(positive, 9), new ItemStack(blockPositive));
      GameRegistry.addShapelessRecipe(new ItemStack(negative, 9), new ItemStack(blockNegative));
      GameRegistry.addShapelessRecipe(new ItemStack(magneticPowder), new ItemStack(positive), new ItemStack(negative));
      GameRegistry.addSmelting(magneticPowderID + 256, new ItemStack(magneticIngot), 1);
  
      
      //Blocks - Coloured Gems
      Register.Block(orePositive, "Positive Ore", "pickaxe", 3);
      Register.Block(oreNegative, "Negative Ore", "pickaxe", 3);
      Register.Block(blockPositive, "Positive Block", "pickaxe" , 3);
      Register.Block(blockNegative, "Negative Block", "pickaxe", 3);
      Register.Block(magneticBlock, "Magnetic Block", "pickaxe", 3);
      Register.Block(TNTlvl1, "Compressed TNT", "pickaxe", 3);
      //Items - Coloured Gems
      Register.Item(positive, "Positive Powder");
      Register.Item(negative, "Negative Powder");
      Register.Item(magneticPowder, "Magnetic Powder");
      Register.Item(magneticIngot, "Magnetic Ingot");
      Register.Item(repelPlayerLvl1, "Magnetic Jump");
      Register.Item(repelPlayerLvl2, "Magnetic Jump");
      Register.Item(repelPlayerLvl3, "Magnetic Jump");
      Register.Item(repelPlayerLvl4, "Magnetic Jump");
      Register.Item(repelPlayerLvl5, "Magnetic Jump");
      Register.Item(magnet, "Magnet");
      Register.Item(jetpack, "Jetpack");
      Register.Item(creativeJetpack, "Creative Flight Jetpack");
      Register.Item(longFall, "Long Fall Boots");
      Register.Item(flightRing, "Flight Ring");
      Register.Item(explosionRing, "Explosion Ring");
      Register.Item(teleportRing, "Teleport Ring");
      Register.Item(itemRing, "Item Ring");
      Register.Item(heatRing, "Heat Ring");
      Register.Item(chillRing, "Chill Ring");
      Register.Item(ring, "Ring");
      
      Register.Ore("itemRepelPlayer", repelPlayerLvl1);
      Register.Ore("itemRepelPlayer", repelPlayerLvl2);
      Register.Ore("itemRepelPlayer", repelPlayerLvl3);
      Register.Ore("itemRepelPlayer", repelPlayerLvl4);
      Register.Ore("itemRepelPlayer", repelPlayerLvl5);
    }
}
