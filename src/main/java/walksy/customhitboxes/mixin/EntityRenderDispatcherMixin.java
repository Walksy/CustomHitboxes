package walksy.customhitboxes.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import walksy.customhitboxes.CustomHitboxesMod;
import walksy.customhitboxes.config.ConfigIntegration;
import walksy.customhitboxes.manager.HitboxRenderManager;

import static walksy.customhitboxes.CustomHitboxesMod.mc;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    @Shadow public abstract <T extends Entity> EntityRenderer<? super T> getRenderer(T entity);

    //Must render at head before matrix manipulation instead of at:
    //Lnet/minecraft/client/render/entity/EntityRenderDispatcher;renderHitbox(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/entity/Entity;FFFF)V
    //Therefore we make our own checks

    //Must be injected at the tail to ensure entities finishing getting rendered before we implement our own code
    @Inject(method = "render", at = @At(value = "TAIL"))
    private <E extends Entity> void renderHitboxes(E entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci)
    {
        if (!ConfigIntegration.CONFIG.instance().modEnabled || !mc.getEntityRenderDispatcher().shouldRenderHitboxes() || entity.isInvisible()) return;
        EntityRenderer entityRenderer = this.getRenderer(entity); //Used to get the position offset of the entity
        HitboxRenderManager.INSTANCE.renderHitbox(entity, entityRenderer, matrices, vertexConsumers, new Vec3d(x, y, z), tickDelta);

        //Redundant on public release
        CustomHitboxesMod.debugMessage("Rendering Entity: " + entity.getClass().getSimpleName());
    }

    @Inject(method = "renderHitbox", at = @At("HEAD"), cancellable = true)
    private static void renderHitboxes(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, float red, float green, float blue, CallbackInfo ci)
    {
        //We use our own rendering method
        if (!ConfigIntegration.CONFIG.instance().modEnabled) return;
        ci.cancel();
    }
}
