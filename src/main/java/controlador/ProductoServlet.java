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
		// No vamos a tener accion esta vez

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
