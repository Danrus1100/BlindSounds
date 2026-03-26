package org.kilka.blindsounds.client;

import net.fabricmc.api.ClientModInitializer;

public class BlindsoundsClient implements ClientModInitializer {

    public static String MOD_ID = "blindsounds";

    public static float darkness = 2.5f;


    @Override
    public void onInitializeClient() {
        Config.initialize();
    }
}
