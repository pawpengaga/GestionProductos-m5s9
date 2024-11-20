package interfaces;

import java.sql.SQLException;
import java.util.List;

import modelo.Producto;

public interface IProductoDAO {

  public void addProduct(Producto p);
  public List<Producto> getAllProducts() throws SQLException;
  public Producto getProducto(int id) throws SQLException;
  public void updateProduct(Producto p);
  public void eliminarProduct(int id);

}
