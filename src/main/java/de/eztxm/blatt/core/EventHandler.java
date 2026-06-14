package de.eztxm.blatt.core;

import java.util.Optional;

@FunctionalInterface
public interface EventHandler {

    String toJs(Optional<String> event);
}
