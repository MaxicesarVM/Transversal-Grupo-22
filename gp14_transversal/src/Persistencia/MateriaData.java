package Persistencia;


import Modelo.Alumno;
    import Modelo.Materia;
import Persistencia.Conexion;
    import java.time.Month;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.logging.Level;
    import java.util.logging.Logger;


public class MateriaData {
    
    private Connection con = null;
    
    public MateriaData(Conexion conexion){
        this.con = conexion.getConexion();
    }
    
    
    public void agregarMateria(Materia a){
        
        String sql = "INSERT INTO materia(nombre, ano, estado) VALUES (?, ?, ?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
           
            ps.setString(1, a.getNombre());
            ps.setDate(2, Date.valueOf(a.getAno()));
            ps.setBoolean(3, a.isEstado());
            int registros = ps.executeUpdate();
            System.out.println(registros);
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                
                a.setId_materia(rs.getInt(1));
                System.out.println("Alta Exitosa");
                
                
            } else{
                System.out.println("No se pudo obtener el id");
            }
                  
            
        
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }        
        
        
    }
    
    
    
    public List<Materia> listarMaterias(){
        Materia m = null;
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * from materia";
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            
            while(resultado.next()){
                m = new Materia();
                m.setId_materia(resultado.getInt("id_materia"));
                m.setNombre(resultado.getString("nombre"));
                m.setAno(resultado.getDate("ano").toLocalDate());
                m.setEstado(resultado.getBoolean("estado"));
                materias.add(m);
            }
            
            ps.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex);
        }   
        
        
        return materias;
    }
    
    
    
    public Materia buscarMateria(int id){
        Materia m = null;
        String sql = "SELECT * FROM materia";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                m= new Materia();
                m.setId_materia(rs.getInt("id_materia"));
                m.setNombre(rs.getString("nombre"));
                m.setAno(rs.getDate("ano").toLocalDate());
                m.setEstado(rs.getBoolean("estado"));
            }
            System.out.println(m.toString());
            
            
        } catch (SQLException ex) {
            System.out.println("No existe ese dni" + ex);
        }
        
        return m;
    }
    
    public void actualizarMateria(Materia m){
        
        String sql = "UPDATE materia SET nombre=?, ano=?, estado=? WHERE id_materia = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setDate(2, Date.valueOf(m.getAno()));
            ps.setBoolean(3, m.isEstado());
            ps.setInt(4, m.getId_materia());
            ps.executeUpdate();
            ps.close();
            System.out.println("Materia actualizada correctamente");
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
        
        
        
    }
    
    public void eliminarMateria(int id){
        
        String sql = "DELETE FROM materia WHERE id_materia=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("Materia eliminada exitosamente");
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error al borrar la materia" + ex);
        }
        
        
        
    }
    
    
    public void altaLogica(Materia m){
        
        String sql = "UPDATE materia SET estado=1 WHERE id_materia=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getId_materia());
            ps.executeUpdate();
            ps.close();
            System.out.println("Materia dada de alta correctamente");
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
        
        
        
    }
    
    
    
    public void bajaLogica(Materia m){
        
        String sql = "UPDATE materia SET estado=0 WHERE id_materia=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getId_materia());
            ps.executeUpdate();
            ps.close();
            System.out.println("Materia dada de baja correctamente");
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error de actualizacion " + ex);
        }
        
        
        
    }
    
    
    
    
    
    
    
    
}
