/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmsqueue;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author sdist
 */
public class Jugador {
    @Resource(mappedName = "jms/GlassFishTestConnectionFactory")  
    private static ConnectionFactory connectionFactory;  
    //@Resource(mappedName = "jms/PapsCaliente")  
    @Resource(mappedName = "jms/GlassFishTestQueue")  
    private static Queue queueOtro;
    @Resource(mappedName = "jms/PapsCaliente")  
    //@Resource(mappedName = "jms/GlassFishTestQueue")  
    private static Queue queueMia;  
    
    public void getPapa()  {    
        Connection connection;
        MessageConsumer messageConsumer;
        ObjectMessage papaMessage;
        boolean goodByeReceived = false;
        Papa aux;
        
        try    {      
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(queueMia);
            connection.start(); 
            while (!goodByeReceived)      {
                System.out.println("Waiting for messages...");
                papaMessage = (ObjectMessage) messageConsumer.receive();
                if (papaMessage != null)        {
                    System.out.print("Received the following message: "); 
                    System.out.println((Papa) papaMessage.getObject());  
                    aux = (Papa) papaMessage.getObject();
                    if(!aux.isMuerto()){
                        System.out.println();    
                        aux.disminuyeCont();
                        producePapa(aux);
                    }else{
                        System.out.println("Juego terminado! Perdí yo :(");
                        goodByeReceived = true;
                    }
                    
                }     
                if (papaMessage.getObject() != null && papaMessage.getObject().equals("Good bye!"))        {  
                    goodByeReceived = true;     
                }   
            }     

            messageConsumer.close();    
            session.close();    
            connection.close(); 
            
        }    catch (JMSException e)    {  
            e.printStackTrace();   
        } 
    }  
    public void producePapa(Papa paps)  {    
        MessageProducer messageProducer;    
        ObjectMessage papaMessage;    
        
        try    {      
            Connection connection = connectionFactory.createConnection();      
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);      
                            
            
            messageProducer = session.createProducer(queueOtro);      
            papaMessage = session.createObjectMessage();      

            papaMessage.setObject(paps);
            System.out.println("Sending papa: " + paps);      
            messageProducer.send(papaMessage);        
            
            messageProducer.close();      
            session.close();      
            connection.close();    
        }    catch (JMSException e)    
        {      
            e.printStackTrace();    
        }  
    }  
    
    public void limpiaColas(){
        Connection connection;
        MessageConsumer messageConsumer;
        TextMessage textMessage;
        boolean goodByeReceived = false;
        try    {      
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(queueMia);
            connection.start(); 
            while (!goodByeReceived)      {
                System.out.println("Waiting for messages...");
                textMessage = (TextMessage) messageConsumer.receive();
                if (textMessage != null)        {
                    System.out.print("Received the following message: "); 
                    System.out.println(textMessage.getText());  
                    System.out.println();     
                }     
                if (textMessage.getText() != null && textMessage.getText().equals("Good bye!"))        {  
                    goodByeReceived = true;     
                }   
            }     

            messageConsumer.close();    
            session.close();    
            connection.close(); 
            
        }    catch (JMSException e)    {  
            e.printStackTrace();   
        } 
    }
    public static void main(String[] args) {
        
        Jugador j = new Jugador();
        //j.limpiaColas();
        Papa miPapa = new Papa();
        j.producePapa(miPapa);
        j.getPapa();
        
        
    }
    
    
    
}
