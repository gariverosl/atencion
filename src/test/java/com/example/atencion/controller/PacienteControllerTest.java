package com.example.atencion.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.atencion.model.Atencion;
import com.example.atencion.model.Paciente;
import com.example.atencion.service.PacienteService;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteService usuarioServiceMock;
    
    @Test
    public void obtenerTodosTest() throws Exception {
        

        Atencion atencion = new Atencion();
        atencion.setId(1);
        atencion.setFecha("01-10-2012");
        atencion.setCausa("test1");
        atencion.setNombreDoc("Dr.F");
        List<Atencion> atenciones = List.of(atencion);

        Paciente paciente1test = new Paciente();
        paciente1test.setNombre("test1");
        paciente1test.setId(1);
        paciente1test.setAtenciones(atenciones);

        Paciente paciente2test = new Paciente();
        paciente2test.setNombre("test2");
        paciente2test.setId(2);
        paciente2test.setAtenciones(atenciones);

        List<Paciente> pacientes = List.of(paciente1test,paciente2test);

        when(usuarioServiceMock.getAllPacientes()).thenReturn(pacientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$    ", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[0].nombre", Matchers.is("test1")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[1].nombre", Matchers.is("test2")));
    }
}
