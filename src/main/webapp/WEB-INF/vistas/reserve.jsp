<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.18/datatables.min.css"/>
    </head>
    <body>
        <div class="container">
            <br>
            <div class="row">
                <div class="col">
    	            <div id="itemId${item.id}" class="card text-white bg-info mb-3" style="max-width: 22rem;">
        	            <div class="card-header">Producto #${itemCommerce.item.brand}</div>
                        <div class="card-body">
            	            <h5 class="card-title"><span class="text-capitalize"><img src="${itemCommerce.item.urlImage}" width="300"></span></h5>
            	            <p class="card-text">Descripción: <span>${itemCommerce.item.description}</span></p>
        	            </div>
    	            </div>
                </div>
                <div class="col">
                    <h3><u>Comercio</u></h3><br>
                    <table id="tableCommerces" class="table table-striped table-bordered table-hover" style="width:100%">
                        <thead>
                            <tr class="table-primary">
                                <th>Nombre</th>
                                <th>Stock</th>
                                <th>Precio</th>
                            </tr>
                        </thead>
                        <tbody>
	                        <tr>
	                            <td class="text-capitalize">${itemCommerce.commerce.name}</td>
	                            <td>${itemCommerce.stock}</td>
	                            <td>${itemCommerce.price}</td>
	                        </tr>
                        </tbody>
                    </table><br>
                    <div>
						<form:form action="save-reserve" method="POST" modelAttribute="reserve">
							<div class="form-group row">
        						<label for="amount" class="col-sm-2 col-form-label">Cantidad: </label>
        						<div class="col-sm-3">
									<form:input class="form-control" path="amount" id="amount" type="number"/>
									<form:input path="itemId" value="${itemCommerce.item.id}" type="hidden"/>
									<form:input path="commerceId" value="${itemCommerce.commerce.commerce_id}" type="hidden"/>
									<form:input path="price" value="${itemCommerce.price}" type="hidden"/>
    							</div>
    						</div>
    						<button type="submit" class="btn btn-success">Reservar</button>
						</form:form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div id="map" style="width: 800px; height: 600px;">
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
                crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
                crossorigin="anonymous"></script>
        <script
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCiIDP3P5IqtJ4LQGy2--zrhbtCsXJGpjI">
    </script>
        <script type="text/javascript">
            $(document).ready(function() {
                var map;
                function initMap() {
                  map = new google.maps.Map(document.getElementById('map'), {
                    center: {lat: -34.7504785, lng: -58.5846362},
                    zoom: 10
                  });
                }
                initMap();
                
                    var pinColor = "FE7569"; /// rojo
                    var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
                        new google.maps.Size(21, 34),
                        new google.maps.Point(0,0),
                        new google.maps.Point(10, 34));

                    var marker = new google.maps.Marker({
                        position: {lat: ${itemCommerce.commerce.latitude}, lng: ${itemCommerce.commerce.longitude}},
                        map: map,
                        icon: pinImage,
                        title: "${itemCommerce.commerce.name}",
                        stock: "${itemCommerce.stock}",
                        price: "${itemCommerce.price}"
                    });
                    
                    var infowindow = new google.maps.InfoWindow({
                        content: marker.title +" Stock: "+ marker.stock + " Precio: $" + marker.price
                      });
                    
                    marker.addListener('click', function() {
                        infowindow.open(map, marker);
                      });

                        var miUbicacion = new google.maps.Marker({
                            position: {lat: ${latitude}, lng: ${longitude}},
                            map: map,
                            title: 'Mi ubicacion',
                            icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|2B17FF'
                        });
            });

        </script>
    </body>
</html>