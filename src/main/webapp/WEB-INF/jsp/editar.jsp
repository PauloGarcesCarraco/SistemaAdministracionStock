<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cl.edutecno.modelo.Producto"%>
<%@page import="cl.edutecno.vo.ProductoVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<style type="text/css">
.cuerpo {
	margin-left: 5%;
	margin-right: 5%
}
</style>
<title>Insert title here</title>
</head>
<body>
<div class="cuerpo">

		<nav class="navbar navbar-light bg-light">
			<a class="navbar-brand" href="#">Logistica CRUD</a>
			<div class="navbar">
				<div class="navbar-nav"></div>
			</div>
		</nav>
	<div class="p-3">

		<h3>Editar Producto</h3>
		<c:if test="${mensaje != null ? true : false}">
			<div class="alert alert-secondary  alert-dismissible fade show"
				role="alert">${mensaje}
			</div>
		</c:if>
		<form action="editar" method="post">
			<input type="hidden" name="idProducto" value="${ProductoVO.getIdProducto()}" />
			<table>

				<tr>
					<td class="p-2"><label for="nombre">Nombre:</label></td>
					<td><input class="form-control" type="text" name="nombre"
						placeholder="Nombre" value="${ProductoVO.getNombre()}" /></td>
				</tr>
				<tr>
					<td class="p-2"><label for="precio">Precio:</label></td>
					<td><input class="form-control" type="number" min="0"
						placeholder="Precio" name="precio" value="${ProductoVO.getPrecio()}" /></td>
				</tr>
				<tr>
					<td class="p-2"><label for="stock">Stock:</label></td>
					<td><input class="form-control" type="number" min="0"
						placeholder="Stock" name="stock" 
						value="${ProductoVO.getStock()}" /></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit"
						class="btn m-2 btn-success" value="Guardar" /></td>
				</tr>
			</table>
		</form>
	</div>
	</div>
	 <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	


</body>
</html>