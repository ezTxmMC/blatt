package de.eztxm.blatt.core;

import de.eztxm.blatt.css.Css;
import de.eztxm.blatt.css.Style;
import de.eztxm.blatt.element.Anchor;
import de.eztxm.blatt.element.Button;
import de.eztxm.blatt.element.Cell;
import de.eztxm.blatt.element.Details;
import de.eztxm.blatt.element.Div;
import de.eztxm.blatt.element.Image;
import de.eztxm.blatt.element.Input;
import de.eztxm.blatt.element.ListItem;
import de.eztxm.blatt.element.Main;
import de.eztxm.blatt.element.Option;
import de.eztxm.blatt.element.Select;
import de.eztxm.blatt.element.Table;
import de.eztxm.blatt.element.TableRow;
import de.eztxm.blatt.element.TextArea;
import de.eztxm.blatt.element.UnorderedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementStyleTest {

    private HtmlWriter writer;

    private String render(Component component) {
        writer = new HtmlWriter();
        component.render(writer);
        return writer.result();
    }

    private String css() {
        return writer.styles().sheet().render();
    }

    @Test
    void state_style_generates_a_class_and_a_rule() {
        String html = render(new Button(new Text("Go"))
            .on(":hover", new Style().color("red")));

        assertEquals("<button class=\"b-1\">Go</button>", html);
        assertEquals(".b-1:hover{color:red;}", css());
    }

    @Test
    void state_style_keeps_the_authored_class() {
        String html = render(new Div().cssClass("card").on(":hover", new Style().color("red")));

        assertEquals("<div class=\"card b-1\"></div>", html);
    }

    @Test
    void base_style_uses_an_empty_state() {
        render(new Div().on("", new Style().display("flex")));
        assertEquals(".b-1{display:flex;}", css());
    }

    @Test
    void several_states_share_one_class() {
        render(new Anchor(new Text("x"))
            .on(":hover", new Style().color("red"))
            .on("::after", new Style().content(Css.quoted(" *"))));

        assertEquals(".b-1:hover{color:red;}.b-1::after{content:\" *\";}", css());
    }

    @Test
    void descendant_state_is_supported() {
        render(new Div().on(" p", new Style().margin("0")));
        assertEquals(".b-1 p{margin:0;}", css());
    }

    @Test
    void identical_styles_reuse_the_same_class() {
        String html = render(new Main(
            new Div().on(":hover", new Style().color("red")),
            new Div().on(":hover", new Style().color("red")),
            new Div().on(":hover", new Style().color("blue"))
        ));

        assertEquals(
            "<main><div class=\"b-1\"></div><div class=\"b-1\"></div><div class=\"b-2\"></div></main>",
            html
        );
        assertEquals(".b-1:hover{color:red;}.b-2:hover{color:blue;}", css());
    }

    @Test
    void void_element_supports_state_styles() {
        String html = render(new Image("a.png", "a").on(":hover", new Style().opacity(0.5)));

        assertEquals("<img src=\"a.png\" alt=\"a\" class=\"b-1\">", html);
        assertEquals(".b-1:hover{opacity:0.5;}", css());
    }

    @Test
    void inline_style_and_state_style_combine() {
        String html = render(new Div()
            .style(new Style().color("black"))
            .on(":hover", new Style().color("red")));

        assertEquals("<div style=\"color:black;\" class=\"b-1\"></div>", html);
        assertEquals(".b-1:hover{color:red;}", css());
    }

    @Test
    void state_styles_reach_the_document_head() {
        String html = new Document(new Main(new Div().on(":hover", new Style().color("red"))))
            .render();

        assertEquals(true, html.contains("<style>.b-1:hover{color:red;}</style>"));
    }

    @Test
    void fluent_calls_keep_the_concrete_type_in_any_order() {
        String html = render(new Button(new Text("Go"))
            .cssClass("action")
            .style(new Style().color("red"))
            .type("submit")
            .disabled());

        assertEquals(
            "<button class=\"action\" style=\"color:red;\" type=\"submit\" disabled=\"\">Go</button>",
            html
        );
    }

    @Test
    void new_elements_render_expected_tags() {
        assertEquals(
            "<ul><li>a</li></ul>",
            render(new UnorderedList(new ListItem(new Text("a"))))
        );
        assertEquals(
            "<table><tr><td colspan=\"2\">a</td></tr></table>",
            render(new Table(new TableRow(new Cell(new Text("a")).colSpan(2))))
        );
        assertEquals(
            "<select name=\"x\"><option value=\"1\" selected=\"\">a</option></select>",
            render(new Select(new Option(new Text("a")).value("1").selected()).name("x"))
        );
        assertEquals(
            "<textarea name=\"bio\" rows=\"3\"></textarea>",
            render(new TextArea().name("bio").rows(3))
        );
        assertEquals("<details open=\"\"></details>", render(new Details().open()));
        assertEquals("<input type=\"text\" required=\"\">", render(new Input("text").required()));
    }

    @Test
    void global_attributes_are_available_on_every_element() {
        assertEquals(
            "<div title=\"t\" role=\"button\" data-key=\"v\" aria-label=\"l\" tabindex=\"0\" hidden=\"\"></div>",
            render(new Div().title("t").role("button").data("key", "v").aria("label", "l")
                .tabIndex(0).hidden())
        );
    }
}
