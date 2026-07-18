package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Alumno;

public class AlumnoDAO 
{

    public void agregar(Alumno a) throws SQLException 
    {
        String sql = "INSERT INTO alumnos (matricula, nombre, grupo, correo, fecha_ingreso) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, a.getMatricula());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getGrupo());
            ps.setString(4, a.getCorreo());
            ps.setDate(5, a.getFechaIngreso() != null ? Date.valueOf(a.getFechaIngreso()) : null);
            ps.executeUpdate();
        }
    }

    public List<Alumno> listar() throws SQLException 
    {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumnos ORDER BY matricula";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) 
        {
            while (rs.next()) 
            {
                Alumno a = new Alumno();
                a.setMatricula(rs.getString("matricula"));
                a.setNombre(rs.getString("nombre"));
                a.setGrupo(rs.getString("grupo"));
                a.setCorreo(rs.getString("correo"));
                Date fecha = rs.getDate("fecha_ingreso");
                a.setFechaIngreso(fecha != null ? fecha.toLocalDate() : null);
                lista.add(a);
            }
        }
        return lista;
    }

    public void editar(String matricula, String nombre, String grupo) throws SQLException 
    {
        String sql = "UPDATE alumnos SET nombre = ?, grupo = ? WHERE matricula = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, nombre);
            ps.setString(2, grupo);
            ps.setString(3, matricula);
            ps.executeUpdate();
        }
    }

    public void eliminar(String matricula) throws SQLException 
    {
        String sql = "DELETE FROM alumnos WHERE matricula = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, matricula);
            ps.executeUpdate();
        }
    }
}
