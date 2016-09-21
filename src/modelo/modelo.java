/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class modelo extends database {
    
    public modelo(){
        
    }
    
    public boolean iniciarSesion(String Nombre, String Contraseña) {
       String nombre= "";
        String contraseña = "";
        boolean comp= false;
        
        try {
            String q = "SELECT * FROM medico ";
            PreparedStatement pstm = this.getdatabase().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            
            
            while (res.next()) {
                
                nombre = res.getString("Nombre");
                contraseña = res.getString("Contraseña");
                
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
    
    public DefaultTableModel listarPaciente()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"idPaciente", "Nombre", "Apellido", "Problema", "Receta"};
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
    Object[][] data = new String[registros][6];
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
   public boolean añadirPaciente(String Nombre, String Apellido, String Problema)
    {
        String q=" INSERT INTO paciente ( Nombre , Apellido, DNI, Telefono, Problema, Receta ) "
                    + "VALUES ('" + Nombre + "', '" + Apellido + "', '" + Problema + "', 'Nada Especificado' ) ";
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
   public void modificarPaciente (String Nombre, String Apellido, int cod){
          
          String q ="update paciente set Nombre ='"+Nombre+"', Apellido ='"+Apellido+"' where idPaciente='"+cod+"' ";
          
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
   
   public void Recetar(String Receta,int cod){
          
          String q ="update paciente set Receta ='"+Receta+"' where idPaciente='"+cod+"' ";
          
          try{
              PreparedStatement pstm = this.getdatabase().prepareStatement(q);
              pstm.execute();
              pstm.close();
              JOptionPane.showMessageDialog(null, "Operación Realizada");
              
          }catch(SQLException e){
              JOptionPane.showInputDialog(null, "Error de los datos Vuelva a Escribirlos");
              System.out.println( e.getMessage() );
          }
      }

    
}
