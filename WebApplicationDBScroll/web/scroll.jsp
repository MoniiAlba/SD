<%-- 
    Document   : scroll
    Created on : 6/03/2019, 04:30:12 PM
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
        <h1>Scrolling database records</h1>
        <%
            HttpSession mySession = request.getSession();
            
            int indice =  Integer.parseInt(mySession.getAttribute("index").toString());
            if(request.getParameter("aumenta")!=null){
                indice+= Integer.parseInt(request.getParameter("aumenta"));
                mySession.setAttribute("index", indice);
            }
            Object[][] tablita;
            if(mySession.getAttribute("tabla")!=null){
                tablita = (Object[][]) mySession.getAttribute("tabla");
                out.println("ID: " + tablita[indice][0]);
                out.println(" NAME: " + tablita[indice][1]);
            }else{
                response.sendRedirect("index.jsp");
            }
        %>
        
        <form action="scroll.jsp">
            <input type="hidden" name="aumenta" value="-1"/>
            <%
                if(indice==0){
                    out.println("<input type='submit' value='<' disabled='disabled' />");
                }else{
                    out.println("<input type='submit' value='<' />");
                }
            %>
            
        </form>
        <form action="scroll.jsp">
            <input type="hidden" name="aumenta" value="1"/>
            <%
                tablita = (Object[][]) mySession.getAttribute("tabla");
                if(indice>=tablita.length - 1){
                    out.println("<input type='submit' value='>' disabled='disabled' />");
                    
                }else{
                    out.println("<input type='submit' value='>'/>");
                }
            %>
        </form>
    </body>
</html>
