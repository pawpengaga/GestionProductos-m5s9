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
  public Producto getProducto(int id) throws SQLException {
    String sql = "SELECT * FROM productos WHERE id = " + id;

    // Producto usando el constructor vacio
    Producto p = new Producto();

    Connection cnx = ConexionDB.getConnect();
    Statement stmt = cnx.createStatement();

    try {
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        // El objeto vacio se llena manualmente a traves de los setters
        p.setIdProducto(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getDouble("precio"));
      }
      // Cerramos el resultset
      rs.close();
      stmt.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Retornamos la lista Java una vez llenada
    return p;

  }

  @Override
    public void updateProduct(Producto p) {
    String query = "UPDATE productos SET nombre = ?, precio = ? WHERE id = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
        conn = ConexionDB.getConnect();
        stmt = conn.prepareStatement(query);
        stmt.setString(1, p.getNombre());
        stmt.setDouble(2, p.getPrecio());
        stmt.setInt(3, p.getIdProducto());
        
        int fila = stmt.executeUpdate();
        if (fila > 0) {
            System.out.println("Producto actualizado con éxito");
        } else {
            System.out.println("No se encontró el producto a actualizar");
        }
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


  @Override
  public void eliminarProduct(int id) {
    
    String query = "DELETE FROM productos WHERE id = ?";
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
      conn = ConexionDB.getConnect();
      stmt = conn.prepareStatement(query);

      // Con el id que trajimos de los params, completamos la SQLQUERY
      stmt.setInt(1, id);

      // Evaluamos el total de lineas afectadas al mismo tiempo que ejectuamos la consulta
      int fila = stmt.executeUpdate();

      if (fila > 0) {
        System.out.println("Producto eliminado...");
      } else {
        System.out.println("Hubo un problema al eliminar el producto...");
      }
      // Se cierra el statement
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  
  }

}
