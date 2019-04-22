/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationrestfulclient;

import webserviceclient.NewJerseyClient;

/**
 *
 * @author sdist
 */
public class JavaApplicationRestfulClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewJerseyClient client = new NewJerseyClient();
        Object response = client.getHtml();
        System.out.println(response);
        client.putHtml("a todos");
        client.close();
    }
    
}
