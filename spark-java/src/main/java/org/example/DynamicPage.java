package org.example;

import java.util.List;

public class DynamicPage {

    static String getDynamicPage(String htmxScript, List<Person> persons) {

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
                <head>""" + htmxScriptWithTag + """
                </head>"""
                + getPersonForm(persons) +
                """ 
                        </html>""";
    }

    static String getPersonForm(List<Person> persons) {
        return """
                <body>
                    <form>
                        <input type = "text" name = "firstName" id = "firstName"></input>
                        <input type = "text" name = "lastName" id = "lastName"></input>
                    </form>
                    <div id = "all-persons">
                    """ + replacePersonsWithHtml(persons) +"""
                    </div>
                    <button hx-vals = 'js:{firstName: getFirstName(), lastName : getLastName()}' hx-post = "/add-person" hx-trigger = "click" hx-target = "#all-persons"> ADD </button>
                </body>
                """;

    }

    static String replacePersonsWithHtml(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return "<div>No persons present</div>";
         }
        StringBuilder sb = new StringBuilder();
        for(Person person: persons) {
            sb.append("<ul>");
            sb.append(person.getFirstName()+ " ");
            sb.append(person.getLastName());
            sb.append("</ul>");
        }
        return sb.toString();
    }
}
