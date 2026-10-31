package de.eztxm.blatt.css;

/**
 * Experimental support for Tailwind CSS v4 through the official browser build.
 * The browser build compiles classes at runtime, so it is meant for development
 * and prototyping, not production. Pages must be served with the
 * {@code Tailwind} option enabled on {@code HeadConfig}, {@code Router} or {@code RouteEntry}.
 */
public final class Tailwind {

    public static final String VERSION = "4.3.3";
    public static final String BROWSER_SCRIPT = "https://cdn.jsdelivr.net/npm/@tailwindcss/browser@" + VERSION;
    public static final String IMPORT = "@import \"tailwindcss\";";

    private Tailwind() {}
}
