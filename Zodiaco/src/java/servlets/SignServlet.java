/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.GregorianCalendar; 


/**
 *
 * @author sdist
 */
public class SignServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Zodiaco :3 </h1>");
            
            try {
                int d,m,y;
                d = Integer.parseInt(request.getParameter("day"));
                m = Integer.parseInt(request.getParameter("month"));
                y = Integer.parseInt(request.getParameter("year"));
                String sign = "";

                GregorianCalendar cal = new GregorianCalendar(y, m-1, d); 
                if (cal.after(new GregorianCalendar(y, 9, 23)) && cal.before(new GregorianCalendar(y, 11, 23))){
                    out.println("<p>Escorpión</p>");
                    sign="Escorpion";
                }
                else{
                    if (cal.after(new GregorianCalendar(y, 0, 21)) && cal.before(new GregorianCalendar(y, 1, 19))){
                        out.println("<p>Acuario</p>");
                        sign="Acuario";
                    }
                }
                out.println("<form action='CompatibleServlet'>");
                out.println("<input type='hidden' name='sign' value='"+ sign +"'>");
                out.println("<input type='submit' value='Ver signos compatibles'>");
                out.println("</form>");

            } catch (Exception e) {
                System.out.println("Error: " + e);
                System.out.println("Redirigiendo al home...");
                response.sendRedirect("index.jsp?error=1"); 
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
