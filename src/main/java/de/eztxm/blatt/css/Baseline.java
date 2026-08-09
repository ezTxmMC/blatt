package de.eztxm.blatt.css;

public final class Baseline {

    private Baseline() {}

    public static StyleSheet reset() {
        return new StyleSheet()
            .rule("*, *::before, *::after", new Style()
                .boxSizing("border-box"))
            .rule("*", new Style()
                .margin("0"))
            .rule("html", new Style()
                .set("-webkit-text-size-adjust", "100%"))
            .rule("body", new Style()
                .minHeight(Css.vh(100))
                .lineHeight(1.5)
                .fontFamily("system-ui", "-apple-system", "Segoe UI", "Roboto", "sans-serif"))
            .rule("img, picture, video, canvas, svg", new Style()
                .display("block")
                .maxWidth(Css.percent(100)))
            .rule("input, button, textarea, select", new Style()
                .font("inherit"))
            .rule("h1, h2, h3, h4, h5, h6", new Style()
                .set("text-wrap", "balance"))
            .rule("p, li, figcaption", new Style()
                .set("text-wrap", "pretty"))
            .media(MediaRule.reducedMotion()
                .rule("*, *::before, *::after", new Style()
                    .set("animation-duration", "0.01ms")
                    .set("animation-iteration-count", "1")
                    .set("transition-duration", "0.01ms")
                    .set("scroll-behavior", "auto")));
    }
}
