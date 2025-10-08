
package Modelo;


public class Inscripcion {
    
    private int id;
    private Alumno alumno;
    private Materia materia;
    private int nota;
    
    

    public Inscripcion(int id, Alumno alumno, Materia materia, int nota) {
        this.id = id;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }
    
    public Inscripcion(Alumno alumno, Materia materia, int nota) {
        this.id = -1;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }
    
    public Inscripcion() {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
    
    
    
    
    
    
    
    
}
