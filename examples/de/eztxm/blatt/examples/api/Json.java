package de.eztxm.blatt.examples.api;

import java.util.List;

/** Tiny JSON writer for the example, so the example needs no JSON library. */
final class Json {

    private Json() {}

    static String item(Item item) {
        return "{\"id\":" + item.id()
            + ",\"name\":" + string(item.name())
            + ",\"done\":" + item.done() + "}";
    }

    static String items(List<Item> items) {
        return items.stream().map(Json::item).collect(java.util.stream.Collectors.joining(",", "[", "]"));
    }

    static String error(String message) {
        return "{\"error\":" + string(message) + "}";
    }

    private static String string(String value) {
        StringBuilder out = new StringBuilder("\"");
        for (char c : value.toCharArray()) {
            switch (c) {
                case '"' -> out.append("\\\"");
                case '\\' -> out.append("\\\\");
                case '\n' -> out.append("\\n");
                case '\r' -> out.append("\\r");
                case '\t' -> out.append("\\t");
                default -> {
                    if (c < 0x20) {
                        out.append(String.format("\\u%04x", (int) c));
                    } else {
                        out.append(c);
                    }
                }
            }
        }
        return out.append('"').toString();
    }
}
