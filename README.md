# htmx_sparkJava
Simple server with htmx frontend. Made for fun and to learn htmx and spark java. The idea is to make the lightest server possible while still using java

Requirements: 
Java 21

How to run: 
There is Main.java. Just compile it like any other java application. What, you expected Docker? Why? This is so light, JVM basically acts as a docker.

Since this is a project made for fun, let's set some "rules of challenge":

1. Make it as light as possible. Use as few external dependencies as possible. No json parsers. No thymeleaf. Not even html. Just spark, java, htmx and a lot of strings (ok, bootstrap was an exception)
2. The idea of htmx is to return pages (or part of them) as response. Make the response as light as possible. Page only needs several strings of change? Return only those several strings to browser
3. Speed over clean code! We already understand importance of well sturcutred, maintained code. This is not a clean code exercise. 

