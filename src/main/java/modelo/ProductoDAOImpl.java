package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaces.IProductoDAO;

public class ProductoDAOImpl implements IProductoDAO {

  
  @Override
  public void addProduct(Producto p) {
    // Como estamos usando base de datos, primero creamos la consulta
    // Se pone solo el nombre y el precio, el id es serial
    String sql = "INSERT INTO productos (nombre, precio) VALUES (?,?)";

    // Trabajar con base de datos simple implica try-catch
    try {
       // Primero creamos la conexion
       // La conexion puede llamarse cnx o conn
      Connection cnx = ConexionDB.getConnect();
  
      // El PreparedStatement se usa para consultas complejas NO SELECT
      // Esto habilita, PREPARA la query para que reciba los datos dinamicos y este completa
      PreparedStatement stmt = cnx.prepareStatement(sql);
      stmt.setString(1, p.getNombre());
      stmt.setDouble(2, p.getPrecio());

      // Una vez preparada, recien se ejectuta
      int fila = stmt.executeUpdate();
      // execute update retorna el numero de filas afectadas con el update, y 0 si nada ocurrio
      if(fila > 0){
        System.out.println("PRODUCTO AGREGADO!!");
      } else {
        System.out.println("Ocurrio un fallo al agregar el producto...");
      }
      // Cerramos el statement
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Producto> getAllProducts() throws SQLException {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT * FROM productos";

    // Nos conectamos y creamos el statement simple
    Connection cnx = ConexionDB.getConnect();
    Statement stmt = cnx.createStatement();

    try {
      // Creamos un objeto que reciba el select
      // Esto se hace CADA VEZ que se usa un SELECT
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        // Ingresamos los nombres del objeto como vienen en el RESULTADO DE LA CONSULTA, considera los ALIAS de SQL
        productos.add(new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio")));
      }
      // Cerramos el resultset
      rs.close();
      stmt.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Retornamos la lista Java una vez llenada
    return productos;
  }

  @Override
  public Producto getProducto(int id) {
    return null;

  }

  @Override
  public void updateProduct(Producto p) {

  }

  @Override
  public void eliminarProduct(int id) {

  }

}
