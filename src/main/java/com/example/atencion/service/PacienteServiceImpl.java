package com.example.atencion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.atencion.model.Paciente;
import com.example.atencion.repository.PacienteRepository;
import com.example.atencion.response.AtenResponse;

@Service
public class PacienteServiceImpl implements PacienteService{

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> getAllPacientes(){
        return pacienteRepository.findAll();
    }
    
    @Override
    public Optional<Paciente> getPacienteById(Integer id){
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente createPaciente(Paciente paciente){
        if(!pacienteRepository.existsById(paciente.getId())){
            return pacienteRepository.save(paciente);    
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public Paciente updatePaciente(Paciente paciente, Integer id){
        if(!pacienteRepository.existsById(id)){
            return null;
                
        }
        else
        {
            return pacienteRepository.save(paciente);
        }
    }

    @Override
    public AtenResponse deletePacienteById(Integer id){
        pacienteRepository.deleteById(id);
        return new AtenResponse("Eliminado: " + id, true);
    }
}
