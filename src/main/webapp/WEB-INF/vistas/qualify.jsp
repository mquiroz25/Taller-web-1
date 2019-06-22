<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
<h2>Sistema de calificaciones</h2>
<br>
	<form:form action="/proyecto-limpio-spring/processQualification" method="GET">

		Calificacion  
		<select name="qualification">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
    <option value="5">5</option>
</select><br><br>
		<textarea name="review" rows="3" cols="15" placeholder="Escriba un comentario"></textarea>
		<input type="hidden" name="id" value="${id_commerce}"><br>
		
	 <input type="submit" value="Calificar y Salir" class="btn btn-primary"/>
	</form:form>
    	
    	
   <div class="container">
    	        <table class="table table-bordered table-hover ">
            <tr class="table-primary">
                <th>Calificacion</th>
                <th>Comentario</th>
                <th></th>
            </tr>

            <c:forEach items="${rankingListCommerce}" var="item">
                <tr>
          
                    <td>${item.value} </td>
                     <td>${item.review} </td>

                </tr>
            </c:forEach>
        </table>
    	</div>

</body>
</html>