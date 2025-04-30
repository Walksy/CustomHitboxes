package walksy.customhitboxes.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import walksy.customhitboxes.config.ConfigIntegration;
import walksy.customhitboxes.manager.BlockOutlineRenderManager;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {


    @Shadow @Final private BufferBuilderStorage bufferBuilders;

    @Shadow @Nullable private ClientWorld world;

    @Inject(method = "drawBlockOutline", at = @At("HEAD"), cancellable = true)
    public void overrideBlockOutline(MatrixStack matrices, VertexConsumer vertexConsumer, Entity entity, double cameraX, double cameraY, double cameraZ, BlockPos pos, BlockState state, CallbackInfo ci)
    {
        if (!ConfigIntegration.CONFIG.instance().blockHoveringEnabled) return;
        VertexConsumerProvider vertexConsumers = this.bufferBuilders.getEntityVertexConsumers();
        matrices.push();
        matrices.translate(pos.getX() - cameraX, (double)pos.getY() - cameraY, (double)pos.getZ() - cameraZ);
        BlockOutlineRenderManager.render(matrices, vertexConsumers, state.getOutlineShape(this.world, pos, ShapeContext.of(entity)));
        matrices.pop();
        ci.cancel();
    }
}
