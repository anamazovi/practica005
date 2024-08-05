package es.cic.curso.practica005.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso.practica005.models.Curso;


/*
 * JpaRepository: Proporciona métodos para realizar operaciones CRUD 
 * y consultas sobre la entidad Curso.
 */
public interface CursoRepository  extends JpaRepository<Curso, Long> {

}

