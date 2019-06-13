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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div class="container">
    <br>
    <div class="row">
    <div class="col">
    	<div id="itemId${item.id}" class="card text-white bg-info mb-3" style="max-width: 22rem;">
        	<div class="card-header">Producto #${item.brand}</div>
        	<div class="card-body">
            	<h5 class="card-title"><span class="text-capitalize"><img src="${item.urlImage}" width="300"></span></h5>
            	<p class="card-text">Descripcion: <span>${item.description}</span></p>
        	</div>
    	</div>
    </div>
    <div class="col">
    <h3><u>Comercios</u></h3>
    <div id="tableCommerces">
        <table class="table table-bordered table-hover ">
            <tr class="table-primary">
                <th>Nombre</th>
                <th>Distancia</th>
                <th>Stock</th>
                <th>Precio</th>
            </tr>

            <c:forEach items="${itemCommerce}" var="commerce">
                <tr>
                    <td class="text-capitalize">${commerce.commerce.name}</td>
                    <td>${commerce.commerce.distance} KMs</td>
                    <td>${commerce.stock}</td>
                    <td>$${commerce.price}</td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </div>
   		</div>
    	<div id="map" style="width: 800px; height: 600px;"></div>
    </div>	

    
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="http://maps.google.com/maps/api/js?key=AIzaSyCiIDP3P5IqtJ4LQGy2--zrhbtCsXJGpjI&sensor=false"
            type="text/javascript"></script>
    <script type="text/javascript">
        var items = [];
        <c:forEach items="${itemCommerce}" var="item">
            items.push({
                key: ${item.id},
                longitude: ${item.commerce.longitude},
                latitude: ${item.commerce.latitude},
                stock: ${item.stock},
                price: ${item.price},
            });
        </c:forEach>

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 10,
            center: new google.maps.LatLng(-34.7504785, -58.5846362),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        var infowindow = new google.maps.InfoWindow();

        var marker, i;


        for (i = 0; i < items.length; i++) {
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(items[i]["latitude"], items[i]["longitude"]),
                map: map
            });

            google.maps.event.addListener(marker, 'click', (function (marker, i) {
                return function () {
                    infowindow.setContent(locations[i]["name"]);
                    infowindow.open(map, marker);
                }
            })(marker, i));
        }


        // Geolocalizacion
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };

                var miUbicacion = new google.maps.Marker({
                    position: pos,
                    map: map,
                    title: 'Mi ubicacion',
                    icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
                });

            }, function () {
                handleLocationError(true, infoWindow, map.getCenter());
            });
        } else {
            // Browser doesn't support Geolocation
            handleLocationError(false, infoWindow, map.getCenter());
        }

    </script>
</body>
</html>