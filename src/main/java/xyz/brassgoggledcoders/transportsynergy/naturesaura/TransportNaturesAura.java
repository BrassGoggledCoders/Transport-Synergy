package xyz.brassgoggledcoders.transportsynergy.naturesaura;

import com.tterrag.registrate.util.entry.RegistryEntry;
import xyz.brassgoggledcoders.transport.api.engine.EngineModule;
import xyz.brassgoggledcoders.transportsynergy.TransportSynergy;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.engine.AuraEngineInstance;
import xyz.brassgoggledcoders.transportsynergy.util.SynergyCompat;

public class TransportNaturesAura extends SynergyCompat {
    public static final RegistryEntry<EngineModule> AURA_ENGINE = TransportSynergy.getRegistrate()
            .object("aura")
            .engineModule(AuraEngineInstance::new)
            .lang("Aura Engine")
            .item("aura_engine")
            .build()
            .register();

    public void construct() {

    }
}
