package de.eztxm.blatt.css;

import java.util.ArrayList;
import java.util.List;

public final class Style {

    private final List<Declaration> declarations;

    public Style() {
        declarations = new ArrayList<>();
    }

    public Style set(String property, String value) {
        declarations.removeIf(declaration -> declaration.hasProperty(property));
        declarations.add(new Declaration(property, value));
        return this;
    }

    public Style variable(String name, String value) {
        return set(name.startsWith("--") ? name : "--" + name, value);
    }

    public Style with(Style other) {
        for (Declaration declaration : other.declarations) {
            set(declaration.property(), declaration.value());
        }
        return this;
    }

    public boolean isEmpty() {
        return declarations.isEmpty();
    }

    public void writeTo(CssWriter writer) {
        for (Declaration declaration : declarations) {
            declaration.writeTo(writer);
        }
    }

    public String inline() {
        CssWriter writer = new CssWriter();
        writeTo(writer);
        return writer.result();
    }

    public Style display(String value) {
        return set("display", value);
    }

    public Style position(String value) {
        return set("position", value);
    }

    public Style top(String value) {
        return set("top", value);
    }

    public Style right(String value) {
        return set("right", value);
    }

    public Style bottom(String value) {
        return set("bottom", value);
    }

    public Style left(String value) {
        return set("left", value);
    }

    public Style inset(String... values) {
        return set("inset", String.join(" ", values));
    }

    public Style zIndex(int value) {
        return set("z-index", String.valueOf(value));
    }

    public Style overflow(String value) {
        return set("overflow", value);
    }

    public Style overflowX(String value) {
        return set("overflow-x", value);
    }

    public Style overflowY(String value) {
        return set("overflow-y", value);
    }

    public Style visibility(String value) {
        return set("visibility", value);
    }

    public Style flexDirection(String value) {
        return set("flex-direction", value);
    }

    public Style flexWrap(String value) {
        return set("flex-wrap", value);
    }

    public Style justifyContent(String value) {
        return set("justify-content", value);
    }

    public Style alignItems(String value) {
        return set("align-items", value);
    }

    public Style alignContent(String value) {
        return set("align-content", value);
    }

    public Style alignSelf(String value) {
        return set("align-self", value);
    }

    public Style flex(String value) {
        return set("flex", value);
    }

    public Style flexGrow(double value) {
        return set("flex-grow", Css.number(value));
    }

    public Style flexShrink(double value) {
        return set("flex-shrink", Css.number(value));
    }

    public Style flexBasis(String value) {
        return set("flex-basis", value);
    }

    public Style order(int value) {
        return set("order", String.valueOf(value));
    }

    public Style gap(String... values) {
        return set("gap", String.join(" ", values));
    }

    public Style rowGap(String value) {
        return set("row-gap", value);
    }

    public Style columnGap(String value) {
        return set("column-gap", value);
    }

    public Style gridTemplateColumns(String... values) {
        return set("grid-template-columns", String.join(" ", values));
    }

    public Style gridTemplateRows(String... values) {
        return set("grid-template-rows", String.join(" ", values));
    }

    public Style gridTemplateAreas(String... values) {
        return set("grid-template-areas", String.join(" ", values));
    }

    public Style gridAutoFlow(String value) {
        return set("grid-auto-flow", value);
    }

    public Style gridAutoColumns(String value) {
        return set("grid-auto-columns", value);
    }

    public Style gridAutoRows(String value) {
        return set("grid-auto-rows", value);
    }

    public Style gridColumn(String value) {
        return set("grid-column", value);
    }

    public Style gridRow(String value) {
        return set("grid-row", value);
    }

    public Style gridArea(String value) {
        return set("grid-area", value);
    }

    public Style placeItems(String... values) {
        return set("place-items", String.join(" ", values));
    }

    public Style placeContent(String... values) {
        return set("place-content", String.join(" ", values));
    }

    public Style width(String value) {
        return set("width", value);
    }

    public Style height(String value) {
        return set("height", value);
    }

    public Style minWidth(String value) {
        return set("min-width", value);
    }

    public Style maxWidth(String value) {
        return set("max-width", value);
    }

    public Style minHeight(String value) {
        return set("min-height", value);
    }

    public Style maxHeight(String value) {
        return set("max-height", value);
    }

    public Style boxSizing(String value) {
        return set("box-sizing", value);
    }

    public Style margin(String... values) {
        return set("margin", String.join(" ", values));
    }

    public Style marginTop(String value) {
        return set("margin-top", value);
    }

    public Style marginRight(String value) {
        return set("margin-right", value);
    }

    public Style marginBottom(String value) {
        return set("margin-bottom", value);
    }

    public Style marginLeft(String value) {
        return set("margin-left", value);
    }

    public Style padding(String... values) {
        return set("padding", String.join(" ", values));
    }

    public Style paddingTop(String value) {
        return set("padding-top", value);
    }

    public Style paddingRight(String value) {
        return set("padding-right", value);
    }

    public Style paddingBottom(String value) {
        return set("padding-bottom", value);
    }

    public Style paddingLeft(String value) {
        return set("padding-left", value);
    }

    public Style border(String... values) {
        return set("border", String.join(" ", values));
    }

    public Style borderTop(String... values) {
        return set("border-top", String.join(" ", values));
    }

    public Style borderRight(String... values) {
        return set("border-right", String.join(" ", values));
    }

    public Style borderBottom(String... values) {
        return set("border-bottom", String.join(" ", values));
    }

    public Style borderLeft(String... values) {
        return set("border-left", String.join(" ", values));
    }

    public Style borderWidth(String... values) {
        return set("border-width", String.join(" ", values));
    }

    public Style borderStyle(String value) {
        return set("border-style", value);
    }

    public Style borderColor(String value) {
        return set("border-color", value);
    }

    public Style borderRadius(String... values) {
        return set("border-radius", String.join(" ", values));
    }

    public Style outline(String... values) {
        return set("outline", String.join(" ", values));
    }

    public Style outlineOffset(String value) {
        return set("outline-offset", value);
    }

    public Style color(String value) {
        return set("color", value);
    }

    public Style font(String... values) {
        return set("font", String.join(" ", values));
    }

    public Style fontFamily(String... values) {
        return set("font-family", String.join(", ", values));
    }

    public Style fontSize(String value) {
        return set("font-size", value);
    }

    public Style fontWeight(String value) {
        return set("font-weight", value);
    }

    public Style fontWeight(int value) {
        return set("font-weight", String.valueOf(value));
    }

    public Style fontStyle(String value) {
        return set("font-style", value);
    }

    public Style lineHeight(String value) {
        return set("line-height", value);
    }

    public Style lineHeight(double value) {
        return set("line-height", Css.number(value));
    }

    public Style letterSpacing(String value) {
        return set("letter-spacing", value);
    }

    public Style wordSpacing(String value) {
        return set("word-spacing", value);
    }

    public Style textAlign(String value) {
        return set("text-align", value);
    }

    public Style textDecoration(String... values) {
        return set("text-decoration", String.join(" ", values));
    }

    public Style textTransform(String value) {
        return set("text-transform", value);
    }

    public Style textShadow(String... values) {
        return set("text-shadow", String.join(", ", values));
    }

    public Style textOverflow(String value) {
        return set("text-overflow", value);
    }

    public Style whiteSpace(String value) {
        return set("white-space", value);
    }

    public Style wordBreak(String value) {
        return set("word-break", value);
    }

    public Style listStyle(String... values) {
        return set("list-style", String.join(" ", values));
    }

    public Style background(String... values) {
        return set("background", String.join(" ", values));
    }

    public Style backgroundColor(String value) {
        return set("background-color", value);
    }

    public Style backgroundImage(String value) {
        return set("background-image", value);
    }

    public Style backgroundSize(String... values) {
        return set("background-size", String.join(" ", values));
    }

    public Style backgroundPosition(String... values) {
        return set("background-position", String.join(" ", values));
    }

    public Style backgroundRepeat(String value) {
        return set("background-repeat", value);
    }

    public Style backgroundClip(String value) {
        return set("background-clip", value);
    }

    public Style boxShadow(String... values) {
        return set("box-shadow", String.join(", ", values));
    }

    public Style opacity(double value) {
        return set("opacity", Css.number(value));
    }

    public Style filter(String... values) {
        return set("filter", String.join(" ", values));
    }

    public Style backdropFilter(String... values) {
        return set("backdrop-filter", String.join(" ", values));
    }

    public Style transform(String... values) {
        return set("transform", String.join(" ", values));
    }

    public Style transformOrigin(String... values) {
        return set("transform-origin", String.join(" ", values));
    }

    public Style transition(String... values) {
        return set("transition", String.join(", ", values));
    }

    public Style animation(String... values) {
        return set("animation", String.join(" ", values));
    }

    public Style cursor(String value) {
        return set("cursor", value);
    }

    public Style pointerEvents(String value) {
        return set("pointer-events", value);
    }

    public Style userSelect(String value) {
        return set("user-select", value);
    }

    public Style content(String value) {
        return set("content", value);
    }

    public Style appearance(String value) {
        return set("appearance", value);
    }

    public Style objectFit(String value) {
        return set("object-fit", value);
    }

    public Style aspectRatio(String value) {
        return set("aspect-ratio", value);
    }

    public Style colorScheme(String value) {
        return set("color-scheme", value);
    }
}
