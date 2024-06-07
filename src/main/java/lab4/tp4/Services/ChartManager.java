package lab4.tp4.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChartManager {

    String urlConexion = "jdbc:mysql://localhost:5432/tp_lab44";
    String usuario = "postgres";
    String clave = "pass2024";

    public ResultSet getDatosChart(){

        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(urlConexion, usuario, clave);

            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = s.executeQuery("SELECT instrumento, cantidad_vendida" +
                    "FROM instrumento GROUP BY instrumento ORDER BY cantidad_vendida");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;


    }
}
