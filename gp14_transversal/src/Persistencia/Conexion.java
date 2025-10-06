
package Persistencia;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexion {
    
    private static Conexion conexion = null;
    
    public Conexion(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        
        }catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Class Conexion: Error en la carga de driver");
        }
            
        
    }
    
    
    
    public static Connection getConexion(){
        Connection con = null;
        if(conexion == null){
            conexion = new Conexion();
        }
        try{
            con = DriverManager.getConnection("jdbc:mariadb://localhost/gp14_universidad", "root", "");
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion");
        }
        
        return con;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
