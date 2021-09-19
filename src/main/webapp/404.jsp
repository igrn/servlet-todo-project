<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String name = request.getAttribute("jakarta.servlet.error.request_uri").toString(); %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Todo App | Not Found</title>
</head>
  <body>
    <h1>HTTP Status 404 – Not Found</h1>
    <hr>
    <p><b>Type:</b> Status Report</p>
    <p><b>Message:</b> The requested resource "<%= name %>" is not available.</p>
    <hr>
  </body>
</html>
