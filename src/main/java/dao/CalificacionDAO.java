package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Calificacion;

public class CalificacionDAO 
{

    public void generarFilasParaMateria(int idMateria) throws SQLException 
    {
        String sql = "INSERT INTO calificaciones (matricula, id_materia) "
                + "SELECT a.matricula, ? "
                + "FROM alumnos a "
                + "WHERE NOT EXISTS ("
                + "  SELECT 1 FROM calificaciones c "
                + "  WHERE c.matricula = a.matricula AND c.id_materia = ?)";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, idMateria);
            ps.setInt(2, idMateria);
            ps.executeUpdate();
        }
    }

    public List<Calificacion> listarPorMateria(int idMateria) throws SQLException 
    {
        List<Calificacion> lista = new ArrayList<>();
        String sql = "SELECT c.id_calificacion, c.matricula, a.nombre AS nombre_alumno, a.grupo, "
                + "c.id_materia, m.nombre AS nombre_materia, c.parcial1, c.parcial2, c.parcial3 "
                + "FROM calificaciones c "
                + "JOIN alumnos a ON a.matricula = c.matricula "
                + "JOIN materias m ON m.id_materia = c.id_materia "
                + "WHERE c.id_materia = ? "
                + "ORDER BY a.matricula";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, idMateria);
            try (ResultSet rs = ps.executeQuery()) 
            {
                while (rs.next()) 
                {
                    Calificacion c = new Calificacion();
                    c.setIdCalificacion(rs.getInt("id_calificacion"));
                    c.setMatricula(rs.getString("matricula"));
                    c.setNombreAlumno(rs.getString("nombre_alumno"));
                    c.setIdMateria(rs.getInt("id_materia"));
                    c.setNombreMateria(rs.getString("nombre_materia"));
                    c.setParcial1(rs.getDouble("parcial1"));
                    c.setParcial2(rs.getDouble("parcial2"));
                    c.setParcial3(rs.getDouble("parcial3"));
                    lista.add(c);
                }
            }
        }
        return lista;
    }

    
    public void actualizarUnParcial(int idCalificacion, int numParcial, double nota) throws SQLException
    {
        String columna;
        switch (numParcial) 
        {
            case 1: columna = "parcial1"; break;
            case 2: columna = "parcial2"; break;
            case 3: columna = "parcial3"; break;
            default: throw new IllegalArgumentException("Parcial invalido: " + numParcial);
        }
        String sql = "UPDATE calificaciones SET " + columna + " = ? WHERE id_calificacion = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setDouble(1, nota);
            ps.setInt(2, idCalificacion);
            ps.executeUpdate();
        }
    }

    public void eliminar(int idCalificacion) throws SQLException 
    {
        String sql = "DELETE FROM calificaciones WHERE id_calificacion = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, idCalificacion);
            ps.executeUpdate();
        }
    }
}
