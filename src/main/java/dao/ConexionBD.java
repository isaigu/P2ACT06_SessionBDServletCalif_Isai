package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.ConfigUtil;

public class ConexionBD 
{

    static 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("No se encontro el driver de MySQL (mysql-connector-j) en WEB-INF/lib: " + e);
        }
    }

    private ConexionBD() 
    {
    }

    public static Connection obtener() throws SQLException 
    {
        String url = ConfigUtil.get("db.url");
        String usuario = ConfigUtil.get("db.usuario");
        String password = ConfigUtil.get("db.password");
        return DriverManager.getConnection(url, usuario, password);
    }
}
