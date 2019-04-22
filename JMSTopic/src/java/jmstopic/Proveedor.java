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
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author sdist
 */
public class Proveedor {
    @Resource(mappedName = "jms/GlassFishTestConnectionFactory")  
    private static ConnectionFactory connectionFactory;
    private int noticias;
    private String[] topicNames = {"TelecomTopic", "BankTopic", "EduTopic", "FoodTopic", "TransportTopic"};
    private Topic[] topics = new Topic[5];  
    private Connection connection;
    private Session session;

    public Proveedor() {
        try {
            connection = connectionFactory.createConnection();      
            session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            
            for(int i = 0; i < 5; i++){
                topics[i] = session.createTopic(topicNames[i]);
            }
            
        } catch (JMSException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setNoticias(int n){
        noticias = n;
    }
    
    public void chismoso(){
        Random r = new Random();
        int topicNum;
        int news;
        for (int i = 0; i < noticias; i++) {
            topicNum = r.nextInt(5);
            news = r.nextInt(7)+1;
            produceMessage(news, topicNum);
            
        }
        for(int i = 0; i < 5; i++){
            produceMessage(-1, i);
        }
        
        try {      
            session.close();
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    public void produceMessage(int news, int topicNum)  {    
        MessageProducer messageProducer;    
        TextMessage textMessage;    
        try    {      
            messageProducer = session.createProducer(topics[topicNum]); 
            textMessage = session.createTextMessage();      

            textMessage.setText(news+"");      
            System.out.println("Sending terrible market news. Level: " + textMessage.getText() + " category: " + topicNames[topicNum]);      
            messageProducer.send(textMessage);     
            
            messageProducer.close();      
                
        }    catch (JMSException e)    
        {      
            e.printStackTrace();    
        }  
    }  
    
    public static void main(String[] args) {
        Proveedor p = new Proveedor();
        p.setNoticias(10);
        p.chismoso();
    }
}
