package walksy.customhitboxes.layers;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;

import java.util.OptionalDouble;


public class ModRenderLayers {

    public static final RenderLayer FILLED_HITBOX = RenderLayer.of(
            "filled_hitbox",
            VertexFormats.POSITION_COLOR,
            VertexFormat.DrawMode.QUADS,
            256,
            false,
            true,
            RenderLayer.MultiPhaseParameters.builder()
                    .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
                    .depthTest(RenderPhase.LEQUAL_DEPTH_TEST)
                    .cull(RenderPhase.DISABLE_CULLING)
                    .program(RenderPhase.COLOR_PROGRAM)
                    .layering(RenderPhase.VIEW_OFFSET_Z_LAYERING)
                    .writeMaskState(RenderPhase.ALL_MASK)
                    .build(false)
    );

    public static RenderLayer getOutlinedHitboxLayer(float lineWidth) {
        return RenderLayer.of(
                "outlined_hitbox_dynamic",
                VertexFormats.LINES,
                VertexFormat.DrawMode.LINES,
                256,
                false,
                true,
                RenderLayer.MultiPhaseParameters.builder()
                        .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
                        .program(RenderPhase.LINES_PROGRAM)
                        .depthTest(RenderPhase.LEQUAL_DEPTH_TEST)
                        .cull(RenderPhase.DISABLE_CULLING)
                        .lineWidth(new RenderPhase.LineWidth(OptionalDouble.of(lineWidth)))
                        .build(false)
        );
    }
}
