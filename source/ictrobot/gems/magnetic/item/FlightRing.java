package ictrobot.gems.magnetic.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ictrobot.core.Core;
import ictrobot.gems.Gems;

public class FlightRing extends Item {

  int Level;
  
  public FlightRing(int id) {
    super(id);
    setTextureName(Core.ModID + ":" + "FlightRing");
    setUnlocalizedName("FlightRing");
    setCreativeTab(CreativeTabs.tabTools);
    setMaxStackSize(1);
  }

  @Override
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
    if( itemStack.getTagCompound() == null ) {
      itemStack.setTagCompound( new NBTTagCompound( ) );
      itemStack.getTagCompound().setInteger("RPLevel", 1); 
    }
    NBTTagCompound tag = itemStack.getTagCompound();
    if (Core.isClient()) {
      if (!player.isSneaking()) {
        player.motionY = (0.75*(tag.getInteger("RPLevel")*0.75)); 
        Gems.proxy.resetPlayerInAirTime(player);
        itemStack.damageItem(1, player);
      }
    }
    if (Core.isServer()) {
      if (player.isSneaking()) {
        int level = tag.getInteger("RPLevel");
        level++;
        if (level>5) {
          level=1;
        }
        tag.setInteger("RPLevel", level);
        player.addChatMessage("\u00A73\u00A7lFlight Ring:\u00A7r\u00A77 Magnetic Jump Level " + level);
      }
    }
    return itemStack;
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void addInformation(ItemStack itemStack, EntityPlayer player, List par3List, boolean par4) {
    if( itemStack.getTagCompound() != null ) {
      NBTTagCompound tag = itemStack.getTagCompound();
      par3List.add("\u00A77Magnetic Jump Level " + tag.getInteger("RPLevel"));
    }
  }
}