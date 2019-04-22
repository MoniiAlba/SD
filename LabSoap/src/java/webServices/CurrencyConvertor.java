/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author sdist
 */
@WebService(serviceName = "CurrencyConvertor")
@Stateless()
public class CurrencyConvertor {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "convertCurrency")
    public double convertCurrency(@WebParam(name = "originCurrency") int originCurrency, @WebParam(name = "targetCurrency") int targetCurrency, @WebParam(name = "amount") double amount) {
        //TODO write your implementation code here:
        
        /*
        0 -> MX
        1-> UsDll
        2-> Cdll
        3-> lb
        
        */
        
        switch( originCurrency ){
            case 0:
                    amount = amount;
                    break;
            
            case 1:
                    amount = amount*19.21;
                    break;
            case 2:
                    amount = amount*14.40;
                    break;
            case 3:
                    amount = amount*25.32;
                    break;      
        }
        
        switch( targetCurrency ){
            case 0:
                    amount = amount;
                    break;
            
            case 1:
                    amount = amount*0.052;
                    break;
            case 2:
                    amount = amount*0.069;
                    break;
            case 3:
                    amount = amount*0.039;
                    break;    
        }
        return amount;
    }
    
}
