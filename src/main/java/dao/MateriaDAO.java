package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Materia;

public class MateriaDAO {

    public void agregar(Materia m) throws SQLException 
    {
        String sql = "INSERT INTO materias (nombre) VALUES (?)";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, m.getNombre());
            ps.executeUpdate();
        }
    }

    public List<Materia> listar() throws SQLException 
    {
        List<Materia> lista = new ArrayList<>();
        String sql = "SELECT * FROM materias ORDER BY id_materia";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) 
        {
            while (rs.next()) 
            {
                lista.add(new Materia(rs.getInt("id_materia"), rs.getString("nombre")));
            }
        }
        return lista;
    }

    public void editar(int idMateria, String nombre) throws SQLException 
    {
        String sql = "UPDATE materias SET nombre = ? WHERE id_materia = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, nombre);
            ps.setInt(2, idMateria);
            ps.executeUpdate();
        }
    }

    public void eliminar(int idMateria) throws SQLException 
    {
        String sql = "DELETE FROM materias WHERE id_materia = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, idMateria);
            ps.executeUpdate();
        }
    }
}
