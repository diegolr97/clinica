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
            String q = "SELECT * FROM Medicos ";
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
   public boolean añadirPaciente(String Nombre, String Apellido, int Telefono, String Problema)
    {
        String q=" INSERT INTO Paciente ( Nombre , Apellido, DNI, Telefono, Problema ) "
                    + "VALUES ('" + Nombre + "', '" + Apellido + "', '" + Telefono + "', '" + Problema + "' ) ";
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
        String q = " DELETE FROM Paciente WHERE  idPaciente='" + cod + "' " ;
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
   public void modificarPaciente (String Nombre, String Apellido, int Telefono){
          
          String q ="update Paciente set Nombre ='"+Nombre+"', Apellido ='"+Apellido+"', Telefono ='"+Telefono+"' ";
          
          try{
              PreparedStatement pstm = this.getdatabase().prepareStatement(q);
              pstm.execute();
              pstm.close();
              JOptionPane.showMessageDialog(null, "Operación Realizada");
              
          }catch(SQLException e){
              JOptionPane.showInputDialog(null, "Error de los datos son incorrectos. \nReviselos y vuelva a intentarlo");
              System.out.println( e.getMessage() );
          }
      }

    
}
