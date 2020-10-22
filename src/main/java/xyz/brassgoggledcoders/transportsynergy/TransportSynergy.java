package xyz.brassgoggledcoders.transportsynergy;

import com.google.common.collect.Maps;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.brassgoggledcoders.transport.Transport;
import xyz.brassgoggledcoders.transport.registrate.TransportRegistrate;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.TransportNaturesAura;
import xyz.brassgoggledcoders.transportsynergy.util.SynergyCompat;

import java.util.Map;
import java.util.function.Supplier;

@Mod(TransportSynergy.ID)
public class TransportSynergy {
    public static final String ID = "transport_synergy";

    private static final NonNullLazy<TransportRegistrate> REGISTRATE = NonNullLazy.of(() ->
            TransportRegistrate.create(ID)
                    .itemGroup(Transport.ITEM_GROUP::get)
    );

    private final Map<String, SynergyCompat> synergyCompat = Maps.newHashMap();

    public TransportSynergy() {
        Transport.setupRegistries();

        this.registerSynergyCompat("naturesaura", () -> TransportNaturesAura::new);
        this.synergyCompat.values().forEach(SynergyCompat::construct);

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        this.synergyCompat.values().forEach(compat -> compat.commonSetup(event));
    }

    public void registerSynergyCompat(String modId, Supplier<Supplier<SynergyCompat>> synergyCompat) {
        if (ModList.get().isLoaded(modId)) {
            this.synergyCompat.put(modId, synergyCompat.get().get());
        }
    }

    public static TransportRegistrate getRegistrate() {
        return REGISTRATE.get();
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(ID, path);
    }
}
