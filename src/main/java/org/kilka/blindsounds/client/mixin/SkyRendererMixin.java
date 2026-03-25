package org.kilka.blindsounds.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.renderer.SkyRenderer;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.level.MoonPhase;
import org.kilka.blindsounds.client.BlindsoundsClient;
import org.kilka.blindsounds.client.Config;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SkyRenderer.class)
public class SkyRendererMixin {

    @WrapMethod(method = "renderSun")
    private void SKyNullifier(float alpha, PoseStack matrices, Operation<Void> original) {
        if(!Config.get().modEnabled) {
            original.call(alpha, matrices);
        }
    }

    @WrapMethod(method = "renderMoon")
    private void SKyNullifier(MoonPhase moonPhase, float alpha, PoseStack matrices, Operation<Void> original) {
        if(!Config.get().modEnabled) {
            original.call(moonPhase, alpha, matrices);
        }
    }
}
