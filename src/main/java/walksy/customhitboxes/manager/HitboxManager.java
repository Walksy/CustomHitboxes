package walksy.customhitboxes.manager;

import dev.isxander.yacl3.api.Option;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.minecraft.entity.*;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Items;
import walksy.customhitboxes.config.ConfigIntegration;
import walksy.customhitboxes.config.setting.Settings;
import walksy.customhitboxes.helper.ColorHelper;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static walksy.customhitboxes.CustomHitboxesMod.mc;

public class HitboxManager {

    private static boolean loadedOnce = false;

        private static Settings getConfig(Entity entity) {
            if (entity instanceof PlayerEntity) {
                return get(Settings.Type.PLAYER);
            } else if (entity instanceof ProjectileEntity projectile) {
                if (projectile instanceof EnderPearlEntity) {
                    return get(Settings.Type.ENDER_PEARL);
                }
                return get(Settings.Type.PROJECTILE);
            } else if (entity instanceof HostileEntity) {
                return get(Settings.Type.HOSTILE_MOB);
            } else if (entity instanceof Angerable) {
                return get(Settings.Type.ANGERABLE_MOB);
            } else if (entity instanceof PassiveEntity) {
                return get(Settings.Type.PASSIVE_MOB);
            } else if (entity instanceof EndCrystalEntity) {
                return get(Settings.Type.END_CRYSTAL);
            } else if (entity instanceof FallingBlockEntity) {
                return get(Settings.Type.BLOCK_ENTITY);
            } else if (entity instanceof ItemEntity) {
                return get(Settings.Type.ITEM);
            }
            return get(Settings.Type.OTHER);
    }

    private static Settings get(Settings.Type type)
    {
        return ConfigIntegration.CONFIG.instance().getCurrentSettings().get(type);
    }


    public static boolean isFilled(Entity entity)
    {
        return getConfig(entity).filledHitbox;
    }

    public static Color getColor(Entity entity) {
        Settings config = getConfig(entity);
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

        if (config.changeColorWhenClose)
        {
            return ColorHelper.lerpToColor(config.color, config.closeColor, entity, config.closeDistance);
        }

        if (config.rainbow)
        {
            return ColorHelper.getRainbowColor();
        }
        return config.color;
    }

    public static Color getFilledColor(Entity entity) {
        Settings config = getConfig(entity);
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

        if (config.changeColorWhenClose)
        {
            return ColorHelper.lerpToColor(config.filledHitboxColor, config.closeColor, entity, config.closeDistance);
        }
        if (config.rainbow)
        {
            return ColorHelper.getRainbowColor();
        }
        return config.filledHitboxColor;
    }

    public static Color getColorGradient(Entity entity) {
        Settings config = getConfig(entity);
        if (entity instanceof PlayerEntity p && getTeamColorGradient(p) != null)
        {
            if (config.changeColorOnDamageTick && p.hurtTime > 0) {
                return config.damageTickColor;
            }
            return getTeamColorGradient(p);
        }
        if (entity instanceof ProjectileEntity projectile)
        {
            if (projectile.getOwner() != null && projectile.getOwner() instanceof PlayerEntity owner && ConfigIntegration.CONFIG.instance().recolorProjectiles)
            {
                if (getTeamColor(owner) != null)
                {
                    return getTeamColorGradient(owner);
                }
            }
        }
        if (config.colorGradientEnabled)
        {
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

            if (config.changeColorWhenClose)
            {
                return ColorHelper.lerpToColor(config.colorGradient, config.closeColor, entity, config.closeDistance);
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
        if (entity instanceof ProjectileEntity projectile && projectile.getOwner() != null && projectile.getOwner() instanceof PlayerEntity owner)
        {
            if (getTeamColor(owner) != null)
            {
                return ConfigIntegration.CONFIG.instance().renderProjectiles;
            }
        }
        return getConfig(entity).shouldRender;
    }

    public static boolean shouldRenderLookVector(Entity entity) {
        return getConfig(entity).renderLookVector;
    }

    public static double getLineThickness(Entity entity) {
        return getConfig(entity).boxThickness;
    }

    public static int hitboxMinus(Entity entity) {
        Settings config = getConfig(entity);
        if (!config.vanishWhenClose || entity == mc.player) return 0;
        if (config.elytraAlwaysRender && entity instanceof PlayerEntity p)
        {
            if (p.getEquippedStack(EquipmentSlot.CHEST).isOf(Items.ELYTRA))
            {
                return 0;
            }
        }
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
        return getConfig(entity).serverSideRender;
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
