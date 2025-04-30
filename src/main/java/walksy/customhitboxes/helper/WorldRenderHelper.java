package walksy.customhitboxes.helper;

import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import walksy.customhitboxes.layers.ModRenderLayers;
import walksy.customhitboxes.manager.HitboxManager;

import java.awt.*;

import static walksy.customhitboxes.CustomHitboxesMod.mc;

public class WorldRenderHelper {

    public static void renderOutlinedBox(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity, Color color, Color colorGradient, float lineWidth) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());

        int alphaMinus = HitboxManager.hitboxMinus(entity);
        float red = color.getRed() / 255F;
        float green = color.getGreen() / 255F;
        float blue = color.getBlue() / 255F;
        float alpha = (Math.max(0, color.getAlpha() - alphaMinus)) / 255F;

        float redGradient = colorGradient.getRed() / 255F;
        float greenGradient = colorGradient.getGreen() / 255F;
        float blueGradient = colorGradient.getBlue() / 255F;
        float alphaGradient = (Math.max(0, colorGradient.getAlpha() - alphaMinus)) / 255F;

        if (alphaGradient == 0 && alpha == 0) return;

        float f = (float) box.maxX;
        float g = (float) box.maxY;
        float h = (float) box.maxZ;
        float i = (float) box.minX;
        float j = (float) box.minY;
        float k = (float) box.minZ;

        matrices.push();
        matrices.translate(mc.getCameraEntity().getX(), mc.getCameraEntity().getY(), mc.getCameraEntity().getZ());

        VertexConsumer consumer = vertexConsumers.getBuffer(ModRenderLayers.getOutlinedHitboxLayer(lineWidth));


        consumer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        consumer.vertex(matrix4f, f, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);

        consumer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        consumer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        consumer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        consumer.vertex(matrix4f, i, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);

        consumer.vertex(matrix4f, i, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), -1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, f, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), -1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, f, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        consumer.vertex(matrix4f, f, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);

        consumer.vertex(matrix4f, f, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, -1.0F, 0.0F);
        consumer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, -1.0F, 0.0F);
        consumer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);

        consumer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, -1.0F);
        consumer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, -1.0F);
        consumer.vertex(matrix4f, f, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, i, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);

        consumer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        consumer.vertex(matrix4f, i, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        consumer.vertex(matrix4f, i, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        consumer.vertex(matrix4f, i, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);

        matrices.pop();
    }

    public static void renderOutlinedBox(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Box box, Color color, Color colorGradient, float lineWidth) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();

        float red = color.getRed() / 255F;
        float green = color.getGreen() / 255F;
        float blue = color.getBlue() / 255F;
        float alpha = color.getAlpha() / 255F;

        float redGradient = colorGradient.getRed() / 255F;
        float greenGradient = colorGradient.getGreen() / 255F;
        float blueGradient = colorGradient.getBlue() / 255F;
        float alphaGradient = colorGradient.getAlpha() / 255F;

        if (alphaGradient == 0 && alpha == 0) return;

        float f = (float) box.maxX;
        float g = (float) box.maxY;
        float h = (float) box.maxZ;
        float i = (float) box.minX;
        float j = (float) box.minY;
        float k = (float) box.minZ;

        matrices.push();
        matrices.translate(mc.getCameraEntity().getX(), mc.getCameraEntity().getY(), mc.getCameraEntity().getZ());

        VertexConsumer consumer = vertexConsumers.getBuffer(ModRenderLayers.getOutlinedHitboxLayer(lineWidth));


        consumer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        consumer.vertex(matrix4f, f, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);

        consumer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        consumer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        consumer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        consumer.vertex(matrix4f, i, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);

        consumer.vertex(matrix4f, i, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), -1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, f, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), -1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, f, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        consumer.vertex(matrix4f, f, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);

        consumer.vertex(matrix4f, f, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, -1.0F, 0.0F);
        consumer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, -1.0F, 0.0F);
        consumer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);

        consumer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, -1.0F);
        consumer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 0.0F, -1.0F);
        consumer.vertex(matrix4f, f, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);
        consumer.vertex(matrix4f, i, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 1.0F, 0.0F, 0.0F);

        consumer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        consumer.vertex(matrix4f, i, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 1.0F, 0.0F);
        consumer.vertex(matrix4f, i, j, h).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);
        consumer.vertex(matrix4f, i, j, k).color(redGradient, greenGradient, blueGradient, alphaGradient).normal(matrices.peek(), 0.0F, 0.0F, 1.0F);

        matrices.pop();
    }

    public static void renderFilledBox(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity, Color color) {
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix = entry.getPositionMatrix();
        Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());
        int alphaMinus = HitboxManager.hitboxMinus(entity);

        float red = color.getRed() / 255F;
        float green = color.getGreen() / 255F;
        float blue = color.getBlue() / 255F;
        float alpha = Math.max(0, color.getAlpha() - alphaMinus) / 255F;

        float x1 = (float) box.minX;
        float y1 = (float) box.minY;
        float z1 = (float) box.minZ;
        float x2 = (float) box.maxX;
        float y2 = (float) box.maxY;
        float z2 = (float) box.maxZ;

        matrices.push();
        matrices.translate(-entity.getX(), -entity.getY(), -entity.getZ());

        VertexConsumer buffer = vertexConsumers.getBuffer(ModRenderLayers.FILLED_HITBOX);

        //Bottom face
        buffer.vertex(matrix, x1, y1, z1).color(red, green, blue, alpha);
        buffer.vertex(matrix, x2, y1, z1).color(red, green, blue, alpha);
        buffer.vertex(matrix, x2, y1, z2).color(red, green, blue, alpha);
        buffer.vertex(matrix, x1, y1, z2).color(red, green, blue, alpha);

        //Top face
        buffer.vertex(matrix, x1, y2, z2).color(red, green, blue, alpha);
        buffer.vertex(matrix, x2, y2, z2).color(red, green, blue, alpha);
        buffer.vertex(matrix, x2, y2, z1).color(red, green, blue, alpha);
        buffer.vertex(matrix, x1, y2, z1).color(red, green, blue, alpha);

        //Front face
        buffer.vertex(matrix, x1, y1, z2).color(red, green, blue, alpha);
        buffer.vertex(matrix, x2, y1, z2).color(red, green, blue, alpha);
        buffer.vertex(matrix, x2, y2, z2).color(red, green, blue, alpha);
        buffer.vertex(matrix, x1, y2, z2).color(red, green, blue, alpha);

        //Back face
        buffer.vertex(matrix, x2, y1, z1).color(red, green, blue, alpha);
        buffer.vertex(matrix, x1, y1, z1).color(red, green, blue, alpha);
        buffer.vertex(matrix, x1, y2, z1).color(red, green, blue, alpha);
        buffer.vertex(matrix, x2, y2, z1).color(red, green, blue, alpha);

        //Left face
        buffer.vertex(matrix, x1, y1, z1).color(red, green, blue, alpha);
        buffer.vertex(matrix, x1, y1, z2).color(red, green, blue, alpha);
        buffer.vertex(matrix, x1, y2, z2).color(red, green, blue, alpha);
        buffer.vertex(matrix, x1, y2, z1).color(red, green, blue, alpha);

        //Right face
        buffer.vertex(matrix, x2, y1, z2).color(red, green, blue, alpha);
        buffer.vertex(matrix, x2, y1, z1).color(red, green, blue, alpha);
        buffer.vertex(matrix, x2, y2, z1).color(red, green, blue, alpha);
        buffer.vertex(matrix, x2, y2, z2).color(red, green, blue, alpha);

        matrices.pop();
    }

    public static void renderFilledBox(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Box box, Color colorTop, Color colorBottom) {
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix = entry.getPositionMatrix();

        float rTop = colorTop.getRed() / 255F;
        float gTop = colorTop.getGreen() / 255F;
        float bTop = colorTop.getBlue() / 255F;
        float aTop = colorTop.getAlpha() / 255F;

        float rBottom = colorBottom.getRed() / 255F;
        float gBottom = colorBottom.getGreen() / 255F;
        float bBottom = colorBottom.getBlue() / 255F;
        float aBottom = colorBottom.getAlpha() / 255F;

        float x1 = (float) box.minX;
        float y1 = (float) box.minY;
        float z1 = (float) box.minZ;
        float x2 = (float) box.maxX;
        float y2 = (float) box.maxY;
        float z2 = (float) box.maxZ;

        matrices.push();
        VertexConsumer buffer = vertexConsumers.getBuffer(ModRenderLayers.FILLED_HITBOX);

        // Bottom face (bottom color)
        buffer.vertex(matrix, x1, y1, z1).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x2, y1, z1).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x2, y1, z2).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x1, y1, z2).color(rBottom, gBottom, bBottom, aBottom);

        // Top face (top color)
        buffer.vertex(matrix, x1, y2, z2).color(rTop, gTop, bTop, aTop);
        buffer.vertex(matrix, x2, y2, z2).color(rTop, gTop, bTop, aTop);
        buffer.vertex(matrix, x2, y2, z1).color(rTop, gTop, bTop, aTop);
        buffer.vertex(matrix, x1, y2, z1).color(rTop, gTop, bTop, aTop);

        // Front face (bottom -> top)
        buffer.vertex(matrix, x1, y1, z2).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x2, y1, z2).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x2, y2, z2).color(rTop, gTop, bTop, aTop);
        buffer.vertex(matrix, x1, y2, z2).color(rTop, gTop, bTop, aTop);

        // Back face
        buffer.vertex(matrix, x2, y1, z1).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x1, y1, z1).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x1, y2, z1).color(rTop, gTop, bTop, aTop);
        buffer.vertex(matrix, x2, y2, z1).color(rTop, gTop, bTop, aTop);

        // Left face
        buffer.vertex(matrix, x1, y1, z1).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x1, y1, z2).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x1, y2, z2).color(rTop, gTop, bTop, aTop);
        buffer.vertex(matrix, x1, y2, z1).color(rTop, gTop, bTop, aTop);

        // Right face
        buffer.vertex(matrix, x2, y1, z2).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x2, y1, z1).color(rBottom, gBottom, bBottom, aBottom);
        buffer.vertex(matrix, x2, y2, z1).color(rTop, gTop, bTop, aTop);
        buffer.vertex(matrix, x2, y2, z2).color(rTop, gTop, bTop, aTop);

        matrices.pop();
    }



    public static void drawVector(MatrixStack matrices, VertexConsumer vertexConsumers, Vector3f offset, Vec3d vec) {
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
}
