package de.eztxm.blatt.css;

public final class Css {

    private Css() {}

    public static String number(double value) {
        if (value == Math.rint(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }

        return String.valueOf(value);
    }

    public static String px(double value) {
        return number(value) + "px";
    }

    public static String rem(double value) {
        return number(value) + "rem";
    }

    public static String em(double value) {
        return number(value) + "em";
    }

    public static String percent(double value) {
        return number(value) + "%";
    }

    public static String vh(double value) {
        return number(value) + "vh";
    }

    public static String vw(double value) {
        return number(value) + "vw";
    }

    public static String vmin(double value) {
        return number(value) + "vmin";
    }

    public static String vmax(double value) {
        return number(value) + "vmax";
    }

    public static String ch(double value) {
        return number(value) + "ch";
    }

    public static String fr(double value) {
        return number(value) + "fr";
    }

    public static String seconds(double value) {
        return number(value) + "s";
    }

    public static String millis(double value) {
        return number(value) + "ms";
    }

    public static String deg(double value) {
        return number(value) + "deg";
    }

    public static String hex(String value) {
        return value.startsWith("#") ? value : "#" + value;
    }

    public static String rgb(int red, int green, int blue) {
        return "rgb(" + red + " " + green + " " + blue + ")";
    }

    public static String rgba(int red, int green, int blue, double alpha) {
        return "rgb(" + red + " " + green + " " + blue + " / " + number(alpha) + ")";
    }

    public static String hsl(double hue, double saturation, double lightness) {
        return "hsl(" + number(hue) + "deg " + number(saturation) + "% " + number(lightness) + "%)";
    }

    public static String hsla(double hue, double saturation, double lightness, double alpha) {
        return "hsl(" + number(hue) + "deg " + number(saturation) + "% " + number(lightness)
            + "% / " + number(alpha) + ")";
    }

    public static String var(String name) {
        return "var(" + variableName(name) + ")";
    }

    public static String var(String name, String fallback) {
        return "var(" + variableName(name) + ", " + fallback + ")";
    }

    public static String calc(String expression) {
        return "calc(" + expression + ")";
    }

    public static String min(String... values) {
        return "min(" + String.join(", ", values) + ")";
    }

    public static String max(String... values) {
        return "max(" + String.join(", ", values) + ")";
    }

    public static String clamp(String minimum, String preferred, String maximum) {
        return "clamp(" + minimum + ", " + preferred + ", " + maximum + ")";
    }

    public static String url(String value) {
        return "url(\"" + value.replace("\"", "\\\"") + "\")";
    }

    public static String quoted(String value) {
        return "\"" + value.replace("\"", "\\\"") + "\"";
    }

    public static String repeat(String count, String track) {
        return "repeat(" + count + ", " + track + ")";
    }

    public static String repeat(int count, String track) {
        return repeat(String.valueOf(count), track);
    }

    private static String variableName(String name) {
        return name.startsWith("--") ? name : "--" + name;
    }
}
