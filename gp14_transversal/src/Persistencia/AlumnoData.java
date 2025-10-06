
package Persistencia;

import Modelo.Alumno;
import java.time.Month;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




public class AlumnoData {
    
    private Connection con = null;
    
    public AlumnoData(Conexion conexion){
        this.con = conexion.getConexion();
    }
    
    
    
    
    public void agregarAlumno(Alumno a){
        
        String sql = "INSERT INTO alumno(dni, apellido, nombre, fecha_nacimiento, estado) VALUES (?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFecha_nacimiento()));
            ps.setBoolean(5, a.isEstado());
            int registros = ps.executeUpdate();
            System.out.println(registros);
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                
                a.setId_alumno(rs.getInt(1));
                System.out.println("Alta Exitosa");
                
                
            } else{
                System.out.println("No se pudo obtener el id");
            }
                  
            
        
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }        
        
        
    }
    
    public List<Alumno> listarAlumnos(){
        Alumno a = null;
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * from alumno";
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            
            while(resultado.next()){
                a = new Alumno();
                a.setId_alumno(resultado.getInt("id_alumno"));
                a.setDni(resultado.getInt("dni"));
                a.setApellido(resultado.getString("apellido"));
                a.setNombre(resultado.getString("nombre"));
                a.setFecha_nacimiento(resultado.getDate("fecha_nacimiento").toLocalDate());
                a.setEstado(resultado.getBoolean("estado"));
                alumnos.add(a);
            }
            
            ps.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }   
        
        
        return alumnos;
    }
    
    public Alumno buscarAlumno(int dni){
        Alumno a = null;
        String sql = "SELECT * FROM alumno WHERE dni = ?";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                a= new Alumno();
                a.setId_alumno(rs.getInt("id_alumno"));
                a.setDni(rs.getInt("dni"));
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                a.setEstado(rs.getBoolean("estado"));
            }
            System.out.println(a.toString());
            
            
        } catch (SQLException ex) {
            System.out.println("No existe ese dni" + ex);
        }
        
        return a;
    }
    
    
    public void actualizarAlumno(Alumno a){
        
        String sql = "UPDATE alumno SET apellido=?, nombre=?, fecha_nacimiento=?, estado=? WHERE dni=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, a.getApellido());
            ps.setString(2, a.getNombre());
            ps.setDate(3, Date.valueOf(a.getFecha_nacimiento()));
            ps.setBoolean(4, a.isEstado());
            ps.setInt(5, a.getDni());
            ps.executeUpdate();
            ps.close();
            System.out.println("Alumno actualizado correctamente");
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
        
        
        
    }
    
    public void eliminarAlumno(int dni){
        
        String sql = "DELETE FROM alumno WHERE dni=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ps.executeUpdate();
            ps.close();
            System.out.println("Alumno eliminado exitosamente");
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error al borrar alumno" + ex);
        }
        
        
        
    }
    
    
    public void altaLogica(Alumno a){
        
        String sql = "UPDATE alumno SET estado=1 WHERE dni=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, a.getDni());
            ps.executeUpdate();
            ps.close();
            System.out.println("Alumno dado de alta correctamente");
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
        
        
        
    }
    
    public void bajaLogica(Alumno a){
        
        String sql = "UPDATE alumno SET estado=0 WHERE dni=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, a.getDni());
            ps.executeUpdate();
            ps.close();
            System.out.println("Alumno dado de baja correctamente");
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
        
        
        
    }
    
    
    
    
    
    
}
