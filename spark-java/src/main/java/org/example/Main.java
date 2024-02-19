package org.example;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class Main {
    static String htmxLink = "https://unpkg.com/htmx.org@1.9.10";

    static List<Person> persons = new ArrayList<>();

    public static void main(String[] args) {
        initializeApplication();
        initializeRoutes();
    }


    private static void initializeApplication() {
        port(8080);
        staticFiles.location("/public");
    }

    private static void initializeRoutes() {
        get("/", (req, res) -> DynamicPage.getDynamicPage(htmxLink, persons));
        post("/add-person", (req, res) -> {
            addPersonToList(getFirstNameFromRequest(req.body()), getLastNameFromRequest(req.body()));
            return DynamicPage.replacePersonsWithHtml(persons);
        });
    }

    private static void addPersonToList(String firstName, String lastName) {
        Person newPerson = new Person(firstName, lastName);
        persons.add(newPerson);
    }


    private static String getFirstNameFromRequest(String body) {
        int firstIndex = body.indexOf("firstName=") + "firstName=".length();
        int secondIndex = body.indexOf("&lastName=") ;
        return body.substring(firstIndex, secondIndex);

    }

    private static String getLastNameFromRequest(String body) {
        int beginIndex = body.indexOf("&lastName=") + "&lastName=".length() ;
        return body.substring(beginIndex);

    }
}
