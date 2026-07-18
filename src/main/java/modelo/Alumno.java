package modelo;

import java.time.LocalDate;

public class Alumno
{

    private String matricula;
    private String nombre;
    private String grupo;
    private String correo;
    private LocalDate fechaIngreso;

    public Alumno() 
    {
    }

    public Alumno(String matricula, String nombre, String grupo, LocalDate fechaIngreso) 
    {
        this.matricula = matricula;
        this.nombre = nombre;
        this.grupo = grupo;
        this.fechaIngreso = fechaIngreso;
    }

    public String getMatricula() 
    {
        return matricula;
    }

    public void setMatricula(String matricula) 
    {
        this.matricula = matricula;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getGrupo() 
    {
        return grupo;
    }

    public void setGrupo(String grupo) 
    {
        this.grupo = grupo;
    }

    public String getCorreo() 
    {
        return correo;
    }

    public void setCorreo(String correo) 
    {
        this.correo = correo;
    }

    public LocalDate getFechaIngreso() 
    {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) 
    {
        this.fechaIngreso = fechaIngreso;
    }
}
