package walksy.customhitboxes.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.gui.controllers.ColorController;
import dev.isxander.yacl3.gui.controllers.slider.DoubleSliderController;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import walksy.customhitboxes.manager.HitboxManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigIntegration {

    /**
     * This class is such a mess... I blame sakura >:(
     */

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

    @SerialEntry public boolean recolorProjectiles = false; //TODO


    /**
     * Player Hitbox Entries
     */

    //Player
    @SerialEntry public boolean playerShouldRender = false;
    @SerialEntry public boolean playerServerSideRender = false;
    @SerialEntry public Color playerColor = Color.WHITE;
    @SerialEntry public boolean playerColorGradientEnabled = false;
    @SerialEntry public Color playerColorGradient = Color.WHITE;
    @SerialEntry public boolean playerChangeColorOnDamageTick = false;
    @SerialEntry public Color playerDamageTickColor = Color.RED;
    @SerialEntry public boolean playerLookVector = false;
    @SerialEntry public double playerBoxThickness = 1;
    @SerialEntry public boolean playerVanishWhenClose = false;
    @SerialEntry public boolean playerVanishFade = false;
    @SerialEntry public double playerVanishDistance = 5;
    @SerialEntry public boolean playerFilledHitbox = false;
    @SerialEntry public Color playerFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean playerRainbow = false;

    /**
     * Projectile Hitbox Entries
     */

    //projectile
    @SerialEntry public boolean projectileShouldRender = false;
    @SerialEntry public boolean projectileServerSideRender = false;
    @SerialEntry public Color projectileColor = Color.WHITE;
    @SerialEntry public boolean projectileColorGradientEnabled = false;
    @SerialEntry public Color projectileColorGradient = Color.WHITE;
    @SerialEntry public double projectileBoxThickness = 1;
    @SerialEntry public boolean projectileVanishWhenClose = false;
    @SerialEntry public boolean projectileVanishFade = false;
    @SerialEntry public double projectileVanishDistance = 5;
    @SerialEntry public boolean projectileFilledHitbox = false;
    @SerialEntry public Color projectileFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean projectileRainbow = false;


    /**
     * Pearl Hitbox Entries
     */

    //pearl
    @SerialEntry public boolean pearlShouldRender = false;
    @SerialEntry public boolean pearlServerSideRender = false;
    @SerialEntry public Color pearlColor = Color.WHITE;
    @SerialEntry public boolean pearlColorGradientEnabled = false;
    @SerialEntry public Color pearlColorGradient = Color.WHITE;
    @SerialEntry public double pearlBoxThickness = 1;
    @SerialEntry public boolean pearlVanishWhenClose = false;
    @SerialEntry public boolean pearlVanishFade = false;
    @SerialEntry public double pearlVanishDistance = 5;
    @SerialEntry public boolean pearlFilledHitbox = false;
    @SerialEntry public Color pearlFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean pearlRainbow = false;

    /**
     * HostileMob Hitbox Entries
     */

    //hostileMob
    @SerialEntry public boolean hostileMobShouldRender = false;
    @SerialEntry public boolean hostileMobServerSideRender = false;
    @SerialEntry public Color hostileMobColor = Color.WHITE;
    @SerialEntry public boolean hostileMobColorGradientEnabled = false;
    @SerialEntry public Color hostileMobColorGradient = Color.WHITE;
    @SerialEntry public boolean hostileMobChangeColorOnDamageTick = false;
    @SerialEntry public Color hostileMobDamageTickColor = Color.RED;
    @SerialEntry public boolean hostileMobLookVector = false;
    @SerialEntry public double hostileMobBoxThickness = 1;
    @SerialEntry public boolean hostileMobVanishWhenClose = false;
    @SerialEntry public boolean hostileMobVanishFade = false;
    @SerialEntry public double hostileMobVanishDistance = 5;
    @SerialEntry public boolean hostileMobFilledHitbox = false;
    @SerialEntry public Color hostileMobFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean hostileMobRainbow = false;

    /**
     * PassiveMob Hitbox Entries
     */

    //passiveMob
    @SerialEntry public boolean passiveMobShouldRender = false;
    @SerialEntry public boolean passiveMobServerSideRender = false;
    @SerialEntry public Color passiveMobColor = Color.WHITE;
    @SerialEntry public boolean passiveMobColorGradientEnabled = false;
    @SerialEntry public Color passiveMobColorGradient = Color.WHITE;
    @SerialEntry public boolean passiveMobChangeColorOnDamageTick = false;
    @SerialEntry public Color passiveMobDamageTickColor = Color.RED;
    @SerialEntry public boolean passiveMobLookVector = false;
    @SerialEntry public double passiveMobBoxThickness = 1;
    @SerialEntry public boolean passiveMobVanishWhenClose = false;
    @SerialEntry public boolean passiveMobVanishFade = false;
    @SerialEntry public double passiveMobVanishDistance = 5;
    @SerialEntry public boolean passiveMobFilledHitbox = false;
    @SerialEntry public Color passiveMobFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean passiveMobRainbow = false;

    /**
     * Angerable Mob Hitbox Entries
     */

    //angerableMob
    @SerialEntry public boolean angerableMobShouldRender = false;
    @SerialEntry public boolean angerableMobServerSideRender = false;
    @SerialEntry public Color angerableMobColor = Color.WHITE;
    @SerialEntry public boolean angerableMobColorGradientEnabled = false;
    @SerialEntry public Color angerableMobColorGradient = Color.WHITE;
    @SerialEntry public boolean angerableMobChangeColorOnDamageTick = false;
    @SerialEntry public Color angerableMobDamageTickColor = Color.RED;
    @SerialEntry public boolean angerableMobLookVector = false;
    @SerialEntry public double angerableMobBoxThickness = 1;
    @SerialEntry public boolean angerableMobVanishWhenClose = false;
    @SerialEntry public boolean angerableMobVanishFade = false;
    @SerialEntry public double angerableMobVanishDistance = 5;
    @SerialEntry public boolean angerableMobFilledHitbox = false;
    @SerialEntry public Color angerableMobFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean angerableMobRainbow = false;

    /**
     * EndCrystal Hitbox Entries
     */

    //endCrystal
    @SerialEntry public boolean endCrystalShouldRender = false;
    @SerialEntry public boolean endCrystalServerSideRender = false;
    @SerialEntry public Color endCrystalColor = Color.WHITE;
    @SerialEntry public boolean endCrystalColorGradientEnabled = false;
    @SerialEntry public Color endCrystalColorGradient = Color.WHITE;
    @SerialEntry public double endCrystalBoxThickness = 1;
    @SerialEntry public boolean endCrystalVanishWhenClose = false;
    @SerialEntry public boolean endCrystalVanishFade = false;
    @SerialEntry public double endCrystalVanishDistance = 5;
    @SerialEntry public boolean endCrystalFilledHitbox = false;
    @SerialEntry public Color endCrystalFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean endCrystalRainbow = false;

    /**
     * Block entity Hitbox Entries
     */

    //blockentity
    @SerialEntry public boolean blockentityShouldRender = false;
    @SerialEntry public boolean blockentityServerSideRender = false;
    @SerialEntry public Color blockentityColor = Color.WHITE;
    @SerialEntry public boolean blockentityColorGradientEnabled = false;
    @SerialEntry public Color blockentityColorGradient = Color.WHITE;
    @SerialEntry public double blockentityBoxThickness = 1;
    @SerialEntry public boolean blockentityVanishWhenClose = false;
    @SerialEntry public boolean blockentityVanishFade = false;
    @SerialEntry public double blockentityVanishDistance = 5;
    @SerialEntry public boolean blockentityFilledHitbox = false;
    @SerialEntry public Color blockentityFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean blockentityRainbow = false;

    /**
     * itemEntity Hitbox Entries
     */

    //itementity
    @SerialEntry public boolean itementityShouldRender = false;
    @SerialEntry public boolean itementityServerSideRender = false;
    @SerialEntry public Color itementityColor = Color.WHITE;
    @SerialEntry public boolean itementityColorGradientEnabled = false;
    @SerialEntry public Color itementityColorGradient = Color.WHITE;
    @SerialEntry public double itementityBoxThickness = 1;
    @SerialEntry public boolean itementityVanishWhenClose = false;
    @SerialEntry public boolean itementityVanishFade = false;
    @SerialEntry public double itementityVanishDistance = 5;
    @SerialEntry public boolean itementityFilledHitbox = false;
    @SerialEntry public Color itementityFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean itementityRainbow = false;

    /**
     * elseEntity Hitbox Entries
     */

    //elseentity
    @SerialEntry public boolean elseEntityShouldRender = false;
    @SerialEntry public boolean elseEntityServerSideRender = false;
    @SerialEntry public Color elseEntityColor = Color.WHITE;
    @SerialEntry public boolean elseEntityColorGradientEnabled = false;
    @SerialEntry public Color elseEntityColorGradient = Color.WHITE;
    @SerialEntry public double elseEntityBoxThickness = 1;
    @SerialEntry public boolean elseEntityVanishWhenClose = false;
    @SerialEntry public boolean elseEntityVanishFade = false;
    @SerialEntry public double elseEntityVanishDistance = 5;
    @SerialEntry public boolean elseEntityFilledHitbox = false;
    @SerialEntry public Color elseEntityFilledHitboxColor = Color.WHITE;
    @SerialEntry public boolean elseEntityRainbow = false;


    @SuppressWarnings("deprecation") //stop the compiler crying
    public static Screen createConfigScreen(Screen parent)
    {
        var screen = YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Text.literal("Custom Hitbox Config Screen"))
                /**
                 * General Settings Category
                 */


                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("General Settings"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("CustomHitbox Mod Enabled"))
                                .description(OptionDescription.of(Text.literal("Should the custom hitbox mod be enabled")))
                                .binding(defaults.modEnabled, () -> config.modEnabled, newVal -> config.modEnabled = newVal)
                                .controller(BooleanControllerBuilder::create)
                                .listener(HitboxManager::optionListener)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Enabled Hitboxes On Game Load"))
                                .description(OptionDescription.of(Text.literal("Should hitboxes be enabled when you load your game")))
                                .binding(defaults.enabledOnGameLoad, () -> config.enabledOnGameLoad, newVal -> config.enabledOnGameLoad = newVal)
                                .controller(BooleanControllerBuilder::create).instant(true)

                                .build())
                        /*
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Debug (DO NOT TURN ON)"))
                                .description(OptionDescription.of(Text.literal("")))
                                .binding(defaults.debug, () -> config.debug, newVal -> config.debug = newVal)
                                .controller(BooleanControllerBuilder::create).instant(true)
                                .build())

                         */
                        .build())
                /**
                 * Team Settings Category
                 */
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Team Settings"))
                        .option(ListOption.<String>createBuilder()
                                .name(Text.literal("Team 1"))
                                .description(OptionDescription.of(Text.literal("Team 1 entries")))
                                .binding(defaults.team1, () -> config.team1, newVal -> config.team1 = newVal)
                                .controller(StringControllerBuilder::create)
                                .initial("")
                                .build())
                        .option(ListOption.<String>createBuilder()
                                .name(Text.literal("Team 2"))
                                .description(OptionDescription.of(Text.literal("Team 2 entries")))
                                .binding(defaults.team2, () -> config.team2, newVal -> config.team2 = newVal)
                                .controller(StringControllerBuilder::create)
                                .initial("")
                                .build())
                        .option(ListOption.<String>createBuilder()
                                .name(Text.literal("Team 3"))
                                .description(OptionDescription.of(Text.literal("Team 3 entries")))
                                .binding(defaults.team3, () -> config.team3, newVal -> config.team3 = newVal)
                                .controller(StringControllerBuilder::create)
                                .initial("")
                                .build())
                        .option(ListOption.<String>createBuilder()
                                .name(Text.literal("Team 4"))
                                .description(OptionDescription.of(Text.literal("Team 4 entries")))
                                .binding(defaults.team4, () -> config.team4, newVal -> config.team4 = newVal)
                                .controller(StringControllerBuilder::create)
                                .initial("")
                                .build())
                        .option(ListOption.<String>createBuilder()
                                .name(Text.literal("Team 5"))
                                .description(OptionDescription.of(Text.literal("Team 5 entries")))
                                .binding(defaults.team5, () -> config.team5, newVal -> config.team5 = newVal)
                                .controller(StringControllerBuilder::create)
                                .initial("")
                                .build())

                        /**
                         * Team Colors
                         */
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Team Global Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the teams")))

                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Recolor Projectiles"))
                                        .description(OptionDescription.of(Text.literal("Should recolor projectiles shot by those team players")))
                                        .binding(defaults.recolorProjectiles, () -> config.recolorProjectiles, newVal ->
                                        {
                                            config.recolorProjectiles = newVal;
                                        })
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .build()) //group

                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Team 1 Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for team 1")))

                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Team 1 Color"))
                                        .description(OptionDescription.of(Text.literal("Color of team 1")))
                                        .binding(defaults.team1Color, () -> config.team1Color, newVal ->
                                        {
                                            config.team1Color = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Team 1 Color Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(defaults.team1ColorGradientEnabled, () -> config.team1ColorGradientEnabled, newVal ->
                                        {
                                            config.team1ColorGradientEnabled = newVal;
                                        })
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Team 1 Color Gradient"))
                                        .description(OptionDescription.of(Text.literal("Color gradient of team 1")))
                                        .binding(defaults.team1ColorGradient, () -> config.team1ColorGradient, newVal ->
                                        {
                                            config.team1ColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .collapsed(true)
                                .build()) //group team 1
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Team 2 Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for team 2")))
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Team 2 Color"))
                                        .description(OptionDescription.of(Text.literal("Color of team 2")))
                                        .binding(defaults.team2Color, () -> config.team2Color, newVal ->
                                        {
                                            config.team2Color = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Team 2 Color Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(defaults.team2ColorGradientEnabled, () -> config.team2ColorGradientEnabled, newVal ->
                                        {
                                            config.team2ColorGradientEnabled = newVal;
                                        })
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Team 2 Color Gradient"))
                                        .description(OptionDescription.of(Text.literal("Color gradient of team 2")))
                                        .binding(defaults.team2ColorGradient, () -> config.team2ColorGradient, newVal ->
                                        {
                                            config.team2ColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .collapsed(true)
                                .build()) //group team 2
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Team 3 Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for team 3")))
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Team 3 Color"))
                                        .description(OptionDescription.of(Text.literal("Color of team 3")))
                                        .binding(defaults.team3Color, () -> config.team3Color, newVal ->
                                        {
                                            config.team3Color = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Team 3 Color Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(defaults.team3ColorGradientEnabled, () -> config.team3ColorGradientEnabled, newVal ->
                                        {
                                            config.team3ColorGradientEnabled = newVal;
                                        })
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Team 3 Color Gradient"))
                                        .description(OptionDescription.of(Text.literal("Color gradient of team 3")))
                                        .binding(defaults.team3ColorGradient, () -> config.team3ColorGradient, newVal ->
                                        {
                                            config.team3ColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .collapsed(true)
                                .build()) //group team 3
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Team 4 Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for team 4")))
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Team 4 Color"))
                                        .description(OptionDescription.of(Text.literal("Color of team 4")))
                                        .binding(defaults.team4Color, () -> config.team4Color, newVal ->
                                        {
                                            config.team4Color = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Team 4 Color Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(defaults.team4ColorGradientEnabled, () -> config.team4ColorGradientEnabled, newVal ->
                                        {
                                            config.team4ColorGradientEnabled = newVal;
                                        })
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Team 4 Color Gradient"))
                                        .description(OptionDescription.of(Text.literal("Color gradient of team 4")))
                                        .binding(defaults.team4ColorGradient, () -> config.team4ColorGradient, newVal ->
                                        {
                                            config.team4ColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .collapsed(true)
                                .build()) //group team 4
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Team 5 Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for team 5")))
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Team 5 Color"))
                                        .description(OptionDescription.of(Text.literal("Color of team 5")))
                                        .binding(defaults.team5Color, () -> config.team5Color, newVal ->
                                        {
                                            config.team5Color = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Team 5 Color Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(defaults.team5ColorGradientEnabled, () -> config.team5ColorGradientEnabled, newVal ->
                                        {
                                            config.team5ColorGradientEnabled = newVal;
                                        })
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Team 5 Color Gradient"))
                                        .description(OptionDescription.of(Text.literal("Color gradient of team 5")))
                                        .binding(defaults.team5ColorGradient, () -> config.team5ColorGradient, newVal ->
                                        {
                                            config.team5ColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .collapsed(true)
                                .build()) //group team 5
                        .build())




                /**
                 * PlayerEntity Settings Category
                 */
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Player Hitboxes"))

                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("General Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the player hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Should Render"))
                                        .description(OptionDescription.of(Text.literal("Should render the player")))
                                        .binding(defaults.playerShouldRender, () -> config.playerShouldRender, newVal -> config.playerShouldRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Using Server-side Position"))
                                        .description(OptionDescription.of(Text.literal("Renders the hitbox based on where the player is server side. Doesn't really have a functionality as entity interactions are determined by client-side calculations. Just for fun really")))
                                        .binding(defaults.playerServerSideRender, () -> config.playerServerSideRender, newVal -> config.playerServerSideRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Look Vector"))
                                        .description(OptionDescription.of(Text.literal("Renders look vector line")))
                                        .binding(defaults.playerLookVector, () -> config.playerLookVector, newVal -> config.playerLookVector = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Hitbox Line Thickness"))
                                        .description(OptionDescription.of(Text.literal("How thick the lines of the hitbox are")))
                                        .binding(defaults.playerBoxThickness, () -> config.playerBoxThickness, newVal -> config.playerBoxThickness = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Fill Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Fill the hitbox")))
                                        .binding(defaults.playerFilledHitbox, () -> config.playerFilledHitbox, newVal -> config.playerFilledHitbox = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Filled Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hitbox when filled")))
                                        .binding(defaults.playerFilledHitboxColor, () -> config.playerFilledHitboxColor, newVal -> config.playerFilledHitboxColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish When Close"))
                                        .description(OptionDescription.of(Text.literal("Remove the hitbox when you get close")))
                                        .binding(defaults.playerVanishWhenClose, () -> config.playerVanishWhenClose, newVal -> config.playerVanishWhenClose = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish Fade"))
                                        .description(OptionDescription.of(Text.literal("Fades away instead of instantly vanishing")))
                                        .binding(defaults.playerVanishFade, () -> config.playerVanishFade, newVal -> config.playerVanishFade = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Vanish Distance"))
                                        .description(OptionDescription.of(Text.literal("How close you have to be for the hitbox to vanish")))
                                        .binding(defaults.playerVanishDistance, () -> config.playerVanishDistance, newVal -> config.playerVanishDistance = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 50, 0.1))
                                        .build())
                                .build()) //group
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Settings"))
                                .description(OptionDescription.of(Text.literal("All the color settings for the player hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Rainbow"))
                                        .description(OptionDescription.of(Text.literal("Enable a rainbow color")))
                                        .binding(defaults.playerRainbow, () -> config.playerRainbow, newVal -> config.playerRainbow = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Top gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the player's hitbox")))
                                        .binding(defaults.playerColor, () -> config.playerColor, newVal ->
                                        {
                                            config.playerColor = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Bottom Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox have a gradient")))
                                        .binding(defaults.playerColorGradientEnabled, () -> config.playerColorGradientEnabled, newVal -> config.playerColorGradientEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Bottom gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the player's hitbox gradient (bottom half)")))
                                        .binding(defaults.playerColorGradient, () -> config.playerColorGradient, newVal ->
                                        {
                                            config.playerColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Damage Color Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox change color in a damage tick")))
                                        .binding(defaults.playerChangeColorOnDamageTick, () -> config.playerChangeColorOnDamageTick, newVal -> config.playerChangeColorOnDamageTick = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Damage Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the player's hitbox when damaged")))
                                        .binding(defaults.playerDamageTickColor, () -> config.playerDamageTickColor, newVal -> config.playerDamageTickColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .build()) //group

                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("EndCrystal Hitboxes"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("General Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the endCrystal hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Should Render"))
                                        .description(OptionDescription.of(Text.literal("Should render the endcrystal")))
                                        .binding(defaults.endCrystalShouldRender, () -> config.endCrystalShouldRender, newVal -> config.endCrystalShouldRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Using Server-side Position"))
                                        .description(OptionDescription.of(Text.literal("Renders the hitbox based on where the endCrystal is server side. Doesn't really have a functionality as entity interactions are determined by client-side calculations ")))
                                        .binding(defaults.endCrystalServerSideRender, () -> config.endCrystalServerSideRender, newVal -> config.endCrystalServerSideRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Hitbox Line Thickness"))
                                        .description(OptionDescription.of(Text.literal("How thick the lines of the hitbox are")))
                                        .binding(defaults.endCrystalBoxThickness, () -> config.endCrystalBoxThickness, newVal -> config.endCrystalBoxThickness = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Fill Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Fill the hitbox")))
                                        .binding(defaults.endCrystalFilledHitbox, () -> config.endCrystalFilledHitbox, newVal -> config.endCrystalFilledHitbox = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Filled Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hitbox when filled")))
                                        .binding(defaults.endCrystalFilledHitboxColor, () -> config.endCrystalFilledHitboxColor, newVal -> config.endCrystalFilledHitboxColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish When Close"))
                                        .description(OptionDescription.of(Text.literal("Remove the hitbox when you get close")))
                                        .binding(defaults.endCrystalVanishWhenClose, () -> config.endCrystalVanishWhenClose, newVal -> config.endCrystalVanishWhenClose = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish Fade"))
                                        .description(OptionDescription.of(Text.literal("Fades away instead of instantly vanishing")))
                                        .binding(defaults.endCrystalVanishFade, () -> config.endCrystalVanishFade, newVal -> config.endCrystalVanishFade = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Vanish Distance"))
                                        .description(OptionDescription.of(Text.literal("How close you have to be for the hitbox to vanish")))
                                        .binding(defaults.endCrystalVanishDistance, () -> config.endCrystalVanishDistance, newVal -> config.endCrystalVanishDistance = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 50, 0.1))
                                        .build())
                                .build()) //group
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Settings"))
                                .description(OptionDescription.of(Text.literal("All the color settings for the endCrystal hitboxes")))
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Top gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the endCrystal's hitbox")))
                                        .binding(defaults.endCrystalColor, () -> config.endCrystalColor, newVal ->
                                        {
                                            config.endCrystalColor = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Bottom Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox have a gradient")))
                                        .binding(defaults.endCrystalColorGradientEnabled, () -> config.endCrystalColorGradientEnabled, newVal -> config.endCrystalColorGradientEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Bottom gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the endCrystal's hitbox gradient (bottom half)")))
                                        .binding(defaults.endCrystalColorGradient, () -> config.endCrystalColorGradient, newVal ->
                                        {
                                            config.endCrystalColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .build()) //group

                        .build())

                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Block Entity Hitboxes"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("General Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the blockentity hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Should Render"))
                                        .description(OptionDescription.of(Text.literal("Should render the blockentity")))
                                        .binding(defaults.blockentityShouldRender, () -> config.blockentityShouldRender, newVal -> config.blockentityShouldRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Using Server-side Position"))
                                        .description(OptionDescription.of(Text.literal("Renders the hitbox based on where the blockentity is server side. Doesn't really have a functionality as entity interactions are determined by client-side calculations ")))
                                        .binding(defaults.blockentityServerSideRender, () -> config.blockentityServerSideRender, newVal -> config.blockentityServerSideRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Hitbox Line Thickness"))
                                        .description(OptionDescription.of(Text.literal("How thick the lines of the hitbox are")))
                                        .binding(defaults.blockentityBoxThickness, () -> config.blockentityBoxThickness, newVal -> config.blockentityBoxThickness = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Fill Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Fill the hitbox")))
                                        .binding(defaults.blockentityFilledHitbox, () -> config.blockentityFilledHitbox, newVal -> config.blockentityFilledHitbox = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Filled Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hitbox when filled")))
                                        .binding(defaults.blockentityFilledHitboxColor, () -> config.blockentityFilledHitboxColor, newVal -> config.blockentityFilledHitboxColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish When Close"))
                                        .description(OptionDescription.of(Text.literal("Remove the hitbox when you get close")))
                                        .binding(defaults.blockentityVanishWhenClose, () -> config.blockentityVanishWhenClose, newVal -> config.blockentityVanishWhenClose = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish Fade"))
                                        .description(OptionDescription.of(Text.literal("Fades away instead of instantly vanishing")))
                                        .binding(defaults.blockentityVanishFade, () -> config.blockentityVanishFade, newVal -> config.blockentityVanishFade = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Vanish Distance"))
                                        .description(OptionDescription.of(Text.literal("How close you have to be for the hitbox to vanish")))
                                        .binding(defaults.blockentityVanishDistance, () -> config.blockentityVanishDistance, newVal -> config.blockentityVanishDistance = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 50, 0.1))
                                        .build())
                                .build()) //group
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Settings"))
                                .description(OptionDescription.of(Text.literal("All the color settings for the blockentity hitboxes")))
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Top gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the blockentity's hitbox")))
                                        .binding(defaults.blockentityColor, () -> config.blockentityColor, newVal ->
                                        {
                                            config.blockentityColor = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Bottom Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox have a gradient")))
                                        .binding(defaults.blockentityColorGradientEnabled, () -> config.blockentityColorGradientEnabled, newVal -> config.blockentityColorGradientEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Bottom gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the blockentity's hitbox gradient (bottom half)")))
                                        .binding(defaults.blockentityColorGradient, () -> config.blockentityColorGradient, newVal ->
                                        {
                                            config.blockentityColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .build()) //group

                        .build())

                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Item Hitboxes"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("General Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the itementity hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Should Render"))
                                        .description(OptionDescription.of(Text.literal("Should render the itementity")))
                                        .binding(defaults.itementityShouldRender, () -> config.itementityShouldRender, newVal -> config.itementityShouldRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Using Server-side Position"))
                                        .description(OptionDescription.of(Text.literal("Renders the hitbox based on where the itementity is server side. Doesn't really have a functionality as entity interactions are determined by client-side calculations ")))
                                        .binding(defaults.itementityServerSideRender, () -> config.itementityServerSideRender, newVal -> config.itementityServerSideRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Hitbox Line Thickness"))
                                        .description(OptionDescription.of(Text.literal("How thick the lines of the hitbox are")))
                                        .binding(defaults.itementityBoxThickness, () -> config.itementityBoxThickness, newVal -> config.itementityBoxThickness = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Fill Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Fill the hitbox")))
                                        .binding(defaults.itementityFilledHitbox, () -> config.itementityFilledHitbox, newVal -> config.itementityFilledHitbox = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Filled Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hitbox when filled")))
                                        .binding(defaults.itementityFilledHitboxColor, () -> config.itementityFilledHitboxColor, newVal -> config.itementityFilledHitboxColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish When Close"))
                                        .description(OptionDescription.of(Text.literal("Remove the hitbox when you get close")))
                                        .binding(defaults.itementityVanishWhenClose, () -> config.itementityVanishWhenClose, newVal -> config.itementityVanishWhenClose = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish Fade"))
                                        .description(OptionDescription.of(Text.literal("Fades away instead of instantly vanishing")))
                                        .binding(defaults.itementityVanishFade, () -> config.itementityVanishFade, newVal -> config.itementityVanishFade = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Vanish Distance"))
                                        .description(OptionDescription.of(Text.literal("How close you have to be for the hitbox to vanish")))
                                        .binding(defaults.itementityVanishDistance, () -> config.itementityVanishDistance, newVal -> config.itementityVanishDistance = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 50, 0.1))
                                        .build())
                                .build()) //group
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Settings"))
                                .description(OptionDescription.of(Text.literal("All the color settings for the itementity hitboxes")))
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Top gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the itementity's hitbox")))
                                        .binding(defaults.itementityColor, () -> config.itementityColor, newVal ->
                                        {
                                            config.itementityColor = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Bottom Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox have a gradient")))
                                        .binding(defaults.itementityColorGradientEnabled, () -> config.itementityColorGradientEnabled, newVal -> config.itementityColorGradientEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Bottom gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the itementity's hitbox gradient (bottom half)")))
                                        .binding(defaults.itementityColorGradient, () -> config.itementityColorGradient, newVal ->
                                        {
                                            config.itementityColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .build()) //group

                        .build())

                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Projectile Hitboxes"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("General Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the projectile hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Should Render"))
                                        .description(OptionDescription.of(Text.literal("Should render the projectile")))
                                        .binding(defaults.projectileShouldRender, () -> config.projectileShouldRender, newVal -> config.projectileShouldRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Using Server-side Position"))
                                        .description(OptionDescription.of(Text.literal("Renders the hitbox based on where the projectile is server side. Doesn't really have a functionality as entity interactions are determined by client-side calculations ")))
                                        .binding(defaults.projectileServerSideRender, () -> config.projectileServerSideRender, newVal -> config.projectileServerSideRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Hitbox Line Thickness"))
                                        .description(OptionDescription.of(Text.literal("How thick the lines of the hitbox are")))
                                        .binding(defaults.projectileBoxThickness, () -> config.projectileBoxThickness, newVal -> config.projectileBoxThickness = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Fill Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Fill the hitbox")))
                                        .binding(defaults.projectileFilledHitbox, () -> config.projectileFilledHitbox, newVal -> config.projectileFilledHitbox = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Filled Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hitbox when filled")))
                                        .binding(defaults.projectileFilledHitboxColor, () -> config.projectileFilledHitboxColor, newVal -> config.projectileFilledHitboxColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish When Close"))
                                        .description(OptionDescription.of(Text.literal("Remove the hitbox when you get close")))
                                        .binding(defaults.projectileVanishWhenClose, () -> config.projectileVanishWhenClose, newVal -> config.projectileVanishWhenClose = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish Fade"))
                                        .description(OptionDescription.of(Text.literal("Fades away instead of instantly vanishing")))
                                        .binding(defaults.projectileVanishFade, () -> config.projectileVanishFade, newVal -> config.projectileVanishFade = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Vanish Distance"))
                                        .description(OptionDescription.of(Text.literal("How close you have to be for the hitbox to vanish")))
                                        .binding(defaults.projectileVanishDistance, () -> config.projectileVanishDistance, newVal -> config.projectileVanishDistance = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 50, 0.1))
                                        .build())
                                .build()) //group
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Settings"))
                                .description(OptionDescription.of(Text.literal("All the color settings for the projectile hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Rainbow"))
                                        .description(OptionDescription.of(Text.literal("Enable a rainbow color")))
                                        .binding(defaults.projectileRainbow, () -> config.projectileRainbow, newVal -> config.projectileRainbow = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Top gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the projectile's hitbox")))
                                        .binding(defaults.projectileColor, () -> config.projectileColor, newVal ->
                                        {
                                            config.projectileColor = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Bottom Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox have a gradient")))
                                        .binding(defaults.projectileColorGradientEnabled, () -> config.projectileColorGradientEnabled, newVal -> config.projectileColorGradientEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Bottom gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the projectile's hitbox gradient (bottom half)")))
                                        .binding(defaults.projectileColorGradient, () -> config.projectileColorGradient, newVal ->
                                        {
                                            config.projectileColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .build()) //group
                        .build())

                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Pearl Hitboxes"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("General Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the pearl hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Should Render"))
                                        .description(OptionDescription.of(Text.literal("Should render the pearl")))
                                        .binding(defaults.pearlShouldRender, () -> config.pearlShouldRender, newVal -> config.pearlShouldRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Using Server-side Position"))
                                        .description(OptionDescription.of(Text.literal("Renders the hitbox based on where the pearl is server side. Doesn't really have a functionality as entity interactions are determined by client-side calculations ")))
                                        .binding(defaults.pearlServerSideRender, () -> config.pearlServerSideRender, newVal -> config.pearlServerSideRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Hitbox Line Thickness"))
                                        .description(OptionDescription.of(Text.literal("How thick the lines of the hitbox are")))
                                        .binding(defaults.pearlBoxThickness, () -> config.pearlBoxThickness, newVal -> config.pearlBoxThickness = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Fill Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Fill the hitbox")))
                                        .binding(defaults.pearlFilledHitbox, () -> config.pearlFilledHitbox, newVal -> config.pearlFilledHitbox = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Filled Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hitbox when filled")))
                                        .binding(defaults.pearlFilledHitboxColor, () -> config.pearlFilledHitboxColor, newVal -> config.pearlFilledHitboxColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish When Close"))
                                        .description(OptionDescription.of(Text.literal("Remove the hitbox when you get close")))
                                        .binding(defaults.pearlVanishWhenClose, () -> config.pearlVanishWhenClose, newVal -> config.pearlVanishWhenClose = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish Fade"))
                                        .description(OptionDescription.of(Text.literal("Fades away instead of instantly vanishing")))
                                        .binding(defaults.pearlVanishFade, () -> config.pearlVanishFade, newVal -> config.pearlVanishFade = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Vanish Distance"))
                                        .description(OptionDescription.of(Text.literal("How close you have to be for the hitbox to vanish")))
                                        .binding(defaults.pearlVanishDistance, () -> config.pearlVanishDistance, newVal -> config.pearlVanishDistance = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 50, 0.1))
                                        .build())
                                .build()) //group
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Settings"))
                                .description(OptionDescription.of(Text.literal("All the color settings for the pearl hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Rainbow"))
                                        .description(OptionDescription.of(Text.literal("Enable a rainbow color")))
                                        .binding(defaults.pearlRainbow, () -> config.pearlRainbow, newVal -> config.pearlRainbow = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Top gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the pearl's hitbox")))
                                        .binding(defaults.pearlColor, () -> config.pearlColor, newVal ->
                                        {
                                            config.pearlColor = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Bottom Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox have a gradient")))
                                        .binding(defaults.pearlColorGradientEnabled, () -> config.pearlColorGradientEnabled, newVal -> config.pearlColorGradientEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Bottom gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the pearl's hitbox gradient (bottom half)")))
                                        .binding(defaults.pearlColorGradient, () -> config.pearlColorGradient, newVal ->
                                        {
                                            config.pearlColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .build()) //group
                        .build())

                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("HostileMob Hitboxes"))

                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("General Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the hostileMob hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Should Render"))
                                        .description(OptionDescription.of(Text.literal("Should render the hostileMob")))
                                        .binding(defaults.hostileMobShouldRender, () -> config.hostileMobShouldRender, newVal -> config.hostileMobShouldRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Using Server-side Position"))
                                        .description(OptionDescription.of(Text.literal("Renders the hitbox based on where the hostileMob is server side. Doesn't really have a functionality as entity interactions are determined by client-side calculations ")))
                                        .binding(defaults.hostileMobServerSideRender, () -> config.hostileMobServerSideRender, newVal -> config.hostileMobServerSideRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Look Vector"))
                                        .description(OptionDescription.of(Text.literal("Renders look vector line")))
                                        .binding(defaults.hostileMobLookVector, () -> config.hostileMobLookVector, newVal -> config.hostileMobLookVector = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Hitbox Line Thickness"))
                                        .description(OptionDescription.of(Text.literal("How thick the lines of the hitbox are")))
                                        .binding(defaults.hostileMobBoxThickness, () -> config.hostileMobBoxThickness, newVal -> config.hostileMobBoxThickness = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Fill Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Fill the hitbox")))
                                        .binding(defaults.hostileMobFilledHitbox, () -> config.hostileMobFilledHitbox, newVal -> config.hostileMobFilledHitbox = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Filled Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hitbox when filled")))
                                        .binding(defaults.hostileMobFilledHitboxColor, () -> config.hostileMobFilledHitboxColor, newVal -> config.hostileMobFilledHitboxColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish When Close"))
                                        .description(OptionDescription.of(Text.literal("Remove the hitbox when you get close")))
                                        .binding(defaults.hostileMobVanishWhenClose, () -> config.hostileMobVanishWhenClose, newVal -> config.hostileMobVanishWhenClose = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish Fade"))
                                        .description(OptionDescription.of(Text.literal("Fades away instead of instantly vanishing")))
                                        .binding(defaults.hostileMobVanishFade, () -> config.hostileMobVanishFade, newVal -> config.hostileMobVanishFade = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Vanish Distance"))
                                        .description(OptionDescription.of(Text.literal("How close you have to be for the hitbox to vanish")))
                                        .binding(defaults.hostileMobVanishDistance, () -> config.hostileMobVanishDistance, newVal -> config.hostileMobVanishDistance = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 50, 0.1))
                                        .build())
                                .build()) //group
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Settings"))
                                .description(OptionDescription.of(Text.literal("All the color settings for the hostileMob hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Rainbow"))
                                        .description(OptionDescription.of(Text.literal("Enable a rainbow color")))
                                        .binding(defaults.hostileMobRainbow, () -> config.hostileMobRainbow, newVal -> config.hostileMobRainbow = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Top gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the hostileMob's hitbox")))
                                        .binding(defaults.hostileMobColor, () -> config.hostileMobColor, newVal ->
                                        {
                                            config.hostileMobColor = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Bottom Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox have a gradient")))
                                        .binding(defaults.hostileMobColorGradientEnabled, () -> config.hostileMobColorGradientEnabled, newVal -> config.hostileMobColorGradientEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Bottom gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the hostileMob's hitbox gradient (bottom half)")))
                                        .binding(defaults.hostileMobColorGradient, () -> config.hostileMobColorGradient, newVal ->
                                        {
                                            config.hostileMobColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Damage Color Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox change color in a damage tick")))
                                        .binding(defaults.hostileMobChangeColorOnDamageTick, () -> config.hostileMobChangeColorOnDamageTick, newVal -> config.hostileMobChangeColorOnDamageTick = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Damage Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hostileMob's hitbox when damaged")))
                                        .binding(defaults.hostileMobDamageTickColor, () -> config.hostileMobDamageTickColor, newVal -> config.hostileMobDamageTickColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .build()) //group

                        .build())

                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("PassiveMob Hitboxes"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("General Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the passiveMob hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Should Render"))
                                        .description(OptionDescription.of(Text.literal("Should render the passiveMob")))
                                        .binding(defaults.passiveMobShouldRender, () -> config.passiveMobShouldRender, newVal -> config.passiveMobShouldRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Using Server-side Position"))
                                        .description(OptionDescription.of(Text.literal("Renders the hitbox based on where the passiveMob is server side. Doesn't really have a functionality as entity interactions are determined by client-side calculations ")))
                                        .binding(defaults.passiveMobServerSideRender, () -> config.passiveMobServerSideRender, newVal -> config.passiveMobServerSideRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Look Vector"))
                                        .description(OptionDescription.of(Text.literal("Renders look vector line")))
                                        .binding(defaults.passiveMobLookVector, () -> config.passiveMobLookVector, newVal -> config.passiveMobLookVector = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Hitbox Line Thickness"))
                                        .description(OptionDescription.of(Text.literal("How thick the lines of the hitbox are")))
                                        .binding(defaults.passiveMobBoxThickness, () -> config.passiveMobBoxThickness, newVal -> config.passiveMobBoxThickness = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Fill Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Fill the hitbox")))
                                        .binding(defaults.passiveMobFilledHitbox, () -> config.passiveMobFilledHitbox, newVal -> config.passiveMobFilledHitbox = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Filled Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hitbox when filled")))
                                        .binding(defaults.passiveMobFilledHitboxColor, () -> config.passiveMobFilledHitboxColor, newVal -> config.passiveMobFilledHitboxColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish When Close"))
                                        .description(OptionDescription.of(Text.literal("Remove the hitbox when you get close")))
                                        .binding(defaults.passiveMobVanishWhenClose, () -> config.passiveMobVanishWhenClose, newVal -> config.passiveMobVanishWhenClose = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish Fade"))
                                        .description(OptionDescription.of(Text.literal("Fades away instead of instantly vanishing")))
                                        .binding(defaults.passiveMobVanishFade, () -> config.passiveMobVanishFade, newVal -> config.passiveMobVanishFade = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Vanish Distance"))
                                        .description(OptionDescription.of(Text.literal("How close you have to be for the hitbox to vanish")))
                                        .binding(defaults.passiveMobVanishDistance, () -> config.passiveMobVanishDistance, newVal -> config.passiveMobVanishDistance = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 50, 0.1))
                                        .build())
                                .build()) //group
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Settings"))
                                .description(OptionDescription.of(Text.literal("All the color settings for the passiveMob hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Rainbow"))
                                        .description(OptionDescription.of(Text.literal("Enable a rainbow color")))
                                        .binding(defaults.passiveMobRainbow, () -> config.passiveMobRainbow, newVal -> config.passiveMobRainbow = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Top gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the passiveMob's hitbox")))
                                        .binding(defaults.passiveMobColor, () -> config.passiveMobColor, newVal ->
                                        {
                                            config.passiveMobColor = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Bottom Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox have a gradient")))
                                        .binding(defaults.passiveMobColorGradientEnabled, () -> config.passiveMobColorGradientEnabled, newVal -> config.passiveMobColorGradientEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Bottom gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the passiveMob's hitbox gradient (bottom half)")))
                                        .binding(defaults.passiveMobColorGradient, () -> config.passiveMobColorGradient, newVal ->
                                        {
                                            config.passiveMobColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Damage Color Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox change color in a damage tick")))
                                        .binding(defaults.passiveMobChangeColorOnDamageTick, () -> config.passiveMobChangeColorOnDamageTick, newVal -> config.passiveMobChangeColorOnDamageTick = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Damage Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the passiveMob's hitbox when damaged")))
                                        .binding(defaults.passiveMobDamageTickColor, () -> config.passiveMobDamageTickColor, newVal -> config.passiveMobDamageTickColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .build()) //group

                        .build())

                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("AngerableMob Hitboxes"))

                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("General Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the angerableMob hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Should Render"))
                                        .description(OptionDescription.of(Text.literal("Should render the angerableMob")))
                                        .binding(defaults.angerableMobShouldRender, () -> config.angerableMobShouldRender, newVal -> config.angerableMobShouldRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Using Server-side Position"))
                                        .description(OptionDescription.of(Text.literal("Renders the hitbox based on where the angerableMob is server side. Doesn't really have a functionality as entity interactions are determined by client-side calculations ")))
                                        .binding(defaults.angerableMobServerSideRender, () -> config.angerableMobServerSideRender, newVal -> config.angerableMobServerSideRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Look Vector"))
                                        .description(OptionDescription.of(Text.literal("Renders look vector line")))
                                        .binding(defaults.angerableMobLookVector, () -> config.angerableMobLookVector, newVal -> config.angerableMobLookVector = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Hitbox Line Thickness"))
                                        .description(OptionDescription.of(Text.literal("How thick the lines of the hitbox are")))
                                        .binding(defaults.angerableMobBoxThickness, () -> config.angerableMobBoxThickness, newVal -> config.angerableMobBoxThickness = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Fill Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Fill the hitbox")))
                                        .binding(defaults.angerableMobFilledHitbox, () -> config.angerableMobFilledHitbox, newVal -> config.angerableMobFilledHitbox = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Filled Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hitbox when filled")))
                                        .binding(defaults.angerableMobFilledHitboxColor, () -> config.angerableMobFilledHitboxColor, newVal -> config.angerableMobFilledHitboxColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish When Close"))
                                        .description(OptionDescription.of(Text.literal("Remove the hitbox when you get close")))
                                        .binding(defaults.angerableMobVanishWhenClose, () -> config.angerableMobVanishWhenClose, newVal -> config.angerableMobVanishWhenClose = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish Fade"))
                                        .description(OptionDescription.of(Text.literal("Fades away instead of instantly vanishing")))
                                        .binding(defaults.angerableMobVanishFade, () -> config.angerableMobVanishFade, newVal -> config.angerableMobVanishFade = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Vanish Distance"))
                                        .description(OptionDescription.of(Text.literal("How close you have to be for the hitbox to vanish")))
                                        .binding(defaults.angerableMobVanishDistance, () -> config.angerableMobVanishDistance, newVal -> config.angerableMobVanishDistance = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 50, 0.1))
                                        .build())
                                .build()) //group
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Settings"))
                                .description(OptionDescription.of(Text.literal("All the color settings for the angerableMob hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Rainbow"))
                                        .description(OptionDescription.of(Text.literal("Enable a rainbow color")))
                                        .binding(defaults.angerableMobRainbow, () -> config.angerableMobRainbow, newVal -> config.angerableMobRainbow = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Top gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the angerableMob's hitbox")))
                                        .binding(defaults.angerableMobColor, () -> config.angerableMobColor, newVal ->
                                        {
                                            config.angerableMobColor = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Bottom Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox have a gradient")))
                                        .binding(defaults.angerableMobColorGradientEnabled, () -> config.angerableMobColorGradientEnabled, newVal -> config.angerableMobColorGradientEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Bottom gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the angerableMob's hitbox gradient (bottom half)")))
                                        .binding(defaults.angerableMobColorGradient, () -> config.angerableMobColorGradient, newVal ->
                                        {
                                            config.angerableMobColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Damage Color Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox change color in a damage tick")))
                                        .binding(defaults.angerableMobChangeColorOnDamageTick, () -> config.angerableMobChangeColorOnDamageTick, newVal -> config.angerableMobChangeColorOnDamageTick = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Damage Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the angerableMob's hitbox when damaged")))
                                        .binding(defaults.angerableMobDamageTickColor, () -> config.angerableMobDamageTickColor, newVal -> config.angerableMobDamageTickColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .build()) //group

                        .build())

                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Other Hitboxes"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("General Settings"))
                                .description(OptionDescription.of(Text.literal("All the general settings for the elseEntity hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Should Render"))
                                        .description(OptionDescription.of(Text.literal("Should render the elseEntity")))
                                        .binding(defaults.elseEntityShouldRender, () -> config.elseEntityShouldRender, newVal -> config.elseEntityShouldRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Render Using Server-side Position"))
                                        .description(OptionDescription.of(Text.literal("Renders the hitbox based on where the elseEntity is server side. Doesn't really have a functionality as entity interactions are determined by client-side calculations ")))
                                        .binding(defaults.elseEntityServerSideRender, () -> config.elseEntityServerSideRender, newVal -> config.elseEntityServerSideRender = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Hitbox Line Thickness"))
                                        .description(OptionDescription.of(Text.literal("How thick the lines of the hitbox are")))
                                        .binding(defaults.elseEntityBoxThickness, () -> config.elseEntityBoxThickness, newVal -> config.elseEntityBoxThickness = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 5, 0.1))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Fill Hitbox"))
                                        .description(OptionDescription.of(Text.literal("Fill the hitbox")))
                                        .binding(defaults.elseEntityFilledHitbox, () -> config.elseEntityFilledHitbox, newVal -> config.elseEntityFilledHitbox = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Filled Color"))
                                        .description(OptionDescription.of(Text.literal("Color of the hitbox when filled")))
                                        .binding(defaults.elseEntityFilledHitboxColor, () -> config.elseEntityFilledHitboxColor, newVal -> config.elseEntityFilledHitboxColor = newVal)
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish When Close"))
                                        .description(OptionDescription.of(Text.literal("Remove the hitbox when you get close")))
                                        .binding(defaults.elseEntityVanishWhenClose, () -> config.elseEntityVanishWhenClose, newVal -> config.elseEntityVanishWhenClose = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Vanish Fade"))
                                        .description(OptionDescription.of(Text.literal("Fades away instead of instantly vanishing")))
                                        .binding(defaults.elseEntityVanishFade, () -> config.elseEntityVanishFade, newVal -> config.elseEntityVanishFade = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(double.class)
                                        .name(Text.literal("Vanish Distance"))
                                        .description(OptionDescription.of(Text.literal("How close you have to be for the hitbox to vanish")))
                                        .binding(defaults.elseEntityVanishDistance, () -> config.elseEntityVanishDistance, newVal -> config.elseEntityVanishDistance = newVal)
                                        .customController(doubleOption -> new <Double>DoubleSliderController(doubleOption, 0, 50, 0.1))
                                        .build())
                                .build()) //group
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Settings"))
                                .description(OptionDescription.of(Text.literal("All the color settings for the elseEntity hitboxes")))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Rainbow"))
                                        .description(OptionDescription.of(Text.literal("Enable a rainbow color")))
                                        .binding(defaults.elseEntityRainbow, () -> config.elseEntityRainbow, newVal -> config.elseEntityRainbow  = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Top gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the elseEntity's hitbox")))
                                        .binding(defaults.elseEntityColor, () -> config.elseEntityColor, newVal ->
                                        {
                                            config.elseEntityColor = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .option(Option.createBuilder(Boolean.class)
                                        .name(Text.literal("Bottom Gradient Enabled"))
                                        .description(OptionDescription.of(Text.literal("Should the hitbox have a gradient")))
                                        .binding(defaults.elseEntityColorGradientEnabled, () -> config.elseEntityColorGradientEnabled, newVal -> config.elseEntityColorGradientEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create).instant(true)
                                        .build())
                                .option(Option.createBuilder(Color.class)
                                        .name(Text.literal("Color (Bottom gradient)"))
                                        .description(OptionDescription.of(Text.literal("Color of the elseEntity's hitbox gradient (bottom half)")))
                                        .binding(defaults.elseEntityColorGradient, () -> config.elseEntityColorGradient, newVal ->
                                        {
                                            config.elseEntityColorGradient = newVal;
                                        })
                                        .customController(colorOption -> new ColorController(colorOption, true))
                                        .build())
                                .build()) //group

                        .build())
        ));
        //CATEGORIES = screen.categories();
        return screen.generateScreen(parent);
    }

}

