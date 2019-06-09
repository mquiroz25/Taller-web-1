<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<br>
<div class="container">
		<h3>Taller Web I</h3><br>
        <form:form action="buscar" method="POST" modelAttribute="message">
        	<div class="form-group row">
        		<label for="category" class="col-sm-2 col-form-label">Categoria: </label>
    			<div class="col-sm-8">
    				<form:input path="category" id="category" type="text" class="form-control"/>
	            	<form:input path="latitude" id="latitude" type="hidden"/>
	            	<form:input path="longitude" id="longitude" type="hidden"/>
    			</div>
    			<div class="col-sm-2">
    				<button type="submit" class="btn btn-primary">Buscar</button>
    			</div>
  			</div>
	        <div class="form-group row">
	  				<p id="info"></p>
			</div>
		</form:form>
		
		
	<a href="/proyecto-limpio-spring/buscarProductoPorMarca"	><button class="btn btn-success">Buscar Productos Por marca</button></a>

	
		
</div>
<script>
	$( document ).ready(function() {
	    function getLocation() {
	        if (navigator.geolocation) {
	            navigator.geolocation.getCurrentPosition(showPosition);
	        } else {
	        	$("#info").text("Geolocalización no disponible.");
	        }
	    }

	    function showPosition(position) {
	        $("#latitude").val(position.coords.latitude);
	        $("#longitude").val(position.coords.longitude);
			$("#info").text("Usando tu ubicación. Click en buscar");
	    }
	    
	    getLocation();
	});
    
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>