package de.eztxm.blatt.core;

public final class Text implements Component {

    private final String value;

    public Text(String value) {
        this.value = value;
    }

    @Override
    public void render(HtmlWriter writer) {
        writer.text(value);
    }
}
