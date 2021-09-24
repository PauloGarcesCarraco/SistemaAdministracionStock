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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<style type="text/css">
.cuerpo {
	margin-left: 5%;
	margin-right: 5%
}

.navbar {
	padding-left: 2%
}

.tablas {
	margin-top: 0rem;
	padding-left: 2%;
	padding-right: 2%;
}

.titulo {
	margin-top: 0rem;
	padding-left: 2%;
	padding-right: 2%;
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

		<div class="titulo">
			<c:if test="${mensaje != null ? true : false}">
				<div class="alert alert-secondary  alert-dismissible fade show"
					role="alert">${mensaje}
				</div>
			</c:if>
		</div>
		<div class="filtro">
			<h3>Buscador de Producto</h3>
			<form action="buscar" method="get">

				<table>
					<tr>
						<td class="p-2"><label for="nombre">Nombre:</label></td>
						<td><input class="form-control" type="text"
							placeholder="Nombre del producto" name="nombre"
							value="${RespuestaVO.setNombre()}" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit"
							class="btn m-2 btn-success" value="Buscar" />
							<a href="/home" class="btn m-2 btn-success" role="button" aria-pressed="true">Mostrar todo</a>
					</tr>
					</tr>
				</table>
			</form>
		</div>
		
		<!-- Botones paginacion -->
		<ul class="pagination pagination-lg justify-content-center">
			<c:forEach items="${paginas}" var="pagina">
				<li class="page-item ${paginaActual == pagina ? 'disabled' : ''}"><a
					class="page-link" href="home?p=${pagina}" tabindex="-1">${pagina}</a></li>
			</c:forEach>
		</ul>
		
		<div class="tablas">

			<table border="1" class="table table-striped">
				<thead class="thead-dark">
					<tr>
						<th scope="col">IdProducto</th>
						<th scope="col">Nombre</th>
						<th scope="col">Precio</th>
						<th scope="col">Stock</th>
						<th scope="col">Acciones</th>
					</tr>
					<c:forEach items="${productoVO.getProductos()}" var="u">
						<tr>
							<td>${u.getIdProducto()}</td>
							<td>${u.getNombre()}</td>
							<td>${u.getPrecio()}</td>
							<td>${u.getStock()}</td>
							<td><a href="editarForm?idProducto=${u.getIdProducto()}"
								class="btn btn-primary btn-sm">Editar</a> <a
								href="eliminar?idProducto=${u.getIdProducto()}"
								class="btn btn-danger btn-sm">Eliminar</a></td>

						</tr>
					</c:forEach>
				</thead>
			</table>
		</div>
	</div>
	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>
</body>
</html>