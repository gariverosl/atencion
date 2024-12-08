package com.example.atencion.service;

import java.util.List;
import java.util.Optional;

import com.example.atencion.model.Paciente;
import com.example.atencion.response.AtenResponse;

public interface PacienteService {

    List<Paciente> getAllPacientes();
    Optional<Paciente> getPacienteById(Integer id);
    Paciente createPaciente (Paciente paciente);
    Paciente updatePaciente (Paciente paciente, Integer id);
    AtenResponse deletePacienteById(Integer id);
}
