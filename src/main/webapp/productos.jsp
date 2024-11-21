<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de productos</title>
</head>
<body>
    
    <h2>Listado de productos</h2>
    <br>
    <button><a href="/GestionProductos/productos?accion=add">Agregar productos</a></button>
    <br>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Producto</th>
                <th>Precio</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="prod" items="${productos}">
                <tr>
                    <td>${prod.id}</td>
                    <td>${prod.nombre}</td>
                    <td>${prod.precio}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>