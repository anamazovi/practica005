package es.cic.curso.practica005.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/*@Entity: Marca esta clase como una entidad JPA que se mapeará a 
una tabla en la base de datos.*/
@Entity
public class Curso {
     /*
     * @Id y @GeneratedValue: Especifican que el campo id es la 
     * clave primaria y su valor se genera automáticamente. 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String descripción;
    private boolean activo;

    /*
     * @OneToMany: Define una relación One-to-Many con la entidad Estudiante. 
     * mappedBy indica que el mapeo de la relación está definido en el 
     * lado de la entidad Estudiante.
     * cascade y orphanRemoval: Configuran el comportamiento de cascada y 
     * eliminación de huérfanos para mantener la integridad referencial.
     */
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Estudiante> estudiantes = new HashSet<>();
    
    public Curso() {
    }

    public Curso(Long id, String nombre, String descripción, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripción = descripción;
        this.activo = activo;
    }

    

   // Getters y setter
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

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set <Estudiante> gEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes (Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    

}
