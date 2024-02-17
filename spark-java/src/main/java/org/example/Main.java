package org.example;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        initializeApplication();
        initializeRoutes();
    }

     static int counter = 0;

    private static void incrementCounter() {
        counter++;
        System.out.println(counter);
    }

    private static void initializeApplication() {
        port(8080);
        staticFiles.location("/public");
    }

    private static void initializeRoutes() {
        get("/increment-counter", (req, res) -> {incrementCounter(); return DynamicPage.getDynamicPage(counter);});
        get("/", (req, res) -> DynamicPage.getDynamicPage(counter));
    }
}
