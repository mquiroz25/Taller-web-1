<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!--  <link href="css/bootstrap.min.css" rel="stylesheet">
      <link href="css/bootstrap-theme.min.css" rel="stylesheet"> -->
</head>
<body>
<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <form:form action="buscar" method="POST" modelAttribute="message">
            <h3 class="form-signin-heading">Taller Web I</h3>
            <hr class="colorgraph"><br>
            <label for="category">Buscar:</label>
            <form:input path="category" id="category" type="text" class="form-control" />
            <form:input path="latitude" id="latitude" type="hidden"/>
            <form:input path="longitude" id="longitude" type="hidden"/>

            <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Login</button>
        </form:form>
        <button onclick="getLocation()">Usar mi ubicación</button>

        <p id="info"></p>
    </div>
</div>
<script>
    var info = document.getElementById("info");

    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            info.innerHTML = "Geolocalización no disponible.";
        }
    }

    function showPosition(position) {
        document.getElementById("latitude").value = position.coords.latitude;
        document.getElementById("longitude").value = position.coords.longitude;

        info.innerHTML = "Usando tu ubicación. Click en buscar"
    }
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>