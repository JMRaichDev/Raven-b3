//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.8.9"!

package keystrokesmod.module.modules.movement;

import keystrokesmod.module.Module;
import keystrokesmod.module.ModuleManager;
import keystrokesmod.module.ModuleSetting2;
import keystrokesmod.ay;
import net.minecraft.client.settings.KeyBinding;

public class BHop extends Module {
   public static ModuleSetting2 a;
   private final double bspd = 0.0025D;

   public BHop() {
      super("Bhop", Module.category.movement, 0);
      this.registerSetting(a = new ModuleSetting2("Speed", 2.0D, 1.0D, 15.0D, 0.2D));
   }

   public void update() {
      if (!ModuleManager.fly.isEnabled() && ay.im() && !mc.thePlayer.isInWater()) {
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), false);
         mc.thePlayer.noClip = true;
         if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
         }

         mc.thePlayer.setSprinting(true);
         double spd = 0.0025D * a.getInput();
         double m = (double)((float)(Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ) + spd));
         ay.ss2(m);
      }
   }
}
