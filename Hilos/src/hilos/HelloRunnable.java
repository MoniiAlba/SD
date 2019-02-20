/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class HelloRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                System.out.println("Hilo Runnable :D " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HelloRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
