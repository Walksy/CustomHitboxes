package walksy.customhitboxes.config.impl;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.gui.controllers.ColorController;
import dev.isxander.yacl3.gui.controllers.slider.DoubleSliderController;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import walksy.customhitboxes.config.ConfigIntegration;
import walksy.customhitboxes.config.setting.Settings;
import walksy.customhitboxes.config.setting.TeamHelper;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class CategoryBank {

    public static ConfigCategory profile(ConfigIntegration config, ConfigIntegration defaults, Screen parent)
    {
        var selectProfileGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Select Profile"));

        var createNewProfileGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Create New Profile"));

        var deleteProfileGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Delete Profiles"));

        var copyOverProfileGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Copy Over Profiles"));

        copyOverProfileGroupBuilder.option(Option.<String>createBuilder()
                .name(Text.literal("Copy From Profile"))
                .instant(true)
                .binding(
                        "default",
                        () -> config.copyFromProfile,
                        value -> config.copyFromProfile = value
                )
                .controller(opt -> CyclingListControllerBuilder.create(opt)
                        .values(config.profiles.keySet())
                        .valueFormatter(Text::literal)
                )
                .build()
        );

// Selector for the profile to copy TO
        copyOverProfileGroupBuilder.option(Option.<String>createBuilder()
                .name(Text.literal("Copy To Profile"))
                .instant(true)
                .binding(
                        "default",
                        () -> config.copyToProfile,
                        value -> config.copyToProfile = value
                )
                .controller(opt -> CyclingListControllerBuilder.create(opt)
                        .values(config.profiles.keySet())
                        .valueFormatter(Text::literal)
                )
                .build()
        );

// Button to perform the copy
        copyOverProfileGroupBuilder.option(ButtonOption.createBuilder()
                .name(Text.literal("Copy Settings"))
                .action((s, button) -> {
                    String from = config.copyFromProfile;
                    String to = config.copyToProfile;

                    if (from == null || to == null) return;
                    if (from.equals(to)) return;

                    Map<Settings.Type, Settings> fromSettings = config.profiles.get(from);
                    if (fromSettings == null) return;

                    Map<Settings.Type, Settings> copiedSettings = new LinkedHashMap<>();
                    for (var entry : fromSettings.entrySet()) {
                        copiedSettings.put(entry.getKey(), entry.getValue().copy());
                    }

                    config.profiles.put(to, copiedSettings);
                    MinecraftClient.getInstance().setScreen(ConfigIntegration.createConfigScreen(parent));
                })
                .text(Text.literal("Copy Over Profile Button"))
                .build()
        );

        // Profile selector (Cycling List Controller)
        selectProfileGroupBuilder.option(Option.<String>createBuilder()
                .name(Text.literal("Selected Profile - Cycling List"))
                .instant(true)
                .binding(
                        defaults.currentProfile,
                        () -> config.currentProfile,
                        value -> {
                            config.currentProfile = value;
                            MinecraftClient.getInstance().setScreen(ConfigIntegration.createConfigScreen(parent));
                        }
                )
                .controller(opt -> CyclingListControllerBuilder.create(opt)
                        .values(config.profiles.keySet())
                        .valueFormatter(Text::literal)
                )
                .build()
        );

            /*
            selectProfileGroupBuilder.option(Option.<Settings.Type>createBuilder()
                    .name(Text.literal("Enum Dropdown"))
                    .binding(
                            defaults.woah,
                            () -> config.woah,
                            (value) -> config.woah = value
                    )
                    .controller(EnumDropdownControllerBuilder::create)
                    .build());

             */

        createNewProfileGroupBuilder.option(Option.<String>createBuilder()
                .name(Text.literal("New Profile Name"))
                .binding(
                        defaults.newProfile,
                        () -> config.newProfile,
                        value -> config.newProfile = value
                )
                .controller(StringControllerBuilder::create)
                .instant(true)
                .build()
        );

        createNewProfileGroupBuilder.option(ButtonOption.createBuilder()
                .name(Text.literal("Create New Profile"))
                .action((s, button) -> {
                    if (config.newProfile != null && !config.newProfile.trim().isEmpty()) {
                        String newProfileName = config.newProfile.trim();

                        if (!config.profiles.containsKey(newProfileName)) {

                            // Create new profile if name is valid
                            Map<Settings.Type, Settings> newProfileSettings = new LinkedHashMap<>();
                            for (Settings.Type type : Settings.Type.values()) {
                                newProfileSettings.put(type, new Settings());
                            }
                            config.profiles.put(newProfileName, newProfileSettings);
                            config.currentProfile = newProfileName;
                            config.newProfile = "";
                            MinecraftClient.getInstance().setScreen(ConfigIntegration.createConfigScreen(parent));
                        } else {
                            System.out.println("Profile already exists: " + newProfileName);
                        }
                    }
                })
                .text(Text.literal("New Profile Button"))
                .build()
        );

        deleteProfileGroupBuilder.option(Option.<String>createBuilder()
                .name(Text.literal("Profile To Delete - Cycling List"))
                .instant(true)
                .binding(
                        defaults.profileToDelete,
                        () -> config.profileToDelete,
                        value -> config.profileToDelete = value
                )
                .controller(opt -> CyclingListControllerBuilder.create(opt)
                        .values(config.profiles.keySet())
                        .valueFormatter(Text::literal)
                )
                .build()
        );

        deleteProfileGroupBuilder.option(ButtonOption.createBuilder()
                .name(Text.literal("Delete Selected Profile"))
                .action((s, button) -> {
                    if (config.profileToDelete != null && !config.profileToDelete.trim().isEmpty()) {
                        String profileToDelete = config.profileToDelete.trim();

                        if (config.profiles.containsKey(profileToDelete)) {
                            config.profiles.remove(profileToDelete);
                            config.profileToDelete = "";

                            if (config.currentProfile.equals(profileToDelete)) {
                                config.currentProfile = "default";
                            }

                            if (config.profiles.isEmpty()) {
                                Map<Settings.Type, Settings> defaultProfileSettings = new LinkedHashMap<>();
                                for (Settings.Type type : Settings.Type.values()) {
                                    defaultProfileSettings.put(type, new Settings());
                                }
                                config.profiles.put("default", defaultProfileSettings);
                            }
                            MinecraftClient.getInstance().setScreen(ConfigIntegration.createConfigScreen(parent));
                        } else {
                            System.out.println("Cannot delete profile: " + profileToDelete);
                        }
                    } else {
                        System.out.println("No profile selected for deletion.");
                    }
                })
                .text(Text.literal("Delete Profile Button"))
                .build()
        );

        return ConfigCategory.createBuilder()
                .name(Text.literal("Profiles"))
                .group(selectProfileGroupBuilder.build())
                .group(createNewProfileGroupBuilder.build())
                .group(deleteProfileGroupBuilder.build())
                .group(copyOverProfileGroupBuilder.build())
                .build();
    }

    public static ConfigCategory general(ConfigIntegration config, ConfigIntegration defaults)
    {
        var modSettingsGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Mod Settings"));

        modSettingsGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Mod Enabled"))
                .binding(
                        defaults.modEnabled,
                        () -> config.modEnabled,
                        value -> config.modEnabled = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        modSettingsGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Custom Block Hovering Overlay Enabled"))
                .binding(
                        defaults.blockHoveringEnabled,
                        () -> config.blockHoveringEnabled,
                        value -> config.blockHoveringEnabled = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        modSettingsGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Enable Hitboxes On Game Load"))
                .binding(
                        defaults.enabledOnGameLoad,
                        () -> config.enabledOnGameLoad,
                        value -> config.enabledOnGameLoad = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        return ConfigCategory.createBuilder()
                .name(Text.literal("General"))
                .group(modSettingsGroupBuilder.build())
                .build();
    }

    public static ConfigCategory entity(Map.Entry<Settings.Type, Settings> entry, Map<Settings.Type, Settings> defaults)
    {
        Settings.Type type = entry.getKey();
        Settings settings = entry.getValue();
        Settings defaultSettings = defaults.get(type);

        var renderGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("General Settings"));

        var colorGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Color Settings"));

        var vanishGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Vanish Behaviour Settings"));

        var filledHitboxGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Filled Hitbox Settings"));

        var damageGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Damage Settings"));

        var closeGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Close Behavior Settings"));

        renderGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Should Render"))
                .binding(
                        defaultSettings.shouldRender,
                        () -> settings.shouldRender,
                        value -> settings.shouldRender = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        renderGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Render Look Vector"))
                .binding(
                        defaultSettings.renderLookVector,
                        () -> settings.renderLookVector,
                        value -> settings.renderLookVector = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        renderGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Server Side Render"))
                .binding(
                        defaultSettings.serverSideRender,
                        () -> settings.serverSideRender,
                        value -> settings.serverSideRender = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        renderGroupBuilder.option(Option.<Double>createBuilder()
                .name(Text.literal("Box Thickness"))
                .binding(
                        defaultSettings.boxThickness,
                        () -> settings.boxThickness,
                        value -> settings.boxThickness = value
                )
                .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                .build()
        );


        colorGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Rainbow"))
                .binding(
                        defaultSettings.rainbow,
                        () -> settings.rainbow,
                        value -> settings.rainbow = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        colorGroupBuilder.option(Option.<Color>createBuilder()
                .name(Text.literal("Color"))
                .binding(
                        defaultSettings.color,
                        () -> settings.color,
                        value -> settings.color = value
                )
                .customController(opt -> new <Color>ColorController(opt, true))
                .build()
        );

        colorGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Color Gradient Enabled"))
                .binding(
                        defaultSettings.colorGradientEnabled,
                        () -> settings.colorGradientEnabled,
                        value -> settings.colorGradientEnabled = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        colorGroupBuilder.option(Option.<Color>createBuilder()
                .name(Text.literal("Color Gradient"))
                .binding(
                        defaultSettings.colorGradient,
                        () -> settings.colorGradient,
                        value -> settings.colorGradient = value
                )
                .customController(opt -> new <Color>ColorController(opt, true))
                .build()
        );

        if (type.equals(Settings.Type.PLAYER)) {
            vanishGroupBuilder.option(Option.<Boolean>createBuilder()
                    .name(Text.literal("Elytra Override"))
                    .binding(
                            defaultSettings.elytraAlwaysRender,
                            () -> settings.elytraAlwaysRender,
                            value -> settings.elytraAlwaysRender = value
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build()
            );
        }

        vanishGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Vanish When Close"))
                .binding(
                        defaultSettings.vanishWhenClose,
                        () -> settings.vanishWhenClose,
                        value -> settings.vanishWhenClose = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        vanishGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Vanish Fade"))
                .binding(
                        defaultSettings.vanishFade,
                        () -> settings.vanishFade,
                        value -> settings.vanishFade = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        vanishGroupBuilder.option(Option.<Double>createBuilder()
                .name(Text.literal("Vanish Distance"))
                .binding(
                        defaultSettings.vanishDistance,
                        () -> settings.vanishDistance,
                        value -> settings.vanishDistance = value
                )
                .controller(DoubleFieldControllerBuilder::create)
                .build()
        );

        filledHitboxGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Filled Hitbox"))
                .binding(
                        defaultSettings.filledHitbox,
                        () -> settings.filledHitbox,
                        value -> settings.filledHitbox = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        filledHitboxGroupBuilder.option(Option.<Color>createBuilder()
                .name(Text.literal("Filled Hitbox Color"))
                .binding(
                        defaultSettings.filledHitboxColor,
                        () -> settings.filledHitboxColor,
                        value -> settings.filledHitboxColor = value
                )
                .customController(opt -> new <Color>ColorController(opt, true))
                .build()
        );

        damageGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Change Color On Damage Tick"))
                .binding(
                        defaultSettings.changeColorOnDamageTick,
                        () -> settings.changeColorOnDamageTick,
                        value -> settings.changeColorOnDamageTick = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        damageGroupBuilder.option(Option.<Color>createBuilder()
                .name(Text.literal("Damage Tick Color"))
                .binding(
                        defaultSettings.damageTickColor,
                        () -> settings.damageTickColor,
                        value -> settings.damageTickColor = value
                )
                .customController(opt -> new <Color>ColorController(opt, true))
                .build()
        );

        closeGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Change Color When Close"))
                .binding(
                        defaultSettings.changeColorWhenClose,
                        () -> settings.changeColorWhenClose,
                        value -> settings.changeColorWhenClose = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        closeGroupBuilder.option(Option.<Color>createBuilder()
                .name(Text.literal("Close Color"))
                .binding(
                        defaultSettings.closeColor,
                        () -> settings.closeColor,
                        value -> settings.closeColor = value
                )
                .customController(opt -> new <Color>ColorController(opt, true))
                .build()
        );

        closeGroupBuilder.option(Option.<Double>createBuilder()
                .name(Text.literal("Close Distance"))
                .binding(
                        defaultSettings.closeDistance,
                        () -> settings.closeDistance,
                        value -> settings.closeDistance = value
                )
                .controller(DoubleFieldControllerBuilder::create)
                .build()
        );

        return ConfigCategory.createBuilder()
                .name(Text.literal(type.id))
                .group(renderGroupBuilder.build())
                .group(colorGroupBuilder.build())
                .group(filledHitboxGroupBuilder.build())
                .group(vanishGroupBuilder.build())
                .group(closeGroupBuilder.build())
                .group(damageGroupBuilder.build())
                .build();
    }

    public static ConfigCategory teams(ConfigIntegration config, ConfigIntegration defaults) {
        var builder = ConfigCategory.createBuilder()
                .name(Text.literal("Team Settings"));

        for (int i = 1; i <= 5; i++) {
            int index = i;

            builder.option(ListOption.<String>createBuilder()
                    .name(Text.literal("Team " + index))
                    .description(OptionDescription.of(Text.literal("Team " + index + " entries")))
                    .binding(TeamHelper.getTeamList(defaults, index), () -> TeamHelper.getTeamList(config, index), newVal -> TeamHelper.setTeamList(config, index, newVal))
                    .controller(StringControllerBuilder::create)
                    .initial("")
                    .build()
            );

            builder.group(OptionGroup.createBuilder()
                    .name(Text.literal("Team " + index + " Settings"))
                    .description(OptionDescription.of(Text.literal("All the general settings for team " + index)))
                    .option(Option.createBuilder(Color.class)
                            .name(Text.literal("Team " + index + " Color"))
                            .description(OptionDescription.of(Text.literal("Color of team " + index)))
                            .binding(TeamHelper.getTeamColor(defaults, index), () -> TeamHelper.getTeamColor(config, index), newVal -> TeamHelper.setTeamColor(config, index, newVal))
                            .customController(option -> new ColorController(option, true))
                            .build())
                    .option(Option.createBuilder(boolean.class)
                            .name(Text.literal("Team " + index + " Color Gradient Enabled"))
                            .description(OptionDescription.of(Text.literal("Requires player gradients to be enabled")))
                            .binding(TeamHelper.getTeamGradientEnabled(defaults, index), () -> TeamHelper.getTeamGradientEnabled(config, index), newVal -> TeamHelper.setTeamGradientEnabled(config, index, newVal))
                            .controller(BooleanControllerBuilder::create)
                            .build())
                    .option(Option.createBuilder(Color.class)
                            .name(Text.literal("Team " + index + " Color Gradient"))
                            .description(OptionDescription.of(Text.literal("Color gradient of team " + index)))
                            .binding(TeamHelper.getTeamGradientColor(defaults, index), () -> TeamHelper.getTeamGradientColor(config, index), newVal -> TeamHelper.setTeamGradientColor(config, index, newVal))
                            .customController(option -> new ColorController(option, true))
                            .build())
                    .collapsed(true)
                    .build());
        }

        builder.group(OptionGroup.createBuilder()
                .name(Text.literal("Team Global Settings"))
                .description(OptionDescription.of(Text.literal("All the general settings for the teams")))
                .option(Option.createBuilder(Boolean.class)
                        .name(Text.literal("Render Team's Projectiles"))
                        .description(OptionDescription.of(Text.literal("Should render team player's projectiles")))
                        .binding(defaults.renderProjectiles, () -> config.renderProjectiles, newVal -> config.renderProjectiles = newVal)
                        .controller(BooleanControllerBuilder::create)
                        .build())
                .option(Option.createBuilder(Boolean.class)
                        .name(Text.literal("Recolor Projectiles"))
                        .description(OptionDescription.of(Text.literal("Should recolor projectiles shot by those team players")))
                        .binding(defaults.recolorProjectiles, () -> config.recolorProjectiles, newVal -> config.recolorProjectiles = newVal)
                        .controller(BooleanControllerBuilder::create)
                        .build())
                .build());

        return builder.build();
    }

    public static ConfigCategory blockHovering(ConfigIntegration config, ConfigIntegration defaults)
    {
        var generalSettingsGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Mod Settings"));

        var colorSettingsGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Color Settings"));

        var filledGroupBuilder = OptionGroup.createBuilder()
                .name(Text.literal("Filled Settings"));

        generalSettingsGroupBuilder.option(Option.<Double>createBuilder()
                .name(Text.literal("Box Thickness"))
                .binding(
                        defaults.blockHoveringBoxThickness,
                        () -> config.blockHoveringBoxThickness,
                        value -> config.blockHoveringBoxThickness = value
                )
                .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                .build()
        );

        colorSettingsGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Rainbow"))
                .binding(
                        defaults.blockHoveringRainbow,
                        () -> config.blockHoveringRainbow,
                        value -> config.blockHoveringRainbow = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        colorSettingsGroupBuilder.option(Option.<Color>createBuilder()
                .name(Text.literal("Color"))
                .binding(
                        defaults.blockHoveringColor,
                        () -> config.blockHoveringColor,
                        value -> config.blockHoveringColor = value
                )
                .customController(opt -> new <Color>ColorController(opt, true))
                .build()
        );

        colorSettingsGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Color Gradient Enabled"))
                .binding(
                        defaults.blockHoveringColorGradientEnabled,
                        () -> config.blockHoveringColorGradientEnabled,
                        value -> config.blockHoveringColorGradientEnabled = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        colorSettingsGroupBuilder.option(Option.<Color>createBuilder()
                .name(Text.literal("Color Gradient"))
                .binding(
                        defaults.blockHoveringColorGradient,
                        () -> config.blockHoveringColorGradient,
                        value -> config.blockHoveringColorGradient = value
                )
                .customController(opt -> new <Color>ColorController(opt, true))
                .build()
        );


        filledGroupBuilder.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Filled Hitbox"))
                .binding(
                        defaults.blockHoveringFilledHitbox,
                        () -> config.blockHoveringFilledHitbox,
                        value -> config.blockHoveringFilledHitbox = value
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );

        filledGroupBuilder.option(Option.<Color>createBuilder()
                .name(Text.literal("Filled Hitbox Color"))
                .binding(
                        defaults.blockHoveringFilledHitboxColor,
                        () -> config.blockHoveringFilledHitboxColor,
                        value -> config.blockHoveringFilledHitboxColor = value
                )
                .customController(opt -> new <Color>ColorController(opt, true))
                .build()
        );

        return ConfigCategory.createBuilder()
                .name(Text.literal("Block Hovering"))
                .group(generalSettingsGroupBuilder.build())
                .group(colorSettingsGroupBuilder.build())
                .group(filledGroupBuilder.build())
                .build();
    }

}
