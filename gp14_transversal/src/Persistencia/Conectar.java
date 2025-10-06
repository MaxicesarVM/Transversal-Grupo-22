
package Persistencia;

import Modelo.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;



public class Conectar {
    
    public static void main (String[] args){
        Connection con = null;
        PreparedStatement ps = null;
        Alumno alumno = null;
        ResultSet rs = null;
        
        con = Conexion.getConexion();
        String sql = "INSERT INTO alumno(dni, apellido, nombre, fecha_nacimiento, estado) VALUES(?,?,?,?,?)";
            
        try{
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            
            sql = "SELECT * FROM alumno";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                
                System.out.println(alumno);
                
        }     
        } catch(SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
    
    
    
    
    
    
    
    
}
}
