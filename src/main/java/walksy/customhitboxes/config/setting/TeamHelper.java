package walksy.customhitboxes.config.setting;

import walksy.customhitboxes.config.ConfigIntegration;

import java.awt.*;
import java.util.List;

public class TeamHelper {

    @SuppressWarnings("unchecked")
    public static List<String> getTeamList(ConfigIntegration config, int index) {
        return switch (index) {
            case 1 -> config.team1;
            case 2 -> config.team2;
            case 3 -> config.team3;
            case 4 -> config.team4;
            case 5 -> config.team5;
            default -> throw new IllegalArgumentException("Invalid team index: " + index);
        };
    }

    @SuppressWarnings("unchecked")
    public static void setTeamList(ConfigIntegration config, int index, List<String> value) {
        switch (index) {
            case 1 -> config.team1 = value;
            case 2 -> config.team2 = value;
            case 3 -> config.team3 = value;
            case 4 -> config.team4 = value;
            case 5 -> config.team5 = value;
            default -> throw new IllegalArgumentException("Invalid team index: " + index);
        }
    }

    public static Color getTeamColor(ConfigIntegration config, int index) {
        return switch (index) {
            case 1 -> config.team1Color;
            case 2 -> config.team2Color;
            case 3 -> config.team3Color;
            case 4 -> config.team4Color;
            case 5 -> config.team5Color;
            default -> throw new IllegalArgumentException("Invalid team index: " + index);
        };
    }

    public static void setTeamColor(ConfigIntegration config, int index, Color value) {
        switch (index) {
            case 1 -> config.team1Color = value;
            case 2 -> config.team2Color = value;
            case 3 -> config.team3Color = value;
            case 4 -> config.team4Color = value;
            case 5 -> config.team5Color = value;
            default -> throw new IllegalArgumentException("Invalid team index: " + index);
        }
    }

    public static boolean getTeamGradientEnabled(ConfigIntegration config, int index) {
        return switch (index) {
            case 1 -> config.team1ColorGradientEnabled;
            case 2 -> config.team2ColorGradientEnabled;
            case 3 -> config.team3ColorGradientEnabled;
            case 4 -> config.team4ColorGradientEnabled;
            case 5 -> config.team5ColorGradientEnabled;
            default -> throw new IllegalArgumentException("Invalid team index: " + index);
        };
    }

    public static void setTeamGradientEnabled(ConfigIntegration config, int index, boolean value) {
        switch (index) {
            case 1 -> config.team1ColorGradientEnabled = value;
            case 2 -> config.team2ColorGradientEnabled = value;
            case 3 -> config.team3ColorGradientEnabled = value;
            case 4 -> config.team4ColorGradientEnabled = value;
            case 5 -> config.team5ColorGradientEnabled = value;
            default -> throw new IllegalArgumentException("Invalid team index: " + index);
        }
    }

    public static Color getTeamGradientColor(ConfigIntegration config, int index) {
        return switch (index) {
            case 1 -> config.team1ColorGradient;
            case 2 -> config.team2ColorGradient;
            case 3 -> config.team3ColorGradient;
            case 4 -> config.team4ColorGradient;
            case 5 -> config.team5ColorGradient;
            default -> throw new IllegalArgumentException("Invalid team index: " + index);
        };
    }

    public static void setTeamGradientColor(ConfigIntegration config, int index, Color value) {
        switch (index) {
            case 1 -> config.team1ColorGradient = value;
            case 2 -> config.team2ColorGradient = value;
            case 3 -> config.team3ColorGradient = value;
            case 4 -> config.team4ColorGradient = value;
            case 5 -> config.team5ColorGradient = value;
            default -> throw new IllegalArgumentException("Invalid team index: " + index);
        }
    }

}
