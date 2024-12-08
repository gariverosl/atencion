package com.example.atencion.service;

import java.util.List;
import java.util.Optional;

import com.example.atencion.model.Atencion;
import com.example.atencion.response.AtenResponse;

public interface AtencionService {

    List<Atencion> getAllAtenciones();
    Optional<Atencion> getAtencionById(Integer id);
    Atencion createAtencion(Atencion atencion);
    Atencion updateAtencion(Atencion atencion, Integer id);
    AtenResponse deleteAtencionById(Integer id);

}
