/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import vista.vista;
import modelo.modelo;

/**
 *
 * @author Usuario
 */
public class controlador implements ActionListener,MouseListener {
    vista vista;
    modelo modelo = new modelo();
    
    
    public controlador( vista vista ){
        this.vista = vista;
        
        
    }
    
    public enum ClinicaAct{
        
       btnEntrar, // //
       btnCancelar, // //
       btnRegistro, // //
       btnInfo, // //                     falta para despues
       btnRegistrar, // //
       btnAtras, // //
       btnListar, // //  
       btnModificar, // //
       btnEliminar, // //
       btnAtras2, // //
       btnRecetar, //                    falta
       tbPaciente, // //
       btnModificar2, // //
       btnCancelar2, // //
       tbInfo, // //
       btnAtras3,
       
       
       
       
       
        
    }
    
    
    public void iniciar(){
        this.vista.setVisible(true);
        this.vista.setBounds(500, 200, 583, 389);
        
        this.vista.btnEntrar.setActionCommand("btnEntrar");
        this.vista.btnEntrar.addActionListener(this);
        
        this.vista.btnCancelar.setActionCommand("btnCancelar");
        this.vista.btnCancelar.addActionListener(this);
        
        this.vista.btnRegistro.setActionCommand("btnRegistro");
        this.vista.btnRegistro.addActionListener(this);
        
        this.vista.btnInfo.setActionCommand("btnInfo");
        this.vista.btnInfo.addActionListener(this);
        
        this.vista.btnRegistrar.setActionCommand("btnRegistrar");
        this.vista.btnRegistrar.addActionListener(this);
        
        this.vista.btnAtras.setActionCommand("btnAtras");
        this.vista.btnAtras.addActionListener(this);
        
        this.vista.btnListar.setActionCommand("btnListar");
        this.vista.btnListar.addActionListener(this);
        
        this.vista.btnModificar.setActionCommand("btnModificar");
        this.vista.btnModificar.addActionListener(this);
        
        this.vista.btnEliminar.setActionCommand("btnEliminar");
        this.vista.btnEliminar.addActionListener(this);
        
        this.vista.btnAtras2.setActionCommand("btnAtras2");
        this.vista.btnAtras2.addActionListener(this);
        
        this.vista.btnRecetar.setActionCommand("btnRecetar");
        this.vista.btnRecetar.addActionListener(this);
        
        this.vista.tbPaciente.addMouseListener(this);
        this.vista.tbPaciente.setName("tbPaciente");
        
        this.vista.btnModificar2.setActionCommand("btnModificar2");
        this.vista.btnModificar2.addActionListener(this);
        
        this.vista.btnCancelar2.setActionCommand("btnCancelar2");
        this.vista.btnCancelar2.addActionListener(this);
        
        this.vista.btnAtras3.setActionCommand("btnAtras3");
        this.vista.btnAtras3.addActionListener(this);
        
        this.vista.tbInfo.addMouseListener(this);
        this.vista.tbInfo.setName("tbInfo");
        
        
        
            
            
          
        
       }

    @Override
    public void actionPerformed(ActionEvent e) {
         switch ( ClinicaAct.valueOf( e.getActionCommand() ) )
                     {
             case btnEntrar :
            if(this.modelo.iniciarSesion(this.vista.txtNombre.getText(), this.vista.txtCrontaseña.getText())){
           JOptionPane.showMessageDialog(null, "Los Datos son correctos");
           this.vista.dispose();
           this.vista.MenuMedico.setVisible(true);
           this.vista.MenuMedico.setBounds(500, 200, 780, 509);
           this.vista.tbPaciente.setModel(this.modelo.listarPaciente());
           
           
           
            }else{
                JOptionPane.showMessageDialog(null, "Datos incorrectos");
            }
           break;
           
             case btnCancelar :
                 this.vista.dispose();
                 
                 break;
                 
             case btnRegistro :
                 this.vista.dispose();
                 this.vista.RegistroPaciente.setVisible(true);
                 this.vista.RegistroPaciente.setBounds(500, 200, 384, 489);
                 this.vista.txtRegistroNombre.setText("");
                 this.vista.txtRegistroApellido.setText("");
                 this.vista.txtRegistroDNI.setText("");
                 this.vista.txtRegistroTelefono.setText("");
                 this.vista.txtRegistroProblema.setText("");
                 
                 break;
                    
             case btnAtras :
                 this.vista.RegistroPaciente.dispose();
                 this.vista.setVisible(true);
                 this.vista.txtNombre.setText("");
                 this.vista.txtCrontaseña.setText("");
                 
                break;
                
             case btnInfo :
                 this.vista.InformacionCuentas.setVisible(true);
                 this.vista.InformacionCuentas.setBounds(500, 200, 271, 197);
                 this.vista.tbInfo.setModel(this.modelo.listarMedico());
                 
                 break;
                 
             case btnAtras3 :
                 this.vista.InformacionCuentas.dispose();
                 
                 break;
                 
             case btnRegistrar :
                 this.modelo.añadirPaciente(this.vista.txtRegistroNombre.getText(), this.vista.txtRegistroApellido.getText(), this.vista.txtRegistroProblema.getText());
                 this.vista.RegistroPaciente.dispose();
                 this.vista.setVisible(true);
                 this.vista.txtNombre.setText("");
                 this.vista.txtCrontaseña.setText("");
                 
                 break;
                 
             case btnListar :
                 this.vista.tbPaciente.setModel(this.modelo.listarPaciente());
                 break;
                 
             case btnEliminar :
                 this.modelo.EliminarPaciente(Integer.parseInt(this.vista.txtId.getText()));
                 this.vista.tbPaciente.setModel(this.modelo.listarPaciente());
                 break;
                 
             case btnModificar :
                 this.vista.ModificarPaciente.setVisible(true);
                 this.vista.ModificarPaciente.setBounds(500, 200, 304, 280);
                 this.vista.txtModificarNombre.setText("");
                 this.vista.txtModificarApellido.setText("");
                 break;
                 
             case btnModificar2 :
                 this.modelo.modificarPaciente(this.vista.txtModificarNombre.getText(), this.vista.txtModificarApellido.getText(), Integer.parseInt(this.vista.txtId.getText()));
                 this.vista.ModificarPaciente.dispose();
                 this.vista.tbPaciente.setModel(this.modelo.listarPaciente());
                 break;
                 
             case btnCancelar2 :
                 this.vista.ModificarPaciente.dispose();
                 this.vista.tbPaciente.setModel(this.modelo.listarPaciente());
                 break;
                 
             case btnAtras2 :
                 this.vista.MenuMedico.dispose();
                 this.vista.setVisible(true);
                 this.vista.setBounds(500, 200, 583, 389);
                 this.vista.txtNombre.setText("");
                 this.vista.txtNombre.setText("");
                 break;
                 
             case btnRecetar :
                 this.modelo.Recetar(this.vista.txtReceta.getText(), Integer.parseInt(this.vista.txtId.getText()));
                 this.vista.tbPaciente.setModel(this.modelo.listarPaciente());
                 break;
     
         }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila;
        switch (controlador.ClinicaAct.valueOf(e.getComponent().getName())){           
        
            case tbPaciente:
             fila = this.vista.tbPaciente.rowAtPoint(e.getPoint());
            if (fila > -1){                
                this.vista.txtId.setText( String.valueOf( this.vista.tbPaciente.getValueAt(fila, 0) ));
                }
            break;
      }
        }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
