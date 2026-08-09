package de.eztxm.blatt.core;

import java.util.List;

public interface Markup extends Component {

    Markup attr(String name, String value);

    List<Component> children();
}
