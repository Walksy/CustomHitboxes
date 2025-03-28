package walksy.customhitboxes.helper;

import net.minecraft.util.math.MathHelper;

import java.awt.*;

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

}

