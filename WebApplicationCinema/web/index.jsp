<%-- 
    Document   : index
    Created on : 22/02/2019, 05:19:38 PM
    Author     : sdist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CineMAS</title>
    </head>
    <body>
        <h1>¡Bienvenido a CineMAS!</h1>
        <p>Permietnos recomendarte una Pelicula.</p>
        <form action="GetSuggestion">
            Zona:
            <select name="zona">
                <option>Centro</option>
                <option>Sur</option>
            </select>
            <br>
            Tipo de pelicula:
            <select name="tipo">
                <option>Comedia</option>
                <option>Mello</option>
            </select>
            <br>
            <input type="reset" value="Limpiar" />
            <input type="submit" value="Ver recomendación" />
        </form>
        
    </body>
</html>
