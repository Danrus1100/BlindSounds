package org.kilka.blindsounds.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import org.kilka.blindsounds.client.Config;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin<S extends EntityRenderState> {
    @WrapMethod(method = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;submit(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;Lnet/minecraft/client/renderer/state/level/CameraRenderState;DDDLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;)V")
    private void EntityFucker(S renderState, CameraRenderState cameraState, double offsetX, double offsetY, double offsetZ, PoseStack matrices, SubmitNodeCollector queue, Operation<Void> original) {

        if(!Config.get().modEnabled || !Config.get().mobsEnabled) {
            original.call(renderState, cameraState, offsetX, offsetY, offsetZ, matrices, queue);
            return;
        }

        if (!(renderState instanceof LivingEntityRenderState) || (renderState instanceof AvatarRenderState)) {
            original.call(renderState, cameraState, offsetX, offsetY, offsetZ, matrices, queue);
        }
    }
}
