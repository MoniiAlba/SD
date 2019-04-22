<%-- 
    Document   : index2
    Created on : 8/03/2019, 05:47:01 PM
    Author     : sdist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Response from Legacy Process</h1>
        <%
            String dato = request.getParameter("datoMejorado");
            out.println(dato);
        %>
    </body>
</html>
