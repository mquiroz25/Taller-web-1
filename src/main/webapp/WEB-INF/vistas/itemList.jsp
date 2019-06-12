<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 <script src="http://maps.google.com/maps/api/js?key=AIzaSyCiIDP3P5IqtJ4LQGy2--zrhbtCsXJGpjI&sensor=false" 
          type="text/javascript"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<div class="container">
			<br>
				<c:choose>
					<c:when test="${empty items}">
						<h4><span>No hay resultados</span></h4>
					</c:when>
    			<c:otherwise>

<div id="tablaLista">
	<table class="table table-bordered table-hover " >
		<tr class="table-primary">
			<th>Producto</th>
			<th>Foto</th>
			<th>Comercio</th>
			<th> Stock</th>
			<th>Precio</th>
			<th>Mapa</th>
		</tr>
	
		<c:forEach items="${items}" var="item">

		<tr>
			<td>${item.item.brand} </td> 
			<td> <a href="${pageContext.request.contextPath}/detalleProducto?marca=${item.item.brand}&descripcion=${item.item.description}&imagen=${item.item.urlImage}&categoria=${item.item.category.name}"><img src="${item.item.urlImage}" width="50">  </a> </td>
			<td>${item.commerce.name}</td>
			<td> ${item.stock}</td>
			<td> ${item.price}</td>
			<td><a href="${pageContext.request.contextPath}/mostrarEnMapa?nombre=${item.commerce.name}&latitud=${item.commerce.latitude}&longitud=${item.commerce.longitude}">Mostrar en mapa</a><br><td>
		</tr>
		</c:forEach>
	</table>
    			</c:otherwise>
				</c:choose>

</div>
</body>
</html>