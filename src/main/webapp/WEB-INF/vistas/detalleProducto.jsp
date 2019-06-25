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
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.18/datatables.min.css"/>
	
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
        <h3><u>Comercios</u></h3><br>
            <table id="tableCommerces" class="table table-striped table-bordered table-hover" style="width:100%">
                <thead>
                    <tr class="table-primary">
                        <th>Nombre</th>
                        <th>Distancia</th>
                        <th>Stock</th>
                        <th>Precio</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${itemCommerce}" var="commerce">
                    <tr>
                        <td class="text-capitalize">${commerce.commerce.name}</td>
                        <td>${commerce.commerce.distance} KMs</td>
                        <td>${commerce.stock}</td>
                        <td>$${commerce.price}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div id="map" style="width: 800px; height: 600px;">
            </div>
        </div>
        <div class="col">
            <div class="col">
                <h3><u>Referencia</u></h3><br>
                <table id="referenceTable" class="table table-striped table-bordered table-hover" style="width:100%">
                    <thead>
                    <tr class="table-primary">
                        <th>Icono</th>
                        <th>Referencia</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><img src="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|2B17FF"></td>
                            <td>Tu posición actual</td>
                        </tr>
                        <tr>
                            <td><img src="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|04FF38"></td>
                            <td>Comercio con el producto en stock y el más barato</td>
                        </tr>
                        <tr>
                            <td><img src="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|FE7569"></td>
                            <td>Comercio con el producto en stock</td>
                        </tr>
                    </tbody>
                </table>
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
    <script src="http://maps.google.com/maps/api/js?key=AIzaSyCiIDP3P5IqtJ4LQGy2--zrhbtCsXJGpjI&sensor=false"
            type="text/javascript"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.18/datatables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/plug-ins/1.10.19/sorting/natural.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
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

        var items = items.sort(function (a, b){
            if (a.price > b.price) return 1;
            if (a.price < b.price) return -1;

            return 0;
        });

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 10,
            center: new google.maps.LatLng(-34.7504785, -58.5846362),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        var infowindow = new google.maps.InfoWindow();

        var marker, i;

        for (i = 0; i < items.length; i++) {
            var pinColor = "FE7569"; /// rojo

            if (i == 0){
                pinColor = "04FF38"; /// verde
            }

            var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
                new google.maps.Size(21, 34),
                new google.maps.Point(0,0),
                new google.maps.Point(10, 34));

            marker = new google.maps.Marker({
                position: new google.maps.LatLng(items[i]["latitude"], items[i]["longitude"]),
                map: map,
                icon: pinImage
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
                    icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|2B17FF'
                });

            }, function () {
                handleLocationError(true, infoWindow, map.getCenter());
            });
        } else {
            // Browser doesn't support Geolocation
            handleLocationError(false, infoWindow, map.getCenter());
        }
        
        $('#tableCommerces').DataTable({
            "searching": true,
            "paging": true,
            "info": false,
            "language": {
                "decimal": ",",
                "thousands": ".",
                "emptyTable": "Sin datos",
                "search": "Buscar:",
                "paginate": {
                    "previous": "Anterior",
                    "next": "Siguiente",
                    "last": "Última"
                },
                "loadingRecords": "Cargando...",
                "processing": "Procesando...",
                "zeroRecords": "No se han encontrado resultados",
                "lengthMenu": "Mostrar _MENU_ resultados"
            },
            "pageLength": 10,
            "columnDefs": [{
                "type": "num-fmt",
                "targets": 3
            }, {
                "type": "natural",
                "targets": 1
            }]
        });
    });
        
	
    </script>
</body>
</html>