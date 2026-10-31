package de.eztxm.blatt.examples.api;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/** In-memory storage for the item API. Data is lost when the server stops. */
final class ItemStore {

    private static final ConcurrentHashMap<Long, Item> ITEMS = new ConcurrentHashMap<>();
    private static final AtomicLong NEXT_ID = new AtomicLong(1);

    static {
        create("Read the Blatt README");
        create("Try a Tailwind page");
    }

    private ItemStore() {}

    static List<Item> all() {
        return ITEMS.values().stream().sorted((a, b) -> Long.compare(a.id(), b.id())).toList();
    }

    static Optional<Item> find(long id) {
        return Optional.ofNullable(ITEMS.get(id));
    }

    static Item create(String name) {
        long id = NEXT_ID.getAndIncrement();
        Item item = new Item(id, name, false);
        ITEMS.put(id, item);
        return item;
    }

    /** Full replacement (PUT). */
    static Optional<Item> replace(long id, String name, boolean done) {
        return ITEMS.computeIfPresent(id, (key, old) -> new Item(key, name, done)) == null
            ? Optional.empty()
            : Optional.of(ITEMS.get(id));
    }

    /** Partial update (PATCH): only the fields that are present change. */
    static Optional<Item> update(long id, Optional<String> name, Optional<Boolean> done) {
        return ITEMS.computeIfPresent(id, (key, old) -> new Item(
            key,
            name.orElse(old.name()),
            done.orElse(old.done())
        )) == null ? Optional.empty() : Optional.of(ITEMS.get(id));
    }

    static boolean delete(long id) {
        return ITEMS.remove(id) != null;
    }
}
