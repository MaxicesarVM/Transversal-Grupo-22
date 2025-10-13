
package Modelo;

import java.time.LocalDate;


public class Materia {
    
    private int id_materia;
    private String nombre;
    private LocalDate ano;
    private boolean estado;

    public Materia(int id_materia, String nombre, LocalDate ano, boolean estado) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.ano = ano;
        this.estado = estado;
    }

    public Materia(String nombre, LocalDate ano, boolean estado) {
        this.id_materia = -1;
        this.nombre = nombre;
        this.ano = ano;
        this.estado = estado;
    }

    
    
    public Materia() {
        this.id_materia = -1;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getAno() {
        return ano;
    }

    public void setAno(LocalDate ano) {
        this.ano = ano;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
}
