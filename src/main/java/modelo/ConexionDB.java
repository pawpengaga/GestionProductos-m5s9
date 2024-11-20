package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

  // 1. Definimos las constantes
  // private static final String URL = "jdbc:postgresql:/localhost:5432/m5_gestion";
  // private static final String USER = "postgres";
  // private static final String PASSWORD = "12345678";

  // Conexion nula
  private static Connection conn = null;


  // Constructor privado, para controlar cuando se llama y no tener mas de una instancia de conexion
  private ConexionDB(){

    try {
      // Se fuerza la carga del driver
      Class.forName("org.postgresql.Driver");

      // Se crea la conexion a partir del objeto conexion
      // conn = DriverManager.getConnection(URL, USER, PASSWORD);
      conn = DriverManager.getConnection("jdbc:postgresql:m5_gestion?user=postgres&password=12345678");

      // Verificar si se logro la conexion
      if (conn != null) {
        System.out.println("Conexion establecida eeeeeeeeeeee");
      } else {
        System.out.println("No se logro conectar en el primer paso...");
      }
      
    } catch (SQLException e){
      e.printStackTrace();
    } catch (ClassNotFoundException e){
      e.printStackTrace();
    }

  }

  // Metodo que va a crear una sola conexion
  public static Connection getConnect(){
    if (conn == null) {
      // Si la conexion es nula, que se cree una primer conexion
      new ConexionDB();
    }
    return conn;
  }


}
