<%-- 
    Document   : index
    Created on : 29/03/2019, 05:50:10 PM
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
        <h1>Hello World!</h1>
    <%-- start web service invocation --%><hr/>
    <%
    try {
	webservices.MyWebService_Service service = new webservices.MyWebService_Service();
	webservices.MyWebService port = service.getMyWebServicePort();
	 // TODO initialize WS operation arguments here
	int a = 5;
	int b = 2;
	// TODO process result here
	int result = port.add(a, b);
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>
</html>
