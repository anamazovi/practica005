package es.cic.curso.practica005;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso.practica005.models.Curso;
import es.cic.curso.practica005.repository.CursoRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CursoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Curso curso;

    /*
     * @BeforeEach: Método que se ejecuta antes de cada test. 
     * Aquí se crea una nueva película y se guarda en la base de datos.
     */
    @BeforeEach
    void setUp() {
        curso = new Curso();
        curso.setNombre("Matemáticas");
        curso.setDescripcion ("Curso de matemáticas avanzadas");
        curso.setActivo(true);
        curso = cursoRepository.save(curso);
    }

    /*
     * @AfterEach: Método que se ejecuta después de cada test. 
     * Limpia la base de datos eliminando todas las películas.
     */
    @AfterEach
    public void tearDown() {
        cursoRepository.deleteAll();
    }

    @Test
    public void testCreateCurso() throws Exception {
        Curso nuevoCurso = new Curso();
        nuevoCurso.setNombre("Ciencias");
        nuevoCurso.setDescripcion("Curso de ciencias básicas");
        nuevoCurso.setActivo(true);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoCurso)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Ciencias"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion").value("Curso de ciencias básicas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.activo").value(true));
    }

    @Test
    public void testGetCursoById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/cursos/{id}", curso.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Matemáticas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion").value("Curso de matemáticas avanzadas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.activo").value(true));
    }

    @Test
    public void testDeleteCurso() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/cursos/{id}", curso.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cursos/{id}", curso.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetAllCursos() throws Exception {
        Curso curso2 = new Curso();
        curso2.setNombre("Geografía");
        curso2.setDescripcion("Curso de geografía mundial");
        curso2.setActivo(true);
        cursoRepository.save(curso2);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cursos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Matemáticas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nombre").value("Geografía"));
    }
}

    


