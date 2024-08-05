package es.cic.curso.practica005.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*@Entity: Marca esta clase como una entidad JPA que se mapeará a 
una tabla en la base de datos.*/
@Entity
@Table(name = "cursos")
public class Curso {
     /*
     * @Id y @GeneratedValue: Especifican que el campo id es la 
     * clave primaria y su valor se genera automáticamente. 
     * Table(name = "cursos"): Define el nombre de la tabla en 
     * la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
     * @Column: Configura la columna en la base de datos.
     */
    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private boolean activo;

    /*
     * @OneToMany: Define una relación One-to-Many con la entidad Estudiante. 
     * mappedBy indica que el mapeo de la relación está definido en el 
     * lado de la entidad Estudiante.
     * cascade y orphanRemoval: Configuran el comportamiento de cascada y 
     * eliminación de huérfanos para mantener la integridad referencial.
     */
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estudiante> estudiantes;
    
    public Curso() {
    }

    public Curso(Long id, String nombre, String descripcion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    

}
