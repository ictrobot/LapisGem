package ictrobot.gems.module.compat;

import ictrobot.core.helper.tool.*;
import ictrobot.core.item.*;
import ictrobot.core.tool.*;
import ictrobot.core.block.*;
import ictrobot.core.helper.register.Register;
import ictrobot.core.world.*;
import ictrobot.gems.magnetic.item.*;

import java.io.File;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

@SuppressWarnings("unused")
public class GenericOreModule {
    
   //Define IDs - Colour Gems
   public static int oreCopperID;
   public static int oreTinID;
   public static int oreSilverID;
   public static int oreLeadID;

   //Define Blocks - Colour Gems
   public static Block oreCopper;
   public static Block oreTin;
   public static Block oreSilver;
   public static Block oreLead;

   public static Dim0WorldGenerator worldCopper;
   public static Dim0WorldGenerator worldTin;
   public static Dim0WorldGenerator worldSilver;
   public static Dim0WorldGenerator worldLead;
   
   public static void Config(File file) {
      Configuration config = new Configuration(file);
      config.load();
      //Items
      oreCopperID = config.get(Configuration.CATEGORY_BLOCK, "oreCopperID", 701).getInt();
      oreTinID = config.get(Configuration.CATEGORY_BLOCK, "oreTinID", 702).getInt();
      oreSilverID = config.get(Configuration.CATEGORY_BLOCK, "oreSilverID", 703).getInt();
      oreLeadID = config.get(Configuration.CATEGORY_BLOCK, "oreLeadID", 704).getInt();
      
      int CMaxH = config.get("Copper Ore", "MaxH", 80).getInt();
      int CMinH = config.get("Copper Ore", "MinH", 5).getInt();
      int CNumberOfVeins = config.get("Copper Ore", "NumberOfVeins", 10).getInt();
      int CNumberInVeins = config.get("Copper Ore", "NumberInVeins", 8).getInt();
      
      int TMaxH = config.get("Tin Ore", "MaxH", 80).getInt();
      int TMinH = config.get("Tin Ore", "MinH", 5).getInt();
      int TNumberOfVeins = config.get("Tin Ore", "NumberOfVeins", 10).getInt();
      int TNumberInVeins = config.get("Tin Ore", "NumberInVeins", 8).getInt();
      
      int SMaxH = config.get("Silver Ore", "MaxH", 40).getInt();
      int SMinH = config.get("Silver Ore", "MinH", 5).getInt();
      int SNumberOfVeins = config.get("Silver Ore", "NumberOfVeins", 5).getInt();
      int SNumberInVeins = config.get("Silver Ore", "NumberInVeins", 8).getInt();
      
      int LMaxH = config.get("Lead Ore", "MaxH", 40).getInt();
      int LMinH = config.get("Lead Ore", "MinH", 5).getInt();
      int LNumberOfVeins = config.get("Lead Ore", "NumberOfVeins", 5).getInt();
      int LNumberInVeins = config.get("Lead Ore", "NumberInVeins", 8).getInt();
      
      config.save();
      
      //Define World Gen
      worldCopper = new Dim0WorldGenerator(oreCopperID, CMaxH, CMinH, CNumberOfVeins, CNumberInVeins);
      worldTin = new Dim0WorldGenerator(oreTinID, TMaxH, TMinH, TNumberOfVeins, TNumberInVeins);
      worldSilver = new Dim0WorldGenerator(oreTinID, SMaxH, SMinH, SNumberOfVeins, SNumberInVeins);
      worldLead = new Dim0WorldGenerator(oreTinID, LMaxH, LMinH, LNumberOfVeins, LNumberInVeins);
    }

    public static void Settings() {
      //Ore
      oreCopper = (new Ore(oreCopperID, "Copper", oreCopperID));
      oreTin = (new Ore(oreTinID, "Tin", oreTinID));
      oreSilver = (new Ore(oreSilverID, "Silver", oreSilverID));
      oreLead = (new Ore(oreLeadID, "Lead", oreLeadID));
   }
    
    public static void WorldGen() {
      GameRegistry.registerWorldGenerator(worldCopper);
      GameRegistry.registerWorldGenerator(worldTin);
      GameRegistry.registerWorldGenerator(worldSilver);
      GameRegistry.registerWorldGenerator(worldLead);
    }
    
    public static void Register(){
      Register.Block(oreCopper, "Copper Ore", "pickaxe", 1);
      Register.Block(oreTin, "Tin Ore", "pickaxe", 1);
      Register.Block(oreSilver, "Silver Ore", "pickaxe", 1);
      Register.Block(oreLead, "Lead Ore", "pickaxe", 1);
      
      Register.Ore("oreCopper", oreCopper);
      Register.Ore("oreTin", oreTin);
      Register.Ore("oreSilver", oreSilver);
      Register.Ore("oreLead", oreLead);
    }
}