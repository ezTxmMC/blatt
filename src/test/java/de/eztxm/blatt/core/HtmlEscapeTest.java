package de.eztxm.blatt.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HtmlEscapeTest {

    @Test
    void content_escapes_ampersand() {
        assertEquals("a&amp;b", HtmlEscape.content("a&b"));
    }

    @Test
    void content_escapes_angle_brackets() {
        assertEquals("&lt;script&gt;", HtmlEscape.content("<script>"));
    }

    @Test
    void content_does_not_escape_double_quotes() {
        assertEquals("say \"hi\"", HtmlEscape.content("say \"hi\""));
    }

    @Test
    void attribute_escapes_double_quotes() {
        assertEquals("say &quot;hi&quot;", HtmlEscape.attribute("say \"hi\""));
    }

    @Test
    void attribute_escapes_all_special_chars() {
        assertEquals("&lt;b&gt;&amp;&quot;", HtmlEscape.attribute("<b>&\""));
    }

    @Test
    void content_leaves_plain_text_unchanged() {
        assertEquals("hello world", HtmlEscape.content("hello world"));
    }
}
