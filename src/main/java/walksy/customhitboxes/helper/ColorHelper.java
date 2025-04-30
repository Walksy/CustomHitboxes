package walksy.customhitboxes.helper;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

import static walksy.customhitboxes.CustomHitboxesMod.mc;

public class ColorHelper {

    //Taken from wurst
    public static Color getRainbowColor() {
        float x = System.currentTimeMillis() % 2000 / 1000F;
        float pi = (float) Math.PI;

        float r = 0.5F + 0.5F * MathHelper.sin(x * pi);
        float g = 0.5F + 0.5F * MathHelper.sin((x + 4F / 3F) * pi);
        float b = 0.5F + 0.5F * MathHelper.sin((x + 8F / 3F) * pi);

        return new Color(r, g, b);
    }

    /**
     * Lerps between two colors based on the distance from the player to the target entity
     *
     * @param color1 The initial color
     * @param color2 The target color
     * @param entity The target entity to lerp towards
     * @return The interpolated color based on the player's distance to the entity
     */
    public static Color lerpToColor(Color color1, Color color2, Entity entity, double fadeDistance) {
        double fadeStartDistance = fadeDistance * 1.5;
        double distanceToEntity = Math.sqrt(mc.gameRenderer.getCamera().getPos().squaredDistanceTo(entity.getPos()));

        if (distanceToEntity > fadeStartDistance) {
            return color1;
        }

        if (distanceToEntity <= fadeDistance) {
            return color2;
        }

        float t = (float) ((fadeStartDistance - distanceToEntity) / (fadeStartDistance - fadeDistance));
        t = MathHelper.clamp(t, 0.0f, 1.0f);

        int r = (int) (color1.getRed() + t * (color2.getRed() - color1.getRed()));
        int g = (int) (color1.getGreen() + t * (color2.getGreen() - color1.getGreen()));
        int b = (int) (color1.getBlue() + t * (color2.getBlue() - color1.getBlue()));
        int a = (int) (color1.getAlpha() + t * (color2.getAlpha() - color1.getAlpha()));

        return new Color(r, g, b, a);
    }

}

