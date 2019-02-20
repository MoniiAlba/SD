/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {

    public static void main (String args[]) {

	Socket s = null;
	    try {
	    	int serverPort = 7896;
	   	
                s = new Socket("localhost", serverPort);    
             //   s = new Socket("127.0.0.1", serverPort);    
		//DataInputStream in = new DataInputStream( s.getInputStream());
		//DataOutputStream out = new DataOutputStream( s.getOutputStream());
//		out.writeUTF("Hello");        	// UTF is a string encoding 
                
                ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                
                out.writeObject(new Person("Moni", 22, "AQUI"));
		Person data = (Person) in.readObject();	      
                System.out.println("Received: "+ data.getPlace()) ;  
                
                
       	    } 
            catch (UnknownHostException e) {
		System.out.println("Sock:"+e.getMessage()); 
	    }
            catch (EOFException e) {
                System.out.println("EOF:"+e.getMessage());
    	    } 
            catch (IOException e) {
                System.out.println("IO:"+e.getMessage());
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                if(s!=null) 
                    try {
                        s.close();
                    } catch (IOException e){
                    System.out.println("close:"+e.getMessage());}
                    }
            }
}
