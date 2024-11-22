<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de productos</title>
<style>
    td, th {
        padding: 5px;
    }
</style>
</head>
<body>
    
    <h2>Listado de productos</h2>
    <p>Parrafo añadido posteriormente...</p>
    <br>
    <a href="/GestionProductos/productos?accion=add"><button>Agregar productos</button></a>
    <br>

    <c:if test="${empty productos}">
        <p>No hay productos registrados...</p>
    </c:if>
    <br>
    <c:if test="${not empty productos}">
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Producto</th>
                <th>Precio</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>

                <c:forEach var="prod" items="${productos}" varStatus="status">
                    <tr>
                        <td>${prod.idProducto}</td>
                        <td>${prod.nombre}</td>
                        <td>${prod.precio}</td>
                        <td>
                            <div style="display: flex; flex-direction: row; gap: 5px;">
                                <div style="padding: 5px;">
                                    <a href="/GestionProductos/productos?accion=editar&id=${status.index}"><button>Editar</button></a>
                                </div>
                                <div style="padding: 5px;">
                                    <a href="/GestionProductos/productos?accion=eliminar&id=${status.index}"><button>Eliminar</button></a>
                                </div>
                            </div>
                        </td>
                    </tr>
				</c:forEach>
            </tbody>
        </table>
    </c:if>
    <br>
    <a href="/GestionProductos">Regresar</a>
</body>
</html>