package de.eztxm.blatt.routing;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.css.StyleSheet;

public interface Page {

    Component root();

    default StyleSheet styles() {
        return new StyleSheet();
    }
}
