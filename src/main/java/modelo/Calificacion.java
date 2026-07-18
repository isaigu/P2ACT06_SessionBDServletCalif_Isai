package modelo;

public class Calificacion 
{

    private int idCalificacion;
    private String matricula;
    private String nombreAlumno;
    private int idMateria;
    private String nombreMateria;
    private double parcial1;
    private double parcial2;
    private double parcial3;

    public Calificacion() 
    {
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public double getParcial1() {
        return parcial1;
    }

    public void setParcial1(double parcial1) {
        this.parcial1 = parcial1;
    }

    public double getParcial2() {
        return parcial2;
    }

    public void setParcial2(double parcial2) {
        this.parcial2 = parcial2;
    }

    public double getParcial3() {
        return parcial3;
    }

    public void setParcial3(double parcial3) {
        this.parcial3 = parcial3;
    }

    public double getPromedio() {
        return (parcial1 + parcial2 + parcial3) / 3.0;
    }

    public double getParcial(int numParcial) {
        switch (numParcial) {
            case 1: return parcial1;
            case 2: return parcial2;
            case 3: return parcial3;
            default: throw new IllegalArgumentException("Parcial invalido: " + numParcial);
        }
    }
}
