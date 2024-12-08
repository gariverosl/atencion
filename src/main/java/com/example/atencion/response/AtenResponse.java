package com.example.atencion.response;

public class AtenResponse {

    String mensaje;
    Boolean estado;

    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public AtenResponse(String mensaje, Boolean estado) {
        this.mensaje = mensaje;
        this.estado = estado;
    }

}
