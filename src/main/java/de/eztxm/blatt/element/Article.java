package de.eztxm.blatt.element;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Tag;

public final class Article extends Tag<Article> {

    public Article(Component... children) {
        super("article", children);
    }
}
