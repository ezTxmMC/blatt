package de.eztxm.blatt.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HtmlWriterTest {

    @Test
    void writes_open_and_close_tag() {
        HtmlWriter writer = new HtmlWriter();
        writer.openTag("div", new Attributes());
        writer.closeTag("div");
        assertEquals("<div></div>", writer.result());
    }

    @Test
    void writes_attribute_in_tag() {
        HtmlWriter writer = new HtmlWriter();
        Attributes attrs = new Attributes();
        attrs.set("class", "foo");
        writer.openTag("div", attrs);
        writer.closeTag("div");
        assertEquals("<div class=\"foo\"></div>", writer.result());
    }

    @Test
    void writes_text_with_escaping() {
        HtmlWriter writer = new HtmlWriter();
        writer.text("Hello & World");
        assertEquals("Hello &amp; World", writer.result());
    }

    @Test
    void writes_raw_without_escaping() {
        HtmlWriter writer = new HtmlWriter();
        writer.raw("<!DOCTYPE html>");
        assertEquals("<!DOCTYPE html>", writer.result());
    }

    @Test
    void attribute_set_replaces_existing() {
        Attributes attrs = new Attributes();
        attrs.set("id", "first");
        attrs.set("id", "second");
        HtmlWriter writer = new HtmlWriter();
        writer.openTag("div", attrs);
        writer.closeTag("div");
        assertEquals("<div id=\"second\"></div>", writer.result());
    }
}
