
package modelo;
import java.sql.*;

/**
 *
 * @author Diego Lucas Romero
 * @version 22/09/2016 17:01, Diego Lucas Romero
 */

/**
 * Concexion a la base de datos utilizando el conector JDBD
 */
public class database {
    
    private static String  host = "192.168.28.3";  
    private String db = "dam20_clinicadondiego";
    private String user = "dam20";
    private String password = "salesianas";
    private String url = "jdbc:mysql://"+host+"/"+db;
    private Connection conn = null;

   /** Constructor de clase */
   public database(){
        this.url = "jdbc:mysql://192.168.28.3/"+this.db;
       try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la database (conexión)
         conn = DriverManager.getConnection( this.url, this.user , this.password );         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }catch(ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
   }

/**
 * obtener database (conexión=
 * @return  conn 
 */
   public Connection getdatabase()
   {
    return this.conn;
   }
    
}
