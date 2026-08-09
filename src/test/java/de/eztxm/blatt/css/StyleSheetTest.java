package de.eztxm.blatt.css;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StyleSheetTest {

    @Test
    void style_renders_inline_declarations() {
        assertEquals(
            "color:red;font-size:16px;",
            new Style().color("red").fontSize(Css.px(16)).inline()
        );
    }

    @Test
    void style_replaces_repeated_property() {
        assertEquals("color:blue;", new Style().color("red").color("blue").inline());
    }

    @Test
    void style_joins_shorthand_values() {
        assertEquals("padding:1rem 2rem;", new Style().padding(Css.rem(1), Css.rem(2)).inline());
    }

    @Test
    void rule_renders_selector_block() {
        assertEquals(
            ".card{display:flex;}",
            new StyleSheet().rule(".card", new Style().display("flex")).render()
        );
    }

    @Test
    void nested_rule_replaces_ampersand_with_parent() {
        StyleSheet sheet = new StyleSheet()
            .rule(new Rule(".card", new Style().color("black"))
                .on(":hover", new Style().color("red")));

        assertEquals(".card{color:black;}.card:hover{color:red;}", sheet.render());
    }

    @Test
    void nested_rule_without_ampersand_is_descendant() {
        StyleSheet sheet = new StyleSheet()
            .rule(new Rule(".card")
                .nested("p", new Style().margin("0")));

        assertEquals(".card p{margin:0;}", sheet.render());
    }

    @Test
    void media_rule_wraps_nested_rules() {
        StyleSheet sheet = new StyleSheet()
            .media(MediaRule.maxWidth(Css.px(480))
                .rule(".card", new Style().width(Css.percent(100))));

        assertEquals("@media (max-width: 480px){.card{width:100%;}}", sheet.render());
    }

    @Test
    void empty_media_rule_renders_nothing() {
        assertEquals("", new StyleSheet().media(MediaRule.print()).render());
    }

    @Test
    void keyframes_render_stops() {
        StyleSheet sheet = new StyleSheet()
            .keyframes(new Keyframes("fade")
                .from(new Style().opacity(0))
                .to(new Style().opacity(1)));

        assertEquals("@keyframes fade{from{opacity:0;}to{opacity:1;}}", sheet.render());
    }

    @Test
    void variables_render_on_root() {
        assertEquals(
            ":root{--accent:#4f46e5;}",
            new StyleSheet().variables(new Style().variable("accent", Css.hex("4f46e5"))).render()
        );
    }

    @Test
    void css_helpers_format_values() {
        assertEquals("1.5rem", Css.rem(1.5));
        assertEquals("100%", Css.percent(100));
        assertEquals("var(--accent)", Css.var("accent"));
        assertEquals("var(--accent, red)", Css.var("--accent", "red"));
        assertEquals("calc(100% - 2rem)", Css.calc("100% - 2rem"));
        assertEquals("rgb(0 0 0 / 0.5)", Css.rgba(0, 0, 0, 0.5));
        assertEquals("repeat(3, 1fr)", Css.repeat(3, Css.fr(1)));
    }

    @Test
    void value_escaping_prevents_style_block_break() {
        assertEquals(
            "content:\\3c /style\\3e ;",
            new Style().content("</style>").inline()
        );
    }

    @Test
    void property_escaping_drops_unsafe_characters() {
        assertEquals("color:red;", new Style().set("co}lor:", "red").inline());
    }

    @Test
    void with_merges_other_style() {
        Style merged = new Style().color("red").with(new Style().color("blue").display("flex"));
        assertEquals("color:blue;display:flex;", merged.inline());
    }

    @Test
    void scoped_sheet_qualifies_every_selector() {
        StyleSheet sheet = new StyleSheet()
            .rule(new Rule("&", new Style().display("flex")))
            .rule(".title", new Style().color("red"))
            .media(MediaRule.print().rule(".title", new Style().color("black")))
            .scoped("[data-card]");

        assertEquals(
            "[data-card]{display:flex;}.title[data-card]{color:red;}"
                + "@media print{.title[data-card]{color:black;}}",
            sheet.render()
        );
    }

    @Test
    void scoped_sheet_leaves_keyframes_untouched() {
        StyleSheet sheet = new StyleSheet()
            .keyframes(new Keyframes("fade").from(new Style().opacity(0)))
            .scoped("[data-card]");

        assertEquals("@keyframes fade{from{opacity:0;}}", sheet.render());
    }

    @Test
    void empty_sheet_is_empty() {
        assertTrue(new StyleSheet().isEmpty());
        assertEquals("", new StyleSheet().render());
    }
}
