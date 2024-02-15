package org.example;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        initializeApplication();
        initializeRoutes();
    }

    private static void initializeApplication() {
        port(8080);
        staticFiles.location("/public");
    }

    private static void initializeRoutes() {
        get("/hello", (req, res) -> "Hello world");
        get("/", (req, res) -> {
            res.redirect("index.html");
            return null;
        });
    }
}
