package org.example;

public class DynamicPage {

    static String getDynamicPage(String htmxScript, int counter) {

        String htmxScriptWithTag =
                "<script src= \" " + htmxScript + " \"></script>";
        return """                             
                <!DOCTYPE html>
                <html>
                <head>""" + htmxScriptWithTag + """
                </head>"""
                + getPageBody(counter) +
                """ 
                        </html>""";
    }

    static String getPageBody(int counter) {
        return """
                <body>
                Count:""" + counter + """
                <button hx-target = "body" hx-get = "/increment-counter">count</button>
                </body>
                """;
    }
}
