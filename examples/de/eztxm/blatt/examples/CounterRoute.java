package de.eztxm.blatt.examples;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Text;
import de.eztxm.blatt.css.Css;
import de.eztxm.blatt.css.MediaRule;
import de.eztxm.blatt.css.Rule;
import de.eztxm.blatt.css.Style;
import de.eztxm.blatt.css.StyleSheet;
import de.eztxm.blatt.element.Button;
import de.eztxm.blatt.element.Heading;
import de.eztxm.blatt.element.Main;
import de.eztxm.blatt.element.Paragraph;
import de.eztxm.blatt.routing.Page;
import de.eztxm.blatt.routing.Route;

@Route("/")
public final class CounterRoute implements Page {

    @Override
    public Component root() {
        return new Main(
                new Heading(1, new Text("Counter")),
                new Paragraph(new Text("0")).id("count").style(new Style()
                        .fontSize(Css.rem(3))
                        .fontWeight(700)),
                new Button(new Text("Increment")).onClick(e -> """
                        const count = document.getElementById('count');
                        count.textContent = ++count.textContent;
                        """).cssClass("action"),
                new CardWidget("Scoped", "Dieses CSS gehört nur der Karte."),
                new CardWidget("Dedupliziert", "Zweimal benutzt, einmal ausgeliefert."))
                .cssClass("counter");
    }

    @Override
    public StyleSheet styles() {
        return new StyleSheet()
                .variables(new Style()
                        .variable("accent", Css.hex("4f46e5"))
                        .variable("surface", Css.hex("ffffff"))
                        .variable("ink", Css.hex("111827")))
                .rule("body", new Style()
                        .display("grid")
                        .placeItems("center")
                        .minHeight(Css.vh(100))
                        .backgroundColor(Css.hex("f3f4f6"))
                        .color(Css.var("ink")))
                .rule(new Rule(".counter", new Style()
                        .display("flex")
                        .flexDirection("column")
                        .alignItems("center")
                        .gap(Css.rem(1))
                        .padding(Css.rem(2.5), Css.rem(3))
                        .backgroundColor(Css.var("surface"))
                        .borderRadius(Css.px(16))
                        .boxShadow("0 10px 30px " + Css.rgba(0, 0, 0, 0.08))))
                .rule(new Rule(".action", new Style()
                        .padding(Css.rem(0.75), Css.rem(1.5))
                        .border("none")
                        .borderRadius(Css.px(8))
                        .backgroundColor(Css.var("accent"))
                        .color(Css.hex("ffffff"))
                        .cursor("pointer")
                        .transition("background-color " + Css.millis(150) + " ease-in-out"))
                        .on(":hover", new Style()
                                .backgroundColor(Css.hex("4338ca")))
                        .on(":focus-visible", new Style()
                                .outline(Css.px(2), "solid", Css.var("accent"))
                                .outlineOffset(Css.px(2))))
                .media(MediaRule.maxWidth(Css.px(480))
                        .rule(".counter", new Style()
                                .width(Css.percent(90))
                                .padding(Css.rem(1.5))));
    }
}
