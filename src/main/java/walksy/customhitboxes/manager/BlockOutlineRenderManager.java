package walksy.customhitboxes.manager;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.shape.VoxelShape;
import walksy.customhitboxes.config.ConfigIntegration;
import walksy.customhitboxes.helper.WorldRenderHelper;

import java.awt.*;

public class BlockOutlineRenderManager {

    public static void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, VoxelShape shape) {
        //TODO RAINBOW
        if (!ConfigIntegration.CONFIG.instance().blockHoveringFilledHitbox) {
            WorldRenderHelper.renderOutlinedBox(
                    matrixStack,
                    vertexConsumers,
                    shape.getBoundingBox(),
                    ConfigIntegration.CONFIG.instance().blockHoveringColor,
                    ConfigIntegration.CONFIG.instance().blockHoveringColorGradientEnabled
                            ? ConfigIntegration.CONFIG.instance().blockHoveringColorGradient
                            : ConfigIntegration.CONFIG.instance().blockHoveringColor,
                    (float) ConfigIntegration.CONFIG.instance().blockHoveringBoxThickness
            );
        } else {
            WorldRenderHelper.renderFilledBox(
                    matrixStack,
                    vertexConsumers,
                    shape.getBoundingBox(),
                    //TODO, have separate gradient enable options for filled
                    //TODO, implement these gradients for the entity hitboxes too
                    ConfigIntegration.CONFIG.instance().blockHoveringColor,
                    ConfigIntegration.CONFIG.instance().blockHoveringColorGradientEnabled
                            ? ConfigIntegration.CONFIG.instance().blockHoveringColorGradient
                            : ConfigIntegration.CONFIG.instance().blockHoveringColor,
            );
        }
    }
}
