package de.eztxm.blatt.core;

import java.util.ArrayList;
import java.util.List;

public final class HeadConfig {

    private String title;
    private String lang;
    private final List<String> scriptSrcs;
    private final List<String> stylesheetHrefs;

    public HeadConfig() {
        title = "";
        lang = "de";
        scriptSrcs = new ArrayList<>();
        stylesheetHrefs = new ArrayList<>();
    }

    public HeadConfig title(String title) {
        this.title = title;
        return this;
    }

    public HeadConfig lang(String lang) {
        this.lang = lang;
        return this;
    }

    public HeadConfig script(String src) {
        scriptSrcs.add(src);
        return this;
    }

    public HeadConfig stylesheet(String href) {
        stylesheetHrefs.add(href);
        return this;
    }

    public String title() {
        return title;
    }

    public String lang() {
        return lang;
    }

    public List<String> scriptSrcs() {
        return List.copyOf(scriptSrcs);
    }

    public List<String> stylesheetHrefs() {
        return List.copyOf(stylesheetHrefs);
    }
}
