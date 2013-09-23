package ictrobot.gems.module;

import ictrobot.core.block.BasicBlock;
import ictrobot.core.block.Ore;
import ictrobot.core.helper.register.Register;
import ictrobot.core.helper.tool.ToolMaterials;
import ictrobot.core.item.Powder;
import ictrobot.core.world.Dim0WorldGenerator;
import ictrobot.gems.magnetic.item.RepelPlayer;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;


public class MagneticModule {
    
   //Define IDs - Colour Gems
   public static int positiveID;
   public static int negativeID;
   public static int orePositiveID;
   public static int oreNegativeID;
   public static int repelPlayerID;
   public static int blockPositiveID;
   public static int blockNegativeID;

   //Define Blocks - Colour Gems
   public static Block orePositive;
   public static Block oreNegative;
   public static Block blockPositive;
   public static Block blockNegative;
   
   //Define Items - Colour Gems
   public static Item positive;
   public static Item negative;
   public static Item repelPlayer;

   public static Dim0WorldGenerator worldPositive;
   public static Dim0WorldGenerator worldNegative;
   
   public static void Config(File file) {
      Configuration config = new Configuration(file);
      config.load();
      //Items
      positiveID = config.get(Configuration.CATEGORY_ITEM, "positiveID", 6041).getInt();
      negativeID = config.get(Configuration.CATEGORY_ITEM, "negativeID", 6042).getInt();
      orePositiveID = config.get(Configuration.CATEGORY_BLOCK, "orePositiveID", 801).getInt();
      oreNegativeID = config.get(Configuration.CATEGORY_BLOCK, "oreNegativeID", 802).getInt();
      repelPlayerID = config.get(Configuration.CATEGORY_ITEM, "repelPlayerID", 6043).getInt();
      blockPositiveID = config.get(Configuration.CATEGORY_BLOCK, "blockPositiveID", 803).getInt();
      blockNegativeID = config.get(Configuration.CATEGORY_BLOCK, "blockNegativeID", 804).getInt();
      config.save();
      
      //Define World Gen
      worldPositive = new Dim0WorldGenerator(orePositiveID, 40, 5, 4, 8);
      worldNegative = new Dim0WorldGenerator(oreNegativeID, 40, 5, 4, 8);
    }

    public static void Settings() {
      //Ore
      orePositive = (new Ore(orePositiveID, "Positive", positiveID));
      oreNegative = (new Ore(oreNegativeID, "Negative", negativeID));
      //Items
      positive = (new Powder(positiveID, "Positive"));
      negative = (new Powder(negativeID, "Negative"));
      repelPlayer = (new RepelPlayer(repelPlayerID, ToolMaterials.Magnetic));
      //StorageBlocks
      blockPositive = (new BasicBlock(blockPositiveID, "Positiveblock", Material.rock)).setHardness(4.0F).setResistance(7.5F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BlockPositive").setCreativeTab(CreativeTabs.tabBlock);
      blockNegative = (new BasicBlock(blockNegativeID, "Negativeblock", Material.rock)).setHardness(4.0F).setResistance(7.5F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BlockNegative").setCreativeTab(CreativeTabs.tabBlock);
   }
    
    public static void WorldGen() {
      GameRegistry.registerWorldGenerator(worldPositive);
      GameRegistry.registerWorldGenerator(worldNegative);
    }
    
    public static void Register(){
    	
      GameRegistry.addRecipe(new ItemStack(repelPlayer), "n n", "p p", "mim", 'm', new ItemStack(Block.pistonBase), 'p', new ItemStack(positive), 'n', new ItemStack(negative), 'i', new ItemStack(Block.blockIron));
      GameRegistry.addRecipe(new ItemStack(repelPlayer), "p p", "n n", "mim", 'm', new ItemStack(Block.pistonBase), 'p', new ItemStack(positive), 'n', new ItemStack(negative), 'i', new ItemStack(Block.blockIron));
      GameRegistry.addRecipe(new ItemStack(blockPositive), "ddd", "ddd", "ddd", 'd', new ItemStack(positive));
      GameRegistry.addRecipe(new ItemStack(blockNegative), "ddd", "ddd", "ddd", 'd', new ItemStack(negative));
      GameRegistry.addShapelessRecipe(new ItemStack(positive, 9), new ItemStack(blockPositive));
      GameRegistry.addShapelessRecipe(new ItemStack(negative, 9), new ItemStack(blockNegative));
  
      
      //Blocks - Coloured Gems
      Register.Block(orePositive, "Positive Ore", "pickaxe", 3);
      Register.Block(oreNegative, "Negative Ore", "pickaxe", 3);
      Register.Block(blockPositive, "Positive Block", "pickaxe" , 3);
      Register.Block(blockNegative, "Negative Block", "pickaxe", 3);
      //Items - Coloured Gems
      Register.Item(positive, "Positive Powder");
      Register.Item(negative, "Negative Powder");
      Register.Item(repelPlayer, "Magnetic Jump");
    }
}
