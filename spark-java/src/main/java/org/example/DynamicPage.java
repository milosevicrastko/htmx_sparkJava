package org.example;

import java.util.Set;

public class DynamicPage {

    private DynamicPage() {
    }

    static String getDynamicPage(String htmxScript, Set<Person> persons) {

        String htmxScriptWithTag =
                "<script src= \" " + htmxScript + " \"></script>" +
                        """
                                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
                                <script>
                                    function getFirstName() {
                                       return document.getElementById("firstName").value;
                                    }
                                    function getLastName() {
                                       return document.getElementById("lastName").value;
                                    }
                                </script>
                                """;
        return """                             
                <!DOCTYPE html>
                <html>
                <head>""" + htmxScriptWithTag + "</head>"
                + getPersonFormInitial(persons) +
                "</html>";
    }

    static String getPersonFormInitial(Set<Person> persons) {
        return """
                <body>
                    <div id = "main-div" >
                    <form>
                        <input type = "text" name = "firstName" id = "firstName"></input>
                        <input type = "text" name = "lastName" id = "lastName"></input>
                    </form>
                    <div id = "all-persons">
                    """ + replacePersonsWithHtml(persons) +"""
                    </div>
                    <button type = "button" class = "btn btn-primary" hx-vals = 'js:{firstName: getFirstName(), lastName : getLastName()}' hx-post = "/add-person" hx-trigger = "click" hx-target = "#all-persons"> ADD </button>
                    </div>
                </body>
                """;

    }

    static String personAlreadyExistsValidation(Set<Person> persons, String message) {
        String validation = "<div style = \"color: red\">"+message+"</div>";
        return validation + replacePersonsWithHtml(persons);
    }

    static String replacePersonsWithHtml(Set<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return "<div>No persons present</div>";
         }
        StringBuilder sb = new StringBuilder();
        sb.append("<ul class = \"list-group\">");
        for(Person person: persons) {
            sb.append("<li class = \"list-group-item\">");
            sb.append(person.firstName()).append(" ");
            sb.append(person.lastName()).append(" ");
            sb.append("<button type = \"button\" class = \"btn btn-danger\" hx-vals = '{\"firstName\":\"").append(person.firstName()).append("\", \"lastName\" :\"").append(person.lastName()).append("\"}' hx-delete = \"/delete-person\" hx-target = \"#all-persons\">Delete</button>");
            sb.append("<button type = \"button\" class = \"btn btn-info\" hx-vals = '{\"firstName\":\"").append(person.firstName()).append("\", \"lastName\" :\"").append(person.lastName()).append("\"}' hx-post = \"/person-more\" hx-target = \"#main-div\">Details</button>");
            sb.append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }

    static String getPageFromPerson(Person person) {
        return """
                <div id = "main-div">
                    <input type = "text" class = "form-control" id = "first-name" value = " """ + person.firstName() + """ 
                 "
                 disabled>
                      </input>
                   <input type = "text" class = "form-control" id = "first-name" value =" """ + person.lastName()+ """ 
                "
                disabled>
                </input>
                <button type = "button" class = "btn btn-primary" hx-get = "/" hx-trigger = "click" hx-target = "#main-div"> Back </button>
                </div>
                """;
    }
}
