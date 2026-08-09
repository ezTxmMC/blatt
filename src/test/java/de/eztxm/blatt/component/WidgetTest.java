package de.eztxm.blatt.component;

import de.eztxm.blatt.core.Document;
import de.eztxm.blatt.core.Element;
import de.eztxm.blatt.core.HeadConfig;
import de.eztxm.blatt.core.HtmlWriter;
import de.eztxm.blatt.core.Text;
import de.eztxm.blatt.css.Css;
import de.eztxm.blatt.css.Rule;
import de.eztxm.blatt.css.Style;
import de.eztxm.blatt.css.StyleSheet;
import de.eztxm.blatt.element.Div;
import de.eztxm.blatt.element.Image;
import de.eztxm.blatt.element.Main;
import de.eztxm.blatt.element.Paragraph;
import de.eztxm.blatt.element.Span;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WidgetTest {

    @Component("card")
    static final class Card extends Widget {

        @Override
        protected Element root() {
            return new Div(new Span(new Text("body")).cssClass("title"));
        }

        @Override
        protected StyleSheet styles() {
            return new StyleSheet()
                .rule(new Rule("&", new Style().padding(Css.rem(1)))
                    .on(":hover", new Style().opacity(0.9)))
                .rule(".title", new Style().fontWeight(700));
        }
    }

    @Component("deep")
    static final class Deep extends Widget {

        @Override
        protected Element root() {
            return new Div(
                new Paragraph(new Text("text"), new Image("a.png", "a")),
                new Span(new Text("x")).cssClass("title")
            );
        }
    }

    @Component("outer")
    static final class Outer extends Widget {

        @Override
        protected Element root() {
            return new Div(new Span(new Text("own")).cssClass("title"), new Card());
        }

        @Override
        protected StyleSheet styles() {
            return new StyleSheet().rule(".title", new Style().color("red"));
        }
    }

    @Component(value = "plain", scoped = false)
    static final class Plain extends Widget {

        @Override
        protected Element root() {
            return new Div(new Span(new Text("x")));
        }

        @Override
        protected StyleSheet styles() {
            return new StyleSheet().rule(".plain", new Style().color("red"));
        }
    }

    static final class Bare extends Widget {

        @Override
        protected Element root() {
            return new Div();
        }
    }

    private String render(de.eztxm.blatt.core.Component component) {
        HtmlWriter writer = new HtmlWriter();
        component.render(writer);
        return writer.result();
    }

    private String attribute(Widget widget) {
        return widget.scope().attribute();
    }

    @Test
    void scope_name_uses_annotation_value_with_stable_suffix() {
        Scope scope = new Card().scope();
        assertTrue(scope.name().startsWith("card-"), scope.name());
        assertEquals(scope.name(), new Card().scope().name());
        assertEquals("[" + scope.attribute() + "]", scope.selector());
    }

    @Test
    void scope_name_falls_back_to_kebab_class_name() {
        assertTrue(new Bare().scope().name().startsWith("bare-"));
    }

    @Test
    void every_element_of_the_subtree_is_marked() {
        Deep deep = new Deep();
        String scope = attribute(deep);

        assertEquals(
            "<div " + scope + "=\"\">"
                + "<p " + scope + "=\"\">text<img src=\"a.png\" alt=\"a\" " + scope + "=\"\"></p>"
                + "<span class=\"title\" " + scope + "=\"\">x</span>"
                + "</div>",
            render(deep)
        );
    }

    @Test
    void styles_qualify_selectors_with_the_scope_attribute() {
        Card card = new Card();
        String scope = "[" + attribute(card) + "]";

        assertEquals(
            scope + "{padding:1rem;}" + scope + ":hover{opacity:0.9;}"
                + ".title" + scope + "{font-weight:700;}",
            card.scopedStyles().render()
        );
    }

    @Test
    void nested_component_keeps_its_own_subtree_private() {
        Outer outer = new Outer();
        String html = render(outer);
        String outerScope = attribute(outer);
        String innerScope = attribute(new Card());

        assertTrue(html.contains("<span class=\"title\" " + outerScope + "=\"\">own</span>"));
        assertTrue(html.contains("<span class=\"title\" " + innerScope + "=\"\">body</span>"));
        assertFalse(html.contains("class=\"title\" " + innerScope + "=\"\" " + outerScope));
        assertFalse(html.contains("class=\"title\" " + outerScope + "=\"\" " + innerScope));
    }

    @Test
    void nested_component_root_also_carries_the_parent_scope() {
        Outer outer = new Outer();
        String html = render(outer);

        assertTrue(
            html.contains("<div " + attribute(new Card()) + "=\"\" " + attribute(outer) + "=\"\">"),
            html
        );
    }

    @Test
    void unscoped_component_marks_nothing_and_keeps_global_selectors() {
        Plain plain = new Plain();
        assertFalse(plain.scope().isScoped());
        assertEquals("<div><span>x</span></div>", render(plain));
        assertEquals(".plain{color:red;}", plain.scopedStyles().render());
    }

    @Test
    void widget_styles_are_collected_while_rendering() {
        HtmlWriter writer = new HtmlWriter();
        Card card = new Card();
        card.render(writer);

        assertFalse(writer.styles().isEmpty());
        assertTrue(writer.styles().sheet().render().contains("[" + attribute(card) + "]"));
    }

    @Test
    void repeated_component_emits_styles_once() {
        Card card = new Card();
        String html = new Document(new Main(new Card(), new Card(), card)).render();
        String block = html.substring(html.indexOf("<style>"), html.indexOf("</style>"));

        assertEquals(1, countOccurrences(block, "[" + attribute(card) + "]{padding:1rem;}"));
    }

    @Test
    void document_emits_component_styles_after_head_styles() {
        HeadConfig head = new HeadConfig()
            .style(new StyleSheet().rule("body", new Style().margin("0")));

        String html = new Document(new Main(new Card()), head).render();
        assertTrue(html.contains("<style>body{margin:0;}["));
    }

    @Test
    void document_collects_styles_of_nested_components() {
        String html = new Document(new Main(new Outer())).render();

        assertTrue(html.contains("[" + attribute(new Outer()) + "]"));
        assertTrue(html.contains("[" + attribute(new Card()) + "]"));
    }

    @Test
    void document_without_components_has_no_style_block() {
        assertFalse(new Document(new Main(new Text("hi"))).render().contains("<style>"));
    }

    private static int countOccurrences(String value, String needle) {
        int count = 0;
        int index = value.indexOf(needle);

        while (index >= 0) {
            count++;
            index = value.indexOf(needle, index + needle.length());
        }

        return count;
    }
}
