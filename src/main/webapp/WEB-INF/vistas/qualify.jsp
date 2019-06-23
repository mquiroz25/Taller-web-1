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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<title>Insert title here</title>
</head>
<body>

<div class="container ">
<h2 class="text-center">${name_commerce}</h2>
<form:form action="/proyecto-limpio-spring/processQualification" method="GET">

 <div class="question" id="q1">
    <p class="criteria">Atencion</p>
    <input type="radio" name="attention" value="1" required>
    <label for="a1">1</label>
    <input type="radio" name="attention" value="2" required>
    <label for="a2">2</label>
    <input type="radio" name="attention" value="3" required>
    <label for="a3">3</label>
    <input type="radio" name="attention" value="4" required>
    <label for="a4">4</label>
    <input type="radio" name="attention" value="5" required>
    <label for="a5">5</label>
  </div>

  <div class="question" id="q2">
     <p class="criteria">Rapidez</p>
    <input type="radio" name="speed" value="1" required>
    <label for="b1">1</label>
    <input type="radio" name="speed" value="2" required>
    <label for="b2">2</label>
    <input type="radio" name="speed" value="3" required>
    <label for="b3">3</label>
    <input type="radio" name="speed" value="4" required>
    <label for="b4">4</label>
    <input type="radio" name="speed" value="5" required>
    <label for="b5">5</label>
  </div>
  
  
  <div class="question" id="q3">
     <p class="criteria">Precios</p>
    <input type="radio" name="prices" value="1" required>
    <label for="c1">1</label>
    <input type="radio" name="prices" value="2" required>
    <label for="c2">2</label>
    <input type="radio" name="prices" value="3" required>
    <label for="c3">3</label>
    <input type="radio" name="prices" value="4" required>
    <label for="c4">4</label>
    <input type="radio" name="prices" value="5" required>
    <label for="c5">5</label>
  </div>


  <div class="form-group">
    <label for="exampleFormControlTextarea1">Escriba un comentario</label>
    <textarea class="form-control" name="review"  id="exampleFormControlTextarea1" rows="2"required></textarea>
  </div>
  <input type="hidden" name="id" value="${id_commerce}"><br>
  <button type="submit" class="btn btn-primary">Calificar</button>
</form:form>
</div>


<hr />
    
            
   <div class="container">
<ul class="list-group">

<c:forEach items="${rankingListCommerce}" var="item">
                
                
                <li class="list-group-item">
    <div class="d-flex w-100 justify-content-between">
     <div class="px-2" id="caja"> <h5 class="mb-1">${item.value}</h5></div>
    </div>
    <p class="mb-1">${item.review}</p>
  </li>
                
                  </c:forEach>
  
</ul>
   </div>


</body>
</html>