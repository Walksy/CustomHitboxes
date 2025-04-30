package walksy.customhitboxes.config.setting;

import dev.isxander.yacl3.config.v2.api.SerialEntry;

import java.awt.*;

public class Settings {

    @SerialEntry public boolean shouldRender = false;
    @SerialEntry public boolean renderLookVector = false;
    @SerialEntry public boolean serverSideRender = false;
    @SerialEntry public Color color = Color.WHITE;
    @SerialEntry public boolean colorGradientEnabled = false;
    @SerialEntry public Color colorGradient = Color.WHITE;
    @SerialEntry public double boxThickness = 1;
    @SerialEntry public boolean vanishWhenClose = false;
    @SerialEntry public boolean vanishFade = false;
    @SerialEntry public double vanishDistance = 5;
    @SerialEntry public boolean filledHitbox = false;
    @SerialEntry public Color filledHitboxColor = Color.WHITE;
    @SerialEntry public boolean changeColorOnDamageTick = false;
    @SerialEntry public Color damageTickColor = Color.WHITE;
    @SerialEntry public boolean changeColorWhenClose = false;
    @SerialEntry public Color closeColor = Color.WHITE;
    @SerialEntry public double closeDistance = 5;
    @SerialEntry public boolean rainbow = false;

    //player specific - request from Quelchi <3
    @SerialEntry public boolean elytraAlwaysRender = false;

    public Settings copy() {
        Settings copy = new Settings();

        copy.shouldRender = this.shouldRender;
        copy.renderLookVector = this.renderLookVector;
        copy.serverSideRender = this.serverSideRender;
        copy.color = new Color(this.color.getRGB(), true);
        copy.colorGradientEnabled = this.colorGradientEnabled;
        copy.colorGradient = new Color(this.colorGradient.getRGB(), true);
        copy.boxThickness = this.boxThickness;
        copy.vanishWhenClose = this.vanishWhenClose;
        copy.vanishFade = this.vanishFade;
        copy.vanishDistance = this.vanishDistance;
        copy.filledHitbox = this.filledHitbox;
        copy.filledHitboxColor = new Color(this.filledHitboxColor.getRGB(), true);
        copy.changeColorOnDamageTick = this.changeColorOnDamageTick;
        copy.damageTickColor = new Color(this.damageTickColor.getRGB(), true);
        copy.changeColorWhenClose = this.changeColorWhenClose;
        copy.closeColor = new Color(this.closeColor.getRGB(), true);
        copy.closeDistance = this.closeDistance;
        copy.rainbow = this.rainbow;
        copy.elytraAlwaysRender = this.elytraAlwaysRender;

        return copy;
    }

    public enum Type
    {
        PLAYER("Player"),
        END_CRYSTAL("End Crystal"),
        PROJECTILE("Projectile"),
        ENDER_PEARL("Ender Pearl"),
        HOSTILE_MOB("Hostile Mobs"),
        PASSIVE_MOB("Passive Mobs"),
        ANGERABLE_MOB("Angerable Mobs"),
        BLOCK_ENTITY("Block Entities"),
        ITEM("Item Entities"),
        OTHER("Other Entities");


        public final String id;

        Type(String id)
        {
            this.id = id;
        }
    }
}
