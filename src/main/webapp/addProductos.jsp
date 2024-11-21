<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agrega producto</title>
</head>
<body>

    <h2>Agrega productos</h2>
    <br>
    <form action="/GestionProductos/productos?accion=add" method="POST">
        <label>Nombre: </label>
        <input type="text" name="nombre" required />
        <br>
        <label>Precio: </label>
        <input type="number" name="precio" required />
        <br>
        <button type="submit">Agregar</button>
    </form>

</body>
</html>