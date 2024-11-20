package interfaces;

import java.util.List;

import modelo.Producto;

public interface IProductoDAO {

  public void addProduct(Producto p);
  public List<Producto> getAllProducts();
  public Producto getProducto(int id);
  public void updateProduct(Producto p);
  public void eliminarProduct(int id);

}
