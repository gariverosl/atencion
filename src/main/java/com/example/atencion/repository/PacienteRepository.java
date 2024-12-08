package com.example.atencion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.atencion.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{


}
