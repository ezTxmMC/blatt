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
        boolean page = Page.class.isAssignableFrom(clazz);
        boolean endpoint = Endpoint.class.isAssignableFrom(clazz);

        if (!page && !endpoint) {
            throw new IllegalStateException(
                clazz.getName() + " is annotated with @Route but implements neither Page nor Endpoint"
            );
        }

        Route annotation = clazz.getAnnotation(Route.class);
        String path = annotation.value();
        HttpMethod method = annotation.method();

        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            if (page) {
                router.register(method, path, (Page) instance);
            } else {
                router.register(method, path, (Endpoint) instance);
            }
        } catch (Exception e) {
            throw new IllegalStateException(
                "Failed to instantiate " + clazz.getName() + " — requires a no-arg constructor", e
            );
        }
    }
}
