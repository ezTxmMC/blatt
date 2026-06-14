package de.eztxm.blatt.routing;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

public final class RouteScanner {

    private final String basePackage;

    public RouteScanner(String basePackage) {
        this.basePackage = basePackage;
    }

    public void scan(Router router) {
        try (ScanResult result = new ClassGraph()
                .enableClassInfo()
                .enableAnnotationInfo()
                .acceptPackages(basePackage)
                .scan()) {

            for (ClassInfo classInfo : result.getClassesWithAnnotation(Route.class.getName())) {
                register(classInfo, router);
            }
        }
    }

    private void register(ClassInfo classInfo, Router router) {
        Class<?> clazz = classInfo.loadClass();

        if (!Page.class.isAssignableFrom(clazz)) {
            throw new IllegalStateException(
                clazz.getName() + " is annotated with @Route but does not implement Page"
            );
        }

        Route annotation = clazz.getAnnotation(Route.class);
        String path = annotation.value();

        try {
            Page page = (Page) clazz.getDeclaredConstructor().newInstance();
            router.register(path, page);
        } catch (Exception e) {
            throw new IllegalStateException(
                "Failed to instantiate " + clazz.getName() + " — requires a no-arg constructor", e
            );
        }
    }
}
