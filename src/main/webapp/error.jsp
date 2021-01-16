<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String exception = pageContext.getException().getClass().toString();
  String message = pageContext.getException().getMessage();
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Todo App | Exception</title>
  </head>
  <body>
    <h1>Exception</h1>
    <hr>
    <p><b>Type:</b> <%= exception %></p>
    <p><b>Message:</b> <%= message %></p>
    <p><b>Description:</b> An unhandled exception occurred while processing the request.</p>
    <hr>
  </body>
</html>
