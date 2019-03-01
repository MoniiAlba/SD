<%-- 
    Document   : welcome
    Created on : 22/02/2019, 04:56:33 PM
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
        <%
            if(request.getParameter("nom") != null){
                out.println("<h1> Bienvenido! </h1>");
                out.println("<p> Holi " + request.getParameter("nom") + " </p>");
                
            }
        %>
    </body>
</html>
