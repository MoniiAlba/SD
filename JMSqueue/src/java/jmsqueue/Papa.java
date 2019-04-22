/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmsqueue;

import java.io.Serializable;

/**
 *
 * @author sdist
 */
public class Papa implements Serializable{
    private int cont;

    public Papa() {
        cont = (int) Math.round(Math.random()*20);
    }

    public int getCont() {
        return cont;
    }
    
    public boolean isMuerto(){
        return cont==0;
    }
    public void disminuyeCont(){
        cont--;
    }
    
    public String toString(){
        return "Papa cont: "+cont;
    }
    
}
