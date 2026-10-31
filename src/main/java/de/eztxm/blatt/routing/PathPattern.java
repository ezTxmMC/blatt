package de.eztxm.blatt.routing;

/**
 * Matches a request path against a registered route path. Segments written as
 * {@code {name}} match exactly one non-empty segment; every other segment must be equal.
 */
final class PathPattern {

    private PathPattern() {}

    static boolean isParameterized(String pattern) {
        return pattern.indexOf('{') >= 0;
    }

    static boolean matches(String pattern, String requested) {
        if (!isParameterized(pattern)) {
            return pattern.equals(requested);
        }

        String[] expected = pattern.split("/", -1);
        String[] actual = requested.split("/", -1);
        if (expected.length != actual.length) {
            return false;
        }

        for (int i = 0; i < expected.length; i++) {
            String segment = expected[i];
            if (isParameterSegment(segment)) {
                if (actual[i].isEmpty()) {
                    return false;
                }
            } else if (!segment.equals(actual[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isParameterSegment(String segment) {
        return segment.length() > 2 && segment.charAt(0) == '{' && segment.charAt(segment.length() - 1) == '}';
    }
}
