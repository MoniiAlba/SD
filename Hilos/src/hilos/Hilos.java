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
public class Hilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            HelloThread hilo1 = new HelloThread();
//            hilo1.start();
//            
//            Thread hilo2 = new Thread(new HelloRunnable());
//            hilo2.start();
//            
//            hilo1.join();
//            hilo2.join();
//            
//            Thread hilo3 = new Thread(new HelloRunnable());
//            hilo3.start();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
//        }

        Counter counter = new Counter(0);
        SynchronizedThread hilo1 = new SynchronizedThread(counter);
        hilo1.start();
        
        SynchronizedThread hilo2 = new SynchronizedThread(counter);
        hilo2.start();
    }
    
}
