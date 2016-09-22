
package controlador;
import vista.vista;

/**
 *
 * @author Diego Lucas Romero
 * @version 22/09/2016 17:01, Diego Lucas Romero
 */

/**
 * Arrancas la vista desde main con el metodo iniciar
 * en el controlador
 */
public class main {
    public static void main(String[] args) {
        //ejecuta el controlador y este la vista
        new controlador( new vista() ).iniciar() ;
        System.out.println("arranque programa");
        
    }
    
}
