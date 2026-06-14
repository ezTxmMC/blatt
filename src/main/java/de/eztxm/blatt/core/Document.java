package de.eztxm.blatt.core;

public final class Document {

    private final Component body;
    private final HeadConfig headConfig;

    public Document(Component body, HeadConfig headConfig) {
        this.body = body;
        this.headConfig = headConfig;
    }

    public Document(Component body) {
        this(body, new HeadConfig());
    }

    public String render() {
        HtmlWriter writer = new HtmlWriter();
        writer.raw("<!DOCTYPE html>");
        writer.raw("<html lang=\"" + HtmlEscape.attribute(headConfig.lang()) + "\">");
        writeHead(writer);
        writeBody(writer);
        writer.raw("</html>");
        return writer.result();
    }

    private void writeHead(HtmlWriter writer) {
        writer.raw("<head>");
        writer.raw("<meta charset=\"UTF-8\">");
        writer.raw("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

        if (!headConfig.title().isEmpty()) {
            writer.raw("<title>");
            writer.text(headConfig.title());
            writer.raw("</title>");
        }

        for (String href : headConfig.stylesheetHrefs()) {
            writer.raw("<link rel=\"stylesheet\" href=\"" + HtmlEscape.attribute(href) + "\">");
        }

        for (String src : headConfig.scriptSrcs()) {
            writer.raw("<script src=\"" + HtmlEscape.attribute(src) + "\" defer></script>");
        }

        writer.raw("</head>");
    }

    private void writeBody(HtmlWriter writer) {
        writer.raw("<body>");
        body.render(writer);
        writer.raw("</body>");
    }
}
