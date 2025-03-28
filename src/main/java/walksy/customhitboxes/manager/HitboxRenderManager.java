package walksy.customhitboxes.manager;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
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

        Vec3d vec3d = entity.getPos();
        double d, e, f;
        if (this.shouldRenderServerSide(entity)) {
            double sX = ((LivingEntityAccessor) entity).getServerX();
            double sY = ((LivingEntityAccessor) entity).getServerY();
            double sZ = ((LivingEntityAccessor) entity).getServerZ();
            Camera camera = mc.gameRenderer.getCamera();
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
            //draw must also be called, since the filled box method doesn't fully enclose the hitbox and leaves gaps
            this.draw(matrices, entity, vertexConsumers, c, cG, lineThickness, tickDelta);
            this.drawFilledBox(matrices, entity, filledC);
        } else {
            this.draw(matrices, entity, vertexConsumers, c, cG, lineThickness, tickDelta);
        }
        matrices.pop();
    }

    //Code adapted from clearhitboxes
    private void draw(MatrixStack matrices, Entity entity, VertexConsumerProvider vertexConsumers, Color color, Color colorGradient, double lineWidth, float tickDelta) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());
        int alphaMinus = HitboxManager.hitboxMinus(entity); //gives a value to decrease the hitbox's alpha value by
        float red = color.getRed() / 255F;
        float green = color.getGreen() / 255F;
        float blue = color.getBlue() / 255F;
        float alpha = (Math.max(0, color.getAlpha() - alphaMinus)) / 255F; //ensures the alpha doesn't go below 0

        float redGradient = colorGradient.getRed() / 255F;
        float greenGradient = colorGradient.getGreen() / 255F;
        float blueGradient = colorGradient.getBlue() / 255F;
        float alphaGradient = (Math.max(0, colorGradient.getAlpha() - alphaMinus)) / 255F;
        if (alphaGradient == 0 && alpha == 0) return; //stop vertex calls & prevents weird transparent glitch
        float f = (float) box.maxX;
        float g = (float) box.maxY;
        float h = (float) box.maxZ;
        float i = (float) box.minX;
        float j = (float) box.minY;
        float k = (float) box.minZ;

        matrices.push();

        matrices.translate(mc.getCameraEntity().getX(), mc.getCameraEntity().getY(), mc.getCameraEntity().getZ());

        Tessellator tessellator = Tessellator.getInstance();
        RenderSystem.setShader(ShaderProgramKeys.POSITION_COLOR);
        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableCull();
        RenderSystem.depthMask(true);

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        //RenderSystem.applyModelViewMatrix();
        RenderSystem.setShader(ShaderProgramKeys.RENDERTYPE_LINES);
        RenderSystem.lineWidth((float) (2.5F * lineWidth));
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES);
        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        buffer.vertex(matrix4f, f, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);

        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        buffer.vertex(matrix4f, i, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);

        buffer.vertex(matrix4f, i, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), -1.0F, 0.0F, 0.0F);
        buffer.vertex(matrix4f, f, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), -1.0F, 0.0F, 0.0F);
        buffer.vertex(matrix4f, f, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        buffer.vertex(matrix4f, f, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);

        buffer.vertex(matrix4f, f, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, -1.0F, 0.0F);
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, -1.0F, 0.0F);
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);

        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, -1.0F);
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, -1.0F);
        buffer.vertex(matrix4f, f, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        buffer.vertex(matrix4f, i, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);

        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        buffer.vertex(matrix4f, i, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        buffer.vertex(matrix4f, i, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        buffer.vertex(matrix4f, i, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);

        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        BufferRenderer.drawWithGlobalProgram(buffer.end());
        matrices.pop();



        if (HitboxManager.shouldRenderLookVector(entity))
        {
            this.drawVector(matrices, vertexConsumers.getBuffer(RenderLayer.getLines()), new Vector3f(0.0F, entity.getStandingEyeHeight(), 0.0F), entity.getRotationVec(tickDelta).multiply(2.0));
        }
    }

    /**
     * Incompatible with mods which override OpenGL, such as vulcan
     * The procedure above still works
     * Vulcan seemed to of only overrided Line formats and not Quad + Position color
     */

    private void drawFilledBox(MatrixStack matrices, Entity entity, Color color) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());
        int alphaMinus = HitboxManager.hitboxMinus(entity);
        float red = color.getRed() / 255F;
        float green = color.getGreen() / 255F;
        float blue = color.getBlue() / 255F;
        float alpha = (Math.max(0, color.getAlpha() - alphaMinus)) / 255F;


        float f = (float) box.maxX;
        float g = (float) box.maxY;
        float h = (float) box.maxZ;
        float i = (float) box.minX;
        float j = (float) box.minY;
        float k = (float) box.minZ;

        matrices.push();

        matrices.translate(mc.getCameraEntity().getX(), mc.getCameraEntity().getY(), mc.getCameraEntity().getZ());

        Tessellator tessellator = Tessellator.getInstance();

        RenderSystem.setShader(ShaderProgramKeys.POSITION_COLOR);
        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableCull();
        RenderSystem.depthMask(true);

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        //RenderSystem.applyModelViewMatrix();
        RenderSystem.setShader(ShaderProgramKeys.RENDERTYPE_LINES);
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        buffer.vertex(matrix4f, i, j, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, f, j, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, f, j, h).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, i, j, h).color(red, green, blue, alpha);

        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha);

        buffer.vertex(matrix4f, i, j, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, f, j, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha);

        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, i, j, h).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, f, j, h).color(red, green, blue, alpha);

        buffer.vertex(matrix4f, i, j, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, i, j, h).color(red, green, blue, alpha);

        buffer.vertex(matrix4f, f, j, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha);
        buffer.vertex(matrix4f, f, j, h).color(red, green, blue, alpha);

        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        BufferRenderer.drawWithGlobalProgram(buffer.end());
        matrices.pop();
    }


    private void drawVector(MatrixStack matrices, VertexConsumer vertexConsumers, Vector3f offset, Vec3d vec) {
        MatrixStack.Entry entry = matrices.peek();
        vertexConsumers
                .vertex(entry, offset).color(-16776961).normal(entry, (float)vec.x, (float)vec.y, (float)vec.z);
        vertexConsumers
                .vertex(
                        entry,
                        (float)((double)offset.x() + vec.x),
                        (float)((double)offset.y() + vec.y),
                        (float)((double)offset.z() + vec.z)).color(-16776961).normal(entry, (float)vec.x, (float)vec.y, (float)vec.z);
    }

    private boolean shouldRenderServerSide(Entity entity)
    {
        return (HitboxManager.shouldRenderServerSide(entity) && entity != mc.player && entity instanceof LivingEntity);
    }
}
