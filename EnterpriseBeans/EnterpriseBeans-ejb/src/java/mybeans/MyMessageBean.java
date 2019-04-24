/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybeans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author sdist
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/GlassFishTestQueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MyMessageBean implements MessageListener {
    
    public MyMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        try {
            System.out.println("Ahí va el mensaje "+((TextMessage)message).getText());
        } catch (JMSException ex) {
            Logger.getLogger(MyMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
