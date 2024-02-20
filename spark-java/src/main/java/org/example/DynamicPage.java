package org.example;

import java.util.Set;

public class DynamicPage {

    private DynamicPage() {
    }

    static String getDynamicPage(String htmxScript) {

        String htmxScriptWithTag =
                "<script src= \" " + htmxScript + " \"></script>" +
                        """
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
                + getPersonFormInitial() +
                "</html>";
    }

    static String getPersonFormInitial() {
        return """
                <body>
                    <form>
                        <input type = "text" name = "firstName" id = "firstName"></input>
                        <input type = "text" name = "lastName" id = "lastName"></input>
                    </form>
                    <div id = "all-persons">
                    """ + replacePersonsWithHtml(null) +"""
                    </div>
                    <button hx-vals = 'js:{firstName: getFirstName(), lastName : getLastName()}' hx-post = "/add-person" hx-trigger = "click" hx-target = "#all-persons"> ADD </button>
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
        for(Person person: persons) {
            sb.append("<ul>");
            sb.append(person.firstName()+ " ");
            sb.append(person.lastName());
            sb.append("<button hx-vals = '{\"firstName\":\""+ person.firstName() +"\", \"lastName\" :\"" + person.lastName()+ "\"}' hx-delete = \"/delete-person\" hx-target = \"#all-persons\">Delete</button>");
            sb.append("</ul>");
        }
        return sb.toString();
    }
}
