<%-- 
    Document   : index
    Created on : 18/02/2019, 04:46:20 PM
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
        <h1>Por favor de su opinión de los servlets</h1>
        <form action="OtroServlet">
            Nombre: <input type="text" name="name" value="" />
            <br>
            Apellidos <input type="text" name="lastName" value="" />
            <br>
            Opinión que le merecen los Servlets
            <input type="radio" name="opinion" value="mala" /> mala
            <input type="radio" name="opinion" value="regular" /> regular
            <input type="radio" name="opinion" value="buena" /> buena
            <br>
            Comentarios
            <textarea name="comments" rows="4" cols="20">
            </textarea>
            <input type="reset" value="Limpiar" />
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
