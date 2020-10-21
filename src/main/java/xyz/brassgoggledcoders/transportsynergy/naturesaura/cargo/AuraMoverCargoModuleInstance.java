package xyz.brassgoggledcoders.transportsynergy.naturesaura.cargo;

import de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.transport.api.cargo.CargoModule;
import xyz.brassgoggledcoders.transport.api.cargo.CargoModuleInstance;
import xyz.brassgoggledcoders.transport.api.entity.IModularEntity;

import java.util.ArrayList;
import java.util.List;

;

public class AuraMoverCargoModuleInstance extends CargoModuleInstance {

    private final List<BlockPos> spotOffsets = new ArrayList<>();
    public boolean isActive;
    private BlockPos lastPosition;

    public AuraMoverCargoModuleInstance(CargoModule cargoModule, IModularEntity modularEntity) {
        super(cargoModule, modularEntity);
    }

    @Override
    public void tick() {
        if (this.isActive) {
            World world = this.getModularEntity().getTheWorld();
            BlockPos pos = this.getModularEntity().getSelf().getPosition();
            if (!this.spotOffsets.isEmpty() && this.getModularEntity().getTheWorld().getGameTime() % 10L == 0L) {

            }

            if (pos.distanceSq(this.lastPosition) >= 64.0D) {
                this.moveAura(world, this.lastPosition, world, pos);
                this.lastPosition = pos;
            }
        }
    }

    private void moveAura(World oldWorld, BlockPos oldPos, World newWorld, BlockPos newPos) {
        for (BlockPos offset : this.spotOffsets) {
            BlockPos spot = oldPos.add(offset);
            IAuraChunk chunk = IAuraChunk.getAuraChunk(oldWorld, spot);
            int amount = chunk.getDrainSpot(spot);
            if (amount > 0) {
                int toMove = Math.min(amount, 300000);
                int drained = chunk.drainAura(spot, toMove, false, false);
                if (drained > 0) {
                    int toLose = MathHelper.ceil((float) drained / 250.0F);
                    BlockPos newSpot = newPos.add(offset);
                    IAuraChunk.getAuraChunk(newWorld, newSpot).storeAura(newSpot, drained - toLose, false, false);
                }
            }
        }
    }

    @Override
    public void onActivatorPass(boolean receivingPower) {
        if (this.isActive != receivingPower) {
            this.isActive = receivingPower;
            if (!this.isActive) {
                this.spotOffsets.clear();
                this.lastPosition = BlockPos.ZERO;
                return;
            }

            BlockPos pos = this.getModularEntity().getSelf().getPosition();
            IAuraChunk.getSpotsInArea(this.getModularEntity().getTheWorld(), pos, 25, (spot, amount) -> {
                if (amount > 0) {
                    this.spotOffsets.add(spot.subtract(pos));
                }

            });
            this.lastPosition = pos;
        }
    }
}
