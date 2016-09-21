/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;

/**
 *
 * @author Usuario
 */
public class database {
    
    private static String  host = "localhost";  
    private String db = "clinicadondiego";
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://"+host+"/"+db;
    private Connection conn = null;

   /** Constructor de clase */
   public database(){
        this.url = "jdbc:mysql://localhost/"+this.db;
       try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conn = DriverManager.getConnection( this.url, this.user , this.password );         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }catch(ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
   }

/**
 * obtener conexion
 * @return  
 */
   public Connection getdatabase()
   {
    return this.conn;
   }
    
}
