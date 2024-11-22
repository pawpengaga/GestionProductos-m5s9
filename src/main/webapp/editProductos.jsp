<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <h2>Edit productos</h2>
        <br>
        <form action="/GestionProductos/productos?accion=editar" method="POST">
            <input type="hidden" name="id" value="${producto.idProducto}" />
            <label>Nombre: </label>
            <input type="text" name="nombre" value="${producto.nombre}" required />
            <br>
            <label>Precio: </label>
            <input type="number" name="precio" value="${producto.precio}" required />
            <br>
            <button type="submit">Editar</button>
        </form>
    </body>
</html>