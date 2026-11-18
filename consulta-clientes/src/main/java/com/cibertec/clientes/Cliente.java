package com.cibertec.clientes;

public class Cliente {

    private int id;
    private String nombre;
    private String correo;
    private boolean estado;

    public Cliente(int id, String nombreCompleto, String correo, boolean estado) {
        this.id = id;
        this.nombre = nombreCompleto;
        this.correo = correo;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public boolean getEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}
