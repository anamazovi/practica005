package es.cic.curso.practica005.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.practica005.exception.CursoNotFoundException;
import es.cic.curso.practica005.models.Curso;
import es.cic.curso.practica005.repository.CursoRepository;

/*Es donde vamos a implementar la lógica de negocio */
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List <Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso getCursoById (Long id) {
        return cursoRepository.findById(id)
                . orElseThrow (() -> new CursoNotFoundException("Curso con ID " +id + " no encontrado"));
    }

    public Curso saveCurso (Curso curso) {
        return cursoRepository.save(curso);   
    }

    public void deleteCurso (Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new CursoNotFoundException("Curso con ID " + id + " no encontrado");
        }
        cursoRepository.deleteById(id);
    }


}
