package com.example.atencion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.atencion.model.Atencion;
import com.example.atencion.repository.AtencionRepository;
import com.example.atencion.response.AtenResponse;




@Service
public class AtencionServiceImpl implements AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    @Override
    public List<Atencion> getAllAtenciones(){
        return atencionRepository.findAll();
    }
    
    @Override
    public Optional<Atencion> getAtencionById(Integer id){
        return atencionRepository.findById(id);
    }

    @Override
    public Atencion createAtencion(Atencion atencion){
        if(!atencionRepository.existsById(atencion.getId())){
            return atencionRepository.save(atencion);    
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public Atencion updateAtencion(Atencion atencion, Integer id){
        if(!atencionRepository.existsById(id)){
            return null;
                
        }
        else
        {
            return atencionRepository.save(atencion);
        }
    }

    @Override
    public AtenResponse deleteAtencionById(Integer id){
        atencionRepository.deleteById(id);
        return new AtenResponse("Eliminado: " + id, true);
    }


}    
    
