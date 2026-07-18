package modelo;

import java.time.LocalDateTime;

public class Usuario {

    private int idUsuario;
    private String usuario;
    private String contrasena;
    private String nombre;
    private String correo;
    private boolean esAdmin;
    private boolean correoValidado;
    private boolean estado;
    private String tokenConfirmacion;
    private LocalDateTime fechaRegistro;

    public Usuario() {
    }

    public Usuario(String usuario, String contrasena, String nombre, String correo) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.correo = correo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public boolean isCorreoValidado() {
        return correoValidado;
    }

    public void setCorreoValidado(boolean correoValidado) {
        this.correoValidado = correoValidado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTokenConfirmacion() {
        return tokenConfirmacion;
    }

    public void setTokenConfirmacion(String tokenConfirmacion) {
        this.tokenConfirmacion = tokenConfirmacion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
