package de.eztxm.blatt.core;

import de.eztxm.blatt.css.Css;
import de.eztxm.blatt.css.Style;
import de.eztxm.blatt.css.StyleSheet;
import de.eztxm.blatt.element.Div;
import de.eztxm.blatt.element.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DocumentStyleTest {

    private String render(Component component) {
        HtmlWriter writer = new HtmlWriter();
        component.render(writer);
        return writer.result();
    }

    @Test
    void element_style_renders_inline_attribute() {
        assertEquals(
            "<div style=\"color:red;padding:1rem;\"></div>",
            render(new Div().style(new Style().color("red").padding(Css.rem(1))))
        );
    }

    @Test
    void void_element_style_renders_inline_attribute() {
        assertEquals(
            "<img src=\"cat.png\" alt=\"A cat\" style=\"width:100%;\">",
            render(new Image("cat.png", "A cat").style(new Style().width(Css.percent(100))))
        );
    }

    @Test
    void head_renders_style_block() {
        HeadConfig head = new HeadConfig()
            .style(new StyleSheet().rule("body", new Style().margin("0")));

        assertTrue(new Document(new Div(), head).render().contains("<style>body{margin:0;}</style>"));
    }

    @Test
    void head_combines_multiple_stylesheets() {
        HeadConfig head = new HeadConfig()
            .style(new StyleSheet().rule("body", new Style().margin("0")))
            .style(new StyleSheet().rule("a", new Style().color("red")));

        assertTrue(new Document(new Div(), head).render()
            .contains("<style>body{margin:0;}a{color:red;}</style>"));
    }

    @Test
    void head_without_styles_renders_no_style_block() {
        assertFalse(new Document(new Div()).render().contains("<style>"));
    }

    @Test
    void style_block_follows_external_stylesheets() {
        HeadConfig head = new HeadConfig()
            .stylesheet("/app.css")
            .style(new StyleSheet().rule("body", new Style().margin("0")));

        String html = new Document(new Div(), head).render();
        assertTrue(html.indexOf("/app.css") < html.indexOf("<style>"));
    }
}
