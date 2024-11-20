package modelo;

public class Producto {

  private int idProducto;
  private String nombre;
  private double precio;

  public Producto(){
    // Constructor vacio para usar setters en objetos temporales luego
  }

  public Producto(int idProducto, String nombre, double precio) {
    this.idProducto = idProducto;
    this.nombre = nombre;
    this.precio = precio;
  }

  public int getIdProducto() {
    return this.idProducto;
  }

  public void setIdProducto(int idProducto) {
    this.idProducto = idProducto;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPrecio() {
    return this.precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }


}
