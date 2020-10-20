package xyz.brassgoggledcoders.transportsynergy.naturesaura.engine;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import net.minecraft.util.math.BlockPos;
import xyz.brassgoggledcoders.transport.api.engine.EngineModule;
import xyz.brassgoggledcoders.transport.api.engine.EngineModuleInstance;
import xyz.brassgoggledcoders.transport.api.engine.PoweredState;
import xyz.brassgoggledcoders.transport.api.entity.IModularEntity;

public class AuraEngineInstance extends EngineModuleInstance {
    private int burnTime = 0;

    public AuraEngineInstance(EngineModule engineModule, IModularEntity componentCarrier) {
        super(engineModule, componentCarrier);
    }

    @Override
    public boolean isRunning() {
        return burnTime > 0;
    }

    @Override
    public double getMaximumSpeed() {
        return 0.25D;
    }

    @Override
    public void tick() {
        if (!this.getModularEntity().getTheWorld().isRemote()) {
            if (burnTime > 0) {
                switch (this.getPoweredState()) {
                    case RUNNING:
                        burnTime--;
                    case IDLE:
                        burnTime--;
                        break;
                    default:
                        break;
                }
            }

            if (burnTime <= 0) {
                burnTime = 0;
                if (this.getPoweredState() == PoweredState.RUNNING) {
                    BlockPos position = this.getModularEntity().getSelf().getPosition();
                    burnTime = this.getModularEntity().getTheWorld().getChunkAt(position)
                            .getCapability(NaturesAuraAPI.capAuraChunk)
                            .map(auraChunk -> auraChunk.drainAura(position, 500) / 25)
                            .orElse(0);
                }
            }
        }
    }
}
