/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;
/**
 *
 * @author JGUTIERRGARC
 */

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPServer {
    
    public static void main (String args[]) {
	try{
		int serverPort = 7896; 
		ServerSocket listenSocket = new ServerSocket(serverPort);
		while(true) {
			System.out.println("Waiting for messages..."); 
                        Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
			Connection c = new Connection(clientSocket);
                        c.start();
		}
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
   
}

class Connection extends Thread {
	ObjectOutputStream out;
        ObjectInputStream in;
	
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
	    try {
		clientSocket = aClientSocket;
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
        
        @Override
	public void run(){
	    try {			                 // an echo server
		Person me = (Person) in.readObject();
                System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress() +" "+ me.getName());
		out.writeObject(me);
                
	    } 
            catch(EOFException e) {
                System.out.println("EOF:"+e.getMessage());
	    } 
            catch(IOException e) {
                System.out.println("IO:"+e.getMessage());
	    } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e){
                    System.out.println(e);
                }
                }
            }
}


