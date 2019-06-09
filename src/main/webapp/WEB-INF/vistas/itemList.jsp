<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rocio
  Date: 01/06/2019
  Time: 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
	<head>
	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 <script src="http://maps.google.com/maps/api/js?key=AIzaSyCiIDP3P5IqtJ4LQGy2--zrhbtCsXJGpjI&sensor=false" 
          type="text/javascript"></script>
	</head>
	<body>
	
		<div class="container">
			<br>
				<c:choose>
    			<c:when test="${empty items}">
        			<h4><span>No hay resultados</span></h4>
    			</c:when>
    			<c:otherwise>
 	
 										<c:forEach items="${items}"  var="item">
        				<div id="itemId${item.id}" class="card text-white bg-info mb-3" style="max-width: 20rem;">
  							<div class="card-header">Producto #${item.brand}</div>
  						<div class="card-body">
    							<h5 class="card-title"><span class="text-capitalize"><img src="${item.urlImage}" width="200"></span></h5>
    							<p class="card-text">
    								Descripcion: <span>${item.description}</span>
    							</p>
    							
    								<p class="card-text"> Comercios:<br>
    										<c:forEach items="${item.commerces}"  var="item2">
        								 	${item2.name}
        								 	
        								 	<a href="/proyecto-limpio-spring/mostrarEnMapa?nombre=${item2.name}&latitud=${item2.latitude}&longitud=	${item2.longitude}">Mostrar en mapa</a><br>
        								 	

 										</c:forEach>
 					
    							</p>
  							</div>
  			
  							</div>

 					</c:forEach>

    			</c:otherwise>
				</c:choose>
					</div>
		
	</body>
</html>