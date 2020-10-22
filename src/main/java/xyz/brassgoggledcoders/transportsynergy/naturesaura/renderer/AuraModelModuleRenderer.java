package xyz.brassgoggledcoders.transportsynergy.naturesaura.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import xyz.brassgoggledcoders.transport.api.module.ModuleInstance;
import xyz.brassgoggledcoders.transport.api.renderer.IModuleRenderer;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.TransportNaturesAura;

public class AuraModelModuleRenderer implements IModuleRenderer {
    private static final ResourceLocation RES = new ResourceLocation(TransportNaturesAura.NA_ID,
            "textures/models/mover_cart.png");
    private final MoverModel model = new MoverModel();

    @Override
    public void render(ModuleInstance<?> moduleInstance, float entityYaw, float partialTicks, MatrixStack matrixStack,
                       IRenderTypeBuffer buffer, int packedLight) {
        matrixStack.push();
        matrixStack.scale(0.75F, 0.75F, 0.75F);
        matrixStack.translate(-0.5D, -0.125, 0.5D);
        matrixStack.translate(0, 1.35, 0);
        matrixStack.rotate(Vector3f.XP.rotationDegrees(180));
        this.model.render(matrixStack, buffer.getBuffer(this.model.getRenderType(RES)), packedLight,
                OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        matrixStack.pop();
    }
}
