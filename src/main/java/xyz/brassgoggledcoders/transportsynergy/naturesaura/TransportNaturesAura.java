package xyz.brassgoggledcoders.transportsynergy.naturesaura;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.brassgoggledcoders.transport.api.cargo.CargoModule;
import xyz.brassgoggledcoders.transport.api.engine.EngineModule;
import xyz.brassgoggledcoders.transport.recipe.ActualNBTIngredient;
import xyz.brassgoggledcoders.transportsynergy.TransportSynergy;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.cargo.AuraAttractorCargoModuleInstance;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.engine.AuraEngineInstance;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.renderer.AuraModelModuleRenderer;
import xyz.brassgoggledcoders.transportsynergy.util.SynergyCompat;

import javax.annotation.Nonnull;
import java.util.Objects;

@SuppressWarnings("unused")
public class TransportNaturesAura extends SynergyCompat {
    public static final String NA_ID = "naturesaura";

    public static final RegistryEntry<EngineModule> AURA_ENGINE = TransportSynergy.getRegistrate()
            .object("aura")
            .engineModule(AuraEngineInstance::new)
            .lang("Aura Engine")
            .item("aura_engine")
            .recipe((context, provider) -> {
                        ItemStack auraBottle = new ItemStack(getNAItem("aura_bottle"));
                        auraBottle.setTagInfo("stored_type", StringNBT.valueOf(NA_ID + ":overworld"));
                        ShapelessRecipeBuilder.shapelessRecipe(context.get())
                                .addIngredient(Tags.Items.DUSTS_REDSTONE)
                                .addIngredient(new ActualNBTIngredient(auraBottle))
                                .addIngredient(getNAItem("infused_iron"))
                                .addIngredient(getNAItem("infused_iron"))
                                .addCriterion("has_item", RegistrateRecipeProvider.hasItem(getNAItem("aura_bottle")))
                                .build(provider);
                    }
            )
            .build()
            .register();

    public static final RegistryEntry<Item> AURA_ATTRACTION_ITEM = TransportSynergy.getRegistrate()
            .object("aura_attraction")
            .item(Item::new)
            .lang("Aura Attraction Cargo")
            .model((context, provider) -> provider.withExistingParent("aura_attraction", provider.mcLoc("block/cube_column"))
                    .texture("side", provider.modLoc("item/aura_attraction_side"))
                    .texture("end", provider.modLoc("item/aura_attraction_end"))
            )
            .recipe((context, provider) -> {
                ShapelessRecipeBuilder.shapelessRecipe(getNAItem("mover_cart"))
                        .addIngredient(context.get())
                        .addIngredient(Items.MINECART)
                        .addCriterion("has_item", RegistrateRecipeProvider.hasItem(Items.MINECART))
                        .build(provider, TransportSynergy.rl("mover_cart"));
                ItemStack endAuraBottle = new ItemStack(getNAItem("aura_bottle"));
                endAuraBottle.setTagInfo("stored_type", StringNBT.valueOf(NA_ID + ":end"));
                ShapedRecipeBuilder.shapedRecipe(context.get())
                        .patternLine("EBE")
                        .patternLine("S S")
                        .patternLine("XIX")
                        .key('E', Items.ENDER_EYE)
                        .key('B', new ActualNBTIngredient(endAuraBottle))
                        .key('S', getNAItem("sky_ingot"))
                        .key('I', getNAItem("infused_iron"))
                        .key('X', getNAItem("infused_brick"))
                        .addCriterion("has_item", RegistrateRecipeProvider.hasItem(Items.ENDER_EYE))
                        .build(provider);
            })
            .register();

    public static final RegistryEntry<CargoModule> ATTRACTOR_CARGO = TransportSynergy.getRegistrate()
            .object("aura_attractor")
            .cargoModule(() -> CargoModule.fromItem(AURA_ATTRACTION_ITEM::get, AuraAttractorCargoModuleInstance::new, true))
            .renderer(() -> AuraModelModuleRenderer::new)
            .register();

    public void construct() {

    }

    @Nonnull
    public static Item getNAItem(String path) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(NA_ID, path)));
    }
}
