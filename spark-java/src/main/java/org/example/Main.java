package org.example;

import spark.Request;

import java.util.HashSet;
import java.util.Set;

import static spark.Spark.*;

public class Main {
    static String htmxLink = "https://unpkg.com/htmx.org@1.9.10";

    static Set<Person> persons = new HashSet<>();

    public static void main(String[] args) {
        initializeApplication();
        initializeRoutes();
    }


    private static void initializeApplication() {
        port(8080);
        staticFiles.location("/public");
    }

    private static void initializeRoutes() {
        get("/", (req, res) -> DynamicPage.getDynamicPage(htmxLink));
        post("/add-person", (req, res) -> addPersonToHtml(req));
    }

    static String addPersonToHtml(Request req) {
        try {
            Person person = new Person(getFirstNameFromRequest(req.body()), getLastNameFromRequest(req.body()));
            checkIfPersonAlreadyExists(person);
            checkIfEntryIsNotEmpty(person);
            persons.add(person);
            return DynamicPage.replacePersonsWithHtml(persons);
        } catch ( ValidationException e) {
            return DynamicPage.personAlreadyExistsValidation(persons, e.getMessage());
        }
    }

    static void checkIfPersonAlreadyExists(Person person) throws ValidationException {
        if (persons.contains(person)) {
            throw new ValidationException("Person already exists");
        }
    }

    static void checkIfEntryIsNotEmpty(Person person) throws ValidationException {
        String firstName = person.firstName();
        String lastName = person.lastName();

        if (firstName.isBlank() || lastName.isBlank()) {
            throw new ValidationException("First name and last name are mandatory");
        }
    }
    private static String getFirstNameFromRequest(String body) {
        int firstIndex = body.indexOf("firstName=") + "firstName=".length();
        int secondIndex = body.indexOf("&lastName=");
        return body.substring(firstIndex, secondIndex);

    }

    private static String getLastNameFromRequest(String body) {
        int beginIndex = body.indexOf("&lastName=") + "&lastName=".length();
        return body.substring(beginIndex);
    }
}
