package xyz.brassgoggledcoders.transportsynergy.naturesaura;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.brassgoggledcoders.transport.api.cargo.CargoModule;
import xyz.brassgoggledcoders.transport.api.engine.EngineModule;
import xyz.brassgoggledcoders.transportsynergy.TransportSynergy;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.cargo.AuraMoverCargoModule;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.cargo.AuraMoverCargoModuleInstance;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.engine.AuraEngineInstance;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.renderer.AuraModelModuleRenderer;
import xyz.brassgoggledcoders.transportsynergy.util.SynergyCompat;

import javax.annotation.Nonnull;

public class TransportNaturesAura extends SynergyCompat {
    public static final String NA_ID = "naturesaura";

    public static final RegistryEntry<EngineModule> AURA_ENGINE = TransportSynergy.getRegistrate()
            .object("aura")
            .engineModule(AuraEngineInstance::new)
            .lang("Aura Engine")
            .item("aura_engine")
            .build()
            .register();

    public static final RegistryEntry<AuraMoverCargoModule> AURA_MOVER = TransportSynergy.getRegistrate()
            .object("aura_mover")
            .cargoModule(() -> new AuraMoverCargoModule(() -> Blocks.GLASS, AuraMoverCargoModuleInstance::new))
            .lang("Mover Cargo")
            .renderer(() -> AuraModelModuleRenderer::new)
            .register();

    public void construct() {

    }
}
