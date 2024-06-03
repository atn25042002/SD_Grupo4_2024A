<%-- 
    Document   : errror
    Created on : 3 jun 2024, 03:20:51
    Author     : Lenovo
--%>

<%@page isErrorPage="true" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <style>
        body {
font-family: Arial, sans-serif;
}
        .error-container {
margin: 50px;
padding: 20px;
border: 1px solid #ff0000;
background-color: #fdd;
}
        .error-message {
color: #d00;
}
        </style>
    </head>
    <body>
        <div class="error-container">
            <h1>Se ha producido un error</h1>
            <p class="error-message"><%= exception.getMessage()%></p>
            <ul>
                <%
                    // Si hay mensajes adicionales, se muestran aquí
                    String[] mensajes = (String[]) request.getAttribute("mensajes");
                    if (mensajes != null) {
                        for (String mensaje : mensajes) {
                            out.println("<li>" + mensaje + "</li>");
                        }
                    }
                %>
            </ul>
            <pre><%= exception.toString()%></pre>
            <pre>
                <%= exception.printStackTrace(new java.io.PrintWriter(out))%>
            </pre>
        </div>
    </body>
</html>
