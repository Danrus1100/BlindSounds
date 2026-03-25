package org.kilka.blindsounds.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundEvent;
import org.kilka.blindsounds.client.Config;
import org.kilka.blindsounds.client.SoundIndicatorManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEnitityMixin {

    @Inject(method = "makeSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"))
    private void onSoundPlayed(SoundEvent sound, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        Minecraft client = Minecraft.getInstance();

        if (client.player == null) return;
        if(!Config.get().modEnabled) return;

        SoundIndicatorManager.add(entity);
    }

}
