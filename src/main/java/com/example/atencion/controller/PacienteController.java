package com.example.atencion.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.atencion.service.PacienteService;
import com.example.atencion.model.Paciente;
import com.example.atencion.response.AtenResponse;


@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    //@GetMapping
    //public List<Paciente> getAllPacientes(){
    //    return pacienteService.getAllPacientes();
    //}
 
    @GetMapping
    public CollectionModel<EntityModel<Paciente>> getAllPacientes(){
        //return pacienteService.getAllPacientes();
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        List<EntityModel<Paciente>> pacientesResources = pacientes.stream()
            .map( paciente -> EntityModel.of(paciente,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(paciente.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPacientes());
        CollectionModel<EntityModel<Paciente>> resources = CollectionModel.of(pacientesResources, linkTo.withRel("pacientes"));

        return resources;
    }
        
    @GetMapping("/{id}")
    public EntityModel<Paciente> getPacienteById(@PathVariable Integer id) {
        //return pacienteService.getPacienteById(id);
        Optional<Paciente> paciente = pacienteService.getPacienteById(id);
        if (paciente.isPresent()) {
            return EntityModel.of(paciente.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPacientes()).withRel("all-pacientes"));
        } else {
            throw new PacienteNotFoundException("Paciente not found with id: " + id);
        }

    }

    @PostMapping("/crear")
    public EntityModel<Paciente> createPaciente (@RequestBody Paciente paciente) {
        //return pacienteService.createPaciente(paciente);
        Paciente createdPaciente = pacienteService.createPaciente(paciente);
        
        return EntityModel.of(createdPaciente,
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(createdPaciente.getId())).withSelfRel(),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPacientes()).withRel("all-pacientes"));


    }
    
    @PutMapping("edit/{id}")
    public EntityModel<Paciente> updatePaciente(@RequestBody Paciente paciente, @PathVariable Integer id) {
        
        Paciente updatedPaciente = pacienteService.updatePaciente(paciente, id);
        
        return EntityModel.of(updatedPaciente,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPacientes()).withRel("all-Pacientes"));
        //paciente.setId(id);
        //return pacienteService.updatePaciente(paciente,id);
   
    }

    @DeleteMapping("eliminar/{id}")
    public AtenResponse deletePacienteById(@PathVariable Integer id){
        
        AtenResponse atenResponse =  pacienteService.deletePacienteById(id);
        return atenResponse;
    }

}
