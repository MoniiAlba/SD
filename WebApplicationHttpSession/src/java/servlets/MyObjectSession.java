/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

/**
 *
 * @author sdist
 */
public class MyObjectSession {
    
    private String usuario;
    private int edad;

    public MyObjectSession() {
    }

    public MyObjectSession(String usuario, int edad) {
        this.usuario = usuario;
        this.edad = edad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
