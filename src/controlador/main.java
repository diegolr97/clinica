/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import vista.vista;

/**
 *
 * @author Usuario
 */
public class main {
    public static void main(String[] args) {
        //ejecuta el controlador y este la vista
        new controlador( new vista() ).iniciar() ;
        System.out.println("arranque programa");
        
    }
    
}
