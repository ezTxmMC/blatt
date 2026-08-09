package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class BlockQuote extends Tag<BlockQuote> {

    public BlockQuote(Component... children) {
        super("blockquote", children);
    }

    public BlockQuote cite(String cite) {
        attribute("cite", cite);
        return this;
    }
}
