package de.eztxm.blatt.css;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScopedSelectorTest {

    private String apply(String selector) {
        return ScopedSelector.apply(selector, "[s]");
    }

    @Test
    void appends_scope_to_simple_selector() {
        assertEquals(".title[s]", apply(".title"));
        assertEquals("div[s]", apply("div"));
        assertEquals("*[s]", apply("*"));
    }

    @Test
    void appends_scope_only_to_last_compound() {
        assertEquals(".card .title[s]", apply(".card .title"));
        assertEquals(".card > .title[s]", apply(".card > .title"));
        assertEquals("li + li[s]", apply("li + li"));
        assertEquals("h2 ~ p[s]", apply("h2 ~ p"));
    }

    @Test
    void inserts_scope_before_pseudo_classes_and_elements() {
        assertEquals("a[s]:hover", apply("a:hover"));
        assertEquals("p[s]::before", apply("p::before"));
        assertEquals(".card .link[s]:focus-visible", apply(".card .link:focus-visible"));
    }

    @Test
    void handles_every_selector_of_a_list() {
        assertEquals("h1[s],h2[s],.a .b[s]", apply("h1, h2, .a .b"));
    }

    @Test
    void ignores_combinators_inside_parentheses_and_brackets() {
        assertEquals("li[s]:not(.a > .b)", apply("li:not(.a > .b)"));
        assertEquals("a[href=\"a b\"][s]", apply("a[href=\"a b\"]"));
        assertEquals("a[s]:is(.x, .y)", apply("a:is(.x, .y)"));
    }

    @Test
    void empty_selector_becomes_the_scope_itself() {
        assertEquals("[s]", apply(""));
        assertEquals("[s]", apply("  "));
    }

    @Test
    void without_scope_the_selector_is_unchanged() {
        assertEquals(".title", ScopedSelector.apply(".title", ""));
    }
}
