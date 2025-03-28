package walksy.customhitboxes.manager;

import dev.isxander.yacl3.api.Option;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import walksy.customhitboxes.config.ConfigIntegration;
import walksy.customhitboxes.config.HitboxConfig;
import walksy.customhitboxes.helper.ColorHelper;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static walksy.customhitboxes.CustomHitboxesMod.mc;

public class HitboxManager {

    private static boolean loadedOnce = false;

    private static HitboxConfig getConfig(Entity entity) {
        var config = ConfigIntegration.CONFIG.instance();
        if (entity instanceof PlayerEntity) {
            return createHitboxConfig(config.playerColor, config.playerColorGradient, config.playerColorGradientEnabled, config.playerShouldRender, config.playerLookVector, config.playerChangeColorOnDamageTick, config.playerDamageTickColor, config.playerBoxThickness, config.playerVanishWhenClose, config.playerVanishFade, config.playerVanishDistance, config.playerServerSideRender, config.playerFilledHitbox, config.playerFilledHitboxColor, config.playerRainbow);
        } else if (entity instanceof ProjectileEntity projectile) {
            if (projectile instanceof EnderPearlEntity)
            {
                return createHitboxConfig(config.pearlColor, config.pearlColorGradient, config.pearlColorGradientEnabled, config.pearlShouldRender, false, false, null, config.pearlBoxThickness, config.pearlVanishWhenClose, config.pearlVanishFade, config.pearlVanishDistance, config.pearlServerSideRender, config.pearlFilledHitbox, config.pearlFilledHitboxColor, config.pearlRainbow);
            }
            return createHitboxConfig(config.projectileColor, config.projectileColorGradient, config.projectileColorGradientEnabled, config.projectileShouldRender, false, false, null, config.projectileBoxThickness, config.projectileVanishWhenClose, config.projectileVanishFade, config.projectileVanishDistance, config.projectileServerSideRender, config.projectileFilledHitbox, config.projectileFilledHitboxColor, config.projectileRainbow);
        } else if (entity instanceof HostileEntity) {
            return createHitboxConfig(config.hostileMobColor, config.hostileMobColorGradient, config.hostileMobColorGradientEnabled, config.hostileMobShouldRender, config.hostileMobLookVector, config.hostileMobChangeColorOnDamageTick, config.hostileMobDamageTickColor, config.hostileMobBoxThickness, config.hostileMobVanishWhenClose, config.hostileMobVanishFade, config.hostileMobVanishDistance, config.hostileMobServerSideRender, config.hostileMobFilledHitbox, config.hostileMobFilledHitboxColor, config.hostileMobRainbow);
        } else if (entity instanceof Angerable) {
            return createHitboxConfig(config.angerableMobColor, config.angerableMobColorGradient, config.angerableMobColorGradientEnabled, config.angerableMobShouldRender, config.angerableMobLookVector, config.angerableMobChangeColorOnDamageTick, config.angerableMobDamageTickColor, config.angerableMobBoxThickness, config.angerableMobVanishWhenClose, config.angerableMobVanishFade, config.angerableMobVanishDistance, config.angerableMobServerSideRender, config.angerableMobFilledHitbox, config.angerableMobFilledHitboxColor, config.angerableMobRainbow);
        } else if (entity instanceof PassiveEntity) {
            return createHitboxConfig(config.passiveMobColor, config.passiveMobColorGradient, config.passiveMobColorGradientEnabled, config.passiveMobShouldRender, config.passiveMobLookVector, config.passiveMobChangeColorOnDamageTick, config.passiveMobDamageTickColor, config.passiveMobBoxThickness, config.passiveMobVanishWhenClose, config.passiveMobVanishFade, config.passiveMobVanishDistance, config.passiveMobServerSideRender, config.passiveMobFilledHitbox, config.passiveMobFilledHitboxColor, config.passiveMobRainbow);
        } else if (entity instanceof EndCrystalEntity) {
            return createHitboxConfig(config.endCrystalColor, config.endCrystalColorGradient, config.endCrystalColorGradientEnabled, config.endCrystalShouldRender, false, false, null, config.endCrystalBoxThickness, config.endCrystalVanishWhenClose, config.endCrystalVanishFade, config.endCrystalVanishDistance, config.endCrystalServerSideRender, config.endCrystalFilledHitbox, config.endCrystalFilledHitboxColor, config.endCrystalRainbow);
        } else if (entity instanceof FallingBlockEntity)
        {
            return createHitboxConfig(config.blockentityColor, config.blockentityColorGradient, config.blockentityColorGradientEnabled, config.blockentityShouldRender, false, false, null, config.blockentityBoxThickness, config.blockentityVanishWhenClose, config.blockentityVanishFade, config.blockentityVanishDistance, config.blockentityServerSideRender, config.blockentityFilledHitbox, config.blockentityFilledHitboxColor, config.blockentityRainbow);
        } else if (entity instanceof ItemEntity)
        {
            return createHitboxConfig(config.itementityColor, config.itementityColorGradient, config.itementityColorGradientEnabled, config.itementityShouldRender, false, false, null, config.itementityBoxThickness, config.itementityVanishWhenClose, config.itementityVanishFade, config.itementityVanishDistance, config.itementityServerSideRender, config.itementityFilledHitbox, config.itementityFilledHitboxColor, config.itementityRainbow);
        }
        return createHitboxConfig(config.elseEntityColor, config.elseEntityColorGradient, config.elseEntityColorGradientEnabled, config.elseEntityShouldRender, false, false, null, config.elseEntityBoxThickness, config.elseEntityVanishWhenClose, config.elseEntityVanishFade, config.elseEntityVanishDistance, config.elseEntityServerSideRender, config.elseEntityFilledHitbox, config.elseEntityFilledHitboxColor, config.elseEntityRainbow);
    }

    private static HitboxConfig createHitboxConfig(Color color, Color colorGradient, boolean colorGradientEnabled, boolean render, boolean renderLookVector, boolean changeColorOnDamageTick, Color damageTickColor, double lineThickness, boolean vanishWhenClose, boolean vanishFade, double vanishDistance, boolean renderServerSide, boolean filled, Color filledColor, boolean rainbow) {
        return new HitboxConfig(color, colorGradient, colorGradientEnabled, render, renderLookVector, changeColorOnDamageTick, damageTickColor, lineThickness, vanishWhenClose, vanishFade, vanishDistance, renderServerSide, filled, filledColor, rainbow);
    }

    public static boolean isFilled(Entity entity)
    {
        return getConfig(entity).filled;
    }

    public static Color getColor(Entity entity) {
        HitboxConfig config = getConfig(entity);
        if (config.changeColorOnDamageTick && entity instanceof LivingEntity && ((LivingEntity) entity).hurtTime > 0) {
            return config.damageTickColor;
        }
        if (entity instanceof ProjectileEntity projectile)
        {
            if (projectile.getOwner() != null && projectile.getOwner() instanceof PlayerEntity owner && ConfigIntegration.CONFIG.instance().recolorProjectiles)
            {
                if (getTeamColor(owner) != null)
                {
                    return getTeamColor(owner);
                }
            }
        }
        if (entity instanceof PlayerEntity p && getTeamColor(p) != null) {
            return getTeamColor(p);
        }

        if (config.rainbow)
        {
            return ColorHelper.getRainbowColor();
        }
        return config.color;
    }

    public static Color getFilledColor(Entity entity) {
        HitboxConfig config = getConfig(entity);
        if (config.changeColorOnDamageTick && entity instanceof LivingEntity && ((LivingEntity) entity).hurtTime > 0) {
            return config.damageTickColor;
        }
        if (config.rainbow)
        {
            return ColorHelper.getRainbowColor();
        }
        return config.filledColor;
    }

    public static Color getColorGradient(Entity entity) {
        HitboxConfig config = getConfig(entity);
        if (config.colorGradientEnabled)
        {
            if (config.changeColorOnDamageTick && entity instanceof LivingEntity && ((LivingEntity) entity).hurtTime > 0) {
                return config.damageTickColor;
            }

            //ew nesting
            if (entity instanceof ProjectileEntity projectile)
            {
                if (projectile.getOwner() != null && projectile.getOwner() instanceof PlayerEntity owner && ConfigIntegration.CONFIG.instance().recolorProjectiles)
                {
                    if (getTeamColor(owner) != null)
                    {
                        return getTeamColor(owner);
                    }
                }
            }

            if (entity instanceof PlayerEntity p && getTeamColorGradient(p) != null)
            {
                return getTeamColorGradient(p);
            }

            if (config.rainbow)
            {
                return ColorHelper.getRainbowColor();
            }

            return config.colorGradient;
        } else {
            return getColor(entity);
        }
    }

    public static boolean shouldRender(Entity entity) {
        return getConfig(entity).render;
    }

    public static boolean shouldRenderLookVector(Entity entity) {
        return getConfig(entity).renderLookVector;
    }

    public static double getLineThickness(Entity entity) {
        return getConfig(entity).lineThickness;
    }

    public static int hitboxMinus(Entity entity) {
        HitboxConfig config = getConfig(entity);
        if (!config.vanishWhenClose || entity == mc.player) return 0;
        int maxFadeAmount = 255;
        double fadeDistance = config.vanishDistance;
        double fadeStartDistance = fadeDistance * 1.5;
        double distanceToEntity = Math.sqrt(mc.gameRenderer.getCamera().getPos().squaredDistanceTo(entity.getPos()));
        if (config.vanishFade && distanceToEntity <= fadeStartDistance) {
            double fadeRatio = Math.min(1.0, (fadeStartDistance - distanceToEntity) / (fadeStartDistance - fadeDistance));
            return (int) (maxFadeAmount * fadeRatio);
        }
        return distanceToEntity <= fadeDistance ? 255 : 0;
    }

    public static boolean shouldRenderServerSide(Entity entity) {
        return getConfig(entity).renderServerSide;
    }

    //This was the only efficient way in my head to do this
    private static Color getTeamColor(PlayerEntity player) {
        String name = player.getName().getString();
        ConfigIntegration config = ConfigIntegration.CONFIG.instance();
        Map<List<String>, Color> teamColorMap = new HashMap<>();
        teamColorMap.put(config.team1, config.team1Color);
        teamColorMap.put(config.team2, config.team2Color);
        teamColorMap.put(config.team3, config.team3Color);
        teamColorMap.put(config.team4, config.team4Color);
        teamColorMap.put(config.team5, config.team5Color);

        for (Map.Entry<List<String>, Color> entry : teamColorMap.entrySet()) {
            if (entry.getKey().contains(name)) {
                return entry.getValue();
            }
        }

        return null;
    }

    private static Color getTeamColorGradient(PlayerEntity player) {
        String name = player.getName().getString();
        ConfigIntegration config = ConfigIntegration.CONFIG.instance();

        Map<List<String>, Color> teamColorMap = new HashMap<>();
        teamColorMap.put(config.team1, config.team1ColorGradient);
        teamColorMap.put(config.team2, config.team2ColorGradient);
        teamColorMap.put(config.team3, config.team3ColorGradient);
        teamColorMap.put(config.team4, config.team4ColorGradient);
        teamColorMap.put(config.team5, config.team5ColorGradient);

        Map<List<String>, Boolean> gradientEnabledMap = new HashMap<>();
        gradientEnabledMap.put(config.team1, config.team1ColorGradientEnabled);
        gradientEnabledMap.put(config.team2, config.team2ColorGradientEnabled);
        gradientEnabledMap.put(config.team3, config.team3ColorGradientEnabled);
        gradientEnabledMap.put(config.team4, config.team4ColorGradientEnabled);
        gradientEnabledMap.put(config.team5, config.team5ColorGradientEnabled);

        for (List<String> team : teamColorMap.keySet()) {
            if (team.contains(name)) {
                if (gradientEnabledMap.get(team)) {
                    return teamColorMap.get(team);
                } else {
                    return null; //return no color
                }
            }
        }

        return null;
    }

    //Enable hitboxes on game load, only does it once
    public static void loadHook() {
        ClientEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!loadedOnce && entity == mc.player) {
                loadedOnce = true;
                if (ConfigIntegration.CONFIG.instance().enabledOnGameLoad) {
                    mc.getEntityRenderDispatcher().setRenderHitboxes(true);
                }
            }
        });
    }


    //TODO OTHER SETTING PARENTS - nvm too annoying
    //TODO pcrit yap yap yap - Done
    public static void optionListener(Option<Boolean> booleanOption, Boolean aBoolean)
    {
        /*
        Birds nest
        String modEnabled = "CustomHitbox Mod Enabled";
        if (booleanOption.name().getString().equals(modEnabled))
        {
            for (ConfigCategory category : ConfigIntegration.CATEGORIES)
            {
                for (OptionGroup group : category.groups())
                {
                    for (Option<?> option : group.options())
                    {
                        if (!option.name().getString().equals(modEnabled)) {
                            option.setAvailable(aBoolean);
                        }
                    }
                }
            }
        }

         */
    }
}
