package ictrobot.gems.magnetic.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ictrobot.core.Core;

public class ItemRing extends Item {

  int Level;
  
  public ItemRing(int id) {
    super(id);
    setTextureName(Core.ModID + ":" + "ItemRing");
    setUnlocalizedName("ItemRing");
    setCreativeTab(CreativeTabs.tabTools);
    setMaxStackSize(1);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
    if (Core.isServer()) {
      if( itemStack.getTagCompound() == null ) {
        itemStack.setTagCompound( new NBTTagCompound( ));
        itemStack.getTagCompound().setInteger("Delay", 10); 
      }
      NBTTagCompound tag = itemStack.getTagCompound();
      EntityPlayer player;
      if (tag.getBoolean("Enabled") && ((entity instanceof EntityPlayer))) {
        float radius = 5;
        player = (EntityPlayer)entity;
        final List<EntityItem> list = (List<EntityItem>)world.getEntitiesWithinAABB((Class)EntityItem.class, player.boundingBox.expand(radius, radius, radius));
        for (final EntityItem e : list) {
          if (e.age >= tag.getInteger("Delay")) {
            if (Core.isServer() && player.inventory.addItemStackToInventory(e.getEntityItem())) {
              world.removeEntity(e);
            }
          }
        }
      }
    }
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
    if (Core.isServer()) {
      if( itemStack.getTagCompound() == null ) {
        itemStack.setTagCompound( new NBTTagCompound( ) );
        itemStack.getTagCompound().setInteger("Delay", 10); 
      }
      NBTTagCompound tag = itemStack.getTagCompound();
      if (player.isSneaking()) {
        if (!tag.getBoolean("Enabled")) {
          tag.setBoolean("Enabled", true);
          player.addChatMessage("\u00A73\u00A7lFlight Ring:\u00A7r\u00A77 Enabled");
        } 
        double time = tag.getInteger("Delay");
        time = time / 20;
        time = time + 0.5;
        if (time>5) {
          time=0.5;
        }
        double ticks = time*20;
        int t = (int)ticks;
        tag.setInteger("Delay", t);
        player.addChatMessage("\u00A73\u00A7lItem Ring:\u00A7r\u00A77 Delay " + time);
      } else {
        if (tag.getBoolean("Enabled")) {
          tag.setBoolean("Enabled", false);
          player.addChatMessage("\u00A73\u00A7lItem Ring:\u00A7r\u00A77 Disabled");
        } else {
          tag.setBoolean("Enabled", true);
          player.addChatMessage("\u00A73\u00A7lItem Ring:\u00A7r\u00A77 Enabled");
        }
      }
    }
    return itemStack;
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void addInformation(ItemStack itemStack, EntityPlayer player, List par3List, boolean par4) {
    if( itemStack.getTagCompound() != null ) {
      NBTTagCompound tag = itemStack.getTagCompound();
      if (tag.getBoolean("Enabled")) {
        par3List.add("\u00A77Enabled");
        double time = tag.getInteger("Delay");
        double delay = time/20;
        par3List.add("\u00A77Delay - " + delay + " seconds");
      } else {
        par3List.add("\u00A77Disabled");
      }
    }
  }
}
