package ictrobot.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommonProxy {

  public void registerRenderers() {
    
  }

  public int addArmor(String armor) {
    return 0;
  }
  
  public void resetPlayerInAirTime(EntityPlayer player) {
    if (!(player instanceof EntityPlayerMP)) return;
      ((EntityPlayerMP)player).playerNetServerHandler.ticksForFloatKick = 0;
  }
}
