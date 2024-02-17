package org.example;

import static spark.Spark.*;

public class Main {
    static int counter = 0;

    static String htmxLink = "https://unpkg.com/htmx.org@1.9.10";

    public static void main(String[] args) {
        initializeApplication();
        initializeRoutes();
    }


    private static void initializeApplication() {
        port(8080);
        staticFiles.location("/public");
    }

    private static void initializeRoutes() {
        get("/increment-counter", (req, res) -> {
            counter++;
            return DynamicPage.getPageBody(counter);
        });
        get("/", (req, res) -> DynamicPage.getDynamicPage(htmxLink, counter));
    }
}
