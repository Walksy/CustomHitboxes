package walksy.customhitboxes.manager;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import walksy.customhitboxes.helper.WorldRenderHelper;
import walksy.customhitboxes.mixin.LivingEntityAccessor;

import java.awt.*;

import static walksy.customhitboxes.CustomHitboxesMod.mc;

public class HitboxRenderManager {

    public static HitboxRenderManager INSTANCE = new HitboxRenderManager();

    public void renderHitbox(Entity entity, EntityRenderer entityRenderer, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Vec3d original, float tickDelta)
    {
        if (!HitboxManager.shouldRender(entity)) return;
        Color c = HitboxManager.getColor(entity);
        Color filledC = HitboxManager.getFilledColor(entity);
        Color cG = HitboxManager.getColorGradient(entity);

        double lineThickness = HitboxManager.getLineThickness(entity);

        Vec3d vec3d = entityRenderer.getPositionOffset(entity, tickDelta);
        double d, e, f;
        Camera camera = mc.gameRenderer.getCamera();
        if (this.shouldRenderServerSide(entity)) {
            double sX = ((LivingEntityAccessor) entity).getServerX();
            double sY = ((LivingEntityAccessor) entity).getServerY();
            double sZ = ((LivingEntityAccessor) entity).getServerZ();
            //idek
            d = (sX - camera.getPos().getX()) + vec3d.getX();
            e = (sY - camera.getPos().getY()) + vec3d.getY();
            f = (sZ - camera.getPos().getZ()) + vec3d.getZ();
        } else {
            d = original.getX() + vec3d.getX();
            e = original.getY() + vec3d.getY();
            f = original.getZ() + vec3d.getZ();
        }
        matrices.push();
        matrices.translate(d, e, f);
        matrices.translate(-vec3d.getX(), -vec3d.getY(), -vec3d.getZ());

        if (HitboxManager.isFilled(entity))
        {
            WorldRenderHelper.renderFilledBox(matrices, vertexConsumers, entity, filledC);
        } else {
            WorldRenderHelper.renderOutlinedBox(matrices, vertexConsumers, entity, c, cG, (float) lineThickness);
        }
        if (HitboxManager.shouldRenderLookVector(entity))
        {
            WorldRenderHelper.drawVector(matrices, vertexConsumers.getBuffer(RenderLayer.getLines()), new Vector3f(0.0F, entity.getStandingEyeHeight(), 0.0F), entity.getRotationVec(tickDelta).multiply(2.0));
        }
        matrices.pop();
    }


    private boolean shouldRenderServerSide(Entity entity)
    {
        return (HitboxManager.shouldRenderServerSide(entity) && entity != mc.player && entity instanceof LivingEntity);
    }
}
