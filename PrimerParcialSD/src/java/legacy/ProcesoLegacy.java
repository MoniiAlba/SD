/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author sdist
 */
public class ProcesoLegacy {
    public static void main(String[] args) {
        /* =========== TCP ================*/
        
        try{
            int serverPort = 7896; 
            ServerSocket listenSocket = new ServerSocket(serverPort);
            boolean flag = true;
            while(flag) {
                    System.out.println("Waiting for messages..."); 
                    Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
                    Connection c = new Connection(clientSocket);
                    c.start();
                    flag = false;
                    
            }
            System.out.println("ENVIO MENSAJE");
            enviaMensajeUDP("Me llamo Moni");
	} catch(IOException e) {
            System.out.println("Listen :"+ e.getMessage());
        
        }
    }
    
    public static void enviaMensajeUDP(String msg){
        DatagramSocket aSocket = null;
	  try {
		aSocket = new DatagramSocket();    
		String myMessage = msg;
                byte [] m = myMessage.getBytes();
		
//                InetAddress aHost = InetAddress.getByName("localhost");148.205.199.97
                InetAddress aHost = InetAddress.getByAddress("localhost", new byte[] {(byte)127, (byte)0, (byte)0 ,(byte) 1});
		int serverPort = 6780;
                System.out.println("ENVIO MONI");
                DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
                aSocket.send(request);	
                /*
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
                aSocket.receive(reply); 
                System.out.println("Reply: " + new String(reply.getData()));	
                */

	  }
          catch (SocketException e){
                System.out.println("Socket: " + e.getMessage());
	  }
          catch (IOException e){
              System.out.println("IO: " + e.getMessage());
          }
	  finally{
            if(aSocket != null) 
                aSocket.close();
         }
    }
    
    
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out =new DataOutputStream(clientSocket.getOutputStream());
         } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
    }

    @Override
    public void run(){
        try {			                 // an echo server
            String data = in.readUTF();	     
            System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
            System.out.println("Message: " + data);
            data = "Me llamo Moni";
            clientSocket.close();
            //out.writeUTF(data);
        } 
        catch(EOFException e) {
            System.out.println("EOF:"+e.getMessage());
        } 
        catch(IOException e) {
            System.out.println("IO:"+e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e){
                System.out.println(e);
            }
        }
    }
    
}