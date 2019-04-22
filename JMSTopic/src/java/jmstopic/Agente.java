/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmstopic;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author sdist
 */
public class Agente {
    @Resource(mappedName = "jms/GlassFishTestConnectionFactory")  
    private static ConnectionFactory connectionFactory;
    private String[] topicNames = {"TelecomTopic", "BankTopic", "EduTopic", "FoodTopic", "TransportTopic"};
    private Topic topic;
    private Connection connection;
    private Session session;

    public Agente() {        
        try {
            connection = connectionFactory.createConnection();      
            session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            Random r = new Random();
            int topicNum = r.nextInt(5);
            topic = session.createTopic(topicNames[topicNum]);
            System.out.println("I'm a floor broker handling " + topicNames[topicNum] + "accounts");
            getMessages();
        } catch (JMSException ex) {
            Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getMessages()  {    
        Connection connection;
        MessageConsumer messageConsumer;
        TextMessage textMessage;
        boolean goodByeReceived = false;
        try    {      
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(topic);
            connection.start(); 
            while (!goodByeReceived)      {
                textMessage = (TextMessage) messageConsumer.receive();
                int num = 0;
                if (textMessage != null)        {
                    System.out.println("I received bad news of level: " + textMessage.getText()); 
                    num = Integer.parseInt(textMessage.getText());
                    if(num > 5){
                        System.out.println("SELLING!");
                    }else{
                        if(num >= 0  ){
                            System.out.println("I have to be PATIENT D:!");
                        }
                    }
                        
                }     
                if (textMessage.getText() != null && num < 0)        {  
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
        Agente a = new Agente();
        
    }
}
