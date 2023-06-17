package com.github.mim1q.convenientdecor.client.render.umbrella;

import com.github.mim1q.convenientdecor.block.UmbrellaStandBlock;
import com.github.mim1q.convenientdecor.block.blockentity.UmbrellaStandBlockEntity;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaRenderer.UmbrellaRenderHelper;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;

public class UmbrellaStandBlockEntityRenderer implements BlockEntityRenderer<UmbrellaStandBlockEntity> {
  private final UmbrellaRenderHelper umbrellaRenderers;

  public UmbrellaStandBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    umbrellaRenderers = new UmbrellaRenderHelper(ctx.getLayerRenderDispatcher());
  }

  @Override
  public void render(UmbrellaStandBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    ItemStack stack = entity.getStack();
    int rotation = entity.getCachedState().get(UmbrellaStandBlock.ROTATION);

    if (stack.getItem() instanceof UmbrellaItem umbrella) {
      matrices.push();

      matrices.translate(0.5F, 0.0F, 0.5F);
      matrices.multiply(Vec3f.NEGATIVE_Y.getDegreesQuaternion(90.0F + rotation * 22.5F));
      matrices.translate(-0.4F, 1.775F, 0.0F);
      matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(200.0F));
      matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0F));

      umbrellaRenderers.getRenderer(stack).render(
        matrices,
        vertexConsumers,
        light,
        overlay,
        umbrella.color,
        true
      );
      matrices.pop();
    }
  }
}
