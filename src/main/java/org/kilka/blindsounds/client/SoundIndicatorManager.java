package org.kilka.blindsounds.client;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoundIndicatorManager {
    private static final Map<LivingEntity, Long> INDICATORS = new ConcurrentHashMap<>();
    private static final long DURATION_MS = Config.get().soundMarkerDuration;
    public static void add(LivingEntity entity) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) return;

        double distance = client.player.position().distanceTo(entity.position());
        if (distance > Config.get().soundMarkersReaction) return;

        INDICATORS.put(entity, System.currentTimeMillis() + DURATION_MS);
    }

    public static Map<LivingEntity, Long> getActive() {
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<LivingEntity, Long>> it = INDICATORS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<LivingEntity, Long> entry = it.next();
            if (entry.getValue() <= now || !entry.getKey().isAlive()) {
                it.remove();
            }
        }
        return INDICATORS;
    }
}