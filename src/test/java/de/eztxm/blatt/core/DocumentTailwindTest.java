package de.eztxm.blatt.core;

import de.eztxm.blatt.css.Tailwind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DocumentTailwindTest {

    @Test
    void tailwind_is_not_loaded_unless_enabled() {
        String html = new Document(new Text("x"), new HeadConfig()).render();

        assertFalse(html.contains("text/tailwindcss"));
        assertFalse(html.contains(Tailwind.BROWSER_SCRIPT));
    }

    @Test
    void enabling_tailwind_writes_directive_theme_and_browser_script() {
        HeadConfig head = new HeadConfig().tailwind("@theme { --color-brand: #4338ca; }");
        String html = new Document(new Text("x"), head).render();

        assertTrue(html.contains("<style type=\"text/tailwindcss\">" + Tailwind.IMPORT
            + "@theme { --color-brand: #4338ca; }</style>"));
        assertTrue(html.contains("<script src=\"" + Tailwind.BROWSER_SCRIPT + "\" defer></script>"));
    }
}
