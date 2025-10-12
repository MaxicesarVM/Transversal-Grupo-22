
package Persistencia;

import Modelo.Alumno;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class InscripcionData {
    
    private Connection con = null;
    
    public InscripcionData(Conexion conexion){
        this.con = conexion.getConexion();
    }
    
    
    public void inscribir(Inscripcion i){
        
        String sql = "INSERT INTO inscripcion(nota, id_alumno, id_materia) VALUES (?, ?, ?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
           
            ps.setInt(1, i.getNota());
            ps.setInt(2, i.getAlumno().getId_alumno());
            ps.setInt(3, i.getMateria().getId_materia());
            int registros = ps.executeUpdate();
            System.out.println(registros);
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                
                i.setId(rs.getInt(1));
                System.out.println("Cursada inscripta correctamente");
                
                
            } else{
                System.out.println("No se pudo obtener el id luego de insertar la inscripcion");
            }
                  
            
        
        } catch (SQLException ex) {
            System.out.println("Error de conexion o al insertar inscripcion: " + ex);
        }        
        
        
    }
    
    
    
    
    
    
    public void anularInscripcion(int idAlumno, int idMateria){
        
        String sql = "DELETE FROM inscripcion WHERE id_alumno = ? and id_materia = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            
            int filas = ps.executeUpdate();
            
            if(filas > 0){
                
                JOptionPane.showMessageDialog(null, "Inscripcion anulada exitosamente");
                
            }
            
            ps.close();
            
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error al anular la inscripcion" + ex);
        }
        
        
        
    }
    
        public int buscarNotaInscripcion(int idAlumno, int idMateria){
            int nota = -1;
        
            String sql = "SELECT nota FROM inscripcion WHERE id_alumno = ? ANd id_materia = ?";
        
            try{
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, idAlumno);
                ps.setInt(2, idMateria);
                ResultSet registros = ps.executeQuery();
                
                if(registros.next()){
                    nota = registros.getInt("nota");
                }
                
                ps.close();
                
                
            } catch (SQLException ex) {
                System.out.println("Error en la busqueda una nota por el id_alumno o id_materia" + ex.getMessage());
        }
            
        
            return nota;
    }
    
    
    
    public void actualizarInscripcion(Inscripcion i){
        
        String sql = "UPDATE inscripcion SET nota = ? WHERE id_alumno = ? AND id_materia = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
           
            ps.setInt(1, i.getNota());
            ps.setInt(2, i.getAlumno().getId_alumno());
            ps.setInt(3, i.getMateria().getId_materia());
            int registros = ps.executeUpdate();
            System.out.println(registros);
            ResultSet rs = ps.getGeneratedKeys();
            
            
                  
            
        
        } catch (SQLException ex) {
            System.out.println("Error de conexion o al actualizar inscripcion: " + ex);
        }        
        
        
    }
    
    
    
    
    
    
    
    
    public List<Materia> obtenerMateriasCursadas(int id){
        List<Materia> materias = new ArrayList<>();
        
        try{
            String sql = "SELECT materia.id_materia, nombre FROM inscripcion, materia WHERE inscripcion.id_materia = materia.id_materia" + 
                    " AND inscripcion.id_alumno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Materia materia;
            while(rs.next()){
                materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materias.add(materia);
                
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Error al mostrar materias cursadas" + ex.getMessage());
        }
        
        
       return materias;  
        
    }
    
    public List<Materia> obtenerMateriasNoCursadas(int id){
        List<Materia> materias = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM materia WHERE id_materia NOT IN " + "(SELECT id_materia FROM inscripcion WHERE id_alumno = ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Materia materia;
            while(rs.next()){
                materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materias.add(materia);
                
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Error al mostrar materias NO cursadas" + ex.getMessage());
        }
        
        
       return materias;  
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
