package walksy.customhitboxes.config;

import java.awt.*;

public class HitboxConfig {

    /**
     * No point encapsulating this class
     * The ui ensures values aren't set to a ridiculous value
     */

    public Color color;
    public Color colorGradient;
    public boolean colorGradientEnabled;
    public boolean render;
    public boolean renderLookVector;
    public boolean changeColorOnDamageTick;
    public Color damageTickColor;
    public double lineThickness;
    public boolean vanishWhenClose;
    public boolean vanishFade;
    public double vanishDistance;
    public boolean renderServerSide;
    public boolean filled;
    public Color filledColor;
    public boolean rainbow;

    public HitboxConfig(Color color, Color colorGradient, boolean colorGradientEnabled, boolean render,
                        boolean renderLookVector, boolean changeColorOnDamageTick, Color damageTickColor,
                        double lineThickness, boolean vanishWhenClose, boolean vanishFade, double vanishDistance,
                        boolean renderServerSide, boolean filled, Color filledColor, boolean rainbow) {
        this.color = color;
        this.colorGradient = colorGradient;
        this.colorGradientEnabled = colorGradientEnabled;
        this.render = render;
        this.renderLookVector = renderLookVector;
        this.changeColorOnDamageTick = changeColorOnDamageTick;
        this.damageTickColor = damageTickColor;
        this.lineThickness = lineThickness;
        this.vanishWhenClose = vanishWhenClose;
        this.vanishFade = vanishFade;
        this.vanishDistance = vanishDistance;
        this.renderServerSide = renderServerSide;
        this.filled = filled;
        this.filledColor = filledColor;
        this.rainbow = rainbow;
    }


}
