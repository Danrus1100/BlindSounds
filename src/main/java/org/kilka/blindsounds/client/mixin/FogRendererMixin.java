package org.kilka.blindsounds.client.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.kilka.blindsounds.client.BlindsoundsClient;
import org.kilka.blindsounds.client.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @ModifyExpressionValue(method = "Lnet/minecraft/client/renderer/fog/FogRenderer;setupFog(Lnet/minecraft/client/Camera;ILnet/minecraft/client/DeltaTracker;FLnet/minecraft/client/multiplayer/ClientLevel;)Lnet/minecraft/client/renderer/fog/FogData;", at = @At(value = "CONSTANT", args = "intValue=16"))
    private int fogFucker(int original) {
        if(!Config.get().modEnabled || !Config.get().fogEnabled) {
            return original;
        }
        return Config.get().blockFogRadius;
    }
}
