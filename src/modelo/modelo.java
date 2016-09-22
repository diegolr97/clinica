
package modelo;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego Lucas Romero
 * @version 22/09/2016 17:01, Diego Lucas Romero
 */
public class modelo extends database {
    
    /** Constructor de clase */
    
    public modelo(){
        
    }
    /**
     * Metodo para iniciar sesion y saber segun el select y las variables contraseña y nombre si es correcto
     * el dato introducido
     * @param Nombre
     * @param Contraseña
     * @return comp
     */
    public boolean iniciarSesion(String Nombre, String Contraseña) {
       String nombre= "";
        String contraseña = "";
        boolean comp= false;
        //consulta sql
        try {
            String q = "SELECT * FROM medico ";
            PreparedStatement pstm = this.getdatabase().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            
            
            while (res.next()) {
                //recorre uno a uno el Nombre y la Contraseña de cada Medico
                nombre = res.getString("Nombre");
                contraseña = res.getString("Contraseña");
                //Si coincide los datos puesto con los datos de la base de datos
                //puedes entrar al menu
                
                 if(nombre.equals(Nombre) && contraseña.equals(Contraseña) ){
              
                    comp = true;
                    
                }    
            }
            res.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
        } 
        return comp;
    }
    
    /**
     * Metodo para listar los Pacientes que se creen una vez registrado, especificando 
     * el nombre de las columnas y lo select especifico para sacar los datos deseados
     * @return tablemodel
     */
    
    public DefaultTableModel listarPaciente()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"idPaciente", "Nombre", "Apellido", "Problema", "Receta", "Estado"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getdatabase().prepareStatement( "SELECT count(*) as todo FROM paciente");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("todo");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][7];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getdatabase().prepareStatement("SELECT * FROM paciente");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                
                data[i][0] = res.getString( "idPaciente" );
                data[i][1] = res.getString( "Nombre");
                data[i][2] = res.getString( "Apellido");
                data[i][3] = res.getString( "Problema");
                data[i][4] = res.getString( "Receta");
                data[i][5] = res.getString( "Estado");
                    
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
        
    }
    /**
     * Metodo para listar los Medicos que se creen una vez registrado, especificando 
     * el nombre de las columnas y lo select especifico para sacar los datos deseados
     * @return tablemodel
     */
    public DefaultTableModel listarMedico()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Nombre", "Contraseña"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getdatabase().prepareStatement( "SELECT count(*) as todo FROM medico");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("todo");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][3];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getdatabase().prepareStatement("SELECT * FROM medico");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                
                data[i][0] = res.getString( "Nombre" );
                data[i][1] = res.getString( "Contraseña");
               
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    /**
     * Metodo para añadir los pacientes una vez registrados, especificando el nombre, apellido,
     * problema
     * @param Nombre
     * @param Apellido
     * @param Problema
     * @return false
     */
   public boolean añadirPaciente(String Nombre, String Apellido, String Problema)
    {
        //consulta sql
        String q=" INSERT INTO paciente ( Nombre , Apellido, Problema, Receta, Estado ) "
                    + "VALUES ('" + Nombre + "', '" + Apellido + "', '" + Problema + "', 'Nada Especificado', 'Cita Previa' ) ";
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = this.getdatabase().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        
        
    }
   /**
     * Metodo para Eliminar los pacientes una vez ya registrados, sabiendo la id del paciente
     * que es el la clave primaria
     * @param cod
     * @return res
     */
   
   public boolean EliminarPaciente(int cod)
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM paciente WHERE  idPaciente='" + cod + "' " ;
        //se ejecuta la consulta
         try {
            PreparedStatement pstm = this.getdatabase().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
        
        
        
    }
   /**
     * Metodo para Modificar los pacientes una vez ya registrados, sabiendo la id del paciente
     * que es el la clave primaria
     * @param Nombre
     * @param Apellido
     * @param cod
     */
   public void modificarPaciente (String Nombre, String Apellido, int cod){
          //consulta sql
          String q ="update paciente set Nombre ='"+Nombre+"', Apellido ='"+Apellido+"' where idPaciente='"+cod+"' ";
          //se ejecuta la consulta
          try{
              PreparedStatement pstm = this.getdatabase().prepareStatement(q);
              pstm.execute();
              pstm.close();
              JOptionPane.showMessageDialog(null, "Operación Realizada");
              
          }catch(SQLException e){
              JOptionPane.showInputDialog(null, "Error de los datos, vuelva a escribirlos");
              System.out.println( e.getMessage() );
          }
      }
   /**
     * Metodo para Recetar los pacientes una vez ya registrados, sabiendo la id del paciente
     * que es el la clave primaria
     * @param Receta
     * @param cod
     * @return res
     */
   
   public void Recetar(String Receta,int cod){
          //consulta sql
          String q ="update paciente set Receta ='"+Receta+"' where idPaciente='"+cod+"' ";
          String w ="update paciente set Estado ='cita realizada'";
          //se ejecutra la consulta sql
          try{
              PreparedStatement pstm = this.getdatabase().prepareStatement(q);
              PreparedStatement pstm1 = this.getdatabase().prepareStatement(w);
              pstm.execute();
              pstm1.execute();
              pstm.close();
              pstm1.close();
              JOptionPane.showMessageDialog(null, "Operación Realizada");
              
          }catch(SQLException e){
              JOptionPane.showInputDialog(null, "Error de los datos Vuelva a Escribirlos");
              System.out.println( e.getMessage() );
          }
      }
   
   
       
   

    
}
