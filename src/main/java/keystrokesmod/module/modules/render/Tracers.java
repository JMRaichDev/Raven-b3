//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.8.9"!

package keystrokesmod.module.modules.render;

import java.awt.Color;
import java.util.Iterator;

import keystrokesmod.*;
import keystrokesmod.main.Ravenb3;
import keystrokesmod.module.Module;
import keystrokesmod.module.ModuleSetting;
import keystrokesmod.module.ModuleSetting2;
import keystrokesmod.module.modules.world.AntiBot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Tracers extends Module {
   public static ModuleSetting a;
   public static ModuleSetting2 b;
   public static ModuleSetting2 c;
   public static ModuleSetting2 d;
   public static ModuleSetting e;
   public static ModuleSetting2 f;
   private boolean g;
   private int rgb_c = 0;

   public Tracers() {
      super("Tracers", Module.category.render, 0);
      this.registerSetting(a = new ModuleSetting("Show invis", true));
      this.registerSetting(f = new ModuleSetting2("Line Width", 1.0D, 1.0D, 5.0D, 1.0D));
      this.registerSetting(b = new ModuleSetting2("Red", 0.0D, 0.0D, 255.0D, 1.0D));
      this.registerSetting(c = new ModuleSetting2("Green", 255.0D, 0.0D, 255.0D, 1.0D));
      this.registerSetting(d = new ModuleSetting2("Blue", 0.0D, 0.0D, 255.0D, 1.0D));
      this.registerSetting(e = new ModuleSetting("Rainbow", false));
   }

   public void onEnable() {
      this.g = mc.gameSettings.viewBobbing;
      if (this.g) {
         mc.gameSettings.viewBobbing = false;
      }

   }

   public void onDisable() {
      mc.gameSettings.viewBobbing = this.g;
   }

   public void update() {
      if (mc.gameSettings.viewBobbing) {
         mc.gameSettings.viewBobbing = false;
      }

   }

   public void guiUpdate() {
      this.rgb_c = (new Color((int)b.getInput(), (int)c.getInput(), (int)d.getInput())).getRGB();
   }

   @SubscribeEvent
   public void o(RenderWorldLastEvent ev) {
      if (ay.isPlayerInGame()) {
         int rgb = e.isToggled() ? ay.gc(2L, 0L) : this.rgb_c;
         Iterator var3;
         if (Ravenb3.debugger) {
            var3 = mc.theWorld.loadedEntityList.iterator();

            while(var3.hasNext()) {
               Entity en = (Entity)var3.next();
               if (en instanceof EntityLivingBase && en != mc.thePlayer) {
                  ru.dtl(en, rgb, (float)f.getInput());
               }
            }

         } else {
            var3 = mc.theWorld.playerEntities.iterator();

            while(true) {
               EntityPlayer en;
               do {
                  do {
                     do {
                        if (!var3.hasNext()) {
                           return;
                        }

                        en = (EntityPlayer)var3.next();
                     } while(en == mc.thePlayer);
                  } while(en.deathTime != 0);
               } while(!a.isToggled() && en.isInvisible());

               if (!AntiBot.bot(en)) {
                  ru.dtl(en, rgb, (float)f.getInput());
               }
            }
         }
      }
   }
}
