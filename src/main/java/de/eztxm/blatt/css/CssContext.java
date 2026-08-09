package de.eztxm.blatt.css;

public final class CssContext {

    private final String parent;
    private final String scope;

    private CssContext(String parent, String scope) {
        this.parent = parent;
        this.scope = scope;
    }

    public static CssContext root() {
        return new CssContext("", "");
    }

    public CssContext nested(String parent) {
        return new CssContext(parent, scope);
    }

    public CssContext scoped(String scope) {
        return new CssContext(parent, scope);
    }

    public String parent() {
        return parent;
    }

    public String selector(String resolved) {
        return ScopedSelector.apply(resolved, scope);
    }
}
