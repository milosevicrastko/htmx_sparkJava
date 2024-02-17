package org.example;

public class DynamicPage {

    static String getDynamicPage(int counter) {
        return """
                                
                <!DOCTYPE html>
                <html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
                <head>
                    <meta charset="UTF-8">
                    <title>Title</title>
                    <script src="https://unpkg.com/htmx.org@1.9.10"></script>
                </head>
                <body>
                <label></label>

                Count:""" + counter + """
                <button hx-target = "body" hx-get = "/increment-counter">count</button>
                </body>
                </html>
                """;
    }
}
