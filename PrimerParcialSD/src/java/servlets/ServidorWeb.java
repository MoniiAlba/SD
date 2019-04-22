/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sdist
 */
public class ServidorWeb extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletInicial</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            
        
            Socket s = null;
            try{
                int serverPort = 7896;

                s = new Socket("localhost", serverPort);    
             //   s = new Socket("127.0.0.1", serverPort);    
                DataInputStream in = new DataInputStream( s.getInputStream());
                DataOutputStream outData =
                        new DataOutputStream( s.getOutputStream());
                outData.writeUTF(request.getParameter("dato"));        	// UTF is a string encoding 
                
                //FUNCIONA
                
                DatagramSocket aSocket = null;
                serverPort = 6780;
                aSocket = new DatagramSocket(serverPort); 
		byte[] buffer = new byte[1000]; // buffer encapsulará mensajes
                boolean flag = true;
 		while(flag){
                    System.out.println("Waiting for messages UDP..."); 
                    DatagramPacket request1 = new DatagramPacket(buffer, buffer.length);
                    aSocket.receive(request1);     
                    System.out.println(new String(request1.getData()));
                    flag = false;
                    String next = "/index2.jsp";
                    out.println("<input type=\"hidden\" name='datoMejorado' value='"+new String(request1.getData())+"' />");
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(next);
                    dispatcher.forward(request, response);
                    out.println("<p>"+new String(request1.getData())+ "</p>");  
                    /*
                    DatagramPacket reply = new DatagramPacket(request.getData(), 
                         request.getLength(), request.getAddress(), request.getPort());
                    System.out.println("Server received a request from "+ request.getAddress());
                    aSocket.send(reply);
                    */
		}
                //String data = in.readUTF();	      
                //out.println("<p>"+data+ "</p>");   
            } 
            catch (UnknownHostException e) {
                System.out.println("Sock:"+e.getMessage()); 
            }
            catch (EOFException e) {
                System.out.println("EOF:"+e.getMessage());
            } 
            catch (IOException e) {
                System.out.println("IO:"+e.getMessage());
            } finally {
                if(s!=null) 
                    try {
                        s.close();
                    } catch (IOException e){
                        System.out.println("close:"+e.getMessage());
                    }
            }
            out.println("</body>");
            out.println("</html>");
        }
        
        
    }
    
    public void recibeUPD(HttpServletResponse response){
        DatagramSocket aSocket = null;
	   try (PrintWriter out = response.getWriter()){
	    	int serverPort = 6789;
                aSocket = new DatagramSocket(serverPort); 
		byte[] buffer = new byte[1000]; // buffer encapsulará mensajes
 		while(true){
                    System.out.println("Waiting for messages UDP..."); 
                    DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                    aSocket.receive(request);     

                    out.println("<p>"+new String(request.getData())+ "</p>");  
                    /*
                    DatagramPacket reply = new DatagramPacket(request.getData(), 
                         request.getLength(), request.getAddress(), request.getPort());
                    System.out.println("Server received a request from "+ request.getAddress());
                    aSocket.send(reply);
                    */
		}
	   }
           catch (SocketException e){
                System.out.println("Socket: " + e.getMessage());
	   }
           catch (IOException e) {
               System.out.println("IO: " + e.getMessage());
           }
           finally {
                if(aSocket != null) 
                    aSocket.close();
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
