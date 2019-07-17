<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
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
	    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCiIDP3P5IqtJ4LQGy2--zrhbtCsXJGpjI&libraries=places"></script>
	        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<div class="container">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark mb-3">
  <!-- Brand/logo -->
  <a class="navbar-brand" href="#">
    <img src="http://www.fm891.com.ar/wp-content/uploads/2018/04/logo_unlam.png" alt="logo" style="width:50px;">
  </a>
  
  <!-- Links -->
  	<ul class="navbar-nav">
    <li class="nav-item">
      		<a class="nav-link" href="${pageContext.request.contextPath}/loadProducts">Cargar datos en db</a>
    </li>
 	 </ul>
	</nav>

	<div class="jumbotron">
  <div class="container">
    <h1 class="display-4">Taller Web I</h1>
    <p class="lead">Busqueda de productos en comercios cercanos</p>
  </div>
</div>

        <form:form action="search" method="POST" modelAttribute="message" onkeydown="return event.key != 'Enter';">
        	<div class="form-group row">
        		<label for="category" class="col-sm-2 col-form-label">Categoria o Marca: </label>
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
	  			<label for="distance" class="col-sm-2 col-form-label">Distancia máxima en Kms: </label>
    			<div class="col-sm-8">
    				<form:input path="distance" id="distance" type="text" class="form-control"/>
    			</div>
			</div>

			<div class="form-check-inline">
 		 <label class="form-check-label">
   		 <input type="radio" class="form-check-input" name="optradio" value="geolocation" required>Mi ubicacion
  		</label>
		</div>
		<div class="form-check-inline">
 		 <label class="form-check-label">
   		 <input type="radio" class="form-check-input" name="optradio" value="address" required>Direccion
  		</label>
		</div>

  			<div class="form-group row" style="display:none" id="addressDiv">
	  			<label for="address" class="col-sm-2 col-form-label" >Direccion: </label>
    			<div class="col-sm-8">
    		    <input id="address" type="text" size="50" placeholder="Ingrese una direccion" autocomplete="on"  class="form-control"/>  
    			</div>
			</div>
	
	
	        <div class="form-group row">
	  				<p id="info"></p>
			</div>
		</form:form>
		
					<div class="card-deck mb-3">
			  <div class="card">
			    <img class="card-img-top mx-auto m-2" src="${pageContext.request.contextPath}/images/comercio.png" alt="Card image cap">
			    <div class="card-body">
			      <h5 class="card-title text-center">Comercios</h5>
			      <p class="card-text"></p>
			    </div>
			  </div>
			  <div class="card">
			    <img class="card-img-top mx-auto m-2" src="${pageContext.request.contextPath}/images/producto.png" alt="Card image cap">
			    <div class="card-body">
			      <h5 class="card-title text-center">Productos</h5>
			      <p class="card-text"></p>
			    </div>
			  </div>
			  <div class="card">
			    <img class="card-img-top mx-auto m-2" src="${pageContext.request.contextPath}/images/googleMaps.png" alt="Card image cap">
			    <div class="card-body">
			      <h5 class="card-title text-center">Google Maps</h5>
			      <p class="card-text"></p>
			    </div>
			  </div>
			   <div class="card">
			    <img class="card-img-top mx-auto m-2" src="${pageContext.request.contextPath}/images/puntuacion.png" alt="Card image cap">
			    <div class="card-body ">
			      <h5 class="card-title text-center">Sistema de puntuacion</h5>
			      <p class="card-text"></p>
			    </div>
			  </div>
			</div>
		
</div>
<script>


$('input[type="radio"]').click(function(){
	  
	  if($(this).attr("value")=="geolocation"){
		  $("#address").prop('required',false);
	    $("#addressDiv").hide();
	    $("#info").show();
	    
	    
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

	  }
	  else{
		 $("#address").prop('required',true);
		 $("#addressDiv").show();
		   $("#info").hide();
		 
		 
		 $( document ).ready(function() {
		 
		 
	          var input = document.getElementById('address');
	          var autocomplete = new google.maps.places.Autocomplete(input);
	            google.maps.event.addListener(autocomplete, 'place_changed', function () {
	                var place = autocomplete.getPlace();
	                document.getElementById('latitude').value = place.geometry.location.lat();
	                document.getElementById('longitude').value = place.geometry.location.lng();
	            }); 
		 });
	  }     

	});
  
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>