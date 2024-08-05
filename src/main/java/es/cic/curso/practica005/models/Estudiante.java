package es.cic.curso.practica005.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String email;

    /*
     * @ManyToOne: Define una relación Many-to-One con la entidad 
     * Curso. Esto significa que muchos estudiantes pueden estar 
     * en un curso.
     * @JoinColumn: Especifica la columna en la tabla Estudiante 
     * que se usará para la relación con Curso.
     */
    @ManyToOne
    @JoinColumn (name = "curso_id")
    private Curso curso;
    
    public Estudiante() {
    }

    public Estudiante(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    //Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso (Curso curso) {
        this.curso = curso;
    }
 }
    

