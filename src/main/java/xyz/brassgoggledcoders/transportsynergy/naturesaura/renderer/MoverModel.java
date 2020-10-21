package xyz.brassgoggledcoders.transportsynergy.naturesaura.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.ParametersAreNonnullByDefault;

public class MoverModel extends Model {

    private final ModelRenderer box;

    public MoverModel() {
        super(RenderType::getEntityCutout);
        this.box = new ModelRenderer(this, 0, 0);
        this.box.setTextureSize(64, 64);
        this.box.addBox(0, 0, 0, 16, 24, 16);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
                       float green, float blue, float alpha) {
        this.box.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
