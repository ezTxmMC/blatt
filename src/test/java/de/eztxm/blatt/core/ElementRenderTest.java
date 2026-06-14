package de.eztxm.blatt.core;

import de.eztxm.blatt.element.Anchor;
import de.eztxm.blatt.element.Button;
import de.eztxm.blatt.element.Div;
import de.eztxm.blatt.element.Heading;
import de.eztxm.blatt.element.Image;
import de.eztxm.blatt.element.Input;
import de.eztxm.blatt.element.Main;
import de.eztxm.blatt.element.Span;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementRenderTest {

    private String render(Component component) {
        HtmlWriter writer = new HtmlWriter();
        component.render(writer);
        return writer.result();
    }

    @Test
    void text_renders_escaped_content() {
        assertEquals("Hello &amp; World", render(new Text("Hello & World")));
    }

    @Test
    void button_renders_with_onclick() {
        assertEquals(
            "<button onclick=\"fn()\">Click</button>",
            render(new Button(new Text("Click")).onClick("fn()"))
        );
    }

    @Test
    void main_renders_nested_child() {
        assertEquals("<main>content</main>", render(new Main(new Text("content"))));
    }

    @Test
    void heading_renders_correct_level() {
        assertEquals("<h1>Title</h1>", render(new Heading(1, new Text("Title"))));
        assertEquals("<h3>Sub</h3>", render(new Heading(3, new Text("Sub"))));
    }

    @Test
    void anchor_renders_href() {
        assertEquals(
            "<a href=\"/about\">Link</a>",
            render(new Anchor(new Text("Link")).href("/about"))
        );
    }

    @Test
    void image_is_void_no_close_tag() {
        assertEquals("<img src=\"cat.png\" alt=\"A cat\">", render(new Image("cat.png", "A cat")));
    }

    @Test
    void input_is_void_with_type() {
        assertEquals("<input type=\"text\">", render(new Input("text")));
    }

    @Test
    void nested_elements_render_correctly() {
        assertEquals("<div><span>hi</span></div>", render(new Div(new Span(new Text("hi")))));
    }

    @Test
    void element_attr_sets_attribute() {
        assertEquals("<div id=\"foo\"></div>", render(new Div().id("foo")));
    }

    @Test
    void onclick_escapes_attribute_value() {
        assertEquals(
            "<button onclick=\"alert(&quot;x&quot;)\">x</button>",
            render(new Button(new Text("x")).onClick("alert(\"x\")"))
        );
    }
}
