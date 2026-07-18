package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class UsuarioDAO 
{

    public void registrar(Usuario u) throws SQLException
    {
        String sql = "INSERT INTO usuarios (usuario, contrasena, nombre, correo, es_admin, correo_validado, estado, token_confirmacion) "
                + "VALUES (?, ?, ?, ?, 0, 0, 0, ?)";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getContrasena());
            ps.setString(3, u.getNombre());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getTokenConfirmacion());
            ps.executeUpdate();
        }
    }

    public boolean existeUsuario(String usuario) throws SQLException 
    {
        String sql = "SELECT 1 FROM usuarios WHERE usuario = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery())
            {
                return rs.next();
            }
        }
    }

    public Usuario buscarPorUsuario(String usuario) throws SQLException
    {
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) 
            {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public Usuario buscarPorToken(String token) throws SQLException 
    {
        String sql = "SELECT * FROM usuarios WHERE token_confirmacion = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) 
            {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public Usuario buscarPorId(int idUsuario) throws SQLException 
    {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) 
            {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public List<Usuario> listarPendientes() throws SQLException 
    {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE estado = 0 ORDER BY fecha_registro";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) 
        {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public void confirmarCorreo(String token) throws SQLException
    {
        String sql = "UPDATE usuarios SET correo_validado = 1 WHERE token_confirmacion = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, token);
            ps.executeUpdate();
        }
    }

    public void darAcceso(int idUsuario) throws SQLException 
    {
        String sql = "UPDATE usuarios SET estado = 1 WHERE id_usuario = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        }
    }

    private Usuario mapear(ResultSet rs) throws SQLException 
    {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setUsuario(rs.getString("usuario"));
        u.setContrasena(rs.getString("contrasena"));
        u.setNombre(rs.getString("nombre"));
        u.setCorreo(rs.getString("correo"));
        u.setEsAdmin(rs.getBoolean("es_admin"));
        u.setCorreoValidado(rs.getBoolean("correo_validado"));
        u.setEstado(rs.getBoolean("estado"));
        u.setTokenConfirmacion(rs.getString("token_confirmacion"));
        return u;
    }
}
