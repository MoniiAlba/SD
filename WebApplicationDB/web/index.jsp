<%-- 
    Document   : index
    Created on : 4/03/2019, 04:49:49 PM
    Author     : sdist
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/myFirstDatabase", "root", "root");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM BOMBONES");
            
            
        %>
        
        <h1> Staff </h1>
        
        <% 
            while(rs.next()){
                out.print("<p>ID: "+ rs.getInt("ID"));
                out.print(" Nombre: "+ rs.getString("NOMBRE"));
                out.println(" Precio: "+ rs.getString("PRECIO")+"</p>");
                
            }
            if(request.getParameter("type")!=null){
                boolean precio = false;
                if(request.getParameter("precio").equals("ON")){
                    precio = true;
                }
                if(request.getParameter("type").equals("add")){

                    int num = query.executeUpdate("INSERT INTO BOMBONES VALUES"
                            + " ("+request.getParameter("id")+", '"+request.getParameter("name")+"', "+ 
                            precio+")");
                }else{
                    if(request.getParameter("type").equals("delete")){
                        int num = query.executeUpdate("DELETE FROM BOMBONES WHERE ID="+request.getParameter("id"));
                    }else{
                        int num = query.executeUpdate("UPDATE BOMBONES SET NOMBRE='"
                            +request.getParameter("name")+"', PRECIO="+precio+" WHERE ID="+request.getParameter("id"));
                    }
                }
            }
                
        %>
        
        <form action="index.jsp" >
            <input type="hidden" name="type" value="add" />
            <h1> Add a record </h1>
            <table border="1">
                <tbody>
                    <tr>
                        <td>ID:</td>
                        <td><input type="text" name="id" value="" /></td>
                        <td>Name:</td>
                        <td><input type="text" name="name" value="" /></td>
                        <td>Precio</td>
                        <td><input type="checkbox" name="precio" value="ON" /></td>
                    </tr>
                </tbody>
            </table>
            <br>
            <input type="submit" name="add" value="Ok" />
        </form>
        
        <form action="index.jsp">
            <input type="hidden" name="type" value="delete" />
            <h1> Delete a record </h1>
            <table border="1">
                <tbody>
                    <tr>
                        <td>ID:</td>
                        <td><input type="text" name="id" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <br>
            <input type="submit" name="delete" value="Ok" />
        </form>
        
        <form action="index.jsp">
            <input type="hidden" name="type" value="update" />
            <h1> Update a record </h1>
            <table border="1">
                <tbody>
                    <tr>
                        <td>ID:</td>
                        <td><input type="text" name="id" value="" /></td>
                        <td>Name:</td>
                        <td><input type="text" name="name" value="" /></td>
                        <td>Precio</td>
                        <td><input type="checkbox" name="precio" value="ON" /></td>
                    </tr>
                </tbody>
            </table>
            <br>
            <input type="submit" name="update" value="Ok" />
        </form>
    </body>
</html>
