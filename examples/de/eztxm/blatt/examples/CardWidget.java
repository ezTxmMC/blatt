package de.eztxm.blatt.examples;

import de.eztxm.blatt.component.Component;
import de.eztxm.blatt.component.Widget;
import de.eztxm.blatt.core.Element;
import de.eztxm.blatt.core.Text;
import de.eztxm.blatt.css.Css;
import de.eztxm.blatt.css.Rule;
import de.eztxm.blatt.css.Style;
import de.eztxm.blatt.css.StyleSheet;
import de.eztxm.blatt.element.Anchor;
import de.eztxm.blatt.element.Div;
import de.eztxm.blatt.element.Heading;
import de.eztxm.blatt.element.ListItem;
import de.eztxm.blatt.element.Paragraph;
import de.eztxm.blatt.element.UnorderedList;

@Component("card")
public final class CardWidget extends Widget {

    private final String title;
    private final String body;

    public CardWidget(String title, String body) {
        this.title = title;
        this.body = body;
    }

    @Override
    protected Element root() {
        return new Div(
                new Heading(2, new Text(title)).cssClass("title"),
                new Paragraph(new Text(body)),
                new UnorderedList(
                        new ListItem(new Anchor(new Text("Mehr erfahren")).href("#")
                                .style(new Style().color("inherit"))
                                .on(":hover", new Style().textDecoration("underline")))));
    }

    @Override
    protected StyleSheet styles() {
        return new StyleSheet()
                .rule(new Rule("&", new Style()
                        .display("flex")
                        .flexDirection("column")
                        .gap(Css.rem(0.5))
                        .padding(Css.rem(1.25))
                        .backgroundColor(Css.var("surface", Css.hex("ffffff")))
                        .borderRadius(Css.px(12))
                        .boxShadow("0 1px 3px " + Css.rgba(0, 0, 0, 0.12))
                        .transition("transform " + Css.millis(150) + " ease-out"))
                        .on(":hover", new Style()
                                .transform("translateY(-2px)")))
                .rule(".title", new Style()
                        .fontSize(Css.rem(1.1))
                        .margin("0")
                        .color(Css.var("accent", Css.hex("4f46e5"))))
                .rule("ul", new Style()
                        .listStyle("none")
                        .padding("0")
                        .margin("0"));
    }
}
