package net.matteo.elitemobs_ftb_patch.mixins;

import net.tonicarter.elitemobs.spawn.EliteSpawner;

import dev.ftb.mods.ftbchunks.api.ClaimedChunk;
import dev.ftb.mods.ftbchunks.data.ClaimedChunkManagerImpl;

import dev.ftb.mods.ftblibrary.math.ChunkDimPos;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(EliteSpawner.class)
public class EliteSpawnerMixin {

    @ModifyReturnValue(method = "findSafeSpawnNear", at = @At(value = "RETURN"))
    private static Optional<BlockPos> tick$elitemobs_ftb_patch(Optional<BlockPos> original, ServerLevel level) {
        if (original.isEmpty()) return original;
        ClaimedChunk claim = ClaimedChunkManagerImpl.getInstance().getChunk(new ChunkDimPos(level, original.get()));

        return claim == null ? original : Optional.empty();
    }
}
