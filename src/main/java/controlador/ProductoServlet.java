package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.ProductoDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class ProductoServlet
 */
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// Nos traemos la implementacion del DAO
	private ProductoDAOImpl implDAO;

	public ProductoServlet() {
			super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Si vamos a tener accion

		String accion = request.getParameter("accion");

		try {
			if (accion.equals("add")) {
				// Redirige al creador de productos
				RequestDispatcher dispatcher = request.getRequestDispatcher("addProductos.jsp");
				dispatcher.forward(request, response);
			} else if (accion.equals("edita")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("addProductos.jsp");
				dispatcher.forward(request, response);
			} else if (accion.equals("elimina")) {
				// Porsiacaso...
			} else {
				// Sin ninguna accion, solo vemos los productos
				try {
					// Primero le pasamos los productos como atributo
					List<Producto> productos = implDAO.getAllProducts();
					request.setAttribute("productos", productos);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
				// Una vez pasados los productos vamos directamente el index de productos
				RequestDispatcher dispatcher = request.getRequestDispatcher("productos.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");

		try {
			if (accion.equals("add")) {

				String nombre = request.getParameter("nombre");
				double precio = Double.parseDouble(request.getParameter("precio"));
				implDAO.addProduct(new Producto(0, nombre, precio));

				// Una vez creado el producto se vuelve a redireccionar al GET del servlet
				response.sendRedirect("/GestionProductos/productos");
				
			} else if (accion.equals("edita")) {

				String nombre = request.getParameter("nombre");
				double precio = Double.parseDouble(request.getParameter("precio"));
				implDAO.updateProduct(new Producto(0, nombre, precio));

				// Una vez creado el producto se vuelve a redireccionar al GET del servlet
				response.sendRedirect("/GestionProductos/productos");
	
			} else if (accion.equals("elimina")) {

				// Para eliminar solo necesitamos el id
				int id = Integer.parseInt(request.getParameter("id"));
				implDAO.eliminarProduct(id);
				response.sendRedirect("/GestionProductos/productos");
	
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
