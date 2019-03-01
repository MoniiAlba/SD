<%-- 
    Document   : welcome
    Created on : 1/03/2019, 04:38:08 PM
    Author     : sdist
--%>

<%@page import="servlets.MyObjectSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            if(request.getParameter("password")!=null){
                if(request.getParameter("password").equals("123456")){
                    //creamos sesion :D
                    HttpSession mySession = request.getSession();
                    
                    mySession.setAttribute("nombre", request.getParameter("nombre")+"-Session");
                    mySession.setAttribute("edad", request.getParameter("edad"));
                    servlets.MyObjectSession miSesion = new MyObjectSession(request.getParameter("nombre"), 
                            Integer.valueOf(request.getParameter("edad")));
                    
                    mySession.setAttribute("objeto", miSesion);
                    mySession.setMaxInactiveInterval(20);
                    
                    out.println("<p> Bienvenido " + request.getParameter("nombre") + "</p>");
                    out.println("<a href='profile.jsp'> Perfil </a>");
                        
                    
                    
                }else{
                    response.sendRedirect("index.html");
                }
            }else{
                response.sendRedirect("index.html");
            }
        %>
    </body>
</html>
