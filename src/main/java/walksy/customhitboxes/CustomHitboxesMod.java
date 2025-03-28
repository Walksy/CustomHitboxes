package walksy.customhitboxes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import walksy.customhitboxes.config.ConfigIntegration;
import walksy.customhitboxes.manager.HitboxManager;



public class CustomHitboxesMod implements ModInitializer {

    public static MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onInitialize()
    {
        ConfigIntegration.CONFIG.load();

        //hooks
        HitboxManager.loadHook();
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> closeHook());
    }

    void closeHook()
    {
        //for some reason I'm forced to call the save procedure otherwise the config doesn't save
        //never had to do this before, probably something wrong with my config integration
        ConfigIntegration.CONFIG.save();
    }

    public static void debugMessage(Object message)
    {
        if (!ConfigIntegration.CONFIG.instance().debug) return;
        mc.inGameHud.getChatHud().addMessage(Text.of(String.valueOf(message)));
    }
}
