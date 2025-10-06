
package gp14_transversal;

import Modelo.Alumno;
import Persistencia.AlumnoData;
import Persistencia.Conexion;
import java.time.LocalDate;
import java.time.Month;


public class Gp14_transversal {

   
    public static void main(String[] args) {
        
        Alumno alejandro = new Alumno(22113355, "Peralta", "Alejandro", LocalDate.of(2005,Month.FEBRUARY,19), true);
        Alumno maxi = new Alumno(39393939, "Velazquez", "Maxi", LocalDate.of(2002,Month.JULY,21), true);
        Alumno daniela = new Alumno(42424242, "Mestre", "Daniela", LocalDate.of(1997,Month.JANUARY,11), true);
        Alumno daniel = new Alumno(12121212, "Rodriguez", "Daniel", LocalDate.of(1994,Month.SEPTEMBER,12), true);
        Alumno facundo = new Alumno(44444444, "Calderon", "Facundo", LocalDate.of(1992,Month.NOVEMBER,04), true);
        Alumno alan = new Alumno(77667766, "Jaciuk", "Alan", LocalDate.of(1991,Month.OCTOBER,01), true);
        Alumno lucas = new Alumno(88998899, "Zarate", "Luquitas", LocalDate.of(2001,Month.MAY,02), true);
        Alumno fernando = new Alumno(44554455, "Suarez", "Fernando", LocalDate.of(1982,Month.FEBRUARY,27), true);
        Alumno fernando2 = new Alumno(4467455, "Rodriguez", "Fernando", LocalDate.of(1982,Month.FEBRUARY,27), true);
        
        Conexion con = new Conexion();
        AlumnoData operacionesAlumnos = new AlumnoData(con);
        
        
        
        operacionesAlumnos.agregarAlumno(fernando2);
        
        
        
        
        
        
        
        
        
    }
    
}
