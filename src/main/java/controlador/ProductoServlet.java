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
import java.util.List;

/**
 * Servlet implementation class ProductoServlet
 */
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// Nos traemos la implementacion del DAO
	private ProductoDAOImpl implDAO = new ProductoDAOImpl();

	public ProductoServlet() {
			super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Si vamos a tener accion
		String accion = request.getParameter("accion");

		try {
			if ("add".equals(accion)) {
				// Redirige al creador de productos
				RequestDispatcher dispatcher = request.getRequestDispatcher("addProductos.jsp");
				dispatcher.forward(request, response);
			} else if ("edita".equals(accion)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("editProductos.jsp");
				dispatcher.forward(request, response);
			} else if ("elimina".equals(accion)) {
				// Porsiacaso...
			} else if (accion == null) {
				System.out.println("Se entro por aqui");
				// Sin ninguna accion, solo vemos los productos
				// Primero le pasamos los productos como atributo
				List<Producto> productos = implDAO.getAllProducts();
				request.setAttribute("productos", productos);
				// Una vez pasados los productos vamos directamente el index de productos
				RequestDispatcher dispatcher = request.getRequestDispatcher("productos.jsp");
				dispatcher.forward(request, response);
		
			}
		} catch (Exception e) {
			System.out.println("Hubo un error... " + e.getMessage());
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
			e.printStackTrace();
		}

	}

}
