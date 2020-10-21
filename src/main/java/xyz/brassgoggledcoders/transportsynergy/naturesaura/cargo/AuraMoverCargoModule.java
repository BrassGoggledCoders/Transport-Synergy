package xyz.brassgoggledcoders.transportsynergy.naturesaura.cargo;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.brassgoggledcoders.transport.api.cargo.CargoModule;
import xyz.brassgoggledcoders.transport.api.cargo.CargoModuleInstance;
import xyz.brassgoggledcoders.transport.api.entity.IModularEntity;
import xyz.brassgoggledcoders.transportsynergy.naturesaura.TransportNaturesAura;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class AuraMoverCargoModule extends CargoModule {
    public AuraMoverCargoModule(Supplier<? extends Block> blockSupplier, BiFunction<CargoModule, IModularEntity, ? extends CargoModuleInstance> cargoInstanceCreator) {
        super(blockSupplier, cargoInstanceCreator);
    }

    @Nonnull
    @Override
    public Item asItem() {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(TransportNaturesAura.NA_ID, "mover_cart"));
    }
}
