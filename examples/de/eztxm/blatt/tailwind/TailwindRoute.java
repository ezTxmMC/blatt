package de.eztxm.blatt.tailwind;

import de.eztxm.blatt.core.Component;
import de.eztxm.blatt.core.Text;
import de.eztxm.blatt.element.Div;
import de.eztxm.blatt.element.Heading;
import de.eztxm.blatt.element.Main;
import de.eztxm.blatt.element.Paragraph;
import de.eztxm.blatt.routing.Page;
import de.eztxm.blatt.routing.Route;

/** Plain Tailwind utility classes. Tailwind is enabled for the whole app in {@link TailwindExampleApp}. */
@Route("/")
public final class TailwindRoute implements Page {

    @Override
    public Component root() {
        return new Main(
            new Div(
                new Heading(1, new Text("Tailwind v4 on Blatt"))
                    .cssClass("text-3xl font-bold text-brand"),
                new Paragraph(new Text("Every class below is generated in the browser."))
                    .cssClass("mt-2 text-slate-600"),
                new Div(
                    new Paragraph(new Text("Card with a brand border"))
                        .cssClass("font-medium"),
                    new Paragraph(new Text("Hover me"))
                        .cssClass("mt-3 inline-block rounded-lg bg-brand px-4 py-2 text-white transition hover:opacity-80"))
                    .cssClass("mt-6 rounded-xl border-2 border-brand bg-white p-6 shadow-lg"))
                .cssClass("mx-auto max-w-xl p-10"))
            .cssClass("min-h-screen bg-slate-100");
    }
}
