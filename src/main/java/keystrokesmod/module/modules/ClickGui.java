//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.8.9"!

package keystrokesmod.module.modules;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import keystrokesmod.*;
import keystrokesmod.main.Ravenb3;
import keystrokesmod.module.Module;
import keystrokesmod.module.modules.client.CommandLine;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class ClickGui extends GuiScreen {
   private ScheduledFuture sf;
   private am aT;
   private am aL;
   private am aE;
   private am aR;
   private ScaledResolution sr;
   private GuiButtonExt s;
   private GuiTextField c;
   public static ArrayList<cm> c4tL1st;

   public ClickGui() {
      c4tL1st = new ArrayList();
      int y = 5;
      Module.category[] values;
      int length = (values = Module.category.values()).length;

      for(int i = 0; i < length; ++i) {
         Module.category c = values[i];
         cm f = new cm(c);
         f.y(y);
         c4tL1st.add(f);
         y += 20;
      }

   }

   public void initMain() {
      (this.aT = this.aE = this.aR = new am(500.0F)).start();
      this.sf = Ravenb3.getExecutor().schedule(() -> {
         (this.aL = new am(650.0F)).start();
      }, 650L, TimeUnit.MILLISECONDS);
   }

   public void initGui() {
      super.initGui();
      this.sr = new ScaledResolution(this.mc);
      (this.c = new GuiTextField(1, this.mc.fontRendererObj, 22, this.height - 100, 150, 20)).setMaxStringLength(256);
      this.buttonList.add(this.s = new GuiButtonExt(2, 22, this.height - 70, 150, 20, "Send"));
      this.s.visible = CommandLine.a;
   }

   public void drawScreen(int x, int y, float p) {
      drawRect(0, 0, this.width, this.height, (int)(this.aR.getValueFloat(0.0F, 0.7F, 2) * 255.0F) << 24);
      int h = this.height / 4;
      int wd = this.width / 2;
      int w_c = 30 - this.aT.getValueInt(0, 30, 3);
      this.drawCenteredString(this.fontRendererObj, "r", wd + 1 - w_c, h - 25, ay.gc(2L, 1500L));
      this.drawCenteredString(this.fontRendererObj, "a", wd - w_c, h - 15, ay.gc(2L, 1200L));
      this.drawCenteredString(this.fontRendererObj, "v", wd - w_c, h - 5, ay.gc(2L, 900L));
      this.drawCenteredString(this.fontRendererObj, "e", wd - w_c, h + 5, ay.gc(2L, 600L));
      this.drawCenteredString(this.fontRendererObj, "n", wd - w_c, h + 15, ay.gc(2L, 300L));
      this.drawCenteredString(this.fontRendererObj, "b3", wd + 1 + w_c, h + 30, ay.gc(2L, 0L));
      this.drawVerticalLine(wd - 10 - w_c, h - 30, h + 43, Color.white.getRGB());
      this.drawVerticalLine(wd + 10 + w_c, h - 30, h + 43, Color.white.getRGB());
      int r;
      if (this.aL != null) {
         r = this.aL.getValueInt(0, 20, 2);
         this.drawHorizontalLine(wd - 10, wd - 10 + r, h - 29, -1);
         this.drawHorizontalLine(wd + 10, wd + 10 - r, h + 42, -1);
      }

      for (cm c : c4tL1st) {
         c.rf(this.fontRendererObj);
         c.up(x, y);

         for (b m : c.gc()) {
            m.uu(x, y);
         }
      }

      GuiInventory.drawEntityOnScreen(this.width + 15 - this.aE.getValueInt(0, 40, 2), this.height - 10, 40, (float)(this.width - 25 - x), (float)(this.height - 50 - y), this.mc.thePlayer);
      if (CommandLine.a) {
         if (!this.s.visible) {
            this.s.visible = true;
         }

         r = CommandLine.animate.isToggled() ? CommandLine.an.getValueInt(0, 200, 2) : 200;
         if (CommandLine.b) {
            r = 200 - r;
            if (r == 0) {
               CommandLine.b = false;
               CommandLine.a = false;
               this.s.visible = false;
            }
         }

         drawRect(0, 0, r, this.height, -1089466352);
         this.drawHorizontalLine(0, r - 1, this.height - 345, -1);
         this.drawHorizontalLine(0, r - 1, this.height - 115, -1);
         drawRect(r - 1, 0, r, this.height, -1);
         gc.rc(this.fontRendererObj, this.height, r, this.sr.getScaleFactor());
         int x2 = r - 178;
         this.c.xPosition = x2;
         this.s.xPosition = x2;
         this.c.drawTextBox();
         super.drawScreen(x, y, p);
      } else if (CommandLine.b) {
         CommandLine.b = false;
      }

   }

   public void mouseClicked(int x, int y, int m) throws IOException {
      Iterator var4 = c4tL1st.iterator();

      while(true) {
         cm c4t;
         do {
            do {
               if (!var4.hasNext()) {
                  if (CommandLine.a) {
                     this.c.mouseClicked(x, y, m);
                     super.mouseClicked(x, y, m);
                  }

                  return;
               }

               c4t = (cm)var4.next();
               if (c4t.v(x, y) && !c4t.i(x, y) && !c4t.d(x, y) && m == 0) {
                  c4t.d(true);
                  c4t.xx = x - c4t.gx();
                  c4t.yy = y - c4t.gy();
               }

               if (c4t.d(x, y) && m == 0) {
                  c4t.oo(!c4t.fv());
               }

               if (c4t.i(x, y) && m == 0) {
                  c4t.cv(!c4t.p());
               }
            } while(!c4t.fv());
         } while(c4t.gc().isEmpty());

         for (b c : c4t.gc()) {
            c.onCl1ck(x, y, m);
         }
      }
   }

   public void mouseReleased(int x, int y, int s) {
      if (s == 0) {
         Iterator var4 = c4tL1st.iterator();

         cm c4t;
         while(var4.hasNext()) {
            c4t = (cm)var4.next();
            c4t.d(false);
         }

         var4 = c4tL1st.iterator();

         while(true) {
            do {
               do {
                  if (!var4.hasNext()) {
                     return;
                  }

                  c4t = (cm)var4.next();
               } while(!c4t.fv());
            } while(c4t.gc().isEmpty());

            for (b c : c4t.gc()) {
               c.mr(x, y, s);
            }
         }
      }
   }

   public void keyTyped(char t, int k) {
      if (k == 1) {
         this.mc.displayGuiScreen((GuiScreen)null);
      } else {
         Iterator var3 = c4tL1st.iterator();

         while(true) {
            cm c4t;
            do {
               do {
                  if (!var3.hasNext()) {
                     if (CommandLine.a) {
                        String cm = this.c.getText();
                        if (k == 28 && !cm.isEmpty()) {
                           gc.rCMD(this.c.getText());
                           this.c.setText("");
                           return;
                        }

                        this.c.textboxKeyTyped(t, k);
                     }

                     return;
                  }

                  c4t = (cm)var3.next();
               } while(!c4t.fv());
            } while(c4t.gc().isEmpty());

            for (b c : c4t.gc()) {
               c.ky(t, k);
            }
         }
      }
   }

   public void actionPerformed(GuiButton b) {
      if (b == this.s) {
         gc.rCMD(this.c.getText());
         this.c.setText("");
      }

   }

   public void onGuiClosed() {
      ab.ss();
      this.aL = null;
      if (this.sf != null) {
         this.sf.cancel(true);
         this.sf = null;
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }
}
