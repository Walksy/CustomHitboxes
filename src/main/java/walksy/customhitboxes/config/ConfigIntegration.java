package walksy.customhitboxes.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.gui.controllers.ColorController;
import dev.isxander.yacl3.gui.controllers.slider.DoubleSliderController;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.apache.commons.lang3.StringUtils;
import walksy.customhitboxes.CustomHitboxesMod;
import walksy.customhitboxes.config.impl.CategoryBank;
import walksy.customhitboxes.config.setting.Settings;
import walksy.customhitboxes.manager.HitboxManager;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ConfigIntegration {

    /**
     * This class is such a mess... I blame sakura for making me do this at 4am >:(
     */

    //TODO - teams

    public static final ConfigClassHandler<ConfigIntegration> CONFIG = ConfigClassHandler.createBuilder(ConfigIntegration.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("walksycustomhitboxes.json"))
                    .build())
            .build();
    //public static ImmutableList<ConfigCategory> CATEGORIES;

    /**
     * General Settings Entries
     */

    @SerialEntry public boolean modEnabled = true;
    @SerialEntry public boolean enabledOnGameLoad = true;
    @SerialEntry public boolean debug = false;


    /**
     * Block Hovering Settings Entries
     */

    @SerialEntry public boolean blockHoveringEnabled = false;
    @SerialEntry public double blockHoveringBoxThickness = 1;
    @SerialEntry public Color blockHoveringColor = Color.WHITE;
    @SerialEntry public boolean blockHoveringColorGradientEnabled = false;
    @SerialEntry public Color blockHoveringColorGradient = Color.WHITE;
    @SerialEntry public boolean blockHoveringFilledHitbox = false;
    @SerialEntry public Color blockHoveringFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean blockHoveringRainbow = false;


    /**
     * Team Entries
     */

    @SerialEntry public List<String> team1 = new ArrayList<>();
    @SerialEntry public Color team1Color = Color.WHITE;
    @SerialEntry public boolean team1ColorGradientEnabled = false;
    @SerialEntry public Color team1ColorGradient = Color.WHITE;

    @SerialEntry public List<String> team2 = new ArrayList<>();
    @SerialEntry public Color team2Color = Color.WHITE;
    @SerialEntry public boolean team2ColorGradientEnabled = false;
    @SerialEntry public Color team2ColorGradient = Color.WHITE;

    @SerialEntry public List<String> team3 = new ArrayList<>();
    @SerialEntry public Color team3Color = Color.WHITE;
    @SerialEntry public boolean team3ColorGradientEnabled = false;
    @SerialEntry public Color team3ColorGradient = Color.WHITE;

    @SerialEntry public List<String> team4 = new ArrayList<>();
    @SerialEntry public Color team4Color = Color.WHITE;
    @SerialEntry public boolean team4ColorGradientEnabled = false;
    @SerialEntry public Color team4ColorGradient = Color.WHITE;

    @SerialEntry public List<String> team5 = new ArrayList<>();
    @SerialEntry public Color team5Color = Color.WHITE;
    @SerialEntry public boolean team5ColorGradientEnabled = false;
    @SerialEntry public Color team5ColorGradient = Color.WHITE;

    @SerialEntry public boolean renderProjectiles = false;
    @SerialEntry public boolean recolorProjectiles = false;


    @SerialEntry
    public Map<String, Map<Settings.Type, Settings>> profiles = new LinkedHashMap<>();

    @SerialEntry
    public String currentProfile = "default";

    @SerialEntry
    public String profileToDelete = "";

    public String newProfile = "";
    public String copyFromProfile = "default";
    public String copyToProfile = "default";

    public Map<Settings.Type, Settings> getCurrentSettings() {
        return profiles.getOrDefault(currentProfile, new LinkedHashMap<>());
    }

    public ConfigIntegration()
    {
        if (!profiles.containsKey(currentProfile)) {
            Map<Settings.Type, Settings> settingsMap = new LinkedHashMap<>();
            for (Settings.Type type : Settings.Type.values()) {
                settingsMap.put(type, new Settings());
            }
            profiles.put(currentProfile, settingsMap);
        }
    }



    @SuppressWarnings("deprecation") //stop the compiler crying
    public static Screen createConfigScreen(Screen parent) {
        var screen = YetAnotherConfigLib.create(CONFIG, (defaults, config, builder) -> {
            builder.title(Text.literal("Custom Hitbox Config"));

            checkDefaultz(config);

            builder.category(CategoryBank.profile(config, defaults, parent));
            builder.category(CategoryBank.general(config, defaults));
            builder.category(CategoryBank.teams(config, defaults));
            builder.category(CategoryBank.blockHovering(config, defaults));

            //Iterate through the settings and create each option
            Map<Settings.Type, Settings> settingsMap = config.getCurrentSettings();
            Map<Settings.Type, Settings> defaultz = defaults.getCurrentSettings();

            for (var entry : settingsMap.entrySet()) {
                builder.category(CategoryBank.entity(entry, defaultz));
            }

            builder.save(CustomHitboxesMod::closeHook);

            return builder;
        });
        return screen.generateScreen(parent);
    }

    private static void checkDefaultz(ConfigIntegration config)
    {
        if (!config.profiles.containsKey(config.currentProfile)) {
            Map<Settings.Type, Settings> defaultProfileSettings = new LinkedHashMap<>();
            for (Settings.Type type : Settings.Type.values()) {
                defaultProfileSettings.put(type, new Settings());
            }
            config.profiles.put("default", defaultProfileSettings);
            config.currentProfile = "default";
        }
    }
}

