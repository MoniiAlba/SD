<%-- 
    Document   : profile
    Created on : 1/03/2019, 04:38:00 PM
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
            HttpSession mySession = request.getSession();
            
            if(mySession.getAttribute("nombre")!=null){
                out.println("<p>" + mySession.getAttribute("nombre") + "</p>");
                out.println("<p>" + mySession.getAttribute("edad") + "</p>");
                out.println("<p>" + mySession.getAttribute("objeto") + "</p>");
                out.println("<a href='SignOut'> Log out </a>");
            }else{
                response.sendRedirect("index.html");
            }
        %>
    </body>
</html>
